package br.ufrpe.spjc.negocio.entidade;

public class Juizado {
	private int id;
	private String nome;
	private String salaAudiencia;

	
	public Juizado() {
		super();
	}

	public Juizado(int id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSalaAudiencia() {
		return salaAudiencia;
	}

	public void setSalaAudiencia(String salaAudiencia) {
		this.salaAudiencia = salaAudiencia;
	}

	@Override
	public String toString() {
		return id + "- " + nome;
	}

	
}
