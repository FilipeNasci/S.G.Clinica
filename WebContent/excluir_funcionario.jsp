<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.Date" %>
<%@ page import="model.C_funcionarios" %>
<jsp:useBean id="conexao" scope="page" class="banco_dados.ConexaoBancoDados"/>
<jsp:useBean id="funcionario" scope="page" class="banco_dados.Funcionarios" />
<jsp:useBean id="converter" scope="page" class="util.Conversao"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<title>SGC - v.1.0</title>
</head>
<body>
<%
ResultSet rsRegistro;
Date dtDataNascimento;
int dia, mes, ano;
boolean conectado = false;

C_funcionarios cfuncionario = new C_funcionarios();
int codigoFuncionario = Integer.parseInt(request.getParameter("codigo_funcionario"));

if(conexao.abrirConexao()){
	funcionario.configurarConexao(conexao.obterConexao());
	rsRegistro = funcionario.lerRegistro(codigoFuncionario);
	dtDataNascimento = rsRegistro.getDate("Data_Nascimento");
	
	dia = converter.DiaData(dtDataNascimento);
	mes = converter.MesData(dtDataNascimento);
	ano = converter.AnoData(dtDataNascimento);
	
	cfuncionario.setNome(rsRegistro.getString("Nome_Completo"));
	cfuncionario.setRg(rsRegistro.getString("Numero_RG"));
	cfuncionario.setOrgao_emissor(rsRegistro.getString("Orgao_Emissor"));
	cfuncionario.setCpf(rsRegistro.getString("Numero_CPF"));
	cfuncionario.setEndereco(rsRegistro.getString("Endereco"));
	cfuncionario.setNumero(rsRegistro.getString("Numero"));
	cfuncionario.setComplemento(rsRegistro.getString("Complemento"));
	cfuncionario.setBairro(rsRegistro.getString("Bairro"));
	cfuncionario.setCidade(rsRegistro.getString("Cidade"));
	cfuncionario.setEstado(rsRegistro.getString("Estado"));
	cfuncionario.setTelefone(rsRegistro.getString("Telefone"));
	cfuncionario.setCelular(rsRegistro.getString("Celular"));
	cfuncionario.setNumero_ctps(rsRegistro.getString("Numero_CTPS"));
	cfuncionario.setNumero_pis(rsRegistro.getString("Numero_PIS"));
	cfuncionario.setDataNascimento(Integer.toString(dia),Integer.toString(mes), Integer.toString(ano));
	cfuncionario.setSexo(rsRegistro.getString("Sexo"));
	
	conexao.fecharConexao();
	conectado = true;
}else{
	out.println("<p>Falha na conexão com o banco de dados!</p>");
}
%>

<%if(conectado){%>
	<h1>SGC - Sistema de Gestão de Clínicas</h1>
	<h2>Cadastro de funcionários - Exclusão</h2>
	<form name="formExcluirFuncionario" method="post" action="ExcluirFuncionario" target="_parent">
		<p>Nome do funcionário: <%=cfuncionario.getNome() %></p>
		<p>Data de nascimento: <%=cfuncionario.getDia_nascimento()%>/<%=cfuncionario.getMes_nascimento()%>/<%=cfuncionario.getAno_nascimento()%></p>
		<p>Sexo: <%=cfuncionario.getSexo() %></p>
		<p>RG: <%=cfuncionario.getRg() %> - Orgão emissor: <%=cfuncionario.getOrgao_emissor() %> - CPF: <%=cfuncionario.getCpf() %></p>
		<p>Endereço/Número: <%=cfuncionario.getEndereco() %>, <%=cfuncionario.getNumero() %></p>
		<p>Complemento: <%=cfuncionario.getComplemento() %></p>
		<p>Bairro: <%=cfuncionario.getBairro() %></p>
		<p>Cidade: <%=cfuncionario.getCidade() %></p>
		<p>Telefone: <%=cfuncionario.getTelefone() %></p>
		<p>CTPS: <%=cfuncionario.getNumero_ctps() %> - PIS: <%=cfuncionario.getNumero_pis() %></p>
		<p><input type="hidden" name="codigo_funcionario" value="<%=codigoFuncionario%>"></p>
		<br>
		<p><input type="submit" name="btnExcluir" value="Excluir">
			<span><a href="javascript:history.back()">[Voltar]</a></span></p>
	</form>
<%} %>
</body>
</html>