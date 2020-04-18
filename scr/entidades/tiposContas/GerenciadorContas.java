package entidades.tiposContas;

import java.util.ArrayList;

public class GerenciadorContas {

	ArrayList<Conta> conta = new ArrayList<>();
	ArrayList<ContaCorrente> contaCorrente = new ArrayList<>();
	ArrayList<ContaEspecial> contaEspecial = new ArrayList<>();
	ArrayList<ContaPoupanca> contaPoupanca = new ArrayList<>();

	// metodos de adicionar ao arraylist

	public void adicionarConta(Conta c) {
		conta.add(c);
	}

	// remover contas metodos
	public boolean removerConta(int numeroConta) {

		for (int i = 0; i < conta.size(); i++) {
			if (conta.get(i).getNumeroConta() == numeroConta) {
				conta.remove(conta.get(i));
				return true;

			}
		}
		return false;
	}

	public String buscarContasEspeciais() {
		String lista = "";
		for (Conta x : conta) {
			if (x.getClass().getName().equals("Banco.ContaEspecial")) {
				contaEspecial.add((ContaEspecial) x);
				for (int i = 0; i < contaEspecial.size(); i++) {
					lista += contaEspecial.get(i).imprimir() + "\n__________________\n";
					contaEspecial.remove(x);
				}
			}
		}
		return "Lista de contas especiais\n" + lista;
	}

	public String buscarClientesUsandoLimite() {
		String lista = "";

		for (Conta x : conta) {
			if (x.getClass().getName().equals("Banco.ContaCorrente")) {
				contaCorrente.add((ContaCorrente) x);
				for (int i = 0; i < contaCorrente.size(); i++) {
					if (contaCorrente.get(i).usandoLimite() == true) {
						lista += "-" + contaCorrente.get(i).getNomeCliente() + " || nº da conta: "
								+ contaCorrente.get(i).getNumeroConta() + "|| Saldo: R$"
								+ contaCorrente.get(i).getSaldo() + "|| Limite: R$ " + contaCorrente.get(i).getLimite()
								+ "\n";
						contaCorrente.remove((ContaCorrente) x);
					}
				}
			}
		}

		return "Seguintes clientes estão usando limite:\n" + lista;
	}

	public String buscarConta(int numeroConta) {

		for (int i = 0; i < conta.size(); i++) {
			if (conta.get(i).getNumeroConta() == numeroConta) {
				return "Dados da conta\nCliente " + conta.get(i).getNomeCliente() + "\nCPF: " + conta.get(i).getCpf()
						+ "\nSaldo: " + conta.get(i).getSaldo();
			}
		}
		return null;

	}

	public boolean transferirValor(int numeroContaFonte, int numeroContaDestino, double valor) {

		for (int i = 0; i < conta.size(); i++) {
			if (conta.get(i).getNumeroConta() == numeroContaFonte) {
				conta.get(i).sacar(valor);
				for (int e = 0; e < conta.size(); e++) {
					if (conta.get(e).getNumeroConta() == numeroContaDestino) {
						conta.get(e).depositar(valor);
					}
				}
				return true;
			}
		}
		return false;
	}

	public boolean sacar(int numeroConta, double valorSacado) {
		for (int i = 0; i < conta.size(); i++) {
			if (conta.get(i).getNumeroConta() == numeroConta) {
				conta.get(i).sacar(valorSacado);
				return true;
			}
		}
		return false;
	}

	public boolean depositar(int numeroConta, double valorDepositado) {
		for (int i = 0; i < conta.size(); i++) {
			if (conta.get(i).getNumeroConta() == numeroConta) {
				conta.get(i).depositar(valorDepositado);
				return true;
			}
		}
		return false;
	}

	public String listarContas() {
		String lista = "";
		if (conta.isEmpty()) {
			lista = "Nenhuma conta cadastrada";
		} else {
			for (Conta x : conta) {
				lista += x.imprimir() + "\n__________________\n";
			}
		}
		return "Lista de todas as contas\n" + lista;
	}

}
