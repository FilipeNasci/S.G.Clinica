<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@ page import="java.sql.ResultSet"%>
<jsp:useBean id="conexao" scope="page" class="banco_dados.ConexaoBancoDados" />
<jsp:useBean id="medico" scope="page" class="banco_dados.Medicos" />

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SGC - v.1.0</title>
</head>
<body>
	<%
		ResultSet registros;

		if (conexao.abrirConexao()) {
			medico.configurarConexao(conexao.obterConexao());
			registros = medico.listarMedicosEspecialidades();
			if (registros != null) {
				out.println("<h2>Lista de médicos</h2>");
				out.println("<br>");
				out.println("<table>");
				out.println("<tr><th>Nomedo do médico</th><th>Especialidade</th><th></th></tr>");
				while (registros.next()) {
					out.println("<tr>");
					out.println("<td>" + registros.getString("Medico") + "</td>");
					out.println("<td>" + registros.getString("Especialidade") + "</td>");
					out.println("<td><a href='excluir_medico.jsp?Codigo=" + registros.getString("CodigoMedico")
							+ "'>Excluir</a></td>");
					out.println("</tr>");
				}
				out.println("</table>");
				out.println("<br>");
			} else
				out.println("<p>Falha na exibição dos registros</p>");

			conexao.fecharConexao();
		} else
			out.println("<p>Falha na conexão com o bando de dados!</p>");
	%>
	<span><a href="javascript:history.back()">[Voltar]</a></span>
</body>
</html>