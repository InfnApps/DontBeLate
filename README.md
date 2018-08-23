# DontBeLate


Descrição do nosso aplicativo de presença segmentado em tarefas


1. Usuário abre o aplicativo e  à telé apresentadoa de Login.
 

Tela de login possui:

- Dois campos para inserir informação (email e senha)

- Um botão para “entrar/logar”

- Um botão/texto não editável que leva à tela de cadastro

- Um botão/texto não editável que leva à tela de recuperação de senha

 

Em termos de funcionalidade, essa tela deve ser capaz de iniciar outras Activities (a depender da viewclicada); também deve ser capaz de verificar se as credenciais do usuário estão corretas (email e senha batem com os cadastrados) e avançar para a tela seguinte ou dar um feedback da falha de autenticação ao usuário.

 

** Este é o tipo de tarefa que eu espero que todo mundo seja capaz de fazer atualmente. Com exceção da validação de credenciais, todo o resto foi cobrado nos ATs das disciplinas anteriores (interface, clicar em botões, abrir outra tela...). Para validar autenticação, é necessário ter usuários cadastrados primeiro; para testar essa funcionalidade, você pode, por exemplo, criar 2 variáveis no meio do código com um valor de email e de senha fictícios para fazer o papel do email e senha cadastrados.
 
---------------------------------------------------------------
2. Usuário não cadastrado decide se cadastrar

 

Tela de cadastro: 

- Pode ser semelhante à tela de login.

- Pode-se acrescentar um campo de repetição (da senha, por exemplo).

- Deve-se remover itens que não fazem sentido estarem aqui em relação à tela de login como, por exemplo, um botão para a tela de cadastro (ela mesma).

 

Em termos de funcionalidade, essa tela deve validar os campos inseridos pelo usuário e armazenar em alguma base de dados, que um novo usuário foi criado; em sala, utilizei a SharedPreferences para armazenar esses dados. Depois de criar o usuário, deve-se levá-lo para outra tela e aqui há uma decisão de projeto a ser feita: retorna-se à tela de login ou avança-se direto para a tela principal? 

 

Após implementar o cadastro, você pode voltar à tela de login e refatorar, refazer, o trecho de autenticação. Ao invés de usar aquelas variáveis de valor fictício criadas para teste, você pode recuperar o valor de fato para comparação. Se conseguir fazer funcionar com os valores em claro, tente modificar o cadastro para armazenar o hashda senha (e possivelmente o hash do email) e depois modifique novamente o login para comparar os hashes.

 

** Eu também espero que vocês sejam capazes de implementar essa tarefa, embora aceite que alguns possam ter dificuldade com o armazenamento dos dados. É importante perceber que as “preferências compartilhadas”ou SharedPreferences armazenam dados em uma estrutura de {chave: valor}  com um uso semelhante a quando você quer passar dados de uma Activity pra outra por meio de uma Intent. Em sala, usei o email como chave e a senha como valor. Dado o TP1 da disciplina “Desenvolvimento Java Android”, você poderia também armazenar em um arquivo, mas eu acho que será mais complexo para o que é preciso.
 
---------------------------------------------------------------
3. Validar o cadastro com o Moodle

Nesta etapa, você deve utilizar o email e a senha digitados pelo usuário para fazer uma requisição ao Moodle e tentar obter o token de autenticação. Caso não consiga obter o token, você não deve cadastrar o usuário. Perceba que este fluxo de validação obriga o usuário a usar a mesma senha do moodle no aplicativo. Para poder usar uma senha diferente, seria necessário requisitar a senha do moodle explicitamente ao usuário em outro campo da Activity de cadastro ou mesmo em outro componente.

A base do código de acesso ao Moodle encontra-se em: 

https://github.com/InfnApps/DontBeLate/blob/login/app/src/main/java/br/edu/infnet/dontbelate/NetworkActivity.java
Nesse código, nós gerenciamos as Threads manualmente. Contudo, a plataforma Android nos disponibiliza uma outra forma de realizar tarefas de longa duração com a classe AsyncTask. 

(https://stackoverflow.com/questions/25647881/android-asynctask-example-and-explanation/25647882#25647882)

Referências sobre o Moodle podem ser encontradas em:
(https://sites.google.com/prof.infnet.edu.br/profpaulomarinho/p%C3%A1gina-inicial/frameworks-front-end/typescript/api-moodle-necess%C3%A1rio-para-assessment)


* Esta parte é a novidade da última aula e tudo bem se não conseguir implementá-la de cara, mas você deve tentar. É neste momento que você irá se deparar com o problema das threads. Fazer uma chamada ao Moodle pode levar vários segundos, por conta da internet. Se nós escrevêssemos o código como sempre, ao chegar na linha de requisição do dado, o programa ficaria parado até terminar de executá-la. O programa inteiro ficaria parado, nada mais poderia ser feito durante esse tempo, inclusive responder às interações do usuário.
 

 4. Tela principal e NavigationDrawer

Nesta etapa, você deve preparar a tela principal do aplicativo com a infraestrutura que precisaremos. Nosso objetivo é exibir pelo menos 2 tipos de informações:

i) Registros de presença do aluno
ii) Perfil do aluno

Nós alternaremos entre essas duas opções usando um Navigation Drawer. Por padrão, o usuário deve ser apresentado ao registro de presença (parte mais importante do App). Neste momento, você pode implementar apenas uma tela com um TextView no meio escrito "Registro de Presença", nós faremos uma lista em etapas seguintes. Em seguida, implemente um NavigationDrawer com um layout de cabeçalho simples e um menu que tenha pelo menos as seguintes opções:

i) Perfil
ii) Registros de presença
iii) Sair

O NavigationDrawer deve ser sincronizado com o "menu hambúrguer" no canto superior esquerdo da aplicação.

* Dado a AT da disciplina de Design de Interfaces, também espero que todo mundo seja capaz de implementar esta etapa por completo. 

5. Tela de perfil

Ao clicar na opção "Perfil" do Navigation Drawer, o usuário deve ser direcionado à tela de perfil. Até o momento, o único dado que temos é o email, que deve ser exibido já pré-preenchido. Nesta tela, deve haver um botão que leve o usuário para a tela de edição de perfil e o permita completar suas informações.

Dados mínimos para perfil:

i) Nome
ii) Curso (graduação)
iii) Bloco atual

Os dados do usuário podem ser salvos em Preferências Compartilhadas ou em um arquivo de texto.

6. Tela de edição de perfil 

Nesta tela, o usuário deve conseguir editar as informações de perfil (exceto o email). O perfil deve ser exibido atualizado após isso (você pode passar informações entre as Activities ou escrever e ler arquivos para isso).

7. Registro de presença

A tela de registro de presenças exibe uma lista com CardViews. Cada card exibe informações de presença de 1 dia de aula. A lista deve ser implementada com uma RecyclerView. Nós implementamos uma RecyclerView em sala para esse uso e você pode utilizá-la como referência (é um ponto que pode causar várias dúvidas).

* Tudo bem ter dificuldades nesta parte. Talvez você esteja mais habituado(a) a lidar com uma ListView. Contudo, a RecyclerView é mais moderna e flexível e, por isso, nós a usaremos neste projeto. Esta é uma oportunidade de você obter um conhecimento mais atualizado e tirar dúvidas a respeito.
