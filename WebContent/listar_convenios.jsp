<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="model.C_convenios"%>
<jsp:useBean id="conexao" scope="page"
	class="banco_dados.ConexaoBancoDados" />
<jsp:useBean id="convenio" scope="page" class="banco_dados.Convenios" />
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SGC - v.1.0</title>
</head>
<body>
	<%
		ResultSet rsRegistros;

		if (conexao.abrirConexao()) {
			convenio.configurarConexao(conexao.obterConexao());
			rsRegistros = convenio.listarRegistros();
			if (rsRegistros != null) {
				out.println("<h2>Lista de convênios médicos:</h2>");
				out.println("<br>");
				out.println("<table>");
				out.println("<tr><th>Convênio</th><th>CNPJ</th><th>Telefone</th></tr>");
				while (rsRegistros.next()) {
					out.println("<br>");
					out.println("<td>" + rsRegistros.getString("Empresa_Convenio") + "</td>");
					out.println("<td>" + rsRegistros.getString("CNPJ") + "</td>");
					out.println("<td>" + rsRegistros.getString("Telefone") + "</td>");
				}
				out.println("</table>");
				out.println("<br>");
			} else
				out.println("<h2>Falha na exibição de registros!</h2>");

			conexao.fecharConexao();
		} else
			out.println("<h2>Falha na conexão com o banco de dados!</h2>");
	%>
	<span><a href="javascript:history.back()">[Voltar]</a></span>
</body>
</html>