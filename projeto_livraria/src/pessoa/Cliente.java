package pessoa;

import pagamento.Cartao;
import pagamento.TipoPagamento;

public class Cliente extends Pessoa{
    private Cartao cartao;
    private String endereco;
    private TipoPagamento pagamentoPadrao = TipoPagamento.CARTAO;

    public Cliente(String nome, String CPF, String data_nascimento, String email, String login, String senha, Cartao cartao, String endereco) {
        super(nome, CPF, data_nascimento, email, login, senha);
        this.cartao = cartao;
        this.endereco = endereco;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public TipoPagamento getPagamentoPadrao() {
        return pagamentoPadrao;
    }

    public void setPagamentoPadrao(TipoPagamento pagamentoPadrao) {
        this.pagamentoPadrao = pagamentoPadrao;
    }

}
