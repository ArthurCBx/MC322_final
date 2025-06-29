import Gerencia.Estoque.GerenciadorEstoque;
import Produtos.Filme.Filme;
import Produtos.Generico.Generico;
import Produtos.Genero;
import Produtos.Livro.Livro;
import pessoa.Cliente;
import pessoa.Funcionario;
import pessoa.Gerente;
import pessoa.UserManager;

import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;

public class FileInputReader {
    /**
     * Método estático para ler um arquivo e processar seus conteúdos
     * @param file Arquivo a ser lido contendo informações de clientes, funcionários e produtos
     */
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
                    String[] s;
                    String[] gens;
                    line = line.substring(line.indexOf(' ') + 1);

                    if(line.startsWith("Livro")){
                        line = line.substring(line.indexOf(' ') + 1);
                        s = line.split(", ");
                        for (int i = 0; i < s.length; i++)
                            s[i] = s[i].substring(s[i].indexOf(' ') + 1);
                        ArrayList<Genero> generos = new ArrayList<>();
                        gens = s[5].split(";");
                        for (String gen : gens){
                            generos.add(Genero.fromString(gen.trim()));
                        }

                        Livro livro = new Livro(s[0],Float.parseFloat(s[3]),s[1],Integer.parseInt(s[2]),generos,Integer.parseInt(s[4]));
                        GerenciadorEstoque.setProduto(livro);
                        GerenciadorEstoque.appendProduto();


                    } else if (line.startsWith("Filme")) {
                        line = line.substring(line.indexOf(' ') + 1);
                        s = line.split(", ");
                        for (int i = 0; i < s.length; i++)
                            s[i] = s[i].substring(s[i].indexOf(' ') + 1);
                        ArrayList<Genero> generos = new ArrayList<>();
                        gens = s[5].split(";");
                        for (String gen : gens){
                            generos.add(Genero.fromString(gen.trim()));
                        }

                        Filme filme = new Filme(s[0],Float.parseFloat(s[3]),s[1],Integer.parseInt(s[2]),generos,Integer.parseInt(s[4]));
                        GerenciadorEstoque.setProduto(filme);
                        GerenciadorEstoque.appendProduto();


                    } else {
                        line = line.substring(line.indexOf(' ') + 1);
                        s = line.split(", ");
                        for (int i = 0; i < s.length; i++)
                            s[i] = s[i].substring(s[i].indexOf(' ') + 1);

                        Generico generico = new Generico(s[0],Float.parseFloat(s[3]),s[1],Integer.parseInt(s[2]),Integer.parseInt(s[4]));
                        GerenciadorEstoque.setProduto(generico);
                        GerenciadorEstoque.appendProduto();

                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        GerenciadorEstoque.salvaProduto();
    }
}