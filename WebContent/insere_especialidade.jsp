<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="conexao" scope="page" class="banco_dados.ConexaoBancoDados"/>
<jsp:useBean id="especialidade" scope="page" class="banco_dados.Especialidades"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Conexão com banco de dados</title>
</head>
<body>
	<h2>Conexão com o banco de dados</h2>
	
	<%
	if(conexao.abrirConexao()){
		especialidade.configurarConexao(conexao.obterConexao());
		if(especialidade.inserirRegistro("Clínica geral"))
			out.println("<h2>Especialidade 'Clínica geral' cadastrada com sucesso!</h2>");
		else
			out.println("<h3>Erro ao tentar cadastrar especialidade!</h3>");
		
		conexao.fecharConexao();
	}else
		out.println("<p>Falha na conexão com o banco de dados!");
	%>
</body>
</html>