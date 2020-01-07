<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.ResultSet"  %>
<%@ page import="java.util.Date" %>
<%@ page import="model.C_funcionarios" %>
<jsp:useBean id="conexao" scope="page" class="banco_dados.ConexaoBancoDados"/>
<jsp:useBean id="funcionario" scope="page" class="banco_dados.Funcionarios"/>
<jsp:useBean id="converter" scope="page" class="util.Conversao"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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

<%
if(conectado){
%>
<p>SGC - Sistema de gestão de clínicas v 1.0</p>
<p>Cadastro de funcionários</p>

<form name="formEditaFuncionario" method="post" action="AtualizarFuncionario" target="_parent">
<p>Nome do funcionário:
	<input type="text" name="txtNomeFuncionario" size="50" maxlength="50" value="<%= cfuncionario.getNome()%>">
</p>
<p>Data de Nascimento (dd/mm/aaaa):
	<input type="text" name="txtDiaNascimento" size="2" maxlength="2" value="<%=cfuncionario.getDia_nascimento()%>">/
	<input type="text" name="txtMesNascimento" size="2" maxlength="2" value="<%=cfuncionario.getMes_nascimento()%>">/
	<input type="text" name="txtAnoNascimento" size="4" maxlength="4" value="<%=cfuncionario.getAno_nascimento()%>">
</p>

<%if(cfuncionario.getSexo().equals("M")){%>
<p>Sexo -
	<input type="radio" name="rbsexo" value="M" checked="checked">Masculino
	<input type="radio" name="rbsexo" value="F">Feminino
</p>
<%}else{%>
<p>Sexo -
	<input type="radio" name="rbsexo" value="M">Masculino
	<input type="radio" name="rbsexo" value="F" checked="checked">Feminino
</p>
<%}%>
<p>Numero do RG:
	<input type="text" name="txtRG" size="12" maxlength="12" value="<%=cfuncionario.getRg()%>">
	-Orgão emissor:
	<input type="text" name="txtOrgaoEmissor" size="6" maxlength="6" value="<%=cfuncionario.getOrgao_emissor()%>">
	-Número CPF:
	<input type="text" name="txtCPF" size="14" maxlength="14" value="<%=cfuncionario.getCpf()%>">
</p>
<p>Endereço/Número:
	<input type="text" name="txtEndereço" size="50" maxlength="50" value="<%=cfuncionario.getEndereco()%>">
	<input type="text" name="txtNumero" size="13" maxlength="13" value="<%=cfuncionario.getNumero()%>">
</p>
<p>Complemento:
	<input type="text" name="txtComplemento" size="30" maxlength="30" value="<%=cfuncionario.getComplemento()%>">
	-Bairro:
	<input type="text" name="txtBairro" size="20" maxlength="20" value="<%=cfuncionario.getBairro()%>">
</p>
<p>Cidade:
	<input type="text" name="txtCidade" size="30" maxlength="30" value="<%=cfuncionario.getCidade()%>">
	-Estado:
	<input type="text" name="txtEstado" size="2" maxlength="2" value="<%=cfuncionario.getEstado()%>">
</p>
<p>Telefone:
	<input type="text" name="txtTelefone" size="20" maxlength="20" value="<%=cfuncionario.getTelefone()%>">
	-Celular:
	<input type="text" name="txtCelular" size="20" maxlength="20" value="<%=cfuncionario.getCelular()%>">
</p>
<p>Número CTPS:
	<input type="text" name="txtCTPS" size="20" maxlength="20" value="<%=cfuncionario.getNumero_ctps()%>">
	-Número PIS:
	<input type="text" name="txtPis" size="20" maxlength="20" value="<%=cfuncionario.getNumero_pis()%>">
</p>
<p>
	<input type="hidden" name="codigo_funcionario" value="<%=codigoFuncionario%>">
</p>
<br>
<p>
	<input type="submit" name="btnAtualizar" value="Atualizar">
	<span><a href="javascript:history.back()">[Voltar]</a></span>
</p>
</form>
<%}%>

</body>
</html>