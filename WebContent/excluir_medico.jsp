<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="model.C_medicos"%>
<jsp:useBean id="conexao" scope="page"
	class="banco_dados.ConexaoBancoDados" />
<jsp:useBean id="medico" scope="page" class="banco_dados.Medicos" />
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SGC - v.1.0</title>
</head>
<body>
	<%
		ResultSet rsRegistro;
		boolean conectado = false;

		C_medicos cmedico = new C_medicos();
		int codigoMedico = Integer.parseInt(request.getParameter("Codigo"));

		if (conexao.abrirConexao()) {
			rsRegistro = medico.lerRegistro(codigoMedico);
			cmedico.setNome_medico(rsRegistro.getString("Nome_Medico"));
			cmedico.setCrm(rsRegistro.getString("CRM"));

			conexao.fecharConexao();
			conectado = true;
		}
	%>
	<%
		if (conectado) {
	%>
	<p>SGC - Sistema de gestão de clínicas v 1.0</p>
	<p>Cadastro de médicos</p>

	<form name="formExcluiMedico" method="post" action="ExcluirMedico"
		target="_parent">
		<p>
			Nome do médico:
			<%=cmedico.getNome_medico()%></p>
		<p>
			CRM:
			<%=cmedico.getCrm()%></p>
		<p>
			<input type="hidden" name="codigo_medico" value="<%=codigoMedico%>">
		</p>
		<br>
		<p>
			<input type="submit" name="btnExcluir" value="Excluir"> <span><a
				href="javascript:history.back()">[Voltar]</a></span>
		</p>
	</form>
	<%}%>

</body>
</html>