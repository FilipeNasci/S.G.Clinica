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
	dtDataNascimento = rsRegistro.getDate("data_nascimento");
	
	dia = converter.DiaData(dtDataNascimento);
	mes = converter.MesData(dtDataNascimento);
	ano = converter.AnoData(dtDataNascimento);
	
	cfuncionario.setNome(rsRegistro.getString("nome"));
	cfuncionario.setRg(rsRegistro.getString("rg"));
	cfuncionario.setOrgao_emissor(rsRegistro.getString("orgao_emissor"));
	cfuncionario.setCpf(rsRegistro.getString("cpf"));
	cfuncionario.setEndereco(rsRegistro.getString("endereco"));
	cfuncionario.setNumero(rsRegistro.getString("numero"));
	cfuncionario.setComplemento(rsRegistro.getString("complemento"));
	cfuncionario.setBairro(rsRegistro.getString("bairro"));
	cfuncionario.setCidade(rsRegistro.getString("cidade"));
	cfuncionario.setEstado(rsRegistro.getString("estado"));
	cfuncionario.setTelefone(rsRegistro.getString("telefone"));
	cfuncionario.setCelular(rsRegistro.getString("celular"));
	cfuncionario.setNumero_ctps(rsRegistro.getString("numero_ctps"));
	cfuncionario.setNumero_pis(rsRegistro.getString("numero_pis"));
	cfuncionario.setDataNascimento(Integer.toString(dia),Integer.toString(mes), Integer.toString(ano));
	cfuncionario.setSexo(rsRegistro.getString("sexo"));
	
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
		<p><input type="hidden" name="codigo_funcionario" value="<%=cfuncionario.getCodigo_funcionario()%>"></p>
		<br>
		<p><input type="submit" name="btnExcluir" value="Excluir">
			<span><a href="javascript:history.back()">[Voltar]</a></span></p>
	</form>
<%} %>
</body>
</html>