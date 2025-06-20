import Produtos.IntProduto;
import Produtos.Livro.Livro;
import pessoa.Cliente;
import pessoa.Funcionario;
import pessoa.Gerente;
import pessoa.UserManager;

import java.io.BufferedReader;
import java.io.File;

public class FileInputReader {
    public static void read(File file){
        try(BufferedReader reader = new BufferedReader(new java.io.FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if(line.startsWith("Cliente: ")){
                    String[] clienteData = line.trim().split(",");
                    for (int i = 0; i < clienteData.length; i++) {
                        clienteData[i] = clienteData[i].trim();
                    }
                    String nome = clienteData[0].substring(8).trim();
                    String login = clienteData[1].substring(6).trim();
                    String senha = clienteData[2].substring(7).trim();
                    String cpf = clienteData[3].substring(4).trim();
                    String dataNascimento = clienteData[4].substring(15).trim();
                    String email = clienteData[5].substring(6).trim();
                    Cliente cliente = new Cliente(nome, cpf, dataNascimento, email, login, senha);
                    UserManager.writeClientToFile(cliente);
                }

                else if (line.startsWith("Gerente: ")) {
                    String[] gerenteData = line.trim().split(",");
                    for (int i = 0; i < gerenteData.length; i++) {
                        gerenteData[i] = gerenteData[i].trim();
                    }
                    String nome = gerenteData[0].substring(8).trim();
                    String login = gerenteData[1].substring(6).trim();
                    String senha = gerenteData[2].substring(7).trim();
                    String cpf = gerenteData[3].substring(4).trim();
                    String dataNascimento = gerenteData[4].substring(15).trim();
                    String email = gerenteData[5].substring(6).trim();
                    Gerente gerente = new Gerente(nome, cpf, dataNascimento, email, login, senha);
                    UserManager.writeFuncionarioToFile(gerente);
                }

                else if (line.startsWith("Funcionario: ")){
                    String[] funcionarioData = line.trim().split(",");
                    for (int i = 0; i < funcionarioData.length; i++) {
                        funcionarioData[i] = funcionarioData[i].trim();
                    }
                    String nome = funcionarioData[0].substring(12).trim();
                    String login = funcionarioData[1].substring(6).trim();
                    String senha = funcionarioData[2].substring(7).trim();
                    String cpf = funcionarioData[3].substring(4).trim();
                    String dataNascimento = funcionarioData[4].substring(15).trim();
                    String email = funcionarioData[5].substring(6).trim();
                    Funcionario funcionario = new Funcionario(nome, cpf, dataNascimento, email, login, senha);
                    UserManager.writeFuncionarioToFile(funcionario);
                }

                else if (line.startsWith("Produto: ")){
                    String[] produtoData = line.trim().split(",");
                    for (int i = 0; i < produtoData.length; i++) {
                        produtoData[i] = produtoData[i].trim();
                    }
                    String tipoProduto = produtoData[0].substring(8).trim();
                    int quantidade = Integer.parseInt(produtoData[1].substring(11).trim());
                    String titulo = produtoData[2].substring(7).trim();
                    int preco = Integer.parseInt(produtoData[3].substring(6).trim());
                    boolean alugavel = Boolean.parseBoolean(produtoData[4].substring(9).trim());
                    boolean compravel = Boolean.parseBoolean(produtoData[5].substring(10).trim());
                    int secao = Integer.parseInt(produtoData[6].substring(6).trim());
                    String genero = produtoData[7].substring(7).trim();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}