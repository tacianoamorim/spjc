package br.ufrpe.spjc.negocio.entidade;

public class Magistrado extends Entity {
	private int matricula;
	private String senha;
	private String numero;
	private Endereco endereco;

	public String getCpf() {
		return super.getCpf();
	}

	public void setCpf(String cpf) {
		super.setCpf(cpf);
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return super.getNome();
	}

	public void setNome(String nome) {
		super.setNome(nome);
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
