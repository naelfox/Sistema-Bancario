package Banco;

public abstract class Conta {

	private int numeroConta;
	private String nomeCliente;
	private String cpf;
	private double saldo;

	public Conta() {
	}

	public Conta(int numeroConta, String nomeCliente, String cpf) {
		this.numeroConta = numeroConta;
		this.nomeCliente = nomeCliente;
		this.cpf = cpf;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public int getNumeroConta() {
		return numeroConta;
	}

	public String getCpf() {
		return cpf;
	}

	public double getSaldo() {
		return saldo;
	}

	public boolean sacar(double valorSacado) {
		saldo -= valorSacado;
		return true;
	}

	public boolean depositar(double valorDepositado) {
		saldo += valorDepositado;
		return true;
	}

	public String imprimir() {
		return "Número da conta: " + numeroConta + "\nNome do cliente: " + nomeCliente + "\nCPF: " + cpf
				+ "\nSaldo: R$ " + String.format("%.2f", saldo);
	}

}
