package aplicacao;
/*
 * Autor: Natanael.
 * 
 * toda codificacao comecou em meados de Novembro de 2019
 * foi criada para o projeto da atividade pratica supervisionada do professor Igor Oliveira Borges
 * 
 */

import java.util.Locale;

import javax.swing.JOptionPane;

import entidades.tiposContas.ContaCorrente;
import entidades.tiposContas.ContaEspecial;
import entidades.tiposContas.ContaPoupanca;
import entidades.tiposContas.GerenciadorContas;

public class Principal {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		GerenciadorContas operacaoContas = new GerenciadorContas();

		// vari�veis
		Object[] operacoes = { "Selecione...", "Adicionar conta", "Remover conta", "Saque", "Dep�sito", "Transfer�ncia",
				"Buscar" };
		Object[] adcConta = { "Selecione...", "Conta corrente", "Conta poupan�a", "Conta especial" };
		Object[] menu = { "Sim", "N�o, voltar ao menu inicial" };
		Object[] confirmacao = { "Sim", "N�o" };
		Object[] buscar = { "Buscar contas", "Contas usando limite", "Contas especiais", "Listar todas as contas" };
		int resposta;
		boolean opcao = false;

		do {
			// Boas vindas
			// menu principal
			try {
				String x = (String) JOptionPane.showInputDialog(null, "Selecione uma opera��o", "BancoAnhembi",
						JOptionPane.PLAIN_MESSAGE, null, operacoes, 0);

				// adicionar Conta
				if (x.equals(operacoes[1])) {
					do {
						String y = (String) JOptionPane.showInputDialog(null, "Selecione o tipo de conta",
								"BancoAnhembi", JOptionPane.PLAIN_MESSAGE, null, adcConta, 0);
						// come�o do tratamento de excessoes

						// adicionar conta corrente
						if (y.equals(adcConta[1])) {

							int numeroDaConta = Integer.parseInt(JOptionPane.showInputDialog("Numero da conta"));
							String nomeDoCliente = JOptionPane.showInputDialog("Nome do Cliente");
							String cpf = JOptionPane.showInputDialog("CPF");
							double limite = Double.parseDouble(JOptionPane.showInputDialog("Limite para essa conta"));
							ContaCorrente c = new ContaCorrente(numeroDaConta, nomeDoCliente, cpf, limite);
							// adiciona no arraylist
							operacaoContas.adicionarConta(c);
							JOptionPane.showMessageDialog(null, "Dados da nova conta\n" + c.imprimir());
						}
						// adicionar conta poupan�a
						if (y.equals(adcConta[2])) {
							int numeroDaConta = Integer.parseInt(JOptionPane.showInputDialog("Numero da conta"));
							String nomeDoCliente = JOptionPane.showInputDialog("Nome do Cliente");
							String cpf = JOptionPane.showInputDialog("CPF");
							ContaPoupanca c = new ContaPoupanca(numeroDaConta, nomeDoCliente, cpf);
							operacaoContas.adicionarConta(c);

							JOptionPane.showMessageDialog(null, "Dados da nova conta\n" + c.imprimir());
						}
						// adicionar conta especial
						if (y.equals(adcConta[3])) {
							int numeroDaConta = Integer.parseInt(JOptionPane.showInputDialog("Numero da conta"));
							String nomeDoCliente = JOptionPane.showInputDialog("Nome do Cliente");
							String cpf = JOptionPane.showInputDialog("CPF");
							double limite = Double.parseDouble(JOptionPane.showInputDialog("Limite para essa conta"));
							String nomeGerente = JOptionPane.showInputDialog("Nome do Gerente");
							ContaEspecial c = new ContaEspecial(numeroDaConta, nomeDoCliente, cpf, limite, nomeGerente);
							operacaoContas.adicionarConta(c);

							JOptionPane.showMessageDialog(null, "Dados da nova conta\n" + c.imprimir());
						}
						// solu��o para as excessoes

						// resposta ou retorno ao menu
						resposta = JOptionPane.showOptionDialog(null, "Desejar criar uma nova conta?", "O que fazer",
								JOptionPane.YES_OPTION, JOptionPane.NO_OPTION, null, menu, 0);
						if (resposta == JOptionPane.YES_OPTION) {
							opcao = true;
						} else {
							opcao = false;
						}

					} while (opcao == true);
				}

				// remover conta
				if (x.equals(operacoes[2])) {

					int numeroConta = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o n�mero da conta",
							"Remover conta", JOptionPane.INFORMATION_MESSAGE));

					String localizador = operacaoContas.buscarConta(numeroConta);

					if (localizador == null) {
						JOptionPane.showMessageDialog(null,
								"Erro! n�o foi possivel localizar nenhuma conta com o n�mero informado.", "Erro", 0);
					} else {

						int resp = JOptionPane.showOptionDialog(null,
								"Deseja remover essa conta?\n" + operacaoContas.buscarConta(numeroConta),
								"Remover conta", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION, null, confirmacao, 0);
						if (resp == JOptionPane.YES_OPTION) {
							operacaoContas.removerConta(numeroConta);

							JOptionPane.showMessageDialog(null, "Conta removida com sucesso!");
						} else {
							JOptionPane.showMessageDialog(null, "N�o removida");
						}

					}
				}

				// saque na conta
				if (x.equals(operacoes[3])) {

					int numeroConta = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o n�mero da conta",
							"Saque em conta", JOptionPane.INFORMATION_MESSAGE));

					String localizador = operacaoContas.buscarConta(numeroConta);

					if (localizador == null) {
						JOptionPane.showMessageDialog(null,
								"Erro! n�o foi possivel localizar nenhuma conta com o n�mero informado.", "Erro", 0);
					} else {
						double valorSacado = Double.parseDouble(JOptionPane.showInputDialog(
								operacaoContas.buscarConta(numeroConta) + "\n\nInforme um valor para saque"));
						int resp = JOptionPane.showOptionDialog(null, "Deseja efetuar esse saque na seguinte conta\n"
								+ operacaoContas.buscarConta(numeroConta) + "\nNo valor de R$ " + valorSacado + "?",
								"Saque em conta", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION, null, confirmacao, 0);

						if (resp == JOptionPane.YES_OPTION) {
							boolean verificador = operacaoContas.sacar(numeroConta, valorSacado);

							if (verificador == true) {
								JOptionPane.showMessageDialog(null,
										"Saque realizado com sucesso!\n" + operacaoContas.buscarConta(numeroConta));
							}
						}
					}
				}

				// dep�sito na conta
				if (x.equals(operacoes[4])) {
					int numeroConta = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o n�mero da conta",
							"Deposito em conta", JOptionPane.INFORMATION_MESSAGE));

					String localizador = operacaoContas.buscarConta(numeroConta);

					if (localizador == null) {
						JOptionPane.showMessageDialog(null,
								"Erro! n�o foi possivel localizar nenhuma conta com o n�mero informado.", "Erro", 0);
					} else {
						double valorDepositado = Double.parseDouble(JOptionPane.showInputDialog(
								operacaoContas.buscarConta(numeroConta) + "\n\nInforme um valor para dep�sito"));
						int resp = JOptionPane.showOptionDialog(null, "Deseja efetuar o dep�sito na seguinte conta\n"
								+ operacaoContas.buscarConta(numeroConta) + "\nNo valor de R$ " + valorDepositado + "?",
								"Remover conta", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION, null, confirmacao, 0);

						if (resp == JOptionPane.YES_OPTION) {
							operacaoContas.depositar(numeroConta, valorDepositado);
							JOptionPane.showMessageDialog(null,
									"Dep�sito realizado com sucesso!\n" + operacaoContas.buscarConta(numeroConta));
						} else {
							JOptionPane.showMessageDialog(null, "Dep�sito cancelado");
						}

					}

				}

				// transfer�ncia de conta a conta
				if (x.equals(operacoes[5])) {

					int numeroContaFonte = Integer
							.parseInt(JOptionPane.showInputDialog(null, "Informe o n�mero da conta fonte",
									"Transfer�ncia em conta", JOptionPane.INFORMATION_MESSAGE));

					String localizador = operacaoContas.buscarConta(numeroContaFonte);
					if (localizador == null) {
						JOptionPane.showMessageDialog(null,
								"Erro! n�o foi possivel localizar nenhuma conta com o n�mero informado.", "Erro", 0);
					}

					int numeroContaDestino = Integer
							.parseInt(JOptionPane.showInputDialog(null, "Informe o n�mero da conta destino",
									"Transfer�ncia em conta", JOptionPane.INFORMATION_MESSAGE));

					localizador = operacaoContas.buscarConta(numeroContaDestino);
					if (localizador == null) {
						JOptionPane.showMessageDialog(null,
								"Erro! n�o foi possivel localizar nenhuma conta com o n�mero informado.", "Erro", 0);
					}

					double valor = Double
							.parseDouble(JOptionPane.showInputDialog(null, "Informe um valor a ser transferido",
									"Transfer�ncia em conta", JOptionPane.INFORMATION_MESSAGE));

					int resp = JOptionPane.showOptionDialog(null,
							"Confirma a realiza��o de dep�sito da conta de n�mero " + numeroContaFonte + " para a "
									+ numeroContaDestino + " no valor de R$ " + valor + "?",
							"Transfer�ncia em andamento", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION, null,
							confirmacao, 0);
					if (resp == JOptionPane.YES_OPTION) {
						operacaoContas.transferirValor(numeroContaFonte, numeroContaDestino, valor);
						JOptionPane.showMessageDialog(null,
								"Transfer�ncia realizada com sucesso!\n" + "Conta fonte:\n"
										+ operacaoContas.buscarConta(numeroContaFonte) + "\n\nConta Destino:\n"
										+ operacaoContas.buscarConta(numeroContaDestino));
					} else {
						JOptionPane.showMessageDialog(null, "Transfer�ncia cancelada");
					}

				}

				// buscar
				if (x.equals(operacoes[6])) {
					String z = (String) JOptionPane.showInputDialog(null, "Selecione uma opera��o",
							"O que desejar buscar?", JOptionPane.PLAIN_MESSAGE, null, buscar, 0);
					// buscar conta
					if (z.equals(buscar[0])) {
						int numeroConta = Integer.parseInt(JOptionPane.showInputDialog(null,
								"Informe o n�mero da conta", "Buscar conta", JOptionPane.INFORMATION_MESSAGE));

						String localizador = operacaoContas.buscarConta(numeroConta);
						if (localizador == null) {
							JOptionPane.showMessageDialog(null,
									"Erro! n�o foi possivel localizar nenhuma conta com o n�mero informado.", "Erro",
									0);
						} else {
							JOptionPane.showMessageDialog(null, operacaoContas.buscarConta(numeroConta));
						}
						// buscar usando limite

					}
					if (z.equals(buscar[1])) {

						JOptionPane.showMessageDialog(null, operacaoContas.buscarClientesUsandoLimite());
					}
					//
					if (z.equals(buscar[2])) {

						JOptionPane.showMessageDialog(null, operacaoContas.buscarContasEspeciais());

					}
					if (z.equals(buscar[3])) {

						JOptionPane.showMessageDialog(null, operacaoContas.listarContas());

					}

				}

			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, e.getLocalizedMessage() + "\nFormato inv�lido!");

			} catch (NullPointerException e) {
				resposta = JOptionPane.showOptionDialog(null, "Fechar?", "Finalizar", JOptionPane.YES_OPTION,
						JOptionPane.NO_OPTION, null, confirmacao, 0);

				if (resposta == JOptionPane.YES_OPTION) {
					opcao = true;
				} else {
					opcao = false;
				}

			} catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog(null, "Status: " + e.getMessage());
			}

		} while (opcao == false);

	}
}
