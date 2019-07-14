package br.ufrpe.spjc.negocio.entidade;

import java.util.Calendar;

public class Audiencia {
	private int id;
	private Calendar hora;
	private int pauta;
	private String processo;
	private String Tipo;
	private String sala;
	private String magistrado;
	private String servidor;

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

	public int getPauta() {
		return pauta;
	}

	public void setPauta(int pauta) {
		this.pauta = pauta;
	}

	public String getProcesso() {
		return processo;
	}

	public void setProcesso(String processo) {
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

	public String getMagistrado() {
		return magistrado;
	}

	public void setMagistrado(String magistrado) {
		this.magistrado = magistrado;
	}

	public String getServidor() {
		return servidor;
	}

	public void setServidor(String servidor) {
		this.servidor = servidor;
	}

	@Override
	public String toString() {
		return id + "(" + hora.get(Calendar.HOUR) + ":" + hora.get(Calendar.MINUTE) + ") " 
				+ processo;
	}

}
