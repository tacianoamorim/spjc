package br.ufrpe.spjc.negocio.vo;

public class RelatorioVO {
	private String juizado;
	private String dataHoraAudiencia;
	private String processo;
	private String polo;
	private String parte;

	public String getJuizado() {
		return juizado;
	}

	public void setJuizado(String juizado) {
		this.juizado = juizado;
	}

	public String getDataHoraAudiencia() {
		return dataHoraAudiencia;
	}

	public void setDataHoraAudiencia(String dataHoraAudiencia) {
		this.dataHoraAudiencia = dataHoraAudiencia;
	}

	public String getProcesso() {
		return processo;
	}

	public void setProcesso(String processo) {
		this.processo = processo;
	}

	public String getPolo() {
		return polo;
	}

	public void setPolo(String polo) {
		this.polo = polo;
	}

	public String getParte() {
		return parte;
	}

	public void setParte(String parte) {
		this.parte = parte;
	}

}
