package Gerencia.Caixa;

import Gerencia.Estoque.Entrada;
import Gerencia.Estoque.GerenciadorEstoque;
import Gerencia.GerenciadorGeral;
import Produtos.IntProduto;
import excecoes.ProdutoNaoEncontrado;
import excecoes.SemEstoque;
import pagamento.TipoPagamento;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Caixa{

    /**
     * Saldo da livraria
     */
    private static float saldo;

    /**
     * Lista que armazena uma dupla de um produto e uma quantiadade
     */
    private static ArrayList<CompraVenda> comprasvendas = new ArrayList<>();

    // Getters e Setters:

    public static float getSaldo() {
        return saldo;
    }

    public static void setSaldo(float saldo) {
        Caixa.saldo = saldo;
    }

    public static ArrayList<CompraVenda> getComprasvendas() {
        return comprasvendas;
    }

    public static void setComprasvendas(ArrayList<CompraVenda> comprasvendas) {
        Caixa.comprasvendas = comprasvendas;
    }

    /**
     * Busca um produto por ID na lista de compraVenda, retorna seu indice ou joga Exceção ProdutoNaoEncontrado
     *
     * @param id ID do produto
     * @return Retorna seu indice
     */
    public static int buscaCompraVenda(String id) {
        int index;
        try {
            index = getComprasvendas().indexOf(getComprasvendas().stream().map(CompraVenda::getProduto).filter(produto -> produto.getID().equals(id)).findFirst().get());
        } catch (NoSuchElementException e) {
            throw new ProdutoNaoEncontrado("Produto: '" + id + "' nao foi encontrado");
        }
        return index;
    }

    /**
     * Adiciona uma compraVenda na lista de comprasVendas
     * @param produto O produto a ser adicionado
     * @param quantidade quantidade de produto a ser adicionado
     */
    public static void adiconarCompraVenda(IntProduto produto, int quantidade) {
        getComprasvendas().add(new CompraVenda(produto, quantidade));
    }

    /**
     * Remove uma compraVenda da lista de comprasVendas
     * @param id ID do produto a ser removido da lista
     */
    public static void removerCompraVenda(String id) {
        getComprasvendas().remove(buscaCompraVenda(id));
    }

    /**
     * Interpreta a lista de comprasVendas como compras e efetua a alteração de saldo, estoque e registra no arquivo "Transações.txt"
     */
    public static void registrarCompra() {
        int valor = 0;
        Entrada entrada;
        for (CompraVenda compra : getComprasvendas()) {
            try {
                entrada = GerenciadorEstoque.getEntradas().get(GerenciadorEstoque.buscaProduto(compra.getProduto().getID()));
                GerenciadorEstoque.carregaProduto(entrada.getProduto());
                GerenciadorEstoque.alteraProduto(entrada.getQuantiade() + compra.getQuantiade());

            } catch (ProdutoNaoEncontrado e) {
                GerenciadorEstoque.carregaProduto(compra.getProduto());
                GerenciadorEstoque.appendProduto(compra.getQuantiade());
            }
            valor += compra.getQuantiade() * compra.getProduto().getValor();
        }
        setSaldo(getSaldo() - valor);

        // ESCRITA NO ARQUIVO

    }


    /**
     * Interpreta a lista de comprasVendas como vendas e efetua a alteração de saldo, estoque e registra no arquivo "Transações.txt"
     */
    public static void registrarVenda(TipoPagamento pagamento) {

        int valor = 0;
        Entrada entrada;
        for (CompraVenda venda : getComprasvendas()) {
            entrada = GerenciadorEstoque.getEntradas().get(GerenciadorEstoque.buscaProduto(venda.getProduto().getID()));
            if (entrada.getQuantiade() < venda.getQuantiade())
                throw new SemEstoque("Não há estoque suficiente de " + venda.getProduto().getNome() + ", " + entrada.getQuantiade() + " dos " + venda.getQuantiade() + " requisitados.")

        }
        for (CompraVenda venda : getComprasvendas()) {
            entrada = GerenciadorEstoque.getEntradas().get(GerenciadorEstoque.buscaProduto(venda.getProduto().getID()));
            GerenciadorEstoque.carregaProduto(entrada.getProduto());
            GerenciadorEstoque.alteraProduto(entrada.getQuantiade() - venda.getQuantiade());

            valor += venda.getQuantiade() * venda.getProduto().getValor();
        }
        setSaldo(getSaldo() + valor);

        // ESCRITA NO ARQUIVO


    }


}
