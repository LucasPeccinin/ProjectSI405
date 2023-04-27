
package model;

public class ContaEspecial extends ContaBancaria {
    private double limiteCredito;

    public ContaEspecial(String dataAbertura, Pessoa pessoa, double saldo, double limitePorTransacao, double limiteCredito) {
        super(dataAbertura, pessoa, saldo, limitePorTransacao);
        this.limiteCredito = limiteCredito;
    }

    public double getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }
    
    @Override
    public void sacar(double valor) throws SaldoOperacaoInsuficienteException, ValorOperacaoZeradoException, ValorExcedeLimiteException {
        if (valor <= 0) {
            throw new ValorOperacaoZeradoException();
        }
        if (this.getSaldo() + this.getLimiteCredito() - valor < 0) {
            throw new SaldoOperacaoInsuficienteException();
        }
        if (valor > this.getLimitePorTransacao()) {
            throw new ValorExcedeLimiteException();
        }
        this.setSaldo(this.getSaldo() - valor);
    }
}