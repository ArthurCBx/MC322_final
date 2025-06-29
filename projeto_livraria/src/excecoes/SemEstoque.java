package excecoes;

/*
 * Exceção para quando não houver estoque suficiente de algum produto durante uma venda
 */
public class SemEstoque extends RuntimeException {
    public SemEstoque(String message) {
        super(message);
    }
}
