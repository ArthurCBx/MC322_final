package pessoa;

import Gerencia.GerenciadorGeral;
import Produtos.Produto;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/*
    Classe abstrata Pessoa que servirá como superclasse para Cliente e Funcionario.
    Possui como atributos as informações básicas de uma pessoa, como CPF, data de nascimento, nome e email.
    Contém o login e senha para autenticação.
    Possui métodos para obter a idade da pessoa e buscar produtos através do GerenciadorGeral.
 */

public abstract class Pessoa {
    private String CPF;
    private final String data_nascimento;
    private final String nome;
    private String email;
    private String login;
    private String senha;

    public Pessoa(String nome, String CPF, String data_nascimento, String email, String login, String senha) {
        this.CPF = CPF;
        this.data_nascimento = data_nascimento;
        this.email = email;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    public String getCPF() {
        return CPF;
    }

    public int getIdade() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate nascimento = LocalDate.parse(data_nascimento, formatter);
        LocalDate hoje = LocalDate.now();
        return Period.between(nascimento, hoje).getYears();
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getNome() {
        return nome;
    }

    public String getData_nascimento() {
        return data_nascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Produto buscarProduto(String nomeProduto) {
        return GerenciadorGeral.realizarBusca(nomeProduto);
    }

}
