package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import banco_dados.ConexaoBancoDados;
import banco_dados.Funcionarios;

/**
 * Servlet implementation class ExcluirFuncionario
 */
@WebServlet("/ExcluirFuncionario")
public class ExcluirFuncionario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExcluirFuncionario() {
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
		
		out.println("<!DOCYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />");
		out.println("<title>SGC - v.1.0</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>SGC - Sistema de Gestão de Clínicas</h1>");
		out.println("<h2>Exclusão de funcionários</h2>");
		
		try {
			ConexaoBancoDados conexao = new ConexaoBancoDados();
			Funcionarios funcionario = new Funcionarios();
			
			if(conexao.abrirConexao()) {
				funcionario.configurarConexao(conexao.obterConexao());
				if(funcionario.excluirRegistro(Integer.parseInt(request.getParameter("codigo_funcinario")))) {
					out.println("<h2>Registro de funcionário exlcuido com sucesso!</h2>");
					out.println("<br><br><br>");
					out.println("<a href='menu_funcionarios.html'>[Fechar]</a>");
				}else {
					out.println("<h2>Não foi possivel excluir o registro de funcionário!</h2>");
				}
				conexao.fecharConexao();
			}else {
				out.println("<h2>Não foi possivel estabelecer conexão com o banco de dados!</h2>");
			}
		} catch (Exception e) {
			e.printStackTrace();
			out.println("<h2>Erro do sistema: processo de cadastro de funcionário!</h2>");
		}
		out.println("</body");
		out.println("</html");
	}

}
