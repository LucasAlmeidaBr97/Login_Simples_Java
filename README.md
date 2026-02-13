# Login_Java

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Version](https://img.shields.io/badge/Version-1.0.0-blue?style=for-the-badge)

O Login_Simples_Java é um sistema feito puramente em Java, projetado para gerenciar autenticação e cadastro de usuários via terminal. Na versão atual, o foco funcional está no perfil "Consumer", permitindo que usuários realizem autoatendimento, como visualização de perfil e atualização de dados cadastrais, através de menus interativos no console.

Consulte a **[Descrição Técnica](#descrição-técnica)** para saber mais sobre especificações técnicas.

## Começando

Para obter uma cópia do projeto e executá-lo em sua máquina local, siga os passos abaixo.

### 📋 Pré-requisitos

*   **Java JDK 11** ou superior instalado (Obrigatório). Para instalar o Java JDK 11 ou superior (como 17 ou 21), baixe o instalador adequado ao seu sistema operacional no site da Oracle: https://www.oracle.com/java/technologies/downloads/  Execute o instalador, siga as instruções na tela e aguarde a conclusão.
*   **Git** instalado (Opcional).
*   Uma IDE Java de sua preferência (VS Code, IntelliJ, Eclipse, NetBeans)(Opcional).

### 🔧 Instalação e Execução

**📌 Nota Importante:** Certifique-se de ter o **JDK 11+** instalado. Sem ele, os comandos de execução abaixo não funcionarão.

1.  **Baixe os arquivos**:

    Acesse o repositório no GitHub: https://github.com/LucasAlmeidaBr97/Login_Simples_Java

    Clique no botão verde `Code`.
    Clique em `Download ZIP`.
    Extraia o arquivo no seu computador.

    **Ou Clone o repositório**

    Abra o terminal e execute o comando:
    ```bash
    git clone https://github.com/LucasAlmeidaBr97/Login_Simples_Java.git
    ```
2. **Execute o projeto**

    **`Windows`**:
    Com a JDK 11 ou superior instalada, na raiz do projeto \Login_Simples_Java procure os arquivos
    Login_Simples_Java.bat. Clique nele e pronto. Você pode criar um novo usuário que será um consumidor final. ou usar um usuário existente.

    **Ou** Ainda na raiz do projeto abra o terminal e execute o comando:

        cd Login_Simples_Java
        java -jar Login_Simples_Java.jar
        

    **`Linux ou Mac`**:
        Abra o terminal e execute o comando:

        cd Login_Simples_Java
        java -jar Login_Simples_Java.jar

    **`IDE`**

    **Importe o projeto**:
     Abra sua IDE e selecione a opção para abrir um projeto existente ou pasta, apontando para o diretório `Login_Simples_Java` que foi criado.

    **Execute**:
    Localize a classe principal na pasta `src` (geralmente `App.java`) e execute-a. O sistema interativo será iniciado no console da sua IDE.

📌 **Nota: Usuários Cadastrados**

    E-mail: joao@email.com        Senha: J14613oao.O        #CONSUMER          
    E-mail: roberto@email.com     Senha: Ro2421berto.       #ADMIN 
    E-mail: lucas@email.com       Senha: Luc3132.As         #STOKIST

### 📂 Estrutura de Pastas
```text
src/
├── auth/           # Lógica de autenticação e estratégias
├── repository/     # FactoryDAO e persistência em memória
├── service/        # Regras de negócio
├── ui/             # Interface de usuário (Console)
├── util/           # Validadores e utilitários
└── App.java        # Ponto de entrada
```
[⬆ Voltar ao inicio](#login_java) 
## Descrição Técnica

### 🏛️ Arquitetura e Organização
O projeto adota uma arquitetura em camadas bem definida, promovendo a separação de responsabilidades e facilitando a manutenção:

* **Camada de Apresentação (`ui`)**: Responsável por toda a interação com o usuário. Classes como `ConsumerMenu` e `LoginForm` gerenciam a entrada e saída de dados no terminal, isolando a lógica de exibição da lógica de negócios.
* **Camada de Serviço (`service` e `auth`)**: Contém as regras de negócio. O `AuthService` gerencia a autenticação, enquanto estratégias específicas (`Strategy`) definem como diferentes tipos de registros ou atualizações devem se comportar.
* **Camada de Acesso a Dados (`repository`**): Utiliza o padrão **FactoryDAO (Data Access Object)** para abstrair a persistência dos dados (atualmente em memória), preparando o terreno para futuras integrações com bancos de dados.
* **Camada de Utilitários (`util`)**: Classes auxiliares transversais, como o `Validator`, que centralizam lógicas comuns como validação de regex e formatação de datas.

### Tecnologias e Conceitos de Design
O código demonstra o uso de conceitos avançados de Programação Orientada a Objetos (POO) e recursos modernos do Java:

### Design Patterns (Padrões de Projeto):

* **Strategy**: Utilizado para flexibilizar comportamentos, como visto em `UserRegistrationStrategy` e `ReactivationStrategy`. Isso permite que o sistema trate o cadastro de um novo usuário e a reativação de um usuário antigo de formas distintas, sem encher o código de condicionais `if/else`.
* **Singleton**: Aplicado nos repositórios (`UserDAO.getInstance()`), garantindo que haja apenas uma instância gerenciadora dos dados em memória durante a execução.
* **Command / State (via Mapas)**: No `ConsumerMenu`, a navegação não usa longas cadeias de `switch-case`. Em vez disso, utiliza um mapa de comandos, tornando o código mais limpo e extensível.

### Java Moderno (Lambdas e Method References):

* O projeto faz uso extensivo de **Expressões Lambda e Method References** (ex: `this::profileFlow`). Isso é visível na classe `MenuNavigator`, onde as ações do menu são passadas como funções, permitindo uma navegação dinâmica e declarativa.

### Encapsulamento e Abstração:

* O uso de interfaces (como `Menu`) garante que diferentes tipos de menus (Admin, Consumer) sigam um contrato padrão, facilitando a polimorfia.

## 🔮 Evolução e Roadmap
O projeto encontra-se em estágio ativo de evolução. A estrutura atual foi desenhada para ser escalável:

* **Novos Perfis**: Embora o foco atual seja o consumidor final, a estrutura para **ADMIN** e **STOKIST** (Estoquista) já existe (`AdminMenu`, `UserRole`). As próximas etapas envolvem a implementação das regras de negócio específicas para esses perfis, como gerenciamento de usuários e controle de estoque.
* **AtualPersistência de Dados**: Atualmente, os dados são voláteis (existem apenas enquanto o programa roda). A arquitetura baseada em DAOs foi escolhida propositalmente para facilitar a transição futura para um Banco de **Dados SQL**, onde a implementação em memória será substituída por conexões JDBC ou JPA/Hibernate sem quebrar o restante da aplicação.

[⬆ Voltar ao inicio](#login_java) 
