# UML — ProjetoTAP

Diagrama de classes (atributos + metodos) da aplicacao de gestao da clinica veterinaria.

## Pacote `projetoFinal`

### Main
- `+ static void main(String[] args)`

---

## Pacote `projetoFinal.modelo`

### Clinica `implements Serializable`
- `- ArrayList<Veterinario> veterinarios`
- `- ArrayList<Cliente> clientes`
- `- ArrayList<Animal> animais`
- `- ArrayList<Intervencao> intervencoes`
- `- String dataHoje = "2026-04-23"`
- `- String pastaDados = "dados"`
- `+ Clinica()`
- `+ ArrayList<Veterinario> getVeterinarios()`
- `+ ArrayList<Cliente> getClientes()`
- `+ ArrayList<Animal> getAnimais()`
- `+ ArrayList<Intervencao> getIntervencoes()`
- `+ String getDataHoje()`
- `+ String getPastaDados()`
- `+ void setVeterinarios(ArrayList<Veterinario>)`
- `+ void setClientes(ArrayList<Cliente>)`
- `+ void setAnimais(ArrayList<Animal>)`
- `+ void setIntervencoes(ArrayList<Intervencao>)`
- `+ void setDataHoje(String)`
- `+ void setPastaDados(String)`
- `+ String caminho(String nome)`
- `+ void garantirPastaDados()`
- `+ Veterinario procurarVeterinarioPorNif(String nif)`
- `+ Cliente procurarClientePorNif(String nif)`
- `+ Animal procurarAnimalPorId(int id)`
- `+ Intervencao procurarIntervencaoPorId(int id)`
- `+ Cliente procurarDonoAnimal(int idAnimal)`
- `+ static Veterinario procurarVeterinarioNaLista(ArrayList<Veterinario>, String nif)`
- `+ static Cliente procurarClienteNaLista(ArrayList<Cliente>, String nif)`
- `+ static Animal procurarAnimalNaLista(ArrayList<Animal>, int id)`
- `+ int proximoIdAnimal()`
- `+ int proximoIdIntervencao()`

### Veterinario `implements Serializable`
- `- String nif`
- `- String nome`
- `- String idOrdem`
- `- Contacto contacto`
- `+ Veterinario(String nif, String nome, String idOrdem, Contacto contacto)`
- getters/setters: `getNif`, `setNif`, `getNome`, `setNome`, `getIdOrdem`, `setIdOrdem`, `getContacto`, `setContacto`
- `+ String toString()`

### Cliente `implements Serializable`
- `- String nif`
- `- String nome`
- `- Morada morada`
- `- Contacto contacto`
- `- boolean ativo`
- `- ArrayList<Animal> animais`
- `+ Cliente(String nif, String nome, Morada morada, Contacto contacto, boolean ativo)`
- getters/setters: `getNif`, `setNif`, `getNome`, `setNome`, `getMorada`, `setMorada`, `getContacto`, `setContacto`, `isAtivo`, `setAtivo`, `getAnimais`, `setAnimais`
- `+ void addAnimal(Animal animal)`
- `+ void removeAnimal(Animal animal)`
- `+ String toString()`

### Animal `implements Serializable`
- `- int id`
- `- String nome`
- `- String especie`
- `- String genero`
- `- double peso`
- `- boolean ativo`
- `+ Animal(int id, String nome, String especie, String genero, double peso, boolean ativo)`
- getters/setters: `getId`, `setId`, `getNome`, `setNome`, `getEspecie`, `setEspecie`, `getGenero`, `setGenero`, `getPeso`, `setPeso`, `isAtivo`, `setAtivo`
- `+ String toString()`

### Intervencao `implements Serializable`
- `- int id`
- `- String tipo`
- `- String data`
- `- String hora`
- `- Veterinario veterinario`
- `- Animal animal`
- `- Cliente cliente`
- `- boolean comDeslocacao`
- `- double distanciaKm`
- `+ Intervencao(int id, String tipo, String data, String hora, Veterinario, Animal, Cliente, boolean comDeslocacao, double distanciaKm)`
- getters/setters: `getId`, `setId`, `getTipo`, `setTipo`, `getData`, `setData`, `getHora`, `setHora`, `getVeterinario`, `setVeterinario`, `getAnimal`, `setAnimal`, `getCliente`, `setCliente`, `isComDeslocacao`, `setComDeslocacao`, `getDistanciaKm`, `setDistanciaKm`
- `+ double calcularCusto()`
- `+ String toString()`

### Morada `implements Serializable`
- `- String rua`
- `- int numero`
- `- String codigoPostal`
- `- String localidade`
- `+ Morada(String rua, int numero, String codigoPostal, String localidade)`
- getters/setters: `getRua`, `setRua`, `getNumero`, `setNumero`, `getCodigoPostal`, `setCodigoPostal`, `getLocalidade`, `setLocalidade`
- `+ String toString()`

### Contacto `implements Serializable`
- `- String email`
- `- String telefone`
- `- String metodoPreferido`
- `+ Contacto(String email, String telefone, String metodoPreferido)`
- getters/setters: `getEmail`, `setEmail`, `getTelefone`, `setTelefone`, `getMetodoPreferido`, `setMetodoPreferido`
- `+ String toString()`

---

## Pacote `projetoFinal.menus`

### MenuPrincipal
- `- Scanner sc`
- `- Clinica clinica`
- `- MenuListagens menuListagens`
- `- MenuIntervencoes menuIntervencoes`
- `- MenuFaturacao menuFaturacao`
- `- MenuGestao menuGestao`
- `+ MenuPrincipal(Scanner sc)`
- `+ void executar()`
- `- void mostrarMenu()`
- `- int lerOpcao()`
- `- void executarOpcao(int opcao)`

### MenuListagens
- `- Clinica clinica`
- `- Scanner sc`
- `+ MenuListagens(Clinica clinica, Scanner sc)`
- `+ void executar()`
- `- void mostrarMenu()`
- `- int lerOpcao()`
- `- void executarOpcao(int opcao)`
- `+ void listarVeterinarios()`
- `+ void listarVeterinariosEClientes()`
- `+ void listarVeterinariosEAnimais()`
- `+ void listarClientesEAnimais()`
- `+ void listarAnimaisEDonos()`
- `+ void listarTiposIntervencao()`
- `- boolean existeClienteNaLista(ArrayList<Cliente>, String nif)`
- `- boolean existeAnimalNaLista(ArrayList<Animal>, int id)`

### MenuIntervencoes
- `- Clinica clinica`
- `- Scanner sc`
- `+ MenuIntervencoes(Clinica clinica, Scanner sc)`
- `+ void executar()`
- `- void mostrarMenu()`
- `- int lerOpcao()`
- `- void executarOpcao(int opcao)`
- `+ void listarPorData()`
- `+ void listarDeVeterinario()`
- `+ void listarDeVeterinarioPorData()`
- `+ void listarPassadasAnimal()`
- `+ void listarHojeAnimal()`
- `+ void listarAgendadasAnimal()`
- `- void listarPorTipo(ArrayList<Intervencao> lista)`

### MenuFaturacao
- `- Clinica clinica`
- `- Scanner sc`
- `+ MenuFaturacao(Clinica clinica, Scanner sc)`
- `+ void executar()`
- `- void mostrarMenu()`
- `- int lerOpcao()`
- `- void executarOpcao(int opcao)`
- `+ void listarEfetuada()`
- `+ void listarAgendada()`
- `- void listarFaturacao(ArrayList<Intervencao> lista)`
- `- boolean contemInt(ArrayList<Integer> lista, int valor)`
- `- boolean contemTexto(ArrayList<String> lista, String valor)`

### MenuGestao
- `- Clinica clinica`
- `- Scanner sc`
- `+ MenuGestao(Clinica clinica, Scanner sc)`
- `+ void executar()`
- `- void mostrarMenu()`
- `- int lerOpcao()`
- `- void executarOpcao(int opcao)`
- `+ void inserirVeterinario()`
- `+ void agendarIntervencao()`
- `+ void inserirClienteEAnimal()`
- `+ void inserirAnimalEmCliente()`
- `+ void desativarAnimal()`
- `+ void emitirRecibo()`
- `- Animal lerAnimalNovo()`
- `- boolean clienteTemAnimal(Cliente cliente, int idAnimal)`

---

## Pacote `projetoFinal.leitura`

### Leitor
- `+ static String lerTexto(Scanner sc, String mensagem)`
- `+ static int lerInteiroMinimo(Scanner sc, String mensagem, int minimo)`
- `+ static double lerDoubleMinimo(Scanner sc, String mensagem, double minimo)`
- `+ static double lerDoubleEntre(Scanner sc, String mensagem, double minimo, double maximo)`
- `+ static String lerSimNao(Scanner sc, String mensagem)`

### LeitorTempo
- `+ static String lerData(Scanner sc)`
- `+ static String lerHora(Scanner sc)`

### LeitorIdentificacao
- `+ static String lerNif(Scanner sc, String mensagem)`
- `+ static String lerCodigoPostal(Scanner sc)`

### LeitorContacto
- `+ static Contacto lerContacto(Scanner sc)`
- `+ static String lerEmail(Scanner sc)`
- `+ static String lerTelefone(Scanner sc)`
- `+ static String lerMetodoPreferido(Scanner sc)`

### LeitorIntervencao
- `+ static String lerTipo(Scanner sc)`

---

## Pacote `projetoFinal.validacao`

### Validador
- `+ static boolean textoNumerico(String texto)`
- `+ static boolean dataValida(String data)`
- `+ static boolean horaValida(String hora)`
- `+ static boolean nifValido(String nif)`
- `+ static boolean telefoneValido(String telefone)`
- `+ static boolean codigoPostalValido(String cp)`
- `+ static boolean emailValido(String email)`
- `+ static boolean metodoContactoValido(String metodo)`

---

## Pacote `projetoFinal.regras`

### TiposIntervencao
- `+ static ArrayList<String> obter()`
- `+ static boolean valido(String tipo)`

### Disponibilidade
- `+ static double duracaoIntervencao(String tipo)`
- `+ static int horaParaMinutos(String hora)`
- `+ static boolean veterinarioDisponivel(Clinica, Veterinario, String data, String hora, String tipo, boolean comDeslocacao, double distanciaKm)`

---

## Pacote `projetoFinal.exibicao`

### Exibir
- `+ static void mostrarVeterinariosDisponiveis(Clinica clinica)`
- `+ static void mostrarClientesDisponiveis(Clinica clinica)`
- `+ static void mostrarAnimaisDoCliente(Cliente cliente)`

---

## Pacote `projetoFinal.persistencia.txt`

### GestorTxt
- `+ static void gravar(Clinica clinica)`
- `+ static boolean ler(Clinica clinica)`
- `- static boolean todosExistem(Clinica clinica)`

### GestorVeterinariosTxt
- `+ static void gravar(String caminho, ArrayList<Veterinario> lista) throws IOException`
- `+ static ArrayList<Veterinario> ler(String caminho) throws IOException`

### GestorClientesTxt
- `+ static void gravar(String caminho, ArrayList<Cliente> lista) throws IOException`
- `+ static ArrayList<Cliente> ler(String caminho) throws IOException`

### GestorAnimaisTxt
- `+ static void gravar(String caminho, ArrayList<Animal> animais, Clinica clinica) throws IOException`
- `+ static ArrayList<Animal> ler(String caminho, ArrayList<Cliente> clientes) throws IOException`

### GestorIntervencoesTxt
- `+ static void gravar(String caminho, ArrayList<Intervencao> lista) throws IOException`
- `+ static ArrayList<Intervencao> ler(String caminho, ArrayList<Veterinario>, ArrayList<Cliente>, ArrayList<Animal>) throws IOException`

---

## Pacote `projetoFinal.persistencia.dat`

### GestorDat
- `+ static void gravar(Clinica clinica)`
- `+ static boolean ler(Clinica clinica)`

---

## Pacote `projetoFinal.persistencia.mock`

### DadosIniciais
- `- static final String PASTA_MOCK = "mock"`
- `+ static void popular(Clinica clinica)`
- `- static boolean jaTemDados(Clinica clinica)`
- `- static String caminhoMock(String nome)`

---

## Relacoes principais

- `Clinica` *1* — *N* `Veterinario`, `Cliente`, `Animal`, `Intervencao`
- `Cliente` *1* — *N* `Animal` (lista propria, sincronizada via `addAnimal` / `removeAnimal`)
- `Cliente` *1* — *1* `Morada`, `Contacto`
- `Veterinario` *1* — *1* `Contacto`
- `Intervencao` *N* — *1* `Veterinario`, `Animal`, `Cliente`
- Menus (`MenuPrincipal`, `MenuListagens`, `MenuIntervencoes`, `MenuFaturacao`, `MenuGestao`) usam a mesma instancia de `Clinica` e o mesmo `Scanner`
- Camadas:
  - `menus` → `leitura`, `validacao`, `regras`, `exibicao`, `modelo`
  - `persistencia.txt` / `persistencia.dat` / `persistencia.mock` → `modelo`
  - `regras`, `validacao`, `exibicao` → `modelo`
