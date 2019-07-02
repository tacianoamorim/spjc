package br.ufrpe.spjc.negocio.controlador;

import java.util.List;

import br.ufrpe.framework.transaction.TransactionProxy;
import br.ufrpe.spjc.negocio.entidade.Pauta;
import br.ufrpe.spjc.negocio.entidade.Processo;
import br.ufrpe.spjc.repositorio.PautaDAO;
import br.ufrpe.spjc.repositorio.ProcessoDAO;

public class ProcessoControl {
	
	private ProcessoDAO repositorio;
	private PautaDAO pautaDAO;
	private static ProcessoControl instance;
	
	public ProcessoControl() {
		repositorio= new ProcessoDAO();
		pautaDAO= new PautaDAO();
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

	public List<Pauta> buscarPautaAtivas(int idJuizado) {
		return pautaDAO.buscarPautaAtivas(idJuizado);
	}
	
	
}
