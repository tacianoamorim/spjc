package br.ufrpe.spjc.negocio.entidade;

import java.util.Calendar;

public class Documento {
	private int id;
	private String magistrado;
	private String servidor;
	private String processo;
	private int tipoDocumento;
	private Calendar dataCriacao;
	private String texto;
	private String assinatura;
	private Calendar dataAssinatura;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getProcesso() {
		return processo;
	}

	public void setProcesso(String processo) {
		this.processo = processo;
	}

	public int getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(int tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Calendar getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Calendar dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getAssinatura() {
		return assinatura;
	}

	public void setAssinatura(String assinatura) {
		this.assinatura = assinatura;
	}

	public Calendar getDataAssinatura() {
		return dataAssinatura;
	}

	public void setDataAssinatura(Calendar dataAssinatura) {
		this.dataAssinatura = dataAssinatura;
	}

	@Override
	public String toString() {
		return id + "- " + processo + " [" + texto + "]";
	}

	
}
