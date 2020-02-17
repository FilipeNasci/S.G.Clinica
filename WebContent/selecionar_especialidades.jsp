<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.Date" %>
<jsp:useBean id="conexao" scope="page" class="banco_dados.ConexaoBancoDados"/>
<jsp:useBean id="especialidade" scope="page" class="banco_dados.Especialidades"/>

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
		especialidade.configurarConexao(conexao.obterConexao());
		rsRegistros = especialidade.listarRegistros("DESCRIÇÃO");
		
		if(rsRegistros != null){
			out.println("<h2><center>Lista de especialidades médicas</center></h2>");
			out.println("<br>");
			out.println("<table align='center' bgcolor='moccasin'>");
			out.println("<tr><th>Especialidade médica</th><th></th><th></th></tr>");
			
			while(rsRegistros.next()){
				out.println("<tr>");
				out.println("<td>"+rsRegistros.getString("Descricao_Especialidade")+"</td");
				out.println("<td><a href='editar_especialidade.jsp?Codigo="+rsRegistros.getString("Codigo_Especialidade")+"'>Editar</a></td>");
				out.println("<td><a href='excluir_especialidade.jsp?Codigo="+rsRegistros.getString("Codigo_Especialidade")+"'>Excluir</a></td>");
				out.println("</tr>");
			}
			out.println("</table>");
			out.println("<br>");
		}else
			out.println("<h2>Falha na exibição dos registros!</h2>");
		
		conexao.fecharConexao();
	}else
		out.println("<h2>Falha na conexão com o banco de dados!</h2>");
%>
<span><a href="javascript:history.back()">[Voltar]</a></span>
</body>
</html>