package excecoes;

/*
 * Exeção quando um produto não estiver cadastrado no sistema
 */
public class ProdutoNaoEncontrado extends RuntimeException {
    public ProdutoNaoEncontrado(String message) {
        super(message);
    }
}
