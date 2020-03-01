<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="model.C_medicos"%>
<jsp:useBean id="conexao" scope="page"
	class="banco_dados.ConexaoBancoDados" />
<jsp:useBean id="especialidade" scope="page"
	class="banco_dados.Especialidades" />
<jsp:useBean id="medico" scope="page" class="banco_dados.Medicos" />
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SGC - v.1.0</title>
</head>
<body>
	<%
		ResultSet registros;
		boolean conectado = false;
		C_medicos cmedico = new C_medicos();
		int codigoMedico = Integer.parseInt(request.getParameter("Codigo"));

		if (conexao.abrirConexao()) {
			medico.configurarConexao(conexao.obterConexao());
			registros = medico.lerRegistro(codigoMedico);
			cmedico.setNome_medico(registros.getString("Nome_Medico"));
			cmedico.setCrm(registros.getString("CRM"));
			cmedico.setCodigo_especialidade(Integer.parseInt(registros.getString("Codigo_Especialidade")));
			conectado = true;
		} else
			out.println("<p>Falha na conexão com o banco de dados!</p>");
	%>
	<%
		if (conectado) {
	%>
	<p>SGC - Versão 1.0</p>
	<p>Cadastro de médicos - Edição</p>
	<form name="formEditaMedico" method="post" action="AtualizarMedico"
		target="_parent">
		<p>
			Nome do médico: <input type="text" name="txtNomeMedico" size="50"
				maxlength="50" value="<%=cmedico.getNome_medico()%>">
		</p>
		<p>
			CRM médico: <input type="text" name="txtCRM" size="20" maxlength="20"
				value="<%=cmedico.getCrm()%>">
		</p>
		<p>
			Especialidade: <select name="lstEspecialidade">
				<%
					if (conectado) {
							String itemLista;
							ResultSet rsEspecialidade;
							especialidade.configurarConexao(conexao.obterConexao());
							rsEspecialidade = especialidade.listarRegistros("DESCRIÇÃO");
							if (rsEspecialidade != null) {
								while (rsEspecialidade.next()) {
									itemLista = "<option value='" + rsEspecialidade.getString("Codigo_Especialidade") + "'";
									if (Integer.parseInt(rsEspecialidade.getString("Codigo_Especialidade")) == cmedico
											.getCodigo_especialidade()) {
										itemLista = itemLista + " selected='selected'"
												+ rsEspecialidade.getString("Descricao_Especialidade") + "</option>";
									} else {
										itemLista = itemLista + ">" + rsEspecialidade;
									}
									out.println(itemLista);
								}
							}
							conexao.fecharConexao();
						}
				%>
			</select>
			</p>
			
			<p><input type="hidden" name="codigo_medico" value="<%=codigoMedico%>"></p>
			<br>
			<p><input type="submit" name="btnAtualizar" value="Atualizar">
			<span><a href="javascript:history.back()">[Voltar]</a></span></p>
	</form>
	<%}%>
</body>
</html>