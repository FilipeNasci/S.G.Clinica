package model;

public class C_convenios {
	private String nomeConvenio, cnpj, telefone;
	private int codigoConvenio;
	
	public C_convenios() {
		this.nomeConvenio = "";
		this.cnpj = "";
		this.telefone = "";
		this.codigoConvenio = 0;
	}
	
	public C_convenios(String nomeConvenio, String cnpj, String telefone) {
		this.nomeConvenio = nomeConvenio.toUpperCase();
		this.cnpj = cnpj;
		this.telefone = telefone;
	}
	
	public String getNomeConvenio() {
		return nomeConvenio;
	}
	public void setNomeConvenio(String nomeConvenio) {
		this.nomeConvenio = nomeConvenio.toUpperCase();
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public int getCodigoConvenio() {
		return codigoConvenio;
	}
	public void setCodigoConvenio(int codigoConvenio) {
		this.codigoConvenio = codigoConvenio;
	}
}
