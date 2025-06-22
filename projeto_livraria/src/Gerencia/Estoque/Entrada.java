package Gerencia.Estoque;

import Gerencia.Caixa.CompraVenda;
import Gerencia.Elemento;
import Produtos.IntProduto;

public class Entrada extends Elemento {
    private boolean modificado;

    protected Entrada(IntProduto produto, boolean modificado) {
        super(produto);
        this.modificado = modificado;
    }

    protected boolean isModificado() {
        return modificado;
    }

    protected void setModificado(boolean modificado) {
        this.modificado = modificado;
    }
}
