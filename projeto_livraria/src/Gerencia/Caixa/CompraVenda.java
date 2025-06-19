package Gerencia.Caixa;

import Gerencia.Elemento;
import Produtos.IntProduto;

public class CompraVenda extends Elemento {

    private int quantiade;

    public CompraVenda(IntProduto produto, int quantiade) {
        super(produto);
        this.quantiade = quantiade;
    }

    protected int getQuantiade() {
        return quantiade;
    }

    protected void setQuantiade(int quantiade) {
        this.quantiade = quantiade;
    }
}
