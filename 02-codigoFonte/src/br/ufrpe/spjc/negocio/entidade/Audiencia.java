package br.ufrpe.spjc.negocio.entidade;

import java.util.Calendar;

public class Audiencia {
	private int id;
	private Calendar hora;
	private Pauta pauta;
	private Processo processo;
	private String Tipo;
	private String sala;
	private EstadoAudiencia estadoAudiencia;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Calendar getHora() {
		return hora;
	}

	public void setHora(Calendar hora) {
		this.hora = hora;
	}

	public Pauta getPauta() {
		return pauta;
	}

	public void setPauta(Pauta pauta) {
		this.pauta = pauta;
	}

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public String getTipo() {
		return Tipo;
	}

	public void setTipo(String tipo) {
		Tipo = tipo;
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public EstadoAudiencia getEstadoAudiencia() {
		return estadoAudiencia;
	}

	public void setEstadoAudiencia(EstadoAudiencia estadoAudiencia) {
		this.estadoAudiencia = estadoAudiencia;
	}

}
