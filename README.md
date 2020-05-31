# Projeto-Final-PCD
Projeto final da disciplina de Programação Concorrente e Distribuida.

Seu objetivo é implementar um sistema que serve como central para fornecedores de produtos de um grande site varejista. Nesse sistema, que funciona com uma arquitetura cliente-servidor​, onde todos os dados são guardados do lado de ​Servidor​. Os usuários terão as seguintes opções dentro da central:
● Cadastro de Produtos para venda;
● Remoção de um Produto à venda;
● Listagem de todos os Produtos que estão à venda no momento pelo usuário;
● Conversar com outros fornecedores via Chat Online para discussão sobre vendas/estratégias. Esse Chat é público e qualquer um pode entrar.

Usuário

Um usuário é criado assim que o Cliente se conectar com o Servidor. Esse usuário deverá ter:
● Nome de Usuário;
● Número Identificador (único); (gerado aleatoriamente pelo sistema)
● Coleção (array) de produtos atualmente cadastrados. (no começo, a coleção é
vazia)
Usuário deve ser cadastrado assim que a conexão cliente-servidor seja concretizada via Socket.
Produto
Produto é constituído de:
● Nome;
● Quantidade Disponível em Estoque;
● Código único do Produto;
O usuário poderá cadastrar produtos assim que o cadastro de usuário seja concluído, exibindo um Menu com as seguintes opções:
● Cadastrar um Produto;
● Listar todos os produtos cadastrados para o usuário logado;
● Remover um produto (usando o código único do produto).
● Entrar em contato com a Rede de Fornecedores (Chat).

Fluxo do Sistema

Você ​pode utilizar de ​Threads para execução de procedimentos de cadastro de novos Clientes se conectando ao servidor.

Chat

O chat deve ser chamado no momento em que o usuário selecionar a opção no Menu. Ele irá se conectar a um servidor que estará pronto para fazer todas as operações de Chat
(envio e recepção de mensagens). O nome de usuário deve ser o nome do objeto ​Usuário​. Você ​deve utilizar de ​Threads para execução de procedimentos de novos Clientes se conectando ao servidor.

O Chat pode ser implementado usando ​Java Swing​, ​Console​ ou ​JOptionPane​.

Interface

A interface do Projeto Final poderá ser implementada usando Console, Java Swing ou JOptionPane. Fica à escolha do programador. Lembrando que as opções de escolha devem estar disponíveis independentemente do tipo de interface (botões, números digitados, etc).



