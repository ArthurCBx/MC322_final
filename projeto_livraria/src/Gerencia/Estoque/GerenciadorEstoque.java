package Gerencia.Estoque;

import Produtos.IntProduto;
import excecoes.ProdutoNaoEncontrado;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class GerenciadorEstoque {

    private static IntProduto produto;
    private static ArrayList<Entrada> entradas;

    public static IntProduto getProduto() {
        return produto;
    }

    public static void setProduto(IntProduto produto) {
        GerenciadorEstoque.produto = produto;
    }

    public static ArrayList<Gerencia.Estoque.Entrada> getEntradas() {
        return entradas;
    }
/*
    public static void criaProduto(String nome,....){
        setProduto(....);

    }

 */

    // Carrega produtos de txt para lista

    public static void carregarLista() {
    }

    // Adiciona uma entrada de produto na lista de produtos (produto da propriedade)

    public static void appendProduto() {
        getEntradas().add(new Entrada(getProduto(),true,-1));
    }

    // Remove uma entrada de produto na lista de produtos

    public static void deleteProduto(String id) {
        getEntradas().remove(buscaProduto(id));
    }

    // Busca um produto na lista de produtos

    public static int buscaProduto(String id) {
        int index;
        try {
            index = getEntradas().indexOf(getEntradas().stream().filter(entrada -> entrada.getProduto().getID().equals(id)).map(Entrada::getProduto).findFirst().get());
        } catch (NoSuchElementException e) {
            throw new ProdutoNaoEncontrado("Produto: '" + id + "' nao foi encontrado");
        }
        return index;
    }

    // Carrega dados de um produto no propriedade produto

    public static void carregaProduto(String id) {
        setProduto(GerenciadorEstoque.getEntradas().get(buscaProduto(id)).getProduto());
    }

    // Sobrescreve o txt de produtos com o conteudo da lista de produtos

    public static void salvaProduto() {
    }

    // Altera o produto carregado

    //public static void alteraProduto(String id,){}


}
