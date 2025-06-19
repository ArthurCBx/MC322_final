package pessoa;

public class Funcionario extends Pessoa{
    private final String id;

    public Funcionario(String nome, String CPF, int idade, String data_nascimento, String email, String id, String login, String senha) {
        super(nome, CPF, idade, data_nascimento, email, login, senha);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void cadastraCliente(){

    }

    public void efetuaPagamento(){

    }
}
