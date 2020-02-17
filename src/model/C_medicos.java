package model;

public class C_medicos {
	private int codigo_medico;
	private String nome_medico;
	private String crm;
	private int codigo_especialidade;


	public C_medicos() {
		codigo_medico = 0;
		nome_medico = "";
		crm = "";
		codigo_especialidade = 0;
	}

	public C_medicos(String nome_medico, String crm, int codigo_especialidade) {
		this.nome_medico = nome_medico.toUpperCase();
		this.crm = crm;
		this.codigo_especialidade = codigo_especialidade;
	}

	public int getCodigo_medico() {
		return codigo_medico;
	}
	public void setCodigo_medico(int codigo_medico) {
		this.codigo_medico = codigo_medico;
	}
	public String getNome_medico() {
		return nome_medico;
	}
	public void setNome_medico(String nome_medico) {
		this.nome_medico = nome_medico;
	}
	public String getCrm() {
		return crm;
	}
	public void setCrm(String crm) {
		this.crm = crm;
	}
	public int getCodigo_especialidade() {
		return codigo_especialidade;
	}
	public void setCodigo_especialidade(int codigo_especialidade) {
		this.codigo_especialidade = codigo_especialidade;
	}

}
