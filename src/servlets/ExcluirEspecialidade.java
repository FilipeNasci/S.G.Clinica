package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import banco_dados.ConexaoBancoDados;
import banco_dados.Especialidades;

/**
 * Servlet implementation class ExcluirEspecialidade
 */
@WebServlet("/ExcluirEspecialidade")
public class ExcluirEspecialidade extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExcluirEspecialidade() {
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
		response.setContentType("text/charset=UTF-8");
		out = response.getWriter();
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta http-equiv='Content-Type' content='text/html'; charset=UTF-8;'");
		out.println("<title>SGC - v.1.0</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>SGC - Sistema de Gestão de Clínicas</h1>");
		out.println("<h2>Cadastro de especialidades</h2>");
		
		try {
			ConexaoBancoDados conexao = new ConexaoBancoDados();
			Especialidades especialidade = new Especialidades();
			
			if(conexao.abrirConexao()) {
				especialidade.configurarConexao(conexao.obterConexao());
				
				if(especialidade.excluirRegistro(Integer.parseInt(request.getParameter("codigo_especialidade")))) {
					out.println("<h2>Registro excluido com sucesso!</h2>");
					out.println("<br><br><br><br>");
					out.println("<a href='menu_especialidades.html'>Fechar</a>");
				}else
					out.println("<h2>Não foi possivel excluir o registro!</h2>");
				
				conexao.fecharConexao();
			}else
				out.println("<h2>Não foi possível estabelecer conexao com o banco de dados!</h2>");			
		} catch (Exception e) {
			e.printStackTrace();
			out.println("<h2>Erro do sistema: processo de cadastro de funcionário!</h2>");
		}
		out.println("</body>");
		out.println("</html>");
	}

}
