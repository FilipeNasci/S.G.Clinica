package model;

public class C_funcionarios extends C_pessoa{
	private int codigo_funcionario;
	private String numero_ctps;
	private String numero_pis;
	
	public C_funcionarios() {
		super("","","","","","","","","","","","","","","","");
		this.numero_ctps = "";
		this.numero_pis = "";
	}
	
	public C_funcionarios(String nome, String rg, String orgao_emissor, String cpf, String endereco, String numero,
			String complemento, String bairro, String cidade, String estado, String telefone, String celular,
			String numero_ctps, String numero_pis, String sexo, String dia_nascimento, String mes_nascimento, String ano_nascimento) {
		super(nome, rg, orgao_emissor, cpf, endereco, numero, complemento, bairro, cidade, estado, telefone, celular, sexo,
				dia_nascimento, mes_nascimento, ano_nascimento);
		this.numero_ctps = numero_ctps;
		this.numero_pis = numero_pis;
	}


	public int getCodigo_funcionario() {
		return codigo_funcionario;
	}


	public void setCodigo_funcionario(int codigo_funcionario) {
		this.codigo_funcionario = codigo_funcionario;
	}


	public String getNumero_ctps() {
		return numero_ctps;
	}


	public void setNumero_ctps(String numero_ctps) {
		this.numero_ctps = numero_ctps;
	}


	public String getNumero_pis() {
		return numero_pis;
	}


	public void setNumero_pis(String numero_pis) {
		this.numero_pis = numero_pis;
	}
}
