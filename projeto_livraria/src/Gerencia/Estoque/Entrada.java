package Gerencia.Estoque;

import Gerencia.Caixa.CompraVenda;
import Gerencia.Elemento;
import Produtos.IntProduto;

public class Entrada extends CompraVenda {
    private boolean modificado;
    private int linha;

    protected Entrada(IntProduto produto, int quantidade, boolean modificado, int linha) {
        super(produto, quantidade);
        this.modificado = modificado;
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
