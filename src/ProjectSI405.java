
import java.util.ArrayList;
import java.util.Scanner;
import model.ContaBancaria;
import model.ContaBancaria.TipoConta;
import model.ContaComum;
import model.ContaEspecial;
import model.ContaPoupanca;
import model.Pessoa;

public class ProjectSI405 {

    static Scanner input = new Scanner(System.in);
    static ArrayList<ContaBancaria> contas;
    static ArrayList<Pessoa> pessoas;

    public static void main(String[] args) throws ContaBancaria.SaldoOperacaoInsuficienteException, ContaBancaria.ValorOperacaoZeradoException, ContaBancaria.ValorExcedeLimiteException {
        contas = new ArrayList<>();
        pessoas = new ArrayList<>();
        operacoes();
    }

    public static void operacoes() throws ContaBancaria.SaldoOperacaoInsuficienteException, ContaBancaria.ValorOperacaoZeradoException, ContaBancaria.ValorExcedeLimiteException {
        System.out.println("------------------------------------------------------");
        System.out.println("***** Selecione uma operacao que deseja realizar *****");
        System.out.println("------------------------------------------------------");
        System.out.println("|   1 - Criar conta     |");
        System.out.println("|   2 - Depositar       |");
        System.out.println("|   3 - Sacar           |");
        System.out.println("|   4 - Transferir      |");
        System.out.println("|   5 - Listar Contas   |");
        System.out.println("|   6 - Atualizar Conta |");
        System.out.println("|   7 - Sair            |");

        int operacao = input.nextInt();

        switch (operacao) {
            case 1:
                criarConta();
                break;

            case 2:
                depositar();
                break;

            case 3:
                sacar();
                break;

            case 4:
                transferir();
                break;

            case 5:
                listarContas();
                break;

            case 6:
                atualizarLimite();
                break;

            case 7:
                System.out.println("Fim!");
                System.exit(0);

            default:
                System.out.println("Opção inválida!");
                operacoes();
                break;
        }
    }

    public static void criarConta() throws ContaBancaria.SaldoOperacaoInsuficienteException, ContaBancaria.ValorOperacaoZeradoException, ContaBancaria.ValorExcedeLimiteException {
        System.out.println("Insira as informacoes do titular da conta:");

        System.out.println("\nCPF: ");
        input.nextLine(); // consome o \n que ficou no buffer
        String cpf = input.nextLine();

        Pessoa pessoa = encontrarPessoa(cpf);

        if (pessoa != null) {
            System.out.println("Cliente ja cadastrado no sistema");
            System.out.println("Insira o saldo inicial da conta: ");
            double saldo = input.nextDouble();

            System.out.println("Insira o limite inicial da conta: ");
            double limite = input.nextDouble();

            System.out.println("Insira a data de abertura da conta: ");
            String dataAbertura = input.nextLine();

            escolhaTipoConta(pessoa, saldo, limite, dataAbertura);
        } else {
            System.out.println("Cadastre o cliente para continuar!");
            System.out.println("Nome: ");
            String nome = input.nextLine();

            System.out.println("Data de Nascimento: ");
            String nascimento = input.nextLine();

            System.out.println("Insira o saldo inicial da conta: ");
            double saldo = input.nextDouble();

            System.out.println("Insira o limite inicial da conta: ");
            double limite = input.nextDouble();

            System.out.println("Insira a data de abertura da conta: ");
            String dataAbertura = input.nextLine();

            pessoa = new Pessoa(nome, cpf, nascimento);

            pessoas.add(pessoa);

            escolhaTipoConta(pessoa, saldo, limite, dataAbertura);
        }
        System.out.println("Selecione o tipo de conta a ser criada:");
    }

    public static void escolhaTipoConta(Pessoa pessoa, double saldo, double limite, String dataAbertura) throws ContaBancaria.SaldoOperacaoInsuficienteException, ContaBancaria.ValorOperacaoZeradoException, ContaBancaria.ValorExcedeLimiteException {
        System.out.println("\n");
        System.out.println("--------------------------------------------------------------");
        System.out.println("-------------Escolha o tipo de conta do cliente---------------");
        System.out.println("--------------------------------------------------------------");
        System.out.println("**************** Selecione a operacao desejada ***************");
        System.out.println("--------------------------------------------------------------");
        System.out.println("|   1 - Conta Comum        |");
        System.out.println("|   2 - Conta Especial     |");
        System.out.println("|   3 - Conta Poupanca     |");

        int operacao = input.nextInt();

        switch (operacao) {
            case 1 -> {
                ContaComum contaC = new ContaComum(dataAbertura, pessoa, saldo, limite, TipoConta.COMUM);

                System.out.println("\nConta Comum criada com sucesso!");
                System.out.println("\n---------- Resumo da operacao ----------");
                System.out.println("Numero da conta: " + contaC.getId());
                System.out.println("Nome do titular: " + pessoa.getNome());
                System.out.println("Tipo da conta: " + contaC.getTipoConta());
                System.out.println("Saldo: " + contaC.getSaldo());
                System.out.println("Limite: " + contaC.getLimitePorTransacao());

                pessoa.adicionarConta(contaC);

                contas.add(contaC);

                operacoes();
            }
            case 2 -> {
                System.out.println("\nDigite o limite de credito: ");
                int limiteCredito = input.nextInt();

                ContaEspecial contaE = new ContaEspecial(dataAbertura, pessoa, saldo, limite, limiteCredito, TipoConta.ESPECIAL);

                System.out.println("\nConta Especial criada com sucesso!");
                System.out.println("\n---------- Resumo da operacao ----------");
                System.out.println("Numero da conta: " + contaE.getId());
                System.out.println("Nome do titular: " + pessoa.getNome());
                System.out.println("Tipo da conta: " + contaE.getTipoConta());
                System.out.println("Saldo: " + contaE.getSaldo());
                System.out.println("Limite: " + contaE.getLimitePorTransacao());
                System.out.println("Limite de credito: " + contaE.getLimiteCredito());

                pessoa.adicionarConta(contaE);

                contas.add(contaE);

                operacoes();
            }
            case 3 -> {
                int diaAniversario;
                do {
                    System.out.println("\nDigite o dia de aniversario da conta: ");
                    diaAniversario = input.nextInt();
                    if(diaAniversario <= 0 || diaAniversario > 31){
                        System.out.println("Digite um dia de mes valido.");
                    }
                } while (diaAniversario <= 0 || diaAniversario > 31);

                ContaPoupanca contaP = new ContaPoupanca(dataAbertura, pessoa, saldo, limite, diaAniversario, TipoConta.COMUM);

                System.out.println("\nConta Especial criada com sucesso!");
                System.out.println("\n---------- Resumo da operacao ----------");
                System.out.println("Numero da conta: " + contaP.getId());
                System.out.println("Nome do titular: " + pessoa.getNome());
                System.out.println("Tipo da conta: " + contaP.getTipoConta());
                System.out.println("Saldo: " + contaP.getSaldo());
                System.out.println("Limite: " + contaP.getLimitePorTransacao());
                System.out.println("Dia aniversario: " + contaP.getDiaAniversario());

                pessoa.adicionarConta(contaP);

                contas.add(contaP);

                operacoes();
            }
            default -> {
                System.out.println("Opcao invalida!");
                operacoes();
            }
        }
    }

    public static void depositar() throws ContaBancaria.SaldoOperacaoInsuficienteException, ContaBancaria.ValorOperacaoZeradoException, ContaBancaria.ValorExcedeLimiteException {
        System.out.println("\nDigite o numero da conta: ");

        int IdConta = input.nextInt();

        ContaBancaria conta = encontrarContaBancaria(IdConta);

        if (conta != null) {
            System.out.println("\nDigite o valor para deposito: ");
            double valorDeposito = input.nextDouble();
            conta.depositar(valorDeposito);
            System.out.println("\nValor depositado com sucesso! O novo saldo da conta eh: " + conta.getSaldo());
        }
        operacoes();
    }

    public static Pessoa encontrarPessoa(String cpf) {
        Pessoa pessoaRetorno = null;

        if (!pessoas.isEmpty()) {
            for (Pessoa pessoa : pessoas) {
                if (pessoa.getCpf().equals(cpf)) {
                    pessoaRetorno = pessoa;
                }
            }
        }
        return pessoaRetorno;
    }

    public static ContaBancaria encontrarContaBancaria(int idConta) {
        ContaBancaria contaRetorno = null;

        if (!contas.isEmpty()) {
            for (ContaBancaria conta : contas) {
                if (conta.getId() == idConta) {
                    contaRetorno = conta;
                }
            }
        }

        if (contaRetorno != null) {
            System.out.println("\nConta localizada!");
            return contaRetorno;
        } else {
            System.out.println("\nA conta nao foi encontrada. Tente novamente.");
            return contaRetorno;
        }
    }

    public static void transferir() throws ContaBancaria.SaldoOperacaoInsuficienteException, ContaBancaria.ValorOperacaoZeradoException, ContaBancaria.ValorExcedeLimiteException {
        System.out.println("\nDigite a conta origem: ");
        int contaO = input.nextInt();

        ContaBancaria contaOrigem = encontrarContaBancaria(contaO);

        if (contaOrigem != null) {
            System.out.println("\nDigite a conta destino: ");
            int contaD = input.nextInt();

            ContaBancaria contaDestino = encontrarContaBancaria(contaD);

            if (contaDestino != null) {

                System.out.println("\nDigite o valor a ser transferido: ");
                double valorTransf = input.nextDouble();

                try {
                    contaOrigem.transferir(valorTransf, contaDestino);
                    System.out.println("\nTransferencia realizada com sucesso!");
                    System.out.println("\n---------- Resumo da operacao ----------");
                    System.out.println("Numero da conta Origem: " + contaOrigem.getId());
                    System.out.println("Numero da conta Destino: " + contaDestino.getId());
                    System.out.println("Saldo final conta Origem: " + contaOrigem.getSaldo());
                    System.out.println("Saldo final conta Destino: " + contaDestino.getSaldo());
                    System.out.println("Valor transferido: " + valorTransf);

                    operacoes();
                } catch (ContaBancaria.SaldoOperacaoInsuficienteException ex) {
                    System.out.println("\nAtualize o saldo da conta de origem e tente novamente.");
                    operacoes();
                } catch (ContaBancaria.ValorOperacaoZeradoException ex) {
                    System.out.println("\nInsira um valor maior do que zero para prosseguir com a transferencia.");
                    operacoes();
                } catch (ContaBancaria.ValorExcedeLimiteException ex) {
                    System.out.println("\nAtualize o limite ou diminua o valor da transferencia.");
                    operacoes();
                }
            } else {
                operacoes();
            }
        } else {
            operacoes();
        }
    }

    public static void sacar() throws ContaBancaria.SaldoOperacaoInsuficienteException, ContaBancaria.ValorOperacaoZeradoException, ContaBancaria.ValorExcedeLimiteException {
        System.out.println("\nDigite a conta para saque: ");
        int contaS = input.nextInt();

        ContaBancaria contaSaque = encontrarContaBancaria(contaS);

        if (contaSaque != null) {
            System.out.println("\nDigite o valor do saque: ");
            double valorSaque = input.nextDouble();

            try {
                contaSaque.sacar(valorSaque);
                System.out.println("\nSaque realizado com sucesso!");
                System.out.println("\n---------- Resumo da operacao ----------");
                System.out.println("Numero da conta: " + contaSaque.getId());
                System.out.println("Valor sacado: " + valorSaque);
                System.out.println("Saldo da conta apos o saque: " + contaSaque.getSaldo());

                operacoes();
            } catch (ContaBancaria.SaldoOperacaoInsuficienteException ex) {
                System.out.println("\nAtualize o saldo da conta de origem e tente novamente.");
                operacoes();
            } catch (ContaBancaria.ValorOperacaoZeradoException ex) {
                System.out.println("\nInsira um valor maior do que zero para prosseguir com a transferencia.");
                operacoes();
            } catch (ContaBancaria.ValorExcedeLimiteException ex) {
                System.out.println("\nAtualize o limite ou diminua o valor da transferencia.");
                operacoes();
            }
        } else {
            operacoes();
        }
    }

    public static void atualizarLimite() throws ContaBancaria.SaldoOperacaoInsuficienteException, ContaBancaria.ValorOperacaoZeradoException, ContaBancaria.ValorExcedeLimiteException {
        System.out.println("Digite a conta a ser atualizada: ");
        int numeroConta = input.nextInt();

        ContaBancaria contaAtualizacao = encontrarContaBancaria(numeroConta);

        if (contaAtualizacao != null) {
            if (contaAtualizacao.getTipoConta() != null) {
                switch (contaAtualizacao.getTipoConta()) {
                    case COMUM, POUPANCA -> {
                        System.out.println("Digite o novo limite de transacoes da conta: ");
                        int novoLimite = input.nextInt();
                        contaAtualizacao.setLimitePorTransacao(novoLimite);
                        System.out.println("Limite atualizado com sucesso!");
                        System.out.println("---------- Resumo da operacao ----------");
                        System.out.println("Numero da conta: " + contaAtualizacao.getId());
                        System.out.println("Valor do novo limite de transacoes: " + novoLimite);

                        operacoes();
                    }
                    case ESPECIAL -> {
                        ContaEspecial contaEspecial = (ContaEspecial) contaAtualizacao;
                        System.out.println("Escolha qual limite deseja atualizar: ");
                        System.out.println("1 - Limite de Transacoes");
                        System.out.println("2 - Limite de Credito");
                        int escolha = input.nextInt();

                        if (escolha == 1) {
                            System.out.println("Digite o novo limite de transacoes da conta: ");
                            int novoLimite = input.nextInt();

                            contaEspecial.setLimitePorTransacao(novoLimite);

                            System.out.println("Limite atualizado com sucesso!");
                            System.out.println("---------- Resumo da operacao ----------");
                            System.out.println("Numero da conta: " + contaAtualizacao.getId());
                            System.out.println("Valor do novo limite de transacoes: " + novoLimite);

                            operacoes();
                        } else if (escolha == 2) {
                            System.out.println("Digite o novo limite de credito da conta: ");

                            int novoCredito = input.nextInt();

                            contaEspecial.setLimiteCredito(novoCredito);
                            System.out.println("Limite atualizado com sucesso!");
                            System.out.println("---------- Resumo da operacao ----------");
                            System.out.println("Numero da conta: " + contaAtualizacao.getId());
                            System.out.println("Valor do novo limite de credito: " + novoCredito);

                            operacoes();
                        }
                    }
                    default -> {
                        System.out.println("Valor invalido!");
                        operacoes();
                    }
                }
            }
        } else {
            operacoes();
        }
    }

    public static void listarContas() throws ContaBancaria.SaldoOperacaoInsuficienteException, ContaBancaria.ValorOperacaoZeradoException, ContaBancaria.ValorExcedeLimiteException {
        if (!contas.isEmpty()) {
            for (ContaBancaria conta : contas) {
                if (conta.getTipoConta() == TipoConta.COMUM || conta.getTipoConta() == TipoConta.POUPANCA) {
                    System.out.println("Numero da conta: " + conta.getId());
                    System.out.println("Nome do titular: " + conta.getNomePessoa());
                    System.out.println("Tipo da conta: " + conta.getTipoConta());
                    System.out.println("Limite de transacoes: " + conta.getLimitePorTransacao());
                    System.out.println("CPF do titular: " + conta.getCpfPessoa());
                    System.out.println("Saldo: " + conta.getSaldo());
                    System.out.println("-----------------------");
                } else if (conta.getTipoConta() == TipoConta.ESPECIAL) {
                    ContaEspecial contaEspecial = (ContaEspecial) conta;
                    System.out.println("Numero da conta: " + conta.getId());
                    System.out.println("Nome do titular: " + conta.getNomePessoa());
                    System.out.println("Tipo da conta: " + conta.getTipoConta());
                    System.out.println("Limite de transacoes: " + conta.getLimitePorTransacao());
                    System.out.println("Limite de credito: " + contaEspecial.getLimiteCredito());
                    System.out.println("CPF do titular: " + conta.getCpfPessoa());
                    System.out.println("Saldo: " + conta.getSaldo());
                    System.out.println("-----------------------");
                }
            }
        } else {
            System.out.println("Nao ha contas cadastradas");
        }
        operacoes();
    }
}