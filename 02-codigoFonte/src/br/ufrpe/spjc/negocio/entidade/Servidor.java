package br.ufrpe.spjc.negocio.entidade;

public class Servidor extends Entity {
	private String senha;
	private int matricula;
	private String tipoServidor;
	private Juizado juizado;

	public String getCpf() {
		return super.getCpf();
	}

	public void setCpf(String cpf) {
		super.setCpf(cpf);
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

	public Juizado getJuizado() {
		return juizado;
	}

	public void setJuizado(Juizado juizado) {
		this.juizado = juizado;
	}

}
