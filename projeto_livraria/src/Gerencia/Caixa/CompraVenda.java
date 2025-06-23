package Gerencia.Caixa;

import Produtos.Produto;

public class CompraVenda {

    private Produto produto;
    private int quantidade;

    public CompraVenda(Produto produto, int quantiade) {
        this.produto = produto;
        this.quantidade = quantiade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
