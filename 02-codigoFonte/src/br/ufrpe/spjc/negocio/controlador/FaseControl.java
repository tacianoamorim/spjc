package br.ufrpe.spjc.negocio.controlador;

import java.util.List;

import br.ufrpe.framework.transaction.TransactionProxy;
import br.ufrpe.spjc.negocio.entidade.Fase;
import br.ufrpe.spjc.repositorio.FaseDAO;

public class FaseControl {
	
	private FaseDAO repositorio;
	private static FaseControl instance;
	
	public FaseControl() {
		repositorio= new FaseDAO();
	}
	
	public static FaseControl getInstance() {
		if ( instance == null )
			instance= (FaseControl) TransactionProxy
					.getInstance(FaseControl.class);
		return instance;
	}

	public List<Fase> list() {
		return repositorio.list();
	}

	public Fase findById(int id) {
		return repositorio.findById(id);
	}
}
