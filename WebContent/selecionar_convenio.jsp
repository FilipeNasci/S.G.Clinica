<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.Date" %>
<jsp:useBean id="conexao" scope="page" class="banco_dados.ConexaoBancoDados"/>
<jsp:useBean id="convenio" scope="page" class="banco_dados.Convenios"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SGC - v.1.0</title>
</head>
<body>
<%
ResultSet rsRegistros;

if(conexao.abrirConexao()){
	convenio.configurarConexao(conexao.obterConexao());
	rsRegistros = convenio.listarRegistros();
	if(rsRegistros != null){
		out.println("<h2>Lista de convênios médicos</h2>");
		out.println("<br>");
		out.println("<table>");
		out.println("<tr><th>Convênio</th><th>CNPJ</th><th>Telefone</th></tr>");
		while(rsRegistros.next()){
			out.println("<tr>");
			out.println("<td>"+rsRegistros.getString("Empresa_Convenio")+"</th>");
			out.println("<td>"+rsRegistros.getString("CNPJ")+"</th>");
			out.println("<td>"+rsRegistros.getString("Telefone")+"</th>");
			out.println("<td><a href='editar_convenio.jsp?codigo="+rsRegistros.getString("Codigo_Convenio")+"'>Editar</a></td>");
			out.println("<td><a href='excluir_convenio.jsp?codigo="+rsRegistros.getString("Codigo_Convenio")+"'>Excluir</a></td>");
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("<br>");
	}else
		out.println("<h2>Falha na exibição de resgistros!</h2>");
}else
	out.println("<h2>Falha na conexão com o banco de dados!</h2>");
%>

<span><a href="javascript:history.back()">[Voltar]</a></span>
</body>
</html>