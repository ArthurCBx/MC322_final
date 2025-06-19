package pessoa;

import pagamento.Cartao;

public class Cliente extends Pessoa{
    private Cartao cartao;
    private String endereco;
    private String telefone;

    public Cliente(String nome, String CPF, int idade, String data_nascimento, String email, String login, String senha, Cartao cartao, String endereco, String telefone) {
        super(nome, CPF, idade, data_nascimento, email, login, senha);
        this.cartao = cartao;
        this.endereco = endereco;
        this.telefone = telefone;
    }

}
