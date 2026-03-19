package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Poupanca extends Conta {

	private double rendimento;

	public Poupanca() {
		super();
		this.rendimento = 0.0;
	}

	public double getRendimento() {
		return rendimento;
	}

	public void setRendimento(double rendimento) {
		this.rendimento = rendimento;
	}

	
	public void criarConta() {
        try {
           
            if (this.getSaldo() == null || this.getSaldo().trim().isEmpty()) {
                System.out.println("✖ Saldo não pode ser vazio ou nulo.");
                return;
            }

            String url = "jdbc:mariadb://localhost:3306/Banco";
            String user = "root";
            String password = "";

            
            double saldo = Double.parseDouble(this.getSaldo().trim());

            Connection conn = DriverManager.getConnection(url, user, password);

            String sql1 = "INSERT INTO tablePoupanca(AGENCIA , CONTA , TITULAR , SALDO , RENDIMENTO) VALUES (? , ? , ? , ? , ? );";
            PreparedStatement ps = conn.prepareStatement(sql1);
            ps.setString(1, this.getAgencia());
            ps.setString(2, this.getConta());
            ps.setString(3, this.getTitular());
            ps.setDouble(4, saldo);  
            ps.setDouble(5, this.getRendimento());

            ps.executeUpdate();

            conn.close();
            System.out.println("✔ CONTA POUPANÇA FEITA COM SUCESSO!");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("✖ ERRO AO CRIAR CONTA POUPANÇA.");
        }
    }



	public void excluirConta(String numeroDaConta) {
		
		try {
			String url = "jdbc:mariadb://localhost:3306/Banco";
			String user = "root";
			String password = "";

			Connection conn = DriverManager.getConnection(url, user, password);

			String sql1 = "DELETE FROM tablePoupanca WHERE CONTA = ?;";
			PreparedStatement ps = conn.prepareStatement(sql1);
			ps.setString(1, numeroDaConta);

			ps.executeUpdate();

			conn.close();
			System.out.println("✔ CONTA POUPANÇA DELETADA COM SUCESSO!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void localizarContaPoupanca(String contaProcurada) {
	    try {
	        String url = "jdbc:mariadb://localhost:3306/Banco";
	        String user = "root";
	        String password = "";

	        Connection conn = DriverManager.getConnection(url, user, password);

	        String sql = "SELECT AGENCIA, CONTA, TITULAR, SALDO, RENDIMENTO FROM tablePoupanca WHERE CONTA = ?;";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, contaProcurada);

	        
	        boolean encontrou = false;
	        var resultado = ps.executeQuery();

	        while (resultado.next()) {
	            encontrou = true;
	            System.out.println("✔ <CONTA POUPANÇA ENCONTRADA>:");
	            System.out.println("Agência: " + resultado.getString("AGENCIA"));
	            System.out.println("Conta: " + resultado.getString("CONTA"));
	            System.out.println("Titular: " + resultado.getString("TITULAR"));
	            System.out.println("Saldo: " + resultado.getDouble("SALDO"));
	            System.out.println("Rendimento: " + resultado.getDouble("RENDIMENTO"));
	        }

	        if (!encontrou) {
	            System.out.println("✖ NENHUMA CONTA POUPANÇA ENCONTRADA COM O NÚMERO: " + contaProcurada);
	        }

	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public void localizarEDepositarContaPoupanca(String contaDeposito, double valorDeposito) {
	    try {
	        String url = "jdbc:mariadb://localhost:3306/Banco";  
	        String user = "root";
	        String password = "";

	        Connection conn = DriverManager.getConnection(url, user, password);

	        
	        String sqlUpdate = "UPDATE tablePoupanca SET saldo = saldo + ? WHERE conta = ?;";
	        PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate);
	        psUpdate.setDouble(1, valorDeposito);  
	        psUpdate.setString(2, contaDeposito);  

	        
	        psUpdate.executeUpdate();
	        System.out.println("Depósito realizado com sucesso!");

	        conn.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	
	

}
