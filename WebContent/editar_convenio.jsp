<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.ResultSet"  %>
<%@ page import="model.C_convenios" %>
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
boolean conectado;

C_convenios cconvenio = new C_convenios();
int codigoConvenio = Integer.parseInt(request.getParameter("codigo"));
conectado = false;

if(conexao.abrirConexao()){
	convenio.configurarConexao(conexao.obterConexao());
	rsRegistros = convenio.lerRegistro(codigoConvenio);
	cconvenio.setNomeConvenio(rsRegistros.getString("Empresa_Convenio"));
	cconvenio.setCnpj(rsRegistros.getString("CNPJ"));
	cconvenio.setTelefone(rsRegistros.getString("Telefone"));
	conexao.fecharConexao();
	conectado = true;
}else
	out.println("<h2>Falha na conexão com o banco de dados!</h2>");
%>
<%if(conectado){ %>
<p>SGC - Sistema de gestão de clínicas v 1.0</p>
<p>Cadastro de convênios - Edição</p>
<form name="formEditaConvenio" method="post" action="AtualizarConvenio" target="_parent">
	<p>Nome do convenio: <input type="text" name="txtNomeConvenio" size="45" maxlength="45" value="<%=cconvenio.getNomeConvenio() %>"></p>
	<p>CNPJ: <input type="text" name="txtCNPJ" size="18" maxlength="18" value="<%=cconvenio.getCnpj()%>"></p>
	<p>Telefone: <input type="text" name="txtTelefone" size="20" maxlength="20" value="<%=cconvenio.getTelefone()%>"></p>
	<p><input type="hidden" name="codigo_convenio" value="<%=codigoConvenio%>"></p>
	<p>
	<input type="submit" name="btnAtualizar" value="Atualizar">
	<span><a href="javascript:history.back()">[Voltar]</a></span>
	</p>
</form>
<%} %>
</body>
</html>