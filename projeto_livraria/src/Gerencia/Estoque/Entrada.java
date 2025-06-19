package Gerencia.Estoque;

import Gerencia.Elemento;
import Produtos.IntProduto;

public class Entrada extends Elemento {
    private boolean modificado;
    private int linha;

    protected Entrada(IntProduto produto, int linha) {
        super(produto);
        this.modificado = false;
        this.linha = linha;
    }

    protected int getLinha() {
        return linha;
    }

    protected boolean isModificado() {
        return modificado;
    }

    protected void setModificado(boolean modificado) {
        this.modificado = modificado;
    }
}
