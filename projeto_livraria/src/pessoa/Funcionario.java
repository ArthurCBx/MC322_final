package pessoa;

public class Funcionario extends Pessoa{
    private final String id;

    public Funcionario(String CPF, int idade, String data_nascimento, String email, String id, int numero_celular) {
        super(CPF, idade, data_nascimento, email, numero_celular);
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
