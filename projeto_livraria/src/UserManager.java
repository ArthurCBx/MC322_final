import pessoa.Cliente;
import pessoa.Funcionario;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class UserManager {
    private static final String clienteLogFile = "projeto_livraria/src/arquivos/cliente_login.txt";
    private static final File clientelogFile = new File(clienteLogFile);
    private static final ArrayList<Cliente> clientes = new ArrayList<>();

    private static final String funcionarioLogFile = "projeto_livraria/src/arquivos/cliente_login.txt";
    private static final File funcionariologFile = new File(funcionarioLogFile);
    private static final ArrayList<Funcionario> funcionarios = new ArrayList<>();


    public static ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public static ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public static void writeClientToFile(Cliente cliente) {
        if(clientes.contains(cliente)){
            return;
        }
        clientes.add(cliente);

        if(!clientelogFile.exists() || !clientelogFile.isFile()) {
            try {
                clientelogFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(clienteLogFile, true))) {
            StringBuilder s = new StringBuilder();
            s.append("Cliente: ").append(cliente).append("\n")
                    .append(cliente.getLogin()).append("\n")
                    .append(cliente.getSenha()).append("\n\n");
            writer.write(s.toString());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao escrever no arquivo: " + clienteLogFile);
        }
    }

    public static void writeFuncionarioToFile(Funcionario funcionario) {
        if(funcionarios.contains(funcionario)){
            return;
        }
        funcionarios.add(funcionario);

        if(!funcionariologFile.exists() || !funcionariologFile.isFile()) {
            try {
                funcionariologFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(funcionarioLogFile, true))) {
            StringBuilder s = new StringBuilder();
            s.append("Funcionario: ").append(funcionario).append(" - id: ").append(funcionario.getId()).append("\n")
                    .append(funcionario.getLogin()).append("\n")
                    .append(funcionario.getSenha()).append("\n\n");
            writer.write(s.toString());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao escrever no arquivo: " + funcionarioLogFile);
        }
    }

    
}
