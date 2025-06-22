package Gerencia.Caixa;

import Produtos.IntProduto;

public class CompraVenda {

    private IntProduto produto;
    private int quantidade;

    public CompraVenda(IntProduto produto, int quantiade) {
        this.produto = produto;
        this.quantidade = quantiade;
    }

    public IntProduto getProduto() {
        return produto;
    }

    public void setProduto(IntProduto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
