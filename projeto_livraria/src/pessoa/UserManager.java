package pessoa;
import java.io.*;
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

    /**
     * Escreve as informações de um cliente no arquivo de login e salva no ArrayList.
     * @param cliente O cliente cujas informações serão escritas.
     */
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
                    .append("Login: ").append(cliente.getLogin()).append("\n")
                    .append("Senha: ").append(cliente.getSenha()).append("\n\n");
            writer.write(s.toString());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao escrever no arquivo: " + clienteLogFile);
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
                    .append("Login: ").append(funcionario.getLogin()).append("\n")
                    .append("Senha: ").append(funcionario.getSenha()).append("\n\n");
            writer.write(s.toString());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao escrever no arquivo: " + funcionarioLogFile);
        }
    }

    /**
     * Remove um funcionário do ArrayList pelo ID e atualiza o arquivo de logins.
     * @param Id O ID do funcionário a ser removido.
     */
    public static void removeFuncionario(String Id){
        Funcionario funcionarioToRemove = null;
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getId().equals(Id)) {
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
        try (BufferedReader reader = new BufferedReader(new FileReader(funcionarioLogFile))){
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

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(funcionarioLogFile, false))) {
            writer.write(s.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Verifica se o login e senha existem no arquivo de logins.
     * @param login O login a ser verificado.
     * @param senha A senha a ser verificada.
     * @param tipo O tipo de usuário ("cliente" ou "funcionario").
     * @return Um array de Boolean onde o primeiro elemento indica se o login existe e o segundo se a senha está correta para o login.
     */
    public static Boolean[] loginExists(String login, String senha, String tipo){
        String filepath;
        boolean loginFound = false;
        boolean senhaCorreta = false;
        Boolean[] verify = new Boolean[2];

        if (tipo.equals("cliente")){
            filepath = clienteLogFile;
        }else if (tipo.equals("funcionario")) {
            filepath = funcionarioLogFile;
        }else{
            return null; // Tipo inválido, não faz nada
        }

        try(BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
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
                        } else {
                            verify[1] = false; // Senha incorreta
                        }
                        return verify; // Login e senha corretos
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        verify[0] = false; verify[1] = false;
        return verify;
    }
}
