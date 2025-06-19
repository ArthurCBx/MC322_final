package pessoa;

import Gerencia.Caixa.Caixa;
import pagamento.TipoPagamento;

/*
    Classe Funcionario que herda de Pessoa.
    Representa um funcionário do sistema, com a capacidade de cadastrar clientes e efetuar pagamentos.
    Ele possui um ID único.
 */

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

    public void efetuaPagamento(TipoPagamento tipo){
        Caixa.registrarVenda(tipo);
    }

}