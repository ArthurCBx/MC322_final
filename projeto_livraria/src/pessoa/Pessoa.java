package pessoa;

public abstract class Pessoa {
    private String CPF;
    private int idade;
    private final String data_nascimento;
    private String email;
    private int numero_celular;

    public Pessoa(String CPF, int idade, String data_nascimento, String email, int numero_celular) {
        this.CPF = CPF;
        this.idade = idade;
        this.data_nascimento = data_nascimento;
        this.email = email;
        this.numero_celular = numero_celular;
    }

    public String getCPF() {
        return CPF;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public int getNumero_celular() {
        return numero_celular;
    }

    public void setNumero_celular(int numero_celular) {
        this.numero_celular = numero_celular;
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
}
