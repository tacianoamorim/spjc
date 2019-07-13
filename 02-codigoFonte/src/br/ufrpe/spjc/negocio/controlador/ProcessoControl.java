package br.ufrpe.spjc.negocio.controlador;

import java.util.List;

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

}
