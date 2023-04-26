package model;

import java.util.ArrayList;


public class Pessoa {
    private static int PessoaCount = 1;
    
    private String nome;
    private String cpf;
    private String nascimento;
    private ArrayList<ContaBancaria> contas;

    public Pessoa(String nome, String cpf, String nascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.nascimento = nascimento;
        //this.contas = new ArrayList();
        Pessoa.PessoaCount += 1;
        this.contas = new ArrayList<ContaBancaria>();
    }
    
    public void adicionarConta(ContaBancaria conta){
        contas.add(conta);
    }

    public String getNome() {
        return nome;
    }
    
    public int getIdPessoa(){
        return PessoaCount;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String toString() {
        return "\nNome:" + this.getNome() + "\nCPF" + this.getCpf() + "\nData de Nascimento" + this.getNascimento() + "\n";
    }
}
