package excecoes;

public class SemEstoque extends RuntimeException {
    public SemEstoque(String message) {
        super(message);
    }
}
