package Gerencia;

import Produtos.IntProduto;

public class Entrada {
    private IntProduto produto;
    private boolean modificado;
    private int linha;

    protected Entrada(IntProduto produto, int linha) {
        this.produto = produto;
        this.modificado = false;
        this.linha = linha;
    }

    protected int getLinha() {
        return linha;
    }

    protected IntProduto getProduto() {
        return produto;
    }

    protected boolean isModificado() {
        return modificado;
    }

    protected void setModificado(boolean modificado) {
        this.modificado = modificado;
    }
}
