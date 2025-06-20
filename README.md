# Descrição
Esse é o sistema de uma livraria que inclui o cadastro de funcionários e clientes, o controle de estoque de livros e o processo de pagamento. 
O controle de estoque envolverá a compra de novos livros para uma fornecedora genérica, incluindo um saldo fictício para a nossa livraria que aumenta com venda de livros para clientes. 
O processo de pagamento envolverá a transação entre o cliente e o estabelecimento e abordará as diferentes forma de pagamento possível, incluindo ou não descontos.
Além disso, será necessário o controle de livros dentro do estabelecimento de acordo com seções para gêneros diferentes.

# Manual de instruções para testes
## Formato do arquivo de input
A primeira coisa a ser feita ao iniciar o código é passar um arquivo .txt contendo as informações que serão utilizadas para o funcionamento do programa. Ele deve estar no formato abaixo, incluindo letras maiusculas e os ':'.
Obs: id deve ser um número.

Cliente: Nome1

Login: usuario1

Senha: senha1

Cliente: Nome2
...

senha: senhaN


Funcionario: Nome1

id: identificador1

Login: usuario1

Senha: senha1
...

Senha: senhaN


Produto: TipoProduto1

Quantidade: numero1

Titulo: titulo1

Preco: preco1

Alugavel: sim/nao

Compravel: sim/nao

Secao: numeroSeção1

Produto: TipoProduto2
...

Secao: numeroSeçãoN
