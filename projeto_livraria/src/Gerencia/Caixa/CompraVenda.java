package Gerencia.Caixa;

import Produtos.Produto;

/*
 * Classe responsável por auxiliar no registro das compras e vendas no caixa
 */
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

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        return s.append(produto.getNome()).append(" - Quantidade: ").append(getQuantidade()).append(" - Preço por unidadee: ").append(produto.getPreco()).append(" - Total: ").append(getQuantidade()*produto.getPreco()).toString();
    }
}
