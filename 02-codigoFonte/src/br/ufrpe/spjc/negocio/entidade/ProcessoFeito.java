package br.ufrpe.spjc.negocio.entidade;

public class ProcessoFeito {
	private String npu;
	private Feito feito;

	public ProcessoFeito() {
		super();
	}

	public ProcessoFeito(String npu, Feito feito) {
		super();
		this.npu = npu;
		this.feito = feito;
	}
	
	public String getNpu() {
		return npu;
	}

	public void setNpu(String npu) {
		this.npu = npu;
	}

	public Feito getFeito() {
		return feito;
	}

	public void setFeito(Feito feito) {
		this.feito = feito;
	}

}
