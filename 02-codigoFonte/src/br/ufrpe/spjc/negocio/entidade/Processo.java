package br.ufrpe.spjc.negocio.entidade;

public class Processo {
	private String npu;
	private ProcessoFase processoFase;
	private ProcessoFeito processoFeito;
	private Juizado juizado;;

	public String getNpu() {
		return npu;
	}

	public void setNpu(String npu) {
		this.npu = npu;
	}

	public ProcessoFase getProcessoFase() {
		return processoFase;
	}

	public void setProcessoFase(ProcessoFase processoFase) {
		this.processoFase = processoFase;
	}

	public ProcessoFeito getProcessoFeito() {
		return processoFeito;
	}

	public void setProcessoFeito(ProcessoFeito processoFeito) {
		this.processoFeito = processoFeito;
	}

	public Juizado getJuizado() {
		return juizado;
	}

	public void setJuizado(Juizado juizado) {
		this.juizado = juizado;
	}

	@Override
	public String toString() {
		return  npu 
				+ " - Juizado= " + juizado.getNome()
				+ " - Fase= " + processoFase.getFase().getNome() 
				+ " - Feito= " + processoFeito.getFeito().getNome();
	}

	
}
