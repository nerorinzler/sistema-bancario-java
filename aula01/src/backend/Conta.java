package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Conta {
	private String agencia;
	private String conta;
	private String titular;
	private String saldo;

	public Conta() {
		super();
	}

	public Conta(String agencia, String conta, String titular, String saldo) {
		this.agencia = agencia;
		this.conta = conta;
		this.titular = titular;
		this.saldo = saldo;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getSaldo() {
		return saldo;
	}

	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}

	public void criarBd() {
		String url = "jdbc:mariadb://localhost:3306/";
		String user = "root";
		String password = "";

		try (Connection conn = DriverManager.getConnection(url, user, password)) {
			String sql1 = "CREATE DATABASE IF NOT EXISTS Banco;";
			try (PreparedStatement ps1 = conn.prepareStatement(sql1)) {
				ps1.execute();
			}

			String sql2 = "USE Banco;";
			try (PreparedStatement ps2 = conn.prepareStatement(sql2)) {
				ps2.execute();
			}

			System.out.println("✔ BANCO DE DADOS CRIADO COM SUCESSO!");

		} catch (Exception e) {
			System.err.println("✖ ERRO AO CRIAR BANCO DE DADOS:");
			e.printStackTrace();
		}
	}

	public void deletarBd() {
		try {
			String url = "jdbc:mariadb://localhost:3306/";
			String user = "root";
			String password = "";

			Connection conn = DriverManager.getConnection(url, user, password);

			String sql1 = "DROP DATABASE IF EXISTS Banco;";
			try (PreparedStatement ps = conn.prepareStatement(sql1)) {
				ps.execute();
			}

			conn.close();

			System.out.println("✔ BANCO DE DADOS DELETADO COM SUCESSO!");

		} catch (Exception e) {
			System.err.println("✖ ERRO AO DELETAR O BANCO DE DADOS:");
			e.printStackTrace();
		}
	}

	public void criarTabelaContaCorrente() {
		try {
			String url = "jdbc:mariadb://localhost:3306/Banco";
			String user = "root";
			String password = "";

			Connection conn = DriverManager.getConnection(url, user, password);

			String sql1 = "CREATE TABLE IF NOT EXISTS tableCorrente(ID INT AUTO_INCREMENT PRIMARY KEY , AGENCIA VARCHAR(255) , CONTA VARCHAR(225) , TITULAR VARCHAR(255), SALDO FLOAT , LIMITE FLOAT);";
			try (PreparedStatement ps = conn.prepareStatement(sql1)) {
				ps.execute();
			}

			conn.close();

			System.out.println("✔ TABELA CORRENTE CRIADA COM SUCESSO!");

		} catch (Exception e) {
			System.err.println("✖ ERRO AO CRIAR TABELA CORRENTE!");
			e.printStackTrace();
		}
	}

	public void criarTabelaContaPoupanca() {
		try {
			String url = "jdbc:mariadb://localhost:3306/Banco";
			String user = "root";
			String password = "";

			Connection conn = DriverManager.getConnection(url, user, password);

			String sql1 = "CREATE TABLE IF NOT EXISTS tablePoupanca (" + "ID INT AUTO_INCREMENT PRIMARY KEY, "
					+ "AGENCIA VARCHAR(255), " + "CONTA VARCHAR(225), " + "TITULAR VARCHAR(255), " + "SALDO FLOAT, "
					+ "RENDIMENTO FLOAT);";
			PreparedStatement ps = conn.prepareStatement(sql1);
			ps.execute();
			conn.close();

			System.out.println("✔ TABELA CONTA POUPANÇA CRIADA COM SUCESSO!");
		} catch (Exception e) {
			System.err.println("✖ ERRO AO CRIAR TABELA POUPANÇA!");
			e.printStackTrace();
		}
	}

	public void excluirTabelaContaCorrente() {
		try {
			String url = "jdbc:mariadb://localhost:3306/Banco";
			String user = "root";
			String password = "";

			Connection conn = DriverManager.getConnection(url, user, password);

			String sql1 = "DROP TABLE IF EXISTS tableCorrente;";
			try (PreparedStatement ps = conn.prepareStatement(sql1)) {
				ps.execute();
			}

			conn.close();

			System.out.println("✔ TABELA CONTA CORRENTE EXCLUIDA COM SUCESSO!");

		} catch (Exception e) {
			System.err.println("✖ ERRO AO EXCLUIR TABELA CORRENTE!");
			e.printStackTrace();
		}
	}

	public void excluirTabelaContaPoupanca() {
		try {
			String url = "jdbc:mariadb://localhost:3306/Banco";
			String user = "root";
			String password = "";

			Connection conn = DriverManager.getConnection(url, user, password);

			String sql1 = "DROP TABLE IF EXISTS tablePoupanca;";
			try (PreparedStatement ps = conn.prepareStatement(sql1)) {
				ps.execute();
			}

			conn.close();

			System.out.println("✔ TABELA CONTA POUPANÇA EXCLUIDA COM SUCESSO!");

		} catch (Exception e) {
			System.err.println("✖ ERRO AO EXCLUIR TABELA POUPANÇA!");
			e.printStackTrace();
		}
	}
}
