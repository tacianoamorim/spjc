package br.ufrpe.spjc.negocio.vo;

public class RelatorioServidorVO {
	private String cpf;
	private String nomeServidor;
	private String dataInicio;
	private String tipoServidor;
	private String idJuizado;
	private String nomeJuizado;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNomeServidor() {
		return nomeServidor;
	}

	public void setNomeServidor(String nomeServidor) {
		this.nomeServidor = nomeServidor;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getTipoServidor() {
		return tipoServidor;
	}

	public void setTipoServidor(String tipoServidor) {
		this.tipoServidor = tipoServidor;
	}

	public String getIdJuizado() {
		return idJuizado;
	}

	public void setIdJuizado(String idJuizado) {
		this.idJuizado = idJuizado;
	}

	public String getNomeJuizado() {
		return nomeJuizado;
	}

	public void setNomeJuizado(String nomeJuizado) {
		this.nomeJuizado = nomeJuizado;
	}

}
