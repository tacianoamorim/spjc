package br.ufrpe.spjc.negocio.controlador;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import br.ufrpe.framework.transaction.TransactionProxy;
import br.ufrpe.spjc.negocio.entidade.Processo;
import br.ufrpe.spjc.repositorio.ProcessoDAO;

public class ProcessoControl {
	
	private ProcessoDAO repositorio;
	private static ProcessoControl instance;
	
	public ProcessoControl() {
		repositorio= new ProcessoDAO();
	}
	
	public static ProcessoControl getInstance() {
		if ( instance == null )
			instance= (ProcessoControl) TransactionProxy
					.getInstance(ProcessoControl.class);
		return instance;
	}
	
	public Processo findById(String npu) {
		return repositorio.findById(npu);
	}
	
	public List<Processo> findByFilter(Processo filtro) {
		return repositorio.findByFilter(filtro);
	}

	public void salvar(Processo processo) {
		String npu= gerarNPU(processo);
		processo.setNpu(npu);
		processo.getProcessoFase().setNpu(npu);
		processo.getProcessoFeito().setNpu(npu);

		repositorio.inserir(processo);
		repositorio.inserirProcessoFase(processo);
		repositorio.inserirProcessoFeito(processo);
	}

	private String gerarNPU(Processo processo) {
        Random gerador = new Random();
        String npu= "";
        for (int i = 0; i < 10; i++) {
        	npu= npu + gerador.nextInt(9);
        }
        
        npu= npu + (new GregorianCalendar()).get(Calendar.YEAR) + "17000" + processo.getJuizado().getId();
		return npu;
	}

}
