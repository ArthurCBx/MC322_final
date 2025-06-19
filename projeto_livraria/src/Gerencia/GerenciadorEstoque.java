package Gerencia;

import Produtos.IntProduto;

public class GerenciadorEstoque {

    private static IntProduto produto;

    public static IntProduto getProduto() {
        return produto;
    }

    public static void setProduto(IntProduto produto) {
        GerenciadorEstoque.produto = produto;
    }


    // Adiciona uma entrada de produto nos .TXTs

    public static void appendProduto(){}

    // Remove uma entrada de produto nos .TXTs

    public static void deleteProduto(){}

    // Carrega dados de um produto no propriedade produto

    public static void carregaProduto(){}

    // Sobrescreve dados de uma entrada com os dados carregados em "produto"

    public static void sobrescreveProduto(String id){}

    // Altera o produto carregado

    //public static void alteraProduto(String id,){}

    



}
