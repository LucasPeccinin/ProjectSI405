package model;

public class ContaComum extends ContaBancaria {
    public ContaComum(String dataAbertura, Pessoa pessoa, double saldo, double limitePorTransacao, TipoConta tipoConta) {
        super(dataAbertura, pessoa, saldo, limitePorTransacao, tipoConta.COMUM);
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