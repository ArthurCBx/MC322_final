import pessoa.Funcionario;
import pessoa.Pessoa;

public class Teste {
    public static void main(String[] args) {
        Pessoa funcionario = new Funcionario("328801007-20", 20, "01/01/2003", "abd@gmail.com", "222",21999, "abd", "123456");
        UserManager.writeClientToFile(funcionario);

    }
}
