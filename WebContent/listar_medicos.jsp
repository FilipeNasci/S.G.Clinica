<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.ResultSet"%>
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
		ResultSet rsRegistros;
		if (conexao.abrirConexao()) {
			medico.configurarConexao(conexao.obterConexao());
			rsRegistros = medico.listarMedicosEspecialidades();

			if (rsRegistros != null) {
				out.println("<h2><center>Lista de médicos</center></h2>");
				out.println("<br>");
				out.println("<table align='center'>");
				out.println("<tr><th>Nome do médico:</th><th>Especialidade</th></tr>");

				while (rsRegistros.next()) {
					out.println("<tr>");
					out.println("<td>" + rsRegistros.getString("Medico") + "</td>");
					out.println("<td>" + rsRegistros.getString("Especialidade") + "</td>");
					out.println("</tr>");
				}

				out.println("</table>");
				out.println("<br>");
			} else
				out.println("<h2>Falha na exibição dos registros!</h2>");
		} else
			out.println("<h2>Falha na conexão com o banco de dados!</h2>");
	%>
	<span><a href="javascript:history.back()">[Voltar]</a></span>
</body>
</html>