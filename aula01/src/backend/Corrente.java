package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Corrente extends Conta {

    private double limite;

   
    public Corrente() {
        super();
    }

  
    public Corrente(String agencia, String conta, String titular, Double saldo, double limite) {
        super();
        this.limite = limite;
    }

   
    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

   
    public void criarConta() {
    	
        try {
            String url = "jdbc:mariadb://localhost:3306/Banco";
            String user = "root";
            String password = "";

            
            Connection conn = DriverManager.getConnection(url, user, password);

            
            String sql1 = "INSERT INTO tableCorrente(AGENCIA, CONTA , TITULAR , SALDO , LIMITE) VALUES (?, ?, ?, ?, ? );";
            PreparedStatement ps = conn.prepareStatement(sql1);
            ps.setString(1, this.getAgencia());
            ps.setString(2, this.getConta());
            ps.setString(3, this.getTitular());
            ps.setDouble(4, Double.parseDouble(this.getSaldo())); 
            ps.setDouble(5, this.getLimite());

       
            ps.executeUpdate();

            conn.close();
            System.out.println("✔ CONTA CORRENTE CRIADA COM SUCESSO!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

  
    public void excluirConta(String numeroDaConta) {
    	
        try {
            String url = "jdbc:mariadb://localhost:3306/Banco";
            String user = "root";
            String password = "";

 
            Connection conn = DriverManager.getConnection(url, user, password);


            String sql1 = "DELETE FROM tableCorrente WHERE CONTA = ?;";
            PreparedStatement ps = conn.prepareStatement(sql1);
            ps.setString(1, numeroDaConta);

           
            ps.executeUpdate();

            conn.close();
            System.out.println("✔ CONTA CORRENTE DELETADA COM SUCESSO!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
