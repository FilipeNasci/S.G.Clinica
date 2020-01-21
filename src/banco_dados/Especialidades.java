package banco_dados;

import java.sql.*;

public class Especialidades {
	private Connection conBanco;
	private PreparedStatement psComando;
	private ResultSet rsRegistros;

	public void configurarConexao(Connection conBanco) {
		this.conBanco = conBanco;
	}

	public boolean inserirRegistro(String strDescricao) {
		String strComandoSQL;

		try {
			strComandoSQL = "INSERT INTO especialidades(Descricao_Especialidade) VALUES "
					+ "('"+strDescricao+"')";
			psComando = conBanco.prepareStatement(strComandoSQL);
			psComando.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean alterarRegistro(int intCodigo, String strDescricao) {
		String strComandoSQL;

		try {
			strComandoSQL = "UPDATE especialidades SET Descricao_Especialidade = '"+strDescricao+"'WHERE"
					+ "Codigo_Especialidade = "+intCodigo;
			psComando = conBanco.prepareStatement(strComandoSQL);
			psComando.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean excluirRegistro(int intCodigo) {
		String strComandoSQL;

		try {
			strComandoSQL = "DELETE FROM especialidades WHERE Codigo_Especialidade = "+intCodigo;
			psComando = conBanco.prepareStatement(strComandoSQL);
			psComando.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;			
		}
	}

	public ResultSet listarRegistros(String strOrdem) {
		String strComandoSQL;

		try {
			if(strOrdem == "CÓDIGO")
				strComandoSQL = "SELECT * FROM especialidades ORDER BY Codigo_Especialidade";
			else
				strComandoSQL = "SELECT * FROM especialidades ORDER BY Descricao_Especialidade";
			psComando = conBanco.prepareStatement(strComandoSQL);
			rsRegistros = psComando.executeQuery();
			return rsRegistros;
		} catch (Exception e) {
			e.printStackTrace();
			return null;	
		}
	}
	
	public ResultSet lerRegistro(int codigoEspecialidade) {
		String comandoSQL;
		
		try {
			comandoSQL = "SELECT * FROM especialidades WHERE Codigo_Especialidade = " + codigoEspecialidade;
			
			psComando = conBanco.prepareStatement(comandoSQL);
			rsRegistros = psComando.executeQuery();
			rsRegistros.next();
			
			return rsRegistros;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
