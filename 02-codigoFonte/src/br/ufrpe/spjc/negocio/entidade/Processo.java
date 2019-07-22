package br.ufrpe.spjc.negocio.entidade;

public class Processo {
	private String npu;
	private ProcessoFase processoFase;
	private ProcessoFeito processoFeito;
	private Juizado juizado;
	private String observacao;

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

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Override
	public String toString() {
		String retorno= "0- ESCOLHA UM PROCESSO ";
		if (!"0".equals(npu)) {
			retorno= npu 
					+ " - Juizado= " + juizado.getNome()
					+ " - Fase= " + processoFase.getFase().getNome() 
					+ " - Feito= " + processoFeito.getFeito().getNome();
		}
		
		return retorno;
	}

	
}
