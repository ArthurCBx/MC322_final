package Gerencia;

import Produtos.IntProduto;

public class Elemento {
    private IntProduto produto;

    public Elemento(IntProduto produto) {
        this.produto = produto;
    }

    public IntProduto getProduto() {
        return produto;
    }

    public void setProduto(IntProduto produto) {
        this.produto = produto;
    }
}
