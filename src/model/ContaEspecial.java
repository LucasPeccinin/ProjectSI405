
package model;

public class ContaEspecial extends ContaBancaria {
    private double limiteCredito;

    public ContaEspecial(int id, String dataAbertura, Pessoa pessoa, double saldo, double limitePorTransacao, double limiteCredito) {
        super(id, dataAbertura, pessoa, saldo, limitePorTransacao);
        this.limiteCredito = limiteCredito;
    }

    public double getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }
    
    @Override
    public void sacar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor do saque deve ser maior que zero");
        }
        if (this.getSaldo() + this.getLimiteCredito() - valor < 0) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar o saque");
        }
        if (valor > this.getLimitePorTransacao()) {
            throw new IllegalArgumentException("Valor do saque excede o limite por transação");
        }
        this.setSaldo(this.getSaldo() - valor);
    }
}