package frontend;

import java.util.Scanner;
import backend.Conta;
import backend.Poupanca;
import backend.Corrente;

public class Principal {

    public static void main(String[] args) throws Exception { 

        Scanner ler = new Scanner(System.in);
        
        String op;
        Conta conta = new Conta(); 
        Poupanca poupanca = new Poupanca(); 
     
        
        do {
            System.out.println("MENU:");
            System.out.println("MODO ADMIN");
            System.out.println("1 - PARA CRIAR <BANCO DE DADOS CONTA BANCARIA>");
            System.out.println("2 - PARA EXCLUIR <BANCO DE DADOS CONTA BANCARIA>");
            System.out.println("3 - PARA CRIAR TABELA CONTA POUPANÇA");
            System.out.println("4 - PARA EXCLUIR <TABELA POUPANÇA>");
            System.out.println("5 - PARA CRIAR <TABELA CONTA CORRENTE>");
            System.out.println("6 - PARA EXCLUIR <TABELA CORRENTE>");
            
            System.out.println("\nMODO CLIENTE");
            System.out.println("7 - PARA CADASTRAR <CONTA POUPANÇA>");
            System.out.println("8 - PARA LOCALIZAR <CONTA POUPANÇA>");
            System.out.println("9 - PARA DEPOSITAR <CONTA POUPANÇA>");
            System.out.println("10 - PARA EXCLUIR <CONTA POUPANÇA>");
            
            System.out.println("\nS - PARA SAIR");
            
            op = ler.nextLine();
            
            switch(op) {
                case "1":
                    conta.criarBd();
                    break;
                case "2":
                    conta.deletarBd();
                    break;
                case "3":
                    conta.criarTabelaContaPoupanca();
                    break;
                case "4":
                    conta.excluirTabelaContaPoupanca();
                    break;
                case "5":
                    conta.criarTabelaContaCorrente();
                    break;
                case "6":
                    conta.excluirTabelaContaCorrente();
                    break;  
                case "7":
                    System.out.println("DIGITE OS DADOS PARA CRIAR A CONTA POUPANÇA:");
                    System.out.print("AGÊNCIA: ");
                    String agencia = ler.nextLine();
                    System.out.print("CONTA: ");
                    String contaNumero = ler.nextLine();
                    System.out.print("TITULAR: ");
                    String titular = ler.nextLine();
                    System.out.print("SALDO: ");
                    String saldo = ler.nextLine();
                    System.out.print("RENDIMENTO: ");
                    double rendimento = Double.parseDouble(ler.nextLine());

                    poupanca.setAgencia(agencia);
                    poupanca.setConta(contaNumero);
                    poupanca.setTitular(titular);
                    poupanca.setSaldo(saldo);
                    poupanca.setRendimento(rendimento);

                    poupanca.criarConta();
                    break;

                    
                case "8":
                    System.out.println("DIGITE O NÚMERO DA CONTA POUPANÇA QUE DESEJA LOCALIZAR:");
                    String contaProcurada = ler.nextLine();
                    poupanca.localizarContaPoupanca(contaProcurada);
                    break;

                case "9":
                  
                    System.out.println("DIGITE O NÚMERO DA CONTA POUPANÇA PARA DEPÓSITO:");
                    String contaDeposito = ler.nextLine();
                    System.out.println("DIGITE O VALOR A SER DEPOSITADO:");
                    double valorDeposito = Double.parseDouble(ler.nextLine());

                    poupanca.localizarEDepositarContaPoupanca(contaDeposito, valorDeposito);
                    break;

                case "10":
                    System.out.println("DIGITE O NÚMERO DA CONTA POUPANÇA QUE DESEJA EXCLUIR:");
                    String contaExcluir = ler.nextLine();
                    poupanca.excluirConta(contaExcluir);
                    break;

                case "s":
                    System.out.println("SAIU");
                    break;

                default:
                    System.out.println("OPÇÃO INVÁLIDA!");
            }
            
        } while(!op.equalsIgnoreCase("s"));
        
        ler.close();
    }
}
