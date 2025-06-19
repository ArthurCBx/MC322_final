package pessoa;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

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

}
