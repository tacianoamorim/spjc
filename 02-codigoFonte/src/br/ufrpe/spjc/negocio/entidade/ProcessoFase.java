package br.ufrpe.spjc.negocio.entidade;

public class ProcessoFase {
	private String npu;
	private Fase fase;

	public ProcessoFase() {
		super();
	}

	public ProcessoFase(String npu, Fase fase) {
		super();
		this.npu = npu;
		this.fase = fase;
	}

	public String getNpu() {
		return npu;
	}

	public void setNpu(String npu) {
		this.npu = npu;
	}

	public Fase getFase() {
		return fase;
	}

	public void setFase(Fase fase) {
		this.fase = fase;
	}

}
