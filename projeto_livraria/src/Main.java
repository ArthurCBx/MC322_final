import interface_swing.MenuInicial;

import javax.swing.*;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Selecione um arquivo contendo as informações de livros, filmes e usuarios a serem cadastrados no sistema.");
            int userSelection = fileChooser.showOpenDialog(null);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File arquivo = fileChooser.getSelectedFile();
                FileInputReader.read(arquivo);
                MenuInicial.iniciarMenuInicial();
            } else {
                System.exit(0);
            }
        });
    }
}
