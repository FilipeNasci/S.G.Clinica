<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.Date" %>
<jsp:useBean id="conexao" scope="page" class="banco_dados.ConexaoBancoDados"/>
<jsp:useBean id="funcionario" scope="page" class="banco_dados.Funcionarios"/>
<jsp:useBean id="converter" scope="page" class="util.Conversao"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<title>SGC - v.1.0</title>
</head>
<body>
<%
ResultSet rsRegistros;

if(conexao.abrirConexao()){
	funcionario.configurarConexao(conexao.obterConexao());
	rsRegistros = funcionario.listarRegistros();
	
	if(rsRegistros != null){
		out.println("<h2>Lista de funcionários:</h2>");
		out.println("<br>");
		out.println("<table>");
		out.println("<tr><th>Nome</th><th>RG</th><th>CPF</th><th>Nascimento</th><th>Endereço</th></tr>");
		while(rsRegistros.next()){
			out.println("<tr>");
			out.println("<td>"+rsRegistros.getString("Nome_Completo")+"</td>");
			out.println("<td>"+rsRegistros.getString("Numero_RG")+"</td>");
			out.println("<td>"+rsRegistros.getString("Numero_CPF")+"</td>");
			out.println("<td>"+converter.DateToString(rsRegistros.getDate("Data_Nascimento"))+"</td>");
			out.println("<td>"+rsRegistros.getString("Endereco")+"</td>");
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("<br>");
	}else{
		out.println("<h2>Falha na exibição de registros!</h2>");
	}
		conexao.fecharConexao();
}else{
	out.println("<h2>Falha na conexão com o banco de dados!</h2>");
}
%>
<span><a href="javascript:history.back()">[Voltar]</a></span>
</body>
</html>