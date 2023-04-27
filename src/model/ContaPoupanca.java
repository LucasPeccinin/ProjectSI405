package model;

public class ContaPoupanca extends ContaBancaria {

    private int diaAniversario;

    public ContaPoupanca(String dataAbertura, Pessoa pessoa, double saldo, double limitePorTransacao, int diaAniversario) {
        super(dataAbertura, pessoa, saldo, limitePorTransacao);
        this.diaAniversario = diaAniversario;
    }

    public int getDiaAniversario() {
        return diaAniversario;
    }

    public void setDiaAniversario(int diaAniversario) {
        this.diaAniversario = diaAniversario;
    }

    @Override
    public void sacar(double valor) throws SaldoOperacaoInsuficienteException, ValorOperacaoZeradoException, ValorExcedeLimiteException {
        if (valor <= 0) {
            throw new ValorOperacaoZeradoException();
        }
        if (this.getSaldo() - valor < 0) {
            throw new SaldoOperacaoInsuficienteException();
        }
        if (valor > this.getLimitePorTransacao()) {
            throw new ValorExcedeLimiteException();
        }
        this.setSaldo(this.getSaldo() - valor);
    }
}
