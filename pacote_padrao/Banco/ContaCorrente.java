package Banco;

public class ContaCorrente extends Conta {
	private double limite;

	public ContaCorrente() {
	}

	public ContaCorrente(int numeroConta, String nomeCliente, String cpf) {
		super(numeroConta, nomeCliente, cpf);
	}

	public ContaCorrente(int numeroConta, String nomeCliente, String cpf, double limite) {
		super(numeroConta, nomeCliente, cpf);
		this.limite = limite;
	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}

	public boolean usandoLimite() {
		if (getSaldo() < 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean sacar(double valorSacado) {
		limite *= -1;
		super.sacar(valorSacado);

		if (getSaldo() < limite) {
			super.depositar(valorSacado);
			limite *= -1;
			throw new IllegalArgumentException(" não foi possivel realizar o saque devido ao limite");

		} else {
			limite *= -1;
			throw new IllegalArgumentException(" saque realizado com sucesso!");
		}
	}

	@Override
	public String imprimir() {
		return super.imprimir() + "\nLimite: R$ " + String.format("%.2f", limite);
	}
}
