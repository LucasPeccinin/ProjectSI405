package model;

public class ContaComum extends ContaBancaria {
    public ContaComum(String dataAbertura, Pessoa pessoa, double saldo, double limitePorTransacao) {
        super(dataAbertura, pessoa, saldo, limitePorTransacao);
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
