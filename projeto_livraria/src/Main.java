import interface_swing.MenuInicial;

import javax.swing.*;
import java.io.File;

public class Main {

    public static void main(String[] args) {
        String toDeleteFile = "projeto_livraria/src/arquivos/login.txt";
        File file = new File(toDeleteFile);
        if (file.exists()) {
            boolean deleted = file.delete();
            if (!deleted) {
                System.err.println("Erro ao deletar o arquivo: " + toDeleteFile);
            }
        }

        // Inicia o processo de seleção de arquivo na thread de eventos do Swing
        SwingUtilities.invokeLater(Main::selecionarArquivo);
    }

    private static void selecionarArquivo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecione um arquivo contendo as informações de livros, filmes e usuarios a serem cadastrados no sistema.");

        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File arquivo = fileChooser.getSelectedFile();

            // Verifica se o arquivo é do tipo .txt
            if (!arquivo.getName().toLowerCase().endsWith(".txt")) {
                JOptionPane.showMessageDialog(null,
                        "Por favor, selecione um arquivo .txt",
                        "Formato de arquivo inválido",
                        JOptionPane.ERROR_MESSAGE);
                selecionarArquivo(); // Volta para a seleção de arquivo
                return; // Encerra a execução atual do metodo para evitar processamento adicional
            }

            // Tenta ler o arquivo e iniciar o menu principal
            try {
                FileInputReader.read(arquivo);
                MenuInicial.iniciarMenuInicial();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Erro ao ler o arquivo selecionado\n" + e.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                selecionarArquivo(); // Volta para a seleção de arquivo em caso de erro
            }
        } else {
            // Se o usuário cancelar a seleção, o programa é encerrado.
            System.exit(0);
        }
    }
}