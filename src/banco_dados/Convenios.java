package banco_dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.C_convenios;

public class Convenios {
	private Connection conBanco;
	private PreparedStatement psComando;
	private ResultSet rsRegistros;
	
	public void configurarConexao(Connection conBanco) {
		this.conBanco = conBanco;
	}
	
	public boolean inserirRegistro(C_convenios convenio) {
		String comandoSQL;
		
		try {
			comandoSQL = "INSERT INTO convenios (Empresa_Convenio, CNPJ, Telefone) VALUES ('"+convenio.getNomeConvenio()+
					"','"+convenio.getCnpj()+
					"','"+convenio.getTelefone()+"')";
			psComando = conBanco.prepareStatement(comandoSQL);
			psComando.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public ResultSet lerRegistro(int codigoConvenio) {
		String comandoSQL;
		
		try {
			comandoSQL = "SELECT * FROM convenios WHERE Codigo_Convenio = "+codigoConvenio;
			psComando = conBanco.prepareStatement(comandoSQL);
			rsRegistros = psComando.executeQuery();
			rsRegistros.next();
			return rsRegistros;			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean alterarRegistro(C_convenios convenio) {
		String comandoSQL;
		
		try {
			comandoSQL = "UPDATE convenios SET Empresa_Convenio = '"+convenio.getNomeConvenio()+"', CNPJ = '"+convenio.getCnpj()+
					"', Telefone = '"+convenio.getTelefone()+"' WHERE Codigo_Convenio = "+convenio.getCodigoConvenio();
			psComando = conBanco.prepareStatement(comandoSQL);
			psComando.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean excluirRegistro(int codigoConvenio) {
		String comandoSQL;
		
		try {
			comandoSQL = "DELETE FROM convenios WHERE Codigo_Convenio = "+codigoConvenio;
			psComando = conBanco.prepareStatement(comandoSQL);
			psComando.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public ResultSet listarRegistros() {
		String comandoSQL;
		
		try {
			comandoSQL = "SELECT * FROM convenios ORDER BY Empresa_Convenio";
			psComando = conBanco.prepareStatement(comandoSQL);
			rsRegistros = psComando.executeQuery();
			return rsRegistros;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
