package model;

import java.util.ArrayList;

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
        return "\nConta Bancaria:" + this.getId() + "\nCliente:" + this.getPessoa() + 
               "\nSaldo:" + this.getSaldo() + "\nLimite por Transação:" + this.getLimitePorTransacao() + "\n";
    }
 
    public void depositar(double valor) {
        this.saldo += valor;
    }
    
    public abstract void sacar(double valor);

    public void transferir(double valor, ContaBancaria contaDestino) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor da transferência deve ser maior que zero");
        }
        if (this.saldo - valor < 0) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar a transferência");
        }
        if (valor > this.limitePorTransacao) {
            throw new IllegalArgumentException("Valor da transferência excede o limite por transação");
        }
        this.saldo -= valor;
        contaDestino.depositar(valor);
    }
    
    public static ArrayList<ContaBancaria> listarContas(ArrayList<ContaBancaria> contas) {
        return contas;
    }
}
