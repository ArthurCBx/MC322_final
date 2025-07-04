package pessoa;

import pagamento.Cartao;
import pagamento.TipoPagamento;

/*
    Classe cliente que herda de Pessoa.
    Possui como atributos o Cartao, o endereco e o pagamentoPadrao, além dos atributos de Pessoa.
    O pagamentoPadrao é do tipo TipoPagamento, que pode ser CARTAO, DINHEIRO ou PIX.
 */

public class Cliente extends Pessoa{
    private Cartao cartao;
    private TipoPagamento pagamentoPadrao = TipoPagamento.CARTAO;

    public Cliente(String nome, String CPF, String data_nascimento, String email, String login, String senha) {
        super(nome, CPF, data_nascimento, email, login, senha);
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public TipoPagamento getPagamentoPadrao() {
        return pagamentoPadrao;
    }

    public void setPagamentoPadrao(TipoPagamento pagamentoPadrao) {
        this.pagamentoPadrao = pagamentoPadrao;
    }

}
