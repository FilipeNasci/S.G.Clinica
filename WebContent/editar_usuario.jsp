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
	
		<form name="formEditaUsuario" method="post" action="AtualizarUsuario" target="_parent">
			<p>Nome do Usuário: <input type="text" name="txtNomeUsuario" size="20" maxlength="20" value="<%=Xusuario.getIdUsuario()%>"></p>
			
			<% if(Xusuario.getModuloAdministrativo().equals("S")){%>
				<p><input name="chkAdministrativo" type="checkbox" value="ModuloAdministrativo" checked="checked">Módulo Administrativo</p>
			<% }else{ %>
				<p><input name="chkAdministrativo" type="checkbox" value="ModuloAdministrativo">Módulo Administrativo</p>
			<%} %>
				
			<% if(Xusuario.getCadastroFuncionario().equals("S")){ %>
				<p><input name="chkFuncionario" type="checkbox" value="Funcionario" checked="checked">Cadastro de funcionários</p>
			<% }else{ %>
				<p><input name="chkFuncionario" type="checkbox" value="Funcionario">Cadastro de funcionários</p>
			<%} %>
			
			<% if(Xusuario.getCadastroUsuario().equals("S")){ %>
				<p><input name="chkUsuario" type="checkbox" value="Usuario" checked="checked">Cadastro de usuários</p>
			<% }else{ %>
				<p><input name="chkUsuario" type="checkbox" value="Usuario">Cadastro de usuários</p>
			<%} %>
			
			<% if(Xusuario.getCadastroEspecialidade().equals("S")){ %>
				<p><input name="chkEspecialidade" type="checkbox" value="Especialidade" checked="checked">Cadastro de especialidade</p>
			<% }else{ %>
				<p><input name="chkEspecialidade" type="checkbox" value="Especialidade">Cadastro de especialidade</p>
			<%} %>
			
			<% if(Xusuario.getCadastroMedico().equals("S")){ %>
				<p><input name="chkMedico" type="checkbox" value="Medico" checked="checked">Cadastro de médicos</p>
			<% }else{ %>
				<p><input name="chkMedico" type="checkbox" value="Medico">Cadastro de médicos</p>
			<%} %>
			
			<% if(Xusuario.getCadastroConvenio().equals("S")){ %>
				<p><input name="chkConvenio" type="checkbox" value="Convenio" checked="checked">Cadastro de convênios</p>
			<% }else{ %>
				<p><input name="chkConvenio" type="checkbox" value="Convenio">Cadastro de convênios</p>
			<%} %>
			
			<% if(Xusuario.getModuloAgendamento().equals("S")){ %>
				<p><input name="chkAgendamento" type="checkbox" value="ModuloAgendamento" checked="checked">Cadastro de agendamento</p>
			<% }else{ %>
				<p><input name="chkAgendamento" type="checkbox" value="ModuloAgendamento">Cadastro de agendamento</p>
			<%} %>
			
			<% if(Xusuario.getCadastroPaciente().equals("S")){ %>
				<p><input name="chkPaciente" type="checkbox" value="Paciente" checked="checked">Cadastro de pacientes</p>
			<% }else{ %>
				<p><input name="chkPaciente" type="checkbox" value="Paciente">Cadastro de Cadastro de pacientes</p>
			<%} %>
			
			<% if(Xusuario.getAgendamentoConsulta().equals("S")){ %>
				<p><input name="chkAgendarConsulta" type="checkbox" value="AgendarConsulta" checked="checked">Agendamento de consulta</p>
			<% }else{ %>
				<p><input name="chkAgendarConsulta" type="checkbox" value="AgendarConsulta">Agendamento de consulta</p>
			<%} %>
			
			<% if(Xusuario.getCancelamentoConsulta().equals("S")){ %>
				<p><input name="chkCancelarConsulta" type="checkbox" value="CancelarConsulta" checked="checked">Cancelamento de Consulta</p>
			<% }else{ %>
				<p><input name="chkCancelarConsulta" type="checkbox" value="CancelarConsulta">Cancelamento de Consulta</p>
			<%} %>
			
			<% if(Xusuario.getModuloAtendimento().equals("S")){ %>
				<p><input name="chkAtendimento" type="checkbox" value="ModuloAtendimento" checked="checked">Módulo de atendimento médico</p>
			<% }else{ %>
				<p><input name="chkAtendimento" type="checkbox" value="ModuloAtendimento">Módulo de atendimento médico</p>
			<%} %>
			
			<p><input type="hidden" name="codigo_usuario" value="<%=intCodigoUsuario %>"> 
			<input type="hidden" name="senha_acesso" value="<%=Xusuario.getSenhaAcesso() %>"></p>
			<br>
			<input type="submit" name="btnAtualizar" value="Atualizar">
			<span><a href="javascript:history.back()">[Voltar]</a></span>
		</form>
	<%} %>
</body>
</html>