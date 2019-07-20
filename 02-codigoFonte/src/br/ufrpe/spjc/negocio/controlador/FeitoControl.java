package br.ufrpe.spjc.negocio.controlador;

import java.util.List;

import br.ufrpe.framework.transaction.TransactionProxy;
import br.ufrpe.spjc.negocio.entidade.Feito;
import br.ufrpe.spjc.repositorio.FeitoDAO;

public class FeitoControl {
	
	private FeitoDAO repositorio;
	private static FeitoControl instance;
	
	public FeitoControl() {
		repositorio= new FeitoDAO();
	}
	
	public static FeitoControl getInstance() {
		if ( instance == null )
			instance= (FeitoControl) TransactionProxy
					.getInstance(FeitoControl.class);
		return instance;
	}

	public List<Feito> list() {
		return repositorio.list();
	}

	public Feito findById(int id) {
		return repositorio.findById(id);
	}
	
}
