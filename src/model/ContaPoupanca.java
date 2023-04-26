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
    public void sacar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor do saque deve ser maior que zero");
        }
        if (this.getSaldo() - valor < 0) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar o saque");
        }
        if (valor > this.getLimitePorTransacao()) {
            throw new IllegalArgumentException("Valor do saque excede o limite por transação");
        }
        this.setSaldo(this.getSaldo() - valor);
    }
}
