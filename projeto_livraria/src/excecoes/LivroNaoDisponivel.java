package excecoes;

/*
 * Exceção quando um livro for solicitado, ele estiver cadastrado no estoque mas encontra-se indisponível
 */
public class LivroNaoDisponivel extends RuntimeException {
    public LivroNaoDisponivel(String message) {
        super(message);
    }
}
