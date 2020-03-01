package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import banco_dados.ConexaoBancoDados;
import banco_dados.Medicos;

/**
 * Servlet implementation class ExcluirMedico
 */
@WebServlet("/ExcluirMedico")
public class ExcluirMedico extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExcluirMedico() {
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
		out.println("<h1>SGC - Sistema de Gest�o de Cl�nicas</h1>");
		out.println("<h2>Exclus�o de m�dico</h2>");
		
		try {
			ConexaoBancoDados conexao = new ConexaoBancoDados();
			Medicos medico = new Medicos();
			
			if(conexao.abrirConexao()) {
				medico.configurarConexao(conexao.obterConexao());
				if(medico.excluirRegistro(Integer.parseInt(request.getParameter("codigo_medico")))) {
					out.println("<h2>Registro m�dico excluido com sucesso!</h2>");
					out.println("<br><br><br>");
					out.println("<a href='menu_medicos.html'>Fechar</a>");
				}else
					out.println("<h2>N�o foi poss�vel excluir o cadastro do m�dico!</h2>");
				
				conexao.fecharConexao();
			}else
				out.println("<h2>N�o foi poss�vel abrir conex�o com o banco de dados!</h2>");
		} catch (Exception e) {
			e.printStackTrace();
			out.println("<h2>Erro do sistema: processo de cadastro de funcion�rio!</h2>");
		}
		out.println("</body");
		out.println("</html");
	}

}
