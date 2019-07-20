package br.ufrpe.spjc.negocio.entidade;

public class Fase {
	private int id;
	private String nome;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public String toString() {
		return id + "- " + nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
}
