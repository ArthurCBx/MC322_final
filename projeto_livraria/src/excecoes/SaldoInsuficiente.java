package excecoes;

/*
 * Exceção para quando não se pode fazer uma compra dada a falta de saldo
 */
public class SaldoInsuficiente extends RuntimeException {
    public SaldoInsuficiente(String message) {
        super(message);
    }
}
