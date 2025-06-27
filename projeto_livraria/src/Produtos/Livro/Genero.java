package Produtos.Livro;

public enum Genero {
    // Ficção:
    Romance("Romance"),
    Aventura("Aventura"),
    Fantasia("Fantasia"),
    Ficcao("Ficção"),
    Ficcao_Cientifica("Ficção Científica"),
    Terror_Horror("Terror / Horror"),
    Suspense_Thriller("Suspense / Thriller"),
    Misterio("Mistério"),
    Misterio_Policial("Mistério / Policial"),
    Drama("Drama"),

    // Não Ficção:

    Biografia_Autobiografia("Biografia / Autobiografia"),
    Memorias("Memórias"),
    Autoajuda("Autoajuda"),
    Desenvolvimento_pessoal("Desenvolvimento pessoal"),
    Negocios_Empreendedorismo("Negócios / Empreendedorismo"),
    Historia("História"),
    Ciencia("Ciência"),
    Filosofia("Filosofia"),
    Religiao_Espiritualidade("Religião / Espiritualidade"),
    Politica("Política"),
    Sociologia_Antropologia("Sociologia / Antropologia"),
    Psicologia("Psicologia"),
    Educacao("Educação"),
    Saude_Bem_estar("Saúde / Bem-estar"),
    Viagens_Relatos_de_viagem("Viagens / Relatos de viagem"),
    Gastronomia_Culinaria("Gastronomia / Culinária"),
    Nao_Listado("Não Listado");

    private final String genero;

    Genero(String genero) {
        this.genero = genero;
    }

    public String getGenero() {
        return genero;
    }

    public static Genero fromString(String texto) {
        for (Genero genero : Genero.values()) {
            if (genero.getGenero().equalsIgnoreCase(texto)) {
                return genero;
            }
        }
        return Nao_Listado;
    }

}
