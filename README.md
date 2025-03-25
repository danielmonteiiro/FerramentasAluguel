# Feramentas Aluguel

## Descrição

Este projeto é uma aplicação que gerencia o aluguel de ferramentas, permitindo que usuários realizem cadastros de clientes, ferramentas e reservas de locação. O sistema é desenvolvido em Java com interface gráfica usando Java Swing.

## Banco de Dados

A aplicação utiliza o MySQL como sistema de gerenciamento de banco de dados. As principais tabelas incluem:

- **Clientes**: armazena informações sobre os clientes, como nome, CPF, e contato.
- **Ferramentas**: contém dados sobre as ferramentas disponíveis para aluguel, incluindo nome, categoria, e preço.
- **Reservas**: registra as locações feitas pelos clientes, incluindo a data de locação e o valor total.

## Tecnologias Utilizadas

- **Java**: linguagem de programação principal
- **Java Swing**: biblioteca para criação da interface gráfica
- **MySQL**: banco de dados utilizado para persistência de dados
## Funcionalidades

A aplicação oferece as seguintes funcionalidades:

### 1. Cadastro de Clientes
- Permite adicionar novos clientes ao sistema.
- Os dados exigidos incluem nome, CPF, e informações de contato (telefone e e-mail).
- Realiza validações de entrada, como formato de CPF e e-mail.

### 2. Cadastro de Ferramentas
- Possibilita o registro de novas ferramentas para aluguel.
- Informações necessárias incluem nome, categoria, descrição e preço diário.
- Valida se os dados informados estão corretos.

### 3. Realização de Reservas
- Os usuários podem criar reservas para locar ferramentas.
- O sistema permite selecionar o cliente e as ferramentas a serem alugadas, além de especificar as datas de locação.
- Calcula o preço total com base na quantidade de dias de aluguel.

### 4. Listagem de Clientes e Ferramentas
- Apresenta listas de todos os clientes e ferramentas cadastrados.
- Oferece a capacidade de pesquisar clientes e ferramentas pelo nome.

### 5. Edição e Exclusão
- Permite a edição das informações de clientes e ferramentas existentes.
- Os usuários podem excluir registros de clientes e ferramentas do sistema.

### 6. Visualização de Reservas
- Os usuários podem visualizar todas as reservas ativas, com detalhes sobre o cliente, ferramentas locadas e datas.
- Fornece uma visão geral das locações em andamento.
