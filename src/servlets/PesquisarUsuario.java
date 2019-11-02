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
import banco_dados.Usuarios;

/**
 * Servlet implementation class PesquisarUsuario
 */
@WebServlet("/PesquisarUsuario")
public class PesquisarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PesquisarUsuario() {
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
		String strUsuario;
		int intCodigoUsuario;
		
		strUsuario = request.getParameter("txtUsuario");
		
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
		out.println("<h2>Cadastro de usuários - Pesquisa</h2>");
		
		try {
			ConexaoBancoDados conexao = new ConexaoBancoDados();
			Usuarios usuario = new Usuarios();
			
			if(conexao.abrirConexao()) {
				usuario.configurarConexao(conexao.obterConexao());
				intCodigoUsuario = usuario.localizarRegistro(strUsuario.toUpperCase());
				
				if(intCodigoUsuario != 0) {
					rsRegistro = usuario.lerRegistro(intCodigoUsuario);
					out.println("Identificação do usuário: " + rsRegistro.getString("Identificacao_Usuario"));
					out.println("<br><br>");
					out.println("<a href='editar_usuario.jsp?codigo_usuario="+intCodigoUsuario+"'>[Editar]</a> <a href='"
							+ "excluir_usuario.jsp?codigo_usuario="+intCodigoUsuario+"'>[Excluir]</a>");
					out.println("<span><a href=\"javascript:history.back()\">[Voltar]</a></span>");
				}else {
					out.println("<h2>Usuário não encontrado!</h2>");
					out.println("<br><br>");
					out.println("<span><a href=\"javascript:history.back()\">[Voltar]</a></span>");
				}
				conexao.fecharConexao();
			}else {
				out.println("Não foi possível estabelecer conexão com o banco de dados!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			out.println("<h2>Erro do sistema: processo de cadastro de usuário!</h2>");
		}
		out.println("</body>");
		out.println("</html>");
	}

}
