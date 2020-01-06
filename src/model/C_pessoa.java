package model;

public class C_pessoa {
	private String nome, rg, orgao_emissor, cpf, endereco, numero, complemento, bairro, cidade, estado, telefone, celular, sexo,
	dia_nascimento, mes_nascimento, ano_nascimento;

	public C_pessoa(String nome, String rg, String orgao_emissor, String cpf, String endereco, String numero,
			String complemento, String bairro, String cidade, String estado, String telefone, String celular,
			String sexo, String dia_nascimento, String mes_nascimento, String ano_nascimento) {
		this.nome = nome;
		this.rg = rg;
		this.orgao_emissor = orgao_emissor;
		this.cpf = cpf;
		this.endereco = endereco;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.telefone = telefone;
		this.celular = celular;
		this.sexo = sexo;
		this.dia_nascimento = dia_nascimento;
		this.mes_nascimento = mes_nascimento;
		this.ano_nascimento = ano_nascimento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getOrgao_emissor() {
		return orgao_emissor;
	}

	public void setOrgao_emissor(String orgao_emissor) {
		this.orgao_emissor = orgao_emissor;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getDia_nascimento() {
		return dia_nascimento;
	}
	public String getMes_nascimento() {
		return mes_nascimento;
	}
	public String getAno_nascimento() {
		return ano_nascimento;
	}
	public String getDataNascimento() {
		return dia_nascimento+"/"+mes_nascimento+"/"+ano_nascimento;
	}
	public void setDataNascimento(String dia_nascimento, String mes_nascimento, String ano_nascimento) {
		this.dia_nascimento = dia_nascimento;
		this.mes_nascimento = mes_nascimento;
		this.ano_nascimento = ano_nascimento;
	}
}
