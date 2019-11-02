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
import model.C_usuarios;

/**
 * Servlet implementation class AtualizarUsuario
 */
@WebServlet("/AtualizarUsuario")
public class AtualizarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AtualizarUsuario() {
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
		String strIdUsuario, strSenha, strAdministrativo, strFuncionario, strUsuario, strEspecialidade, strMedico, strConvenio,
		strAgendamento, strPaciente, strAgendarConsulta, strCancelarConsulta, strAtendimento;
		int intCodigoUsuario;
		PrintWriter out;

		strIdUsuario = request.getParameter("txtNomeUsuario");
		strSenha = request.getParameter("senha_acesso");
		intCodigoUsuario = Integer.parseInt(request.getParameter("codigo_usuario"));

		if(request.getParameter("chkAdministrativo") != null)
			strAdministrativo = "S";
		else
			strAdministrativo = "N";

		if(request.getParameter("chkFuncionario") != null)
			strFuncionario = "S";
		else
			strFuncionario = "N";

		if(request.getParameter("chkUsuario") != null)
			strUsuario = "S";
		else
			strUsuario = "N";

		if(request.getParameter("chkEspecialidade") != null)
			strEspecialidade = "S";
		else
			strEspecialidade = "N";

		if(request.getParameter("chkMedico") != null)
			strMedico = "S";
		else
			strMedico = "N";

		if(request.getParameter("chkConvenio") != null)
			strConvenio = "S";
		else
			strConvenio = "N";

		if(request.getParameter("chkAgendamento") != null)
			strAgendamento = "S";
		else
			strAgendamento = "N";

		if(request.getParameter("chkPaciente") != null)
			strPaciente = "S";
		else
			strPaciente = "N";

		if(request.getParameter("chkAgendarConsulta") != null)
			strAgendarConsulta = "S";
		else
			strAgendarConsulta = "N";

		if(request.getParameter("chkCancelarConsulta") != null)
			strCancelarConsulta = "S";
		else
			strCancelarConsulta = "N";

		if(request.getParameter("chkAtendimento") != null)
			strAtendimento = "S";
		else
			strAtendimento = "N";
		
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />");
		out.println("<title>SGC - v.1.0</title>");
		//Colocar href do css
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>SGC - Sistema de Gest�o de Cl�nicas</h1>");
		out.println("<h2>Cadastro de usu�rios</h2>");
		
		try {
			ConexaoBancoDados conexao = new ConexaoBancoDados();
			Usuarios usuario = new Usuarios();
			
			C_usuarios Xusuario = new C_usuarios(strIdUsuario.toUpperCase(), strSenha, strAdministrativo,
					strFuncionario, strUsuario, strEspecialidade, strMedico,
					strConvenio, strAgendamento, strPaciente, strAgendarConsulta,
					strCancelarConsulta, strAtendimento, intCodigoUsuario);
			
			if(conexao.abrirConexao()) {
				usuario.configurarConexao(conexao.obterConexao());
				if(usuario.alterarRegistro(Xusuario)) {
					out.println("<h2>Dados do usu�rio atualizados com sucesso!</h2>");
					out.println("<br><br><br>");
					out.println("<a href='menu_usuarios.html'>Voltar</a>");
				}else {
					out.println("<h2>N�o foi possivel atualizar os dados do usu�rio!</h2>");
				}
				conexao.fecharConexao();
			}else {
				out.println("<h2>N�o foi possivel estabelecer conex�o com o banco de dados!</h2>");
			}
		} catch (Exception e) {
			e.printStackTrace();
			out.println("<h2>Erro do sistema: processo de cadastro de usu�rio!</h2>");
		}
		out.println("</body>");
		out.println("</html>");
	}

}
