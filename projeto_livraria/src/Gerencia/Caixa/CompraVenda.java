package Gerencia.Caixa;

import Gerencia.Elemento;
import Produtos.IntProduto;

public class CompraVenda extends Elemento {

    private int quantidade;

    public CompraVenda(IntProduto produto, int quantiade) {
        super(produto);
        this.quantidade = quantiade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
