package Produtos;

public enum Propriedade {
    ;

    String nomePropriedade;
    String valorPropriedade;

    Propriedade(String nomePropriedade, String valorPropriedade) {
        this.nomePropriedade = nomePropriedade;
        this.valorPropriedade = valorPropriedade;
    }

    public String getNomePropriedade() {
        return nomePropriedade;
    }

    public void setNomePropriedade(String nomePropriedade) {
        this.nomePropriedade = nomePropriedade;
    }

    public String getValorPropriedade() {
        return valorPropriedade;
    }

    public void setValorPropriedade(String valorPropriedade) {
        this.valorPropriedade = valorPropriedade;
    }
}
