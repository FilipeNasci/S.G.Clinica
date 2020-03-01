package banco_dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.C_medicos;

public class Medicos {
	private Connection conBanco;
	private PreparedStatement psComando;
	private ResultSet rsRegistros;

	public void configurarConexao(Connection conBanco) {
		this.conBanco = conBanco;
	}

	public boolean inserirRegistro(C_medicos medico) {
		String comando;

		try {
			comando = "INSERT INTO medicos (Nome_Medico, CRM, Codigo_Especialidade) VALUES ('"+
					medico.getNome_medico()+"', '"+
					medico.getCrm()+"', '"+
					medico.getCodigo_especialidade()+"')";
			psComando = conBanco.prepareStatement(comando);
			psComando.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public ResultSet lerRegistro(int codigo_medico) {
		String comando;

		try {
			comando = "SELECT * FROM medicos WHERE Codigo_Medico = "+codigo_medico;
			psComando = conBanco.prepareStatement(comando);
			rsRegistros = psComando.executeQuery();
			rsRegistros.next();
			return rsRegistros;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean alterarRegistro(C_medicos medico) {
		String comando;
		
		try {
			comando = "UPDATE medicos SET Nome_medico = '"+ medico.getNome_medico()+"', CRM = '"+ medico.getCrm()+
					"', Codigo_Especialidade = '"+medico.getCodigo_especialidade()+"' WHERE Codigo_Medico = "+medico.getCodigo_medico();
			psComando = conBanco.prepareStatement(comando);
			psComando.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean excluirRegistro(int codigo_medico) {
		String comando;
		
		try {
			comando = "DELETE FROM medicos WHERE Codigo_Medico = "+codigo_medico;
			psComando = conBanco.prepareStatement(comando);
			psComando.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public ResultSet listarRegistros() {
		String comando;
		
		try {
			comando = "SELECT * FROM medicos ORDER BY Nome_Medico";
			psComando = conBanco.prepareStatement(comando);
			rsRegistros = psComando.executeQuery();
			return rsRegistros;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ResultSet listarMedicosEspecialidades() {
		String comando;
		
		try {
			comando = "SELECT medicos.Codigo_Medico AS CodigoMedico, medicos.Nome_Medico AS Medico, medicos.Codigo_Especialidade AS "+
					"MedicoEspecialidade, especialidades.Codigo_Especialidade AS CodigoEspecialidade, especialidades.Descricao_Especialidade AS "+
					"Especialidade FROM medicos, especialidades WHERE medicos.Codigo_Especialidade = especialidades.Codigo_Especialidade "+
					"ORDER BY medicos.Nome_Medico";
			psComando = conBanco.prepareStatement(comando);
			rsRegistros = psComando.executeQuery();
			return rsRegistros;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
