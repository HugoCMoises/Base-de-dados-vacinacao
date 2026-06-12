import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private PacienteDAO pacienteDAO = new PacienteDAO();
    private VacinaDAO vacinaDAO = new VacinaDAO();
    private AplicacaoDAO aplicacaoDAO = new AplicacaoDAO();

    private ArrayList<Paciente> pacientes = new ArrayList<>();
    private ArrayList<Vacina> vacinas = new ArrayList<>();
    private ArrayList<Aplicacao> aplicacoes = new ArrayList<>();

    // Mostra o menu
    public void iniciar() {
        int opcao;

        do {
            System.out.println("\n===== SISTEMA DE VACINACAO =====");
            System.out.println("1 - Cadastrar paciente");
            System.out.println("2 - Consultar paciente");
            System.out.println("3 - Listar pacientes");
            System.out.println("4 - Cadastrar vacina");
            System.out.println("5 - Registrar vacinacao");
            System.out.println("6 - Consultar historico de vacinacao");
            System.out.println("7 - Relatorios");
            System.out.println("8 - Consultas estatisticas");
            System.out.println("9 - Sair");
            System.out.print("Escolha uma opcao: ");
            opcao = lerInteiro();

            switch (opcao) {
                case 1:
                    cadastrarPaciente();
                    break;
                case 2:
                    consultarPaciente();
                    break;
                case 3:
                    pacienteDAO.listarPacientes();
                    break;
                case 4:
                    cadastrarVacina();
                    break;
                case 5:
                    registrarVacinacao();
                    break;
                case 6:
                    consultarHistorico();
                    break;
                case 7:
                    aplicacaoDAO.mostrarRelatorios();
                    break;
                case 8:
                    aplicacaoDAO.mostrarConsultasEstatisticas();
                    break;
                case 9:
                    System.out.println("Sistema encerrado.");
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
        } while (opcao != 9);
    }

    private void cadastrarPaciente() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Idade: ");
        int idade = lerInteiro();
        System.out.print("Sexo: ");
        String sexo = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Endereco: ");
        String endereco = scanner.nextLine();
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();

        mostrarRegioes();
        System.out.print("ID da regiao: ");
        int regiaoId = lerInteiro();

        mostrarEscolaridades();
        System.out.print("ID da escolaridade: ");
        int escolaridadeId = lerInteiro();

        System.out.print("Esta doente? (S/N): ");
        boolean doente = scanner.nextLine().equalsIgnoreCase("S");

        Paciente paciente = new Paciente(nome, idade, sexo, cpf, telefone, endereco, cidade,
                regiaoId, escolaridadeId, doente);

        pacientes.add(paciente);
        pacienteDAO.salvarPaciente(paciente);
    }

    private void consultarPaciente() {
        System.out.print("Digite o CPF do paciente: ");
        String cpf = scanner.nextLine();
        pacienteDAO.buscarPaciente(cpf);
    }

    private void cadastrarVacina() {
        System.out.print("Nome da vacina: ");
        String nome = scanner.nextLine();
        System.out.print("Fabricante: ");
        String fabricante = scanner.nextLine();
        System.out.print("Tipo: ");
        String tipo = scanner.nextLine();
        System.out.print("Quantidade de doses: ");
        int quantidadeDoses = lerInteiro();
        System.out.print("Lote: ");
        String lote = scanner.nextLine();
        System.out.print("Data de aplicacao padrao (AAAA-MM-DD): ");
        LocalDate dataAplicacao = LocalDate.parse(scanner.nextLine());

        Vacina vacina = new Vacina(nome, fabricante, tipo, quantidadeDoses, lote, dataAplicacao);
        vacinas.add(vacina);
        vacinaDAO.salvarVacina(vacina);
    }

    private void registrarVacinacao() {
        System.out.print("ID do paciente: ");
        int pacienteId = lerInteiro();
        System.out.print("ID da vacina: ");
        int vacinaId = lerInteiro();
        System.out.print("Dose: ");
        int dose = lerInteiro();
        System.out.print("Data (AAAA-MM-DD): ");
        LocalDate data = LocalDate.parse(scanner.nextLine());
        System.out.print("Observacoes: ");
        String observacoes = scanner.nextLine();

        Aplicacao aplicacao = new Aplicacao(pacienteId, vacinaId, dose, data, observacoes);
        aplicacoes.add(aplicacao);
        aplicacaoDAO.registrarAplicacao(aplicacao);
    }

    private void consultarHistorico() {
        System.out.print("ID do paciente: ");
        int pacienteId = lerInteiro();
        aplicacaoDAO.consultarHistorico(pacienteId);
    }

    private int lerInteiro() {
        while (!scanner.hasNextInt()) {
            System.out.print("Digite um numero valido: ");
            scanner.nextLine();
        }

        int numero = scanner.nextInt();
        scanner.nextLine();
        return numero;
    }

    private void mostrarRegioes() {
        System.out.println("Regioes cadastradas:");
        System.out.println("1 - Norte");
        System.out.println("2 - Nordeste");
        System.out.println("3 - Centro-Oeste");
        System.out.println("4 - Sudeste");
        System.out.println("5 - Sul");
    }

    private void mostrarEscolaridades() {
        System.out.println("Escolaridades cadastradas:");
        System.out.println("1 - Fundamental incompleto");
        System.out.println("2 - Fundamental completo");
        System.out.println("3 - Ensino medio");
        System.out.println("4 - Ensino superior");
        System.out.println("5 - Pos-graduacao");
    }
}
