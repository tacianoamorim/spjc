package br.ufrpe.spjc.negocio.entidade;

public class Servidor {
	private String cpf;
	private String nome;
	private String senha;
	private int matricula;
	private String tipoServidor;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public String getTipoServidor() {
		return tipoServidor;
	}

	public void setTipoServidor(String tipoServidor) {
		this.tipoServidor = tipoServidor;
	}

}
