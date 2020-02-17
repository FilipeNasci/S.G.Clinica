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
import model.C_medicos;

/**
 * Servlet implementation class InserirMedico
 */
@WebServlet("/InserirMedico")
public class InserirMedico extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserirMedico() {
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
		String descricao;
		PrintWriter out;
		
		descricao = request.getParameter("txtDescricao");
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />");
		out.println("<title>SGC - v.1.0</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>SGC - Sistema de Gestão de Clínicas</h1>");
		out.println("<h2>Cadastro de médicos</h2>");
		
		try {
			ConexaoBancoDados conexao = new ConexaoBancoDados();
			Medicos medico = new Medicos();
			C_medicos cmedico = new C_medicos(request.getParameter("txtNomeMedico"),
					request.getParameter("txtCRM"),Integer.parseInt(request.getParameter("lstEspecialidade")));
			if(conexao.abrirConexao()) {
				medico.configurarConexao(conexao.obterConexao());
				if(medico.inserirRegistro(cmedico)) {
					out.println("<h2>Médico cadastrado com sucesso!</h2>");
					out.println("<br><br><br>");
					out.println("<a href='menu_medicos.html'>Voltar</a>");
				}else {
					out.println("<h2>Não foi possivel realizar o cadastro!</h2>");
				}
			}else {
				out.println("<h2>Não foi possivel estabelecer conexão com o banco de dados!</h2>");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			out.println("<h2>Erro do sistema: processo de cadastro de médico!</h2>");
		}
		out.println("</body>");
		out.println("</html>");
	}
}
