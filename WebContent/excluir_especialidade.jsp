<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.ResultSet" %>
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
boolean conectado;
String descricao = "";
int codigoEspecialidade = Integer.parseInt(request.getParameter("Codigo"));
conectado = false;

if(conexao.abrirConexao()){
	especialidade.configurarConexao(conexao.obterConexao());
	rsRegistros = especialidade.lerRegistro(codigoEspecialidade);
	descricao = rsRegistros.getString("Descricao_Especialidade");
	conexao.fecharConexao();
	conectado = true;
}else{
	out.println("<h2>Falha na conexão com o banco de dados!<h2>");
}
%>
<%if(conectado){ %>
<p>SGC - Sistema de gestão de clínicas v 1.0</p>
<p>Cadastro de funcionários</p>
<form name="formExcluirEspecialidade" method="post" action="ExcluirEspecialidade" target="_parent">
	<p>Descrição da especialidade: <%=descricao %></p>
	<p><input type="hidden" name="codigo_especialidade" value="<%=codigoEspecialidade %>"></p>
	<br>
	<p><input type="submit" name="btnExcluir" value="Excluir">
	<span><a href="javascript:history.back()">[Voltar]</a></span></p>
</form>
<%} %>
</body>
</html>