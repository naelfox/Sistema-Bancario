package entidades.tiposContas;

public class ContaPoupanca extends Conta{
	
	
	
	public ContaPoupanca(int numeroConta, String nomeCliente, String cpf) {
	super(numeroConta, nomeCliente, cpf);
	}

	
	

	public void calcularRendimento(double porcentagemRendimento) {	
		System.out.println("Rende R$ " + ((getSaldo() + (porcentagemRendimento / 100)) *12) + " ao ano.");
	}
	
}
