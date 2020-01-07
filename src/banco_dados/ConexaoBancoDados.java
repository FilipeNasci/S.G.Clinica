package banco_dados;

import java.sql.*;

public class ConexaoBancoDados {
	Connection conBanco;
	
	public boolean abrirConexao() {
		String url = "jdbc:mysql://localhost/clinica_medica?user=root&password=";
		
		//String url = "jdbc:mysql://localhost/clinica_medica?autoReconnect=true&useSSL=false";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conBanco = DriverManager.getConnection(url);
			//conBanco = DriverManager.getConnection(url, "root", "");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERRO");
			return false;
		}
	}
	
	public boolean fecharConexao() {
		try {
			conBanco.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public Connection obterConexao() {
		return conBanco;
	}
}
