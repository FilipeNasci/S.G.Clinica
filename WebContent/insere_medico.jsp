<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet" %>
<jsp:useBean id="conexao" scope="page" class="banco_dados.ConexaoBancoDados"/>
<jsp:useBean id="especialidade" scope="page" class="banco_dados.Especialidades"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SGC - Versão 1.0</title>
</head>
<body>
	<h2>Cadastro de médicos</h2>
	
	<form name="formInsereMedico" method="post" action="InserirMedico" target="_parent">
		<p>Nome do médico: <input type="text" name="txtNomeMedico" size="50" maxlength="50"></p>
		<p>CRM: <input type="text" name="txtCRM" size="20" maxlength="20"></p>
		<p>Especialidade:
			<select name="lstEspecialidade">
				<%
				ResultSet registros;
				if(conexao.abrirConexao()){
					especialidade.configurarConexao(conexao.obterConexao());
					registros = especialidade.listarRegistros("DESCRIÇÂO");
					if(registros != null){
						while(registros.next()){
							//verificar tag option
							out.println("<option value='"+registros.getString("Codigo_Especialidade")+"'>"+
							registros.getString("Descricao_Especialidade")+"</option>");
						}
						conexao.fecharConexao();
					}
				}
				%>
			</select>
		</p>
		<br>
		<p><input type="submit" name="btnGravar" value="Gravar">
		<span><a href="javascript:history.back()">[Voltar]</a></span>
		</p>
		
	</form>

</body>
</html>