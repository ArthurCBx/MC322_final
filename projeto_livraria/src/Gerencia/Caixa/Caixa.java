package Gerencia.Caixa;

import Gerencia.Estoque.Entrada;
import Gerencia.Estoque.GerenciadorEstoque;
import Produtos.IntProduto;
import excecoes.ProdutoNaoEncontrado;
import excecoes.SemEstoque;
import pagamento.TipoPagamento;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Caixa {

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
        int index;      // Irá retornar o indice
        try {           // Lista de ComprasVendas -> index da compraVenda que contem o produto com o ID fornecido
            index = getComprasvendas().indexOf(getComprasvendas().stream().map(CompraVenda::getProduto).filter(produto -> produto.getID().equals(id)).findFirst().get());
        } catch (NoSuchElementException e) {
            throw new ProdutoNaoEncontrado("Produto: '" + id + "' nao foi encontrado");
        }
        return index;
    }

    /**
     * Adiciona uma compraVenda na lista de comprasVendas
     *
     * @param produto    O produto a ser adicionado
     * @param quantidade quantidade de produto a ser adicionado
     */
    public static void adiconarCompraVenda(IntProduto produto, int quantidade) {
        getComprasvendas().add(new CompraVenda(produto, quantidade));
    }

    /**
     * Remove uma compraVenda da lista de comprasVendas
     *
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
        for (CompraVenda compra : getComprasvendas()) { // Realiza a Compra de produtos, criando uma entrada se necessario
            try {
                entrada = GerenciadorEstoque.getEntradas().get(GerenciadorEstoque.buscaProduto(compra.getProduto().getID()));
                GerenciadorEstoque.carregaProduto(entrada.getProduto());
                GerenciadorEstoque.alteraProduto(compra.getQuantidade());

            } catch (ProdutoNaoEncontrado e) {  // Nova entrada
                GerenciadorEstoque.carregaProduto(compra.getProduto());
                GerenciadorEstoque.appendProduto(compra.getQuantidade());
            } finally {
                valor += compra.getQuantidade() * compra.getProduto().getPreco();
            }
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

        for (CompraVenda venda : getComprasvendas()) {  // Para toda Venda, se verifica se há produto suficiente no estoque
            entrada = GerenciadorEstoque.getEntradas().get(GerenciadorEstoque.buscaProduto(venda.getProduto().getID()));
            if (entrada.getProduto().getQuantidadeDisponivel() < venda.getQuantidade())
                throw new SemEstoque("Não há estoque suficiente de " + venda.getProduto().getNome() + ", " + entrada.getProduto().getQuantidadeDisponivel() + " dos " + venda.getQuantidade() + " requisitados.");
        }

        for (CompraVenda venda : getComprasvendas()) {  // Realiza a Venda de produtos, removendo do estoque e adicionando o valor em valor
            entrada = GerenciadorEstoque.getEntradas().get(GerenciadorEstoque.buscaProduto(venda.getProduto().getID()));
            GerenciadorEstoque.carregaProduto(entrada.getProduto());
            GerenciadorEstoque.alteraProduto(-venda.getQuantidade());

            valor += venda.getQuantidade() * venda.getProduto().getPreco();
        }
        setSaldo(getSaldo() + valor);

        // ESCRITA NO ARQUIVO


    }


}
