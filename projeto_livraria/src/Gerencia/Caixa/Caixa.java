package Gerencia.Caixa;

import Gerencia.Estoque.GerenciadorEstoque;
import Gerencia.GerenciadorGeral;
import Produtos.Produto;
import excecoes.ProdutoNaoEncontrado;
import excecoes.SemEstoque;
import pagamento.Cartao;
import pagamento.TipoCartao;
import pagamento.TipoPagamento;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Caixa {

    private static final String LogTransacoes = "projeto_livraria/src/arquivos/Transações.txt";
    private static final File logTransacoes = new File(LogTransacoes);

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
            index = getComprasvendas().stream().map(CompraVenda::getProduto).toList().indexOf(getComprasvendas().stream().map(CompraVenda::getProduto).filter(produto -> produto.getId().equals(id)).findFirst().get());
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
    public static void adiconarCompraVenda(Produto produto, int quantidade) {
        getComprasvendas().add(new CompraVenda(produto, quantidade));
    }

    /**
     * Remove uma compraVenda da lista de comprasVendas
     *
     * @param index index do produto a ser removido da lista
     */
    public static void removerCompraVenda(int index) {
        getComprasvendas().remove(index);
    }


    /**
     * Interpreta a lista de comprasVendas como compras e efetua a alteração de saldo, estoque e registra no arquivo "Transações.txt"
     */
    public static void registrarCompra() {
        int valor = 0;
        Produto produto;
        for (CompraVenda compra : getComprasvendas()) { // Realiza a Compra de produtos, criando uma produto se necessario
            try {
                produto = GerenciadorEstoque.getProdutos().get(GerenciadorEstoque.buscaProduto(compra.getProduto().getId()));
                GerenciadorEstoque.carregaProduto(produto);
                GerenciadorEstoque.alteraProduto(compra.getQuantidade());

            } catch (ProdutoNaoEncontrado e) {  // Nova produto
                GerenciadorEstoque.carregaProduto(compra.getProduto());
                GerenciadorEstoque.appendProduto();
            } finally {
                valor += compra.getQuantidade() * compra.getProduto().getPreco();
            }
        }

        setSaldo(getSaldo() - valor);

        if (!logTransacoes.exists() || !logTransacoes.isFile()) {
            try {
                logTransacoes.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LogTransacoes, true))) {
            StringBuilder s = new StringBuilder();

            s.append("COMPRA realizada por: ").append(GerenciadorGeral.getFuncionario().getNome()).append(", ID: ").append(GerenciadorGeral.getFuncionario().getId()).append("\n")
                    .append("    Produto - ID - Quantidade - Valor individual - Valor total:").append("\n");

            for (CompraVenda compra : getComprasvendas()) {
                s.append("    ").append(compra.getProduto().getNome()).append(" - ")
                        .append(compra.getProduto().getId()).append(" - ")
                        .append(compra.getQuantidade()).append(" - ")
                        .append(compra.getProduto().getPreco()).append(" - ")
                        .append(compra.getProduto().getPreco() * compra.getQuantidade()).append("\n");
            }

            s.append("Valor total da compra: ").append(valor).append("\n")
                    .append("Saldo resultante: ").append(getSaldo()).append("\n\n");
            writer.write(s.toString());

            getComprasvendas().clear();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao escrever no arquivo: " + LogTransacoes);
        }

    }


    /**
     * Interpreta a lista de comprasVendas como vendas e efetua a alteração de saldo, estoque e registra no arquivo "Transações.txt"
     */
    public static void registrarVenda(TipoPagamento pagamento, TipoCartao tipoCartao) {

        int valor = 0;
        Produto produto;

        for (CompraVenda venda : getComprasvendas()) {  // Para toda Venda, se verifica se há produto suficiente no estoque
            produto = GerenciadorEstoque.getProdutos().get(GerenciadorEstoque.buscaProduto(venda.getProduto().getId()));
            if (produto.getQuantidadeDisponivel() < venda.getQuantidade())
                throw new SemEstoque("Não há estoque suficiente de " + venda.getProduto().getNome() + ", " + produto.getQuantidadeDisponivel() + " dos " + venda.getQuantidade() + " requisitados.");
        }

        for (CompraVenda venda : getComprasvendas()) {  // Realiza a Venda de produtos, removendo do estoque e adicionando o valor em valor
            produto = GerenciadorEstoque.getProdutos().get(GerenciadorEstoque.buscaProduto(venda.getProduto().getId()));
            GerenciadorEstoque.carregaProduto(produto);
            GerenciadorEstoque.alteraProduto(-venda.getQuantidade());

            valor += venda.getQuantidade() * venda.getProduto().getPreco();
        }

        setSaldo(getSaldo() + valor);

        if (!logTransacoes.exists() || !logTransacoes.isFile()) {
            try {
                logTransacoes.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LogTransacoes, true))) {
            StringBuilder s = new StringBuilder();

            s.append("VENDA realizada por: ").append(GerenciadorGeral.getFuncionario().getNome()).append(", ID: ").append(GerenciadorGeral.getFuncionario().getId()).append("\n")
                    .append("Cliente: ").append(GerenciadorGeral.getCliente().getNome()).append(", Login: ").append(GerenciadorGeral.getCliente().getLogin()).append(", Metodo de Pagamento: ").append(pagamento.toString());
                    if(pagamento.equals(TipoPagamento.CARTAO))
                        s.append(", Tipo Cartão: ").append(tipoCartao);
                    s.append("\n").append("    Produto - Quantidade - Valor individual - Valor total:").append("\n");

            for (CompraVenda venda : getComprasvendas()) {
                s.append("    ").append(venda.getProduto()).append(" - ")
                        .append(venda.getQuantidade()).append(" - ")
                        .append(venda.getProduto().getPreco()).append(" - ")
                        .append(venda.getProduto().getPreco() * venda.getQuantidade()).append("\n");
            }

            s.append("Valor total da compra: ").append(valor).append("\n")
                    .append("Saldo resultante: ").append(getSaldo()).append("\n\n");
            writer.write(s.toString());

            getComprasvendas().clear();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao escrever no arquivo: " + LogTransacoes);
        }

    }


}
