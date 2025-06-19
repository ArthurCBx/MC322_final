package Gerencia.Estoque;

import Produtos.IntProduto;
import excecoes.ProdutoNaoEncontrado;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class GerenciadorEstoque {

    private static IntProduto produto;
    private static ArrayList<Entrada> entradas = new ArrayList<>();

    public static IntProduto getProduto() {
        return produto;
    }

    public static void setProduto(IntProduto produto) {
        GerenciadorEstoque.produto = produto;
    }

    public static ArrayList<Gerencia.Estoque.Entrada> getEntradas() {
        return entradas;
    }

    /**
     * Carrega os dados armazenados em Estoque.txt na lista de entradas
     */
    public static void carregarLista() {

        // LEITURA DE ARQUIVO

    }

    /**
     * Adiciona o produto carregado como uma nova entrada na lista de entradas
     *
     * @param quantidade quantidade desse produto adicionado
     */
    public static void appendProduto(int quantidade) {
        getEntradas().add(new Entrada(getProduto(), quantidade, true, -1));
    }

    /**
     * Deleta uma entrada da lista de entradas
     *
     * @param id ID da entrada
     */
    public static void deleteProduto(String id) {
        getEntradas().remove(buscaProduto(id));
    }

    /**
     * Busca um produto por ID na lista do estoque (entradas), retorna seu indice ou joga Exceção ProdutoNaoEncontrado
     *
     * @param id ID do produto
     * @return Retorna seu indice
     */
    public static int buscaProduto(String id) {
        int index;
        try {
            index = getEntradas().indexOf(getEntradas().stream().map(Entrada::getProduto).filter(entradaProduto -> entradaProduto.getID().equals(id)).findFirst().get());
        } catch (NoSuchElementException e) {
            throw new ProdutoNaoEncontrado("Produto: '" + id + "' nao foi encontrado");
        }
        return index;
    }

    /**
     * Carrega uma interface produto via ID na propriedade "produto" do Gerenciador
     *
     * @param id um ID de produto
     */
    public static void carregaProduto(String id) {
        setProduto(GerenciadorEstoque.getEntradas().get(buscaProduto(id)).getProduto());
    }

    /**
     * Carrega uma interface produto na propriedade "produto" do Gerenciador
     *
     * @param produto uma interface produto
     */
    public static void carregaProduto(IntProduto produto) {
        setProduto(produto);
    }

    /**
     * Registra a lista de entradas no arquivo texto "Estoque.txt"
     */
    public static void salvaProduto() {

        // ESCRITA DE ARQUIVO

    }

    /**
     * Altera a quantidade de uma entrada com o minimo de zero
     *
     * @param quantidade soma essa quantidade na quantidade atual da entrada
     */
    public static void alteraProduto(int quantidade) {
        int index = buscaProduto(getProduto().getID());
        getEntradas().get(index).setQuantiade(Math.min(getEntradas().get(index).getQuantiade() + quantidade, 0));
    }

}
