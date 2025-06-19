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

    // Carrega produtos de txt para lista

    public static void carregarLista() {

        // LEITURA DE ARQUIVO

    }

    // Adiciona uma entrada de produto na lista de produtos (produto da propriedade)

    public static void appendProduto(int quantidade) {
        getEntradas().add(new Entrada(getProduto(), quantidade, true, -1));
    }

    // Remove uma entrada de produto na lista de produtos

    public static void deleteProduto(String id) {
        getEntradas().remove(buscaProduto(id));

        GerenciadorEstoque.getEntradas().get(GerenciadorEstoque.buscaProduto(produto.getID())).getQuantiade()
    }

    // Busca um produto na lista de produtos


    /**
     * Busca um produto por ID na lista do estoque (entradas), retorna seu indice ou joga Produto Não
     *
     * @param id
     * @return
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
     * Registra a lista de entradas no arquivo texto "Estoque"
     */
    public static void salvaProduto() {

        // ESCRITA DE ARQUIVO


    }

    /**
     * Altera a quantidade de uma entrada com o minimo de zero
     * @param quantidade soma essa quantidade na quantidade atual da entrada
     */
    public static void alteraProduto(int quantidade) {
        int index = buscaProduto(getProduto().getID());
        getEntradas().get(index).setQuantiade(Math.min(getEntradas().get(index).getQuantiade() + quantidade, 0));
    }

}
