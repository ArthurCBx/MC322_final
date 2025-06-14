package excecoes;

public class LivroNaoDisponivel extends RuntimeException {
    public LivroNaoDisponivel(String message) {
        super(message);
    }
}
