# Sistema de Gerenciamento de Livraria

## Sumário
- [Integrantes do Grupo](##-integrantes-do-grupo)
- [Descrição Geral](##-descrição-geral)
- [Manual de Instruções e Testes](##-manual-de-instruções-e-testes)
  - [Formato do Arquivo de Entrada](##formato-do-arquivo-de-entrada)
  - [Menus do Sistema](##menus-do-sistema)
- [Estrutura do Projeto](##-estrutura-do-projeto)
- [Como Compilar e Executar](##-como-compilar-e-executar)

## Integrantes do grupo

| Nome                     | RA      |
|--------------------------|---------|
| Arthur Costa Barreto     | 206325  |
| Vitor Benaglia Albertini | 206645  |
| Eduardo Pereira Tejada   | 183451  |

## Descrição
Esse é o sistema de uma livraria que inclui o cadastro de funcionários e clientes, o controle de estoque de livros e o processo de pagamento. 
O controle de estoque envolverá a compra de novos livros para uma fornecedora genérica, incluindo um saldo fictício para a nossa livraria que aumenta com venda de livros para clientes. 
O processo de pagamento envolverá a transação entre o cliente e o estabelecimento e abordará as diferentes forma de pagamento possível.
Além disso, será necessário o controle de livros dentro do estabelecimento de acordo com seções para gêneros diferentes.

## Manual de instruções para testes
### Formato do arquivo de input
A primeira coisa a ser feita ao iniciar o código é passar um arquivo .txt contendo as informações que serão utilizadas para o funcionamento do programa. Ele deve estar no formato abaixo, incluindo letras maiusculas e os ':'. Todas as informações sobre um objeto devem estar na mesma linha e a linha deve conter cada uma das informações descritas abaixo na ordem em que estão descritas. Como o formato é específico, há um arquivo txt de teste no caminho ```"projeto_livraria/src/arquivos/teste.txt"```

Cliente: Nome1, Login: usuario1, Senha: senha1, CPF: cpf1, dataNascimento: dd/mm/aaaa, email: email1

Cliente: Nome2, Login: usuario2, Senha: senha2, CPF: cpf2, dataNascimento: dd/mm/aaaa, email: email2

...

Gerente: Nome, Login: usuario, Senha: senha1, CPF: cpf, dataNascimento: dd/mm/aaaa, email: email1

Funcionario: Nome1, Login: usuario1, Senha: senha1, CPF: cpf1, dataNascimento: dd/mm/aaaa, email: email1

Funcionario: Nome2, Login: usuario2, Senha: senha2, CPF: cpf2, dataNascimento: dd/mm/aaaa, email: email2

...

Produto: TipoProduto1, Nome: nome1, ID: id1, Quantidade: 2, Preco 55.20, Secao: 20, Genero: Fantasia; Ficcao; ...
...

### Menu Principal
No menu principal, existem 3 opções a seguir: 
- Criar um novo cadastro (login só vai ser aceito se não existir um igual);
- Fazer login em um dos perfis já cadastrados no arquivo de input;
- Encerrar o programa. (É preferível que o programa seja encerrado por meio do botão "Sair" na medida que este salva alterações feitas em certas áreas, como o estoque)

#### Menu do Usuário
No menu do usuário, você poderá interagir com o sistema da livraria para busca de produtos. Você pode verificar os livros, filmes ou produtos gerais da nossa 
livraria ou buscar um deles especificamente pelo nome. Além disso poderá adicionar um novo cartão que será associado ao perfil do cliente ou voltar ao menu principal.

#### Menu do Gerente
No menu do gerente, você poderá registrar novos produtos para a nossa biblioteca ou alterar a quantidade disponível dos já existentes. Além disso, poderá adicionar funcionários novos ou remover funcionários do sistema.

#### Menu do Funcionário
No menu do Funcionário, você poderá realizar compras e vendas dos produtos cadastrados, ou seja, um funcionário pode realizar compras de produtos para a livraria e pode realizar vendas para clientes. Durante a venda há a possibilidade de registro de um cliente caso esse não esteja cadastrado no sistema. As compras e vendas são registradas no arquivo Transações.txt.

## Estruta do projeto
```
MC322_final
├── README.md
└── projeto_livraria
    ├── projeto_livraria.iml
    └── src
        ├── FileInputReader.java
        ├── Gerencia
        │   ├── Caixa
        │   │   ├── Caixa.java
        │   │   └── CompraVenda.java
        │   ├── Estoque
        │   │   └── GerenciadorEstoque.java
        │   └── GerenciadorGeral.java
        ├── Main.java
        ├── Produtos
        │   ├── Filme
        │   │   └── Filme.java
        │   ├── Generico
        │   │   └── Generico.java
        │   ├── Genero.java
        │   ├── Livro
        │   │   └── Livro.java
        │   └── Produto.java
        ├── Teste.java
        ├── arquivos
        │   ├── Estoque.txt
        │   └── teste.txt
        ├── diagrama_livraria.drawio
        ├── excecoes
        │   ├── LivroNaoDisponivel.java
        │   ├── ProdutoNaoEncontrado.java
        │   ├── SaldoInsuficiente.java
        │   └── SemEstoque.java
        ├── interface_swing
        │   ├── MenuFuncionario.java
        │   ├── MenuGerente.java
        │   ├── MenuInicial.java
        │   └── MenuUsuario.java
        ├── pagamento
        │   ├── Cartao.java
        │   ├── TipoCartao.java
        │   └── TipoPagamento.java
        └── pessoa
            ├── Cliente.java
            ├── Funcionario.java
            ├── Gerente.java
            ├── Pessoa.java
            └── UserManager.java
```

## Como compilar e executar nosso código
## 1. Vá para o diretório onde salvou o projeto
Para isso, no terminal vá até:
```bash
cd caminho/para/MC322_final
```
substituindo caminho/para pelo caminho real em seu sistema
### 2. Compile o código
```bash
find projeto_livraria/src -name "*.java" > sources.txt
mkdir -p out
javac -d out @sources.txt
```
### 3. Execute-o
```bash
java -cp out Main
```
