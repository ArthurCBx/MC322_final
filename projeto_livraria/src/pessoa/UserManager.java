package pessoa;
import java.io.*;
import java.util.ArrayList;

/*
    Classe UserManager que gerencia os usuários do sistema.
    Ela é responsável por ler e escrever informações de clientes e funcionários em arquivos de logins,
    além de verificar se um login e senha existem.
    Também é capaz de remover um funcionário do sistema.
 */

public class UserManager {
    private static final String LogFile = "projeto_livraria/src/arquivos/login.txt";
    private static final File logFile = new File(LogFile);
    private static final ArrayList<Cliente> clientes = new ArrayList<>();
    private static final ArrayList<Funcionario> funcionarios = new ArrayList<>();


    public static ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public static ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    /**
     * Escreve as informações de um cliente no arquivo de login e salva no ArrayList.
     * @param cliente O cliente cujas informações serão escritas.
     */
    public static void writeClientToFile(Cliente cliente) {
        if(clientes.contains(cliente)){
            return;
        }
        clientes.add(cliente);

        if(!logFile.exists() || !logFile.isFile()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LogFile, true))) {
            StringBuilder s = new StringBuilder();
            s.append("Cliente: ").append(cliente.getNome()).append("\n")
                    .append("Login: ").append(cliente.getLogin()).append("\n")
                    .append("Senha: ").append(cliente.getSenha()).append("\n\n");
            writer.write(s.toString());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao escrever no arquivo: " + LogFile);
        }
    }

    /**
     * Escreve as informações de um funcionário no arquivo de login e salva no ArrayList.
     * @param funcionario O funcionário cujas informações serão escritas.
     */
    public static void writeFuncionarioToFile(Funcionario funcionario) {
        if(funcionarios.contains(funcionario)){
            return;
        }
        funcionarios.add(funcionario);

        if(!logFile.exists() || !logFile.isFile()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LogFile, true))) {
            StringBuilder s = new StringBuilder();
            if (funcionario instanceof Gerente) {
                s.append("Gerente: ");
            } else {
                s.append("Funcionario: ");
            }
            s.append(funcionario.getNome()).append(" - id: ").append(funcionario.getId()).append("\n")
                    .append("Login: ").append(funcionario.getLogin()).append("\n")
                    .append("Senha: ").append(funcionario.getSenha()).append("\n\n");
            writer.write(s.toString());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao escrever no arquivo: " + LogFile);
        }
    }

    /**
     * Remove um funcionário do ArrayList pelo ID e atualiza o arquivo de logins.
     * @param Id O ID do funcionário a ser removido.
     */
    public static void removeFuncionario(int Id){
        Funcionario funcionarioToRemove = null;
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getId() == (Id)) {
                funcionarioToRemove = funcionario;
                funcionarios.remove(funcionario);
                break;
            }
        }
        if (funcionarioToRemove == null) {
            return;
        }

        String line;
        StringBuilder s = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(LogFile))){
            while((line = reader.readLine()) != null) {
                if (line.contains("Funcionario: " + funcionarioToRemove.getNome() + " - id: " + funcionarioToRemove.getId())) {
                    reader.readLine(); // Pula a linha do login
                    reader.readLine(); // Pula a linha da senha
                    continue;          // Não adiciona as linhas do funcionário removido
                }
                s.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LogFile, false))) {
            writer.write(s.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Verifica se o login e senha existem no arquivo de logins.
     * @param login O login a ser verificado.
     * @param senha A senha a ser verificada.
     * @return Um array de Boolean {login_exists, password_correct, isCliente, isFuncionario, isGerente}.
     */
    public static Boolean[] loginExists(String login, String senha){
        boolean loginFound = false;
        boolean senhaCorreta = false;
        Boolean[] verify = new Boolean[]{false, false, false, false, false};

        try(BufferedReader reader = new BufferedReader(new FileReader(LogFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Login: ")){
                    String loginLine = line.substring(7).trim();
                    String senhaLine = null;
                    line = reader.readLine();

                    if (line != null && line.startsWith("Senha: ")) {
                        senhaLine = line.substring(7).trim();
                    }

                    if (loginLine.equals(login)){
                        verify[0] = true;
                        if (senhaLine != null && senhaLine.equals(senha)) {
                            verify[1] = true;
                        }
                        break;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        for(Cliente cliente : clientes) {
            if (cliente.getLogin().equals(login)) {
                verify[2] = true; // É um cliente
                verify[3] = false; // Não é um funcionário
                verify[4] = false; // Não é um gerente
                return verify;
            }
        }

        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getLogin().equals(login)) {
                verify[2] = false; // Não é um cliente
                if (funcionario instanceof Gerente) {
                    verify[3] = false; // Não é um funcionário
                    verify[4] = true; // É um gerente
                } else {
                    verify[3] = true; // É um funcionário
                    verify[4] = false; // Não é um gerente
                }
                return verify;
            }
        }

        return verify; // Login não encontrado
    }
}
