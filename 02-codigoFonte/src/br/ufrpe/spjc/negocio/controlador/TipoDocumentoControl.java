package br.ufrpe.spjc.negocio.controlador;

import java.util.List;

import br.ufrpe.framework.transaction.TransactionProxy;
import br.ufrpe.spjc.negocio.entidade.TipoDocumento;
import br.ufrpe.spjc.repositorio.TipoDocumentoDAO;

public class TipoDocumentoControl {
	
	private TipoDocumentoDAO repositorio;
	private static TipoDocumentoControl instance;
	
	public TipoDocumentoControl() {
		repositorio= new TipoDocumentoDAO();
	}
	
	public static TipoDocumentoControl getInstance() {
		if ( instance == null )
			instance= (TipoDocumentoControl) TransactionProxy
					.getInstance(TipoDocumentoControl.class);
		return instance;
	}
	
	public List<TipoDocumento> list() {
		return repositorio.list();
	}

	
}
