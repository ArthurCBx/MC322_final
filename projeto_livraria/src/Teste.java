import Gerencia.Estoque.GerenciadorEstoque;

import java.io.File;

public class Teste {

    public static void main(String[] args) {
        String LogEstoque = "projeto_livraria/src/arquivos/teste.txt";
        File logEstoque = new File(LogEstoque);

        FileInputReader.read(logEstoque);
        GerenciadorEstoque.carregarLista();
        GerenciadorEstoque.salvaProduto();
    }
}
