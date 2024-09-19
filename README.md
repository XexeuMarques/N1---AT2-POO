## Como Usar o Sistema
### Compilar o Projeto: 
Va até a pasta raiz do projeto e compile todos os arquivos .java. Isso pode ser feito executando o seguinte comando no terminal:  
```
javac -d bin -sourcepath src src/view/MainView.java
```
O diretório src contém os arquivos .java, e o diretório bin conterá os arquivos compilados.

### Executar o Sistema: 
Após a compilação, você pode executar o sistema com o seguinte comando:
```
java -cp bin view.MainView
```

---
## Aspectos
O projeto foi organizado no padrão **MVC** (Model-View-Controller), que separa a lógica de negócio, a apresentação e o gerenciamento de dados de maneira modular e independente. 
Isso melhora a escalabilidade e a manutenção do código

- **Model**: As classes `Funcionario` e `Avaliacao` representam os objetos principais do sistema, cada um contendo atributos e métodos relacionados à manipulação de dados.
- **View**: A classe `MainView` é responsável pela interface de texto (console/terminal) que interage com o usuário.
- **Controller**: A classe `FuncionarioController` centraliza a lógica de negócio, como o gerenciamento de funcionários e avaliações.

### Estrutura de Pastas:

- **src/model**: Contém as classes de dados (`Funcionario`, `Avaliacao`).
- **src/view**: Contém a interface de interação com o usuário (`MainView`).
- **src/controller**: Contém a lógica de negócios e as operações *CRUD* (`FuncionarioController`).

---
## Tópicos abordados pelo professor na ultima aula
### **Construtores**
Os **construtores** são métodos especiais usados para inicializar objetos. No código, cada classe (`Funcionario` e `Avaliacao`) possui dois construtores, permitindo flexibilidade na criação dos objetos.
#### **Construtores na Classe `Funcionario`**:

- **Construtor com Todos os Atributos**
``` java
 public Funcionario(String nome, String cargo, double salario)
```

- **Construtor Sem Salário**:
``` java
public Funcionario(String nome, String cargo)
```

#### **Construtores na Classe `Avaliacao`**:

- **Construtor com Nota e Data**:
``` java
public Avaliacao(double nota, LocalDate data)
```

- **Construtor com Nota (usa a Data Atual)**:
``` java
public Avaliacao(double nota)
```

Esses construtores garantem flexibilidade ao criar objetos de acordo com os dados disponíveis no momento.

### **Métodos Getters e Setters**
Os **getters** e **setters** são métodos usados para acessar e modificar os atributos de um objeto. Eles seguem a convenção de encapsulamento, o que significa que os atributos são privados e acessados de maneira controlada.

**Exemplo do funcionário**:
``` java
public String getNome() {
    return nome;
}

public String getCargo() {
    return cargo;
}

public double getSalario() {
    return salario;
}
```

- Esses métodos permitem que o nome, cargo e salário de um funcionário sejam lidos, mas não diretamente alterados, o que ajuda a manter a integridade dos dados.
- **Setters**: Neste exemplo, não criamos setters porque os dados de `Funcionario` são definidos no momento da criação e não são alterados diretamente. Isso é uma escolha de design para garantir que os dados permaneçam imutáveis depois de definidos.

### **Modificadores de Acesso** (encapsulação)
Os **modificadores de acesso** controlam a visibilidade de classes, atributos e métodos. Eles garantem a encapsulação, limitando o acesso direto a certos componentes do código.

 **Modificadores Usados**:
- **`private`**: Atributos como `nome`, `cargo`, e `salario` na classe `Funcionario` e `nota`, `data` na classe `Avaliacao` são marcados como `private`. Isso significa que só podem ser acessados ou modificados indiretamente através de métodos públicos (getters e setters).

Exemplo:
``` java
private String nome;
private double salario;
```

- **`public`**: Construtores, getters, setters e métodos da classe (como `adicionarAvaliacao`) são `public`, permitindo seu acesso de fora da classe.

Exemplo:
``` java
public Funcionario(String nome, String cargo) {
}
```

---
## Uma pequena ideia de defesa do codigo:
Este sistema foi desenvolvido com uma arquitetura simples e modular baseada no padrão **MVC**, promovendo separação de responsabilidades e facilitando a escalabilidade e manutenção. A implementação de **construtores** flexíveis e **modificadores de acesso** garante a integridade dos dados, permitindo apenas operações controladas no sistema
.
- **Validação de dados**: As notas das avaliações são validadas para garantir que estejam entre 0 e 10. Caso contrário, uma exceção é lançada.
- **Extensibilidade**: O código pode ser facilmente expandido, por exemplo, adicionando funcionalidades como a remoção ou edição de avaliações.
- **Boa prática de encapsulamento**: O uso de getters e setters permite um acesso seguro aos atributos das classes, evitando modificações diretas indesejadas.

O código também foi desenhado para ser **claro e eficiente**, utilizando estruturas de repetição e condicionais (`switch-case`, `if-else`) de forma apropriada para interações com o usuário e classificação de avaliações.

Este projeto fornece uma base sólida para um sistema de avaliação de funcionários, com boas práticas de programação, arquitetura modular e flexibilidade de uso. Ele pode ser facilmente adaptado e melhorado
