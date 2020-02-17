<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.ResultSet"  %>
<%@ page import="model.C_convenios"  %>
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
ResultSet rsRegistro;
boolean conectado;

C_convenios cconvenio = new C_convenios();
int codigoConvenio =Integer.parseInt(request.getParameter("codigo"));
conectado = false;

if(conexao.abrirConexao()){
	convenio.configurarConexao(conexao.obterConexao());
	rsRegistro = convenio.lerRegistro(codigoConvenio);
	cconvenio.setNomeConvenio(rsRegistro.getString("Empresa_Convenio"));
	cconvenio.setCnpj(rsRegistro.getString("CNPJ"));
	cconvenio.setTelefone(rsRegistro.getString("Telefone"));
	cconvenio.setCodigoConvenio(Integer.parseInt(rsRegistro.getString("Codigo_Convenio")));
	
	conexao.fecharConexao();
	conectado = true;
}else
	out.println("<h2>Falha na conexão com o bando de dados!</h2>");
%>
<%if(conectado){ %>
<p>SGC - Sistema de gestão de clínicas v 1.0</p>
<p>Cadastro de convênios</p>
<form name="formExcluiConvenio" method="post" action="ExcluirConvenio" target="_parent">
	<p>Nome do convênio: <%=cconvenio.getNomeConvenio() %></p>
	<p>CNPJ: <%=cconvenio.getCnpj() %></p>
	<p>Telefone: <%=cconvenio.getTelefone() %></p>
	<p><input type="hidden" name="codigo_convenio" value="<%=codigoConvenio%>"></p>
	<br>
	<p><input type="submit" name="btnAtualizar" value="Atualizar">
	<span><a href="javascript:history.back()">[Voltar]</a></span></p>
</form>
<%} %>
</body>
</html>