package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import banco_dados.ConexaoBancoDados;
import banco_dados.Convenios;
import model.C_convenios;

/**
 * Servlet implementation class InserirConvenio
 */
@WebServlet("/InserirConvenio")
public class InserirConvenio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserirConvenio() {
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
		String nomeConvenio, cnpj, telefone;
		
		nomeConvenio = request.getParameter("txtNomeConvenio");
		cnpj = request.getParameter("txtCNPJ");
		telefone = request.getParameter("txtTelefone");
		
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
		out.println("<h2>Cadastro de convênio</h2>");
		
		try {
			ConexaoBancoDados conexao = new ConexaoBancoDados();
			Convenios convenio = new Convenios();
			C_convenios cconvenio = new C_convenios(nomeConvenio, cnpj, telefone);
			
			if(conexao.abrirConexao()) {
				convenio.configurarConexao(conexao.obterConexao());
				
				if(convenio.inserirRegistro(cconvenio)) {
					out.println("<h2>Convênio salva com sucesso!</h2>");
					out.println("<br><br><br>");
					out.println("<a href='menu_convenios.html>Voltar</a>");
				}else
					out.println("<h2>Não foi possível cadastrar o convênio!</h2>");
				
				conexao.fecharConexao();
			}else
				out.println("<h2>Não foi possível estabelecer uma conexão como banco de dados!</h2>");
		} catch (Exception e) {
			e.printStackTrace();
			out.println("<h2>Erro do sistema: processo de cadastro de convênio!</h2>");
		}
		out.println("</body>");
		out.println("</html>");
	}

}
