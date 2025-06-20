import javax.swing.*;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Selecione um arquivo contendo as informações de livros e filmes a serem cadastrados no sistema. Também deve conter os logins e senhas dos usuários.");
            int userSelection = fileChooser.showOpenDialog(null);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File arquivo = fileChooser.getSelectedFile();
                FileInputReader.read(arquivo);

            } else {
                System.exit(0);
            }
        });
    }
}
