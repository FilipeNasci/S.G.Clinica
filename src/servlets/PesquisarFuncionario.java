package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import banco_dados.ConexaoBancoDados;
import banco_dados.Funcionarios;
import util.Conversao;

/**
 * Servlet implementation class PesquisarFuncionario
 */
@WebServlet("/PesquisarFuncionario")
public class PesquisarFuncionario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PesquisarFuncionario() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResultSet rsRegistro;
		PrintWriter out;
		String strOpcaoPesquisa, strNome, strRG, strCPF, strCampoPesquisa, strValorPesquisa, strDataNascimento;
		int intCodigoFuncionario;
		Conversao converter = new Conversao();

		strOpcaoPesquisa = request.getParameter("rbOpcaoPesquisa");
		strNome = request.getParameter("txtNomeFuncionario");
		strRG = request.getParameter("txtRG");
		strCPF = request.getParameter("txtCPF");

		if(strOpcaoPesquisa.equals("Nome")) {
			strCampoPesquisa = "NOME";
			strValorPesquisa = strNome;
		}else if(strOpcaoPesquisa.equals("RG")){
			strCampoPesquisa = "RG";
			strValorPesquisa = strRG;
		}else {
			strCampoPesquisa = "CPF";
			strValorPesquisa = strCPF;
		}

		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();

		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta http-equiv='Content-Type' content='text/html'; charset=UTF-8;'");
		out.println("<title>SGC - v.1.0</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>SGC - Sistema de Gestão de Clínicas</h1>");
		out.println("<h2>Cadastro de funcionários</h2>");

		try {
			ConexaoBancoDados conexao = new ConexaoBancoDados();
			Funcionarios funcionario = new Funcionarios();

			if(conexao.abrirConexao()) {
				funcionario.configurarConexao(conexao.obterConexao());
				intCodigoFuncionario = funcionario.localizarRegistro(strCampoPesquisa, strValorPesquisa);

				if(intCodigoFuncionario != 0) {
					rsRegistro = funcionario.lerRegistro(intCodigoFuncionario);
					strDataNascimento = converter.DateToString(rsRegistro.getDate("Data_Nascimento"));

					out.println("Nome do funcionário: "+rsRegistro.getString("Nome_Completo"));
					out.println("Data de nascimento: "+strDataNascimento+" - Sexo: "+rsRegistro.getString("Sexo")+"<br>");
					out.println("RG: "+rsRegistro.getString("Numero_RG")+" - Órgão emissor: "+rsRegistro.getString("orgao_emissor")+"<br>");
					out.println("CPF: "+rsRegistro.getString("Numero_CPF")+"<br>");
					out.println("Endereço: "+rsRegistro.getString("Endereco")+", "+rsRegistro.getString("Numero")+"<br>");
					out.println("Complemento: "+rsRegistro.getString("Complemento")+"<br>");
					out.println("Cidade: "+rsRegistro.getString("Cidade")+" - Estado: "+rsRegistro.getString("Estado")+"<br>");
					out.println("Telefone: "+rsRegistro.getString("Telefone")+" - Celular: "+rsRegistro.getString("Celular")+"<br>");
					out.println("CTPS: "+rsRegistro.getString("Numero_CTPS")+" - PIS:"+rsRegistro.getString("Numero_PIS")+"<br>");
					out.println("<br><br>");
					out.println("<a href='editar_funcionario.jsp?codigo_funcionario="+intCodigoFuncionario+"'>[Editar]</a> "
							+ "<a href='excluir_funcionario.jsp?codigo_funcionario="+intCodigoFuncionario+"'>[Excluir]</a>");
					out.println("<span><a href='javascript:history.back()'>[Voltar]</a></span>");
				}else {
					out.println("<h2>Funcionário não encontrado!</h2>");
					out.println("<br><br><br>");
					out.println("\"<span><a href='javascript:history.back()'>[Voltar]</a></span>\"");
				}
				conexao.fecharConexao();
			}else {
				out.println("<h2>Não foi possivel estabelecer uma conexão com o banco de dados!</h2>");
			}
		} catch (Exception e) {
			e.printStackTrace();
			out.println("<h2>Erro do sistema: processo de cadastro de funcionário!</h2>");
		}
		out.println("</body>");
		out.println("</html>");
	}

}
