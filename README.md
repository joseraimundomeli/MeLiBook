<h1 align="center">Social Meli</h1>

<p align="center"> Sistema Backend utilizando Java Spring para provê uma API-Rest para uma rede social ficticia entre clientes e vendedores do Mercado Livre</p>

### Pré-requisitos 

#### Ambiente de desenvolvimento
Para execução do projeto de forma mais clara e agradável, é recomendado ter instalado as seguintes ferramentas:

 - [InteliJ](https://www.jetbrains.com/pt-br/idea/)

 - [Git](https://git-scm.com/book/pt-br/v2/Come%C3%A7ando-Instalando-o-Git)
 - [Kit de desenvolvimento Java](https://www.oracle.com/br/java/technologies/javase-jdk11-downloads.html)
 - [Postman](https://www.postman.com/)

### Modo de usar
#### Interface de acesso ao banco de teste H2

Faca o download do projeto por meio da ferramenta git, utilizando o comando:

	git clone https://github.com/joseraimundomeli/desafio_spring.git

Após isso, abra o projeto utilizando o InteliJ, após carregar as dependências (O InteliJ fará isso automaticamente), execute o projeto e acesse o console do banco em:
	
	http://localhost:8080/h2

Clique no ícone de **Connect** e você será direcionado para o console SQL.

#### Importante

O banco de dados H2 é uma ferramenta para testes normalmente utilizada em ambientes de desenvolvimento, por isso ela armazena os dados em um arquivo, este arquivo já se encontra populado no projeto que está neste repositório, porém caso tenha dificuldades é só remover o arquivo **desafio_spring/ SocialMeli/data/data.mv.db** e executar o seguintes passos:

 1. Abrir o console SQL no H2.
 2. Copiar o conteúdo do arquivo  **script-dataset.sql**, que está presente neste repositório.
 3. Colar no console.
 4. Clicar no ícone executar do H2.

Por este console também é possível verificar as alterações no banco por meio de consultas **select**.

### Testando os Endpoints

Neste diretório você encontrará um arquivo chamado **SocialMeli.postman_collection.json**, que contem as configurações e end-points pré definidos para utilizar na ferramenta Postman, para isso basta abrir a ferramenta e importar o arquivo: Home -> Import an existing file. Pronto, agora é só utilizar.

