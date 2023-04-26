
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import model.ContaBancaria;
import model.ContaComum;
import model.Pessoa;

public class ProjectSI405 {

    static Scanner input = new Scanner(System.in);
    static ArrayList<ContaBancaria> contas;

    public static void main(String[] args) {
        contas = new ArrayList<>();
        operacoes();
    }

    public static void operacoes() {

        System.out.println("------------------------------------------------------");
        System.out.println("-------------Bem vindos a nossa Agencia---------------");
        System.out.println("------------------------------------------------------");
        System.out.println("***** Selecione uma operação que deseja realizar *****");
        System.out.println("------------------------------------------------------");
        System.out.println("|   1 - Criar conta   |");
        System.out.println("|   2 - Depositar     |");
        System.out.println("|   3 - Sacar         |");
        System.out.println("|   4 - Transferir    |");
        System.out.println("|   5 - Listar        |");
        System.out.println("|   6 - Sair          |");

        int operacao = input.nextInt();

        switch (operacao) {
            case 1:
                criarConta();
                break;

            case 2:
                //depositar();
                break;

            case 3:
                //sacar();
                break;

            case 4:
                //transferir();
                break;

            case 5:
                listarContas();
                break;

            case 6:
                System.out.println("Fim!");
                System.exit(0); // para o sistema

            default:
                System.out.println("Opção inválida!");
                operacoes();
                break;
        }
    }

    public static void criarConta() {
        System.out.println("Insira as informacoes do titular da conta:\n");

        System.out.println("Nome: ");
        String nome = input.next();

        System.out.println("\nCPF: ");
        String cpf = input.next();

        System.out.println("\nData de Nascimento: ");
        String nascimento = input.next();

        Pessoa pessoa = new Pessoa(nome, cpf, nascimento);
        
        System.out.println("\nInsira o saldo inicial da conta: ");
        double saldo = input.nextDouble();
        
        System.out.println("\nInsira o limite inicial da conta: ");
        double limite = input.nextDouble();
        
        System.out.println("\nInsira a data de abertura da conta: ");
        String dataAbertura = input.next();
        
        System.out.println("Selecione o tipo de conta a ser criada:");

        escolhaTipoConta(pessoa, saldo, limite, dataAbertura);
    }

    public static void escolhaTipoConta(Pessoa pessoa, double saldo, double limite, String dataAbertura) {
        System.out.println("--------------------------------------------------------------");
        System.out.println("-------------Escolha o tipo de conta do cliente---------------");
        System.out.println("--------------------------------------------------------------");
        System.out.println("**************** Selecione a operação desejada ***************");
        System.out.println("--------------------------------------------------------------");
        System.out.println("|   1 - Conta Comum        |");
        System.out.println("|   2 - Conta Especial     |");
        System.out.println("|   3 - Conta Poupanca     |");

        ContaBancaria conta;
        int operacao = input.nextInt();

        switch (operacao) {
            case 1:
                conta = new ContaComum(dataAbertura, pessoa, saldo, limite);
                System.out.println("Conta Comum criada com sucesso!");
                pessoa.adicionarConta(conta);
                contas.add(conta);
                operacoes();
                break;

            case 2:
                //depositar();
                break;

            case 3:
                //sacar();
                break;

            default:
                System.out.println("Opção inválida!");
                operacoes();
                break;
        }
    }

    public static void listarContas() {
        if (contas.size() > 0) {
            for (ContaBancaria conta : contas) {
                System.out.println("Numero da conta: " + conta.getId());
                System.out.println("Nome do titular: " + conta.getNomePessoa());
                System.out.println("Saldo: " + conta.getSaldo());
                System.out.println("-----------------------");
            }
        } else{
            System.out.println("Nao ha contas cadastradas\n");
        }
    }
}
