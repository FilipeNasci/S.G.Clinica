<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="model.C_usuarios" %>
<jsp:useBean id="conexao" scope="page" class="banco_dados.ConexaoBancoDados"/>
<jsp:useBean id="usuario" scope="page" class="banco_dados.Usuarios" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<title>SGC - v.1.0</title>
</head>
<body>
<%
ResultSet rsRegistro;
boolean blnConectado;

C_usuarios Xusuario = new C_usuarios();
int intCodigoUsuario = Integer.parseInt(request.getParameter("codigo_usuario"));
blnConectado = false;

if(conexao.abrirConexao()){
	usuario.configurarConexao(conexao.obterConexao());
	
	rsRegistro = usuario.lerRegistro(intCodigoUsuario);
	Xusuario.setIdUsuario(rsRegistro.getString("Identificacao_Usuario"));
	Xusuario.setSenhaAcesso(rsRegistro.getString("Senha_Acesso"));
	Xusuario.setCadastroFuncionario(rsRegistro.getString("Cadastro_Funcionario"));
	Xusuario.setCadastroUsuario(rsRegistro.getString("Cadastro_Usuario"));
	Xusuario.setCadastroPaciente(rsRegistro.getString("Cadastro_Paciente"));
	Xusuario.setCadastroEspecialidade(rsRegistro.getString("Cadastro_Especialidade"));
	Xusuario.setCadastroMedico(rsRegistro.getString("Cadastro_Medico"));
	Xusuario.setCadastroConvenio(rsRegistro.getString("Cadastro_Convenio"));
	Xusuario.setAgendamentoConsulta(rsRegistro.getString("Agendamento_Consulta"));
	Xusuario.setCancelamentoConsulta(rsRegistro.getString("Cancelamento_Consulta"));
	Xusuario.setModuloAdministrativo(rsRegistro.getString("Modulo_Administrativo"));
	Xusuario.setModuloAgendamento(rsRegistro.getString("Modulo_Agendamento"));
	Xusuario.setModuloAtendimento(rsRegistro.getString("Modulo_Atendimento"));
	Xusuario.setCodigoUsuario(intCodigoUsuario);
	
	conexao.fecharConexao();
	blnConectado = true;
}else{
	out.println("<p>Falha na conexão com o banco de dados!");
}
%>
<%	if(blnConectado){ %>
		<h1>SGC - Sistema de Gestão de Clínicas</h1>
		<h2>Cadastro de usuários</h2>
		<form name="formExcluiUsuario" method="post" action="ExcluirUsuario" target="_parent">
		<p>Nome do usuário: <%=Xusuario.getIdUsuario() %></p>
		<br>
		<p>Módulo administrativo: <%=Xusuario.getModuloAdministrativo() %></p>
		<br>
		<p>Cadastro de funcionários: <%=Xusuario.getCadastroFuncionario() %></p>
		<br>
		<p>Cadastro de usuários: <%=Xusuario.getCadastroUsuario() %></p>
		<br>
		<p>Cadastro de especialidades: <%=Xusuario.getCadastroEspecialidade() %></p>
		<br>
		<p>Cadastro de médicos: <%=Xusuario.getCadastroMedico() %></p>
		<br>
		<p>Cadastro de convênios: <%=Xusuario.getCadastroConvenio() %></p>
		<br>
		<p>Módulo de agendamento: <%=Xusuario.getModuloAgendamento() %></p>
		<br>
		<p>Cadastro de pacientes: <%=Xusuario.getCadastroPaciente() %></p>
		<br>
		<p>Agendamento de consulta: <%=Xusuario.getAgendamentoConsulta() %></p>
		<br>
		<p>Cancelamento de consulta: <%=Xusuario.getCancelamentoConsulta() %></p>
		<br>
		<p>Módulo de atendimento médico: <%=Xusuario.getModuloAtendimento() %></p>
		<br>
		<p><input type="hidden" name="codigo_usuario" value="<%=intCodigoUsuario %>"></p>
		<br>
		<p><input type="submit" name="btnExcluir" value="Excluir">
		<span><a href="javascript:history.back()">[Voltar]</a></span></p>
		</form>
		<%} %>
</body>
</html>