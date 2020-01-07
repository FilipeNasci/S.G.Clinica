package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import banco_dados.ConexaoBancoDados;
import banco_dados.Usuarios;

/**
 * Servlet implementation class ExcluirUsuario
 */
@WebServlet("/ExcluirUsuario")
public class ExcluirUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExcluirUsuario() {
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
		PrintWriter out;
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
			
			if (conexao.abrirConexao()) {
				usuario.configurarConexao(conexao.obterConexao());
				if(usuario.excluirRegistro(Integer.parseInt(request.getParameter("codigo_usuario")))) {
					out.println("<h2>Registro de funcionário exlcuido com sucesso!</h2>");
					out.println("<br><br><br>");
					out.println("<a href='menu_usuarios.html'>[Fechar]</a>");
				}else {
					out.println("<h2>Não foi possivel excluir o registro de funcionário!</h2>");
				}
				conexao.fecharConexao();
			}else {
				out.println("<h2>Não foi possivel estabelecer conexão com o banco de dados!</h2>");
			}
		} catch (Exception e) {
			e.printStackTrace();
			out.println("<h2>Erro do sistema: processo de exclusão de usuário!</h2>");
		}
		out.println("</body");
		out.println("</html");
	}

}
