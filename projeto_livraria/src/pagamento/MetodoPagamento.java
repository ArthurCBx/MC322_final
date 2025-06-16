package pagamento;

public interface MetodoPagamento {

    String infopagamento();

    void efetuarpagamento(TipoPagamento tipoPagamento);

}
