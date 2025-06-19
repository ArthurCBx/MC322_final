package pagamento;

public enum TipoPagamento {
    Vista(100,1),
    Desconto(85,1),
    PrazoSemJuros(20,5),
    PrazoComJuros(20,5);

    private int valorPorParcela;
    private int parcelas;

    TipoPagamento(int valorporParcela, int parcelas){
        this.valorPorParcela = valorporParcela;
        this.parcelas = parcelas;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public int getValorPorParcela() {
        return valorPorParcela;
    }

    public void setValorPorParcela(int valorPorParcela) {
        this.valorPorParcela = valorPorParcela;
    }
}
