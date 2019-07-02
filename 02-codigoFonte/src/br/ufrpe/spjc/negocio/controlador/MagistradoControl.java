package br.ufrpe.spjc.negocio.controlador;

import br.ufrpe.framework.transaction.TransactionProxy;
import br.ufrpe.spjc.negocio.entidade.Magistrado;
import br.ufrpe.spjc.repositorio.MagistradoDAO;

public class MagistradoControl {
	
	private MagistradoDAO repositorio;
	private static MagistradoControl instance;
	
	public MagistradoControl() {
		repositorio= new MagistradoDAO();
	}
	
	public static MagistradoControl getInstance() {
		if ( instance == null )
			instance= (MagistradoControl) TransactionProxy
					.getInstance(MagistradoControl.class);
		return instance;
	}
	
	public Magistrado findById(String id) {
		Magistrado magistrado= repositorio.findById(id);
		return magistrado;
	}
}
