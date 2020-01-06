package banco_dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import model.C_funcionarios;
import util.Conversao;

public class Funcionarios {
	private Connection conBanco;
	private PreparedStatement psComando;
	private ResultSet rsRegistros;

	public void configurarConexao(Connection conBanco) {
		this.conBanco = conBanco;
	}

	public boolean inserirRegistro(C_funcionarios funcionario) {
		Conversao converter = new Conversao();
		Date dtDataNascimento;
		String strDataInvertida, strComandoSQL;

		try {
			dtDataNascimento = converter.StringToDate(funcionario.getDataNascimento());

			if(dtDataNascimento != null) 
				strDataInvertida = converter.DataInvertida(dtDataNascimento);
			else 
				strDataInvertida = null;


			if(!(strDataInvertida.equals("null")))
				strDataInvertida = "'"+strDataInvertida+"'";

			strComandoSQL = "INSERT INTO funcionarios(Nome_Completo, Numero_RG, Orgao_Emissor, "
					+ "Numero_CPF, Endereco, Numero, Complemento, Bairro, Cidade, Estado, Telefone,"
					+ "Celular, Numero_CTPS, Numero_PIS, Sexo, Data_Nascimento) VALUES('"+
					funcionario.getNome()+"','"+
					funcionario.getRg()+"','"+
					funcionario.getOrgao_emissor()+"','"+
					funcionario.getCpf()+"','"+
					funcionario.getEndereco()+"','"+
					funcionario.getNumero()+"','"+
					funcionario.getComplemento()+"','"+
					funcionario.getBairro()+"','"+
					funcionario.getCidade()+"','"+
					funcionario.getEstado()+"','"+
					funcionario.getTelefone()+"','"+
					funcionario.getCelular()+"','"+
					funcionario.getNumero_ctps()+"','"+
					funcionario.getNumero_pis()+"','"+
					funcionario.getSexo()+"',"+
					strDataInvertida+")";

			psComando = conBanco.prepareStatement(strComandoSQL);
			psComando.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public int localizarRegistro(String strCampo, String strValor) {
		int intCodigoFuncionario = 0;
		String strComandoSQL;

		try {
			if(strCampo.equals("NOME")) 
				strComandoSQL = "SELECT codigo_funcionario FROM funcionarios WHERE nome like '%"+strValor+"%'";
			else if(strCampo.equals("RG"))
				strComandoSQL = "SELECT codigo_funcionario FROM funcionarios WHERE rg like '%"+strValor+"%'";
			else
				strComandoSQL = "SELECT codigo_funcionario FROM funcionarios WHERE cpf like '%"+strValor+"%'";

			psComando = conBanco.prepareStatement(strComandoSQL);
			rsRegistros = psComando.executeQuery();
			rsRegistros.next();

			intCodigoFuncionario = rsRegistros.getInt("codigo_funcionario");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return intCodigoFuncionario;
	}

	public ResultSet lerRegistro(int intCodigoFuncionario) {
		String strComandoSQL;
		try {
			strComandoSQL = "SELECT * FROM funcionarios WHERE codigo_funcionario = " + intCodigoFuncionario;
			psComando = conBanco.prepareStatement(strComandoSQL);
			rsRegistros = psComando.executeQuery();
			rsRegistros.next();
			return rsRegistros;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean alterarRegistro(C_funcionarios funcionario) {
		Conversao converter = new Conversao();
		Date dtDataNascimento;
		String strDataInvertida, strComandoSQL;

		try {
			dtDataNascimento = converter.StringToDate(funcionario.getDataNascimento());
			if(dtDataNascimento != null)
				strDataInvertida = converter.DataInvertida(dtDataNascimento);
			else
				strDataInvertida = "null";

			if(!(strDataInvertida.equals("null")))
				strDataInvertida = "'" + strDataInvertida + "'";

			strComandoSQL = "UPDATE funcionarios SET nome = '" + funcionario.getNome() + "', " +
					"rg = '" + funcionario.getRg() + "', " +
					"orgao_emissor = '" + funcionario.getOrgao_emissor() + "', " +
					"cpf = '" + funcionario.getCpf() + "', " +
					"endereco = '" + funcionario.getEndereco() + "', " +
					"numero = '" + funcionario.getNumero() + "', " +
					"complemento = '" + funcionario.getComplemento() + "', " +
					"bairro = '" + funcionario.getBairro() + "', " +
					"cidade = '" + funcionario.getCidade() + "', " +
					"estado = '" + funcionario.getEstado() + "', " +
					"telefone = '" + funcionario.getTelefone() + "', " +
					"celular = '" + funcionario.getCelular() + "', " +
					"numero_ctps = '" + funcionario.getNumero_ctps() + "', " +
					"numero_pis = '" + funcionario.getNumero_pis() + "', " +
					"sexo = '" + funcionario.getSexo() + "', " +
					"data_nascimento = '" + strDataInvertida +
					"WHERE codigo_funcionario = " + funcionario.getCodigo_funcionario();
			
			psComando = conBanco.prepareStatement(strComandoSQL);
			psComando.executeQuery();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean excluirRegistro(int intCodigoFuncionario) {
		String comandoSQL;
		
		try {
			comandoSQL = "DELETE FROM funcionarios WHERE condigo_funcionario = " + intCodigoFuncionario;
			psComando = conBanco.prepareStatement(comandoSQL);
			psComando.executeQuery();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public ResultSet listarRegistros() {
		String comandoSQL;
		
		try {
			comandoSQL = "SELECT * FROM funcionarios ORDER BY nome";
			psComando = conBanco.prepareStatement(comandoSQL);
			rsRegistros = psComando.executeQuery();
			return rsRegistros;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
