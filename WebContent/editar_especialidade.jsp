<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.ResultSet"  %>
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
ResultSet rsRegistro;
boolean conectado;
String descricao = "";
int codigoEspecialidade = Integer.parseInt(request.getParameter("Codigo"));
conectado = false;

if(conexao.abrirConexao()){
	especialidade.configurarConexao(conexao.obterConexao());
	rsRegistro = especialidade.lerRegistro(codigoEspecialidade);
	descricao = rsRegistro.getString("Descricao_Especialidade");
	conexao.fecharConexao();
	conectado = true;
}else{
	out.println("<p>Falha na conexão com o banco de dados!");
}
%>

<%if(conectado){%>
<p>SGC - Sistema de gestão de clínicas v 1.0</p>
<p>Cadastro de funcionários</p>
<form name="formEditarEspecialidade" method="post" action="AtualizarEspecialidade" target="_parent">
	<p>Descrição: <input type="text" name="txtDescricao" size="45" maxlength="45" value="<%=descricao%>"></p>
	<br>
	<p><input type="submit" name="btnAtualizar" value="Atualizar">
	<span><a href="javascript:history.back()">[Voltar]</a></span></p>
	<p><input type="hidden" name="codigo_especialidade" value="<%=codigoEspecialidade%>"></p>
</form>
<%}%>
</body>
</html>