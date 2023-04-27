package model;

public abstract class ContaBancaria {

    private static int ContaCount = 1;

    private int id;
    private String dataAbertura;
    private Pessoa pessoa;
    private double saldo = 0.0;
    private double limitePorTransacao;

    public ContaBancaria(String dataAbertura, Pessoa pessoa, double saldo, double limitePorTransacao) {
        this.id = ContaBancaria.ContaCount;
        this.dataAbertura = dataAbertura;
        this.saldo = saldo;
        this.pessoa = pessoa;
        this.limitePorTransacao = limitePorTransacao;
        ContaBancaria.ContaCount += 1;
    }

    public int getId() {
        return id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getNomePessoa() {
        return this.pessoa.getNome();
    }

    public String getCpfPessoa() {
        return this.pessoa.getCpf();
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getLimitePorTransacao() {
        return limitePorTransacao;
    }

    public void setLimitePorTransacao(double limitePorTransacao) {
        this.limitePorTransacao = limitePorTransacao;
    }

    @Override
    public String toString() {
        return "\nConta Bancaria:" + this.getId() + "\nCliente:" + this.getPessoa()
                + "\nSaldo:" + this.getSaldo() + "\nLimite por Transação:" + this.getLimitePorTransacao() + "\n";
    }

    public void depositar(double valor) {
        this.saldo += valor;
    }

    public abstract void sacar(double valor) throws SaldoOperacaoInsuficienteException, ValorOperacaoZeradoException, ValorExcedeLimiteException;

    public void transferir(double valor, ContaBancaria contaDestino) throws SaldoOperacaoInsuficienteException, ValorOperacaoZeradoException, ValorExcedeLimiteException {
        if (valor <= 0) {
            throw new ValorOperacaoZeradoException();
        }
        if (this.saldo - valor < 0) {
            throw new SaldoOperacaoInsuficienteException();
        }
        if (valor > this.limitePorTransacao) {
            throw new ValorExcedeLimiteException();
        }
        this.saldo -= valor;
        contaDestino.depositar(valor);
    }

    public class SaldoOperacaoInsuficienteException extends Exception {

        public SaldoOperacaoInsuficienteException() {
            System.out.println("\nSaldo insuficiente para realizar a transferencia");
        }
    }

    public class ValorOperacaoZeradoException extends Exception {

        public ValorOperacaoZeradoException() {
            System.out.println("\nValor da transferencia deve ser maior que zero");
        }
    }
    
    public class ValorExcedeLimiteException extends Exception {

        public ValorExcedeLimiteException() {
            System.out.println("\nValor da operacao excede o limite por transacao");
        }
    }
}
