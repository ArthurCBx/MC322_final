package Gerencia.Caixa;

import Gerencia.Estoque.Entrada;
import Gerencia.Estoque.GerenciadorEstoque;
import Produtos.IntProduto;
import excecoes.ProdutoNaoEncontrado;
import excecoes.SemEstoque;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Caixa {

    private static float saldo;

    private static ArrayList<CompraVenda> comprasvendas = new ArrayList<>();

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

    public static int buscaCompraVenda(String id) {
        int index;
        try {
            index = getComprasvendas().indexOf(getComprasvendas().stream().map(CompraVenda::getProduto).filter(produto -> produto.getID().equals(id)).findFirst().get());
        } catch (NoSuchElementException e) {
            throw new ProdutoNaoEncontrado("Produto: '" + id + "' nao foi encontrado");
        }
        return index;
    }


    public void adiconarCompraVenda(IntProduto produto, int quantidade) {
        getComprasvendas().add(new CompraVenda(produto, quantidade));
    }

    public void removerCompraVenda(String id) {
        getComprasvendas().remove(buscaCompraVenda(id));
    }

    // Interpreta a lista de compravenda como compras

    public void registrarCompra(String msg) {
        int valor = 0;
        Entrada entrada;
        for (CompraVenda compra : getComprasvendas()) {
            entrada = GerenciadorEstoque.getEntradas().get(GerenciadorEstoque.buscaProduto(compra.getProduto().getID()));
            try {
                GerenciadorEstoque.carregaProduto(entrada.getProduto());
                GerenciadorEstoque.alteraProduto(entrada.getQuantiade() + compra.getQuantiade());

            } catch (ProdutoNaoEncontrado e) {
                GerenciadorEstoque.carregaProduto(compra.getProduto());
                GerenciadorEstoque.appendProduto(compra.getQuantiade());
            }
            valor += compra.getQuantiade() * compra.getProduto().getValor();
        }
        setSaldo(getSaldo() + valor);

        // ESCRITA NO ARQUIVO

    }

    // Interpreta a lista de compravenda como vendas

    public void registrarVenda(String msg) {

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
        setSaldo(getSaldo() - valor);

        // ESCRITA NO ARQUIVO


    }


}
