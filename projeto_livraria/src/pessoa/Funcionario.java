package pessoa;

import Gerencia.Caixa.Caixa;
import pagamento.MetodoPagamento;

public class Funcionario extends Pessoa{
    private final String id;

    public Funcionario(String nome, String CPF, String data_nascimento, String email, String id, String login, String senha) {
        super(nome, CPF, data_nascimento, email, login, senha);
        this.id = id;
        UserManager.writeFuncionarioToFile(this);
    }

    public String getId() {
        return id;
    }

    public void cadastraCliente(Cliente cliente){
        UserManager.writeClientToFile(cliente);
    }

    public void efetuaPagamento(MetodoPagamento metodoPagamento){
        Caixa.s
    }

}