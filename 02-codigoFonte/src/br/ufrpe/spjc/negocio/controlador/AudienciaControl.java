package br.ufrpe.spjc.negocio.controlador;

import java.util.List;

import br.ufrpe.framework.transaction.TransactionProxy;
import br.ufrpe.spjc.negocio.entidade.Audiencia;
import br.ufrpe.spjc.repositorio.AudienciaDAO;

public class AudienciaControl {
	
	private AudienciaDAO repositorio;
	private static AudienciaControl instance;
	
	public AudienciaControl() {
		repositorio= new AudienciaDAO();
	}
	
	public static AudienciaControl getInstance() {
		if ( instance == null )
			instance= (AudienciaControl) TransactionProxy
					.getInstance(AudienciaControl.class);
		return instance;
	}

	public List<Audiencia> list() {
		return repositorio.list();
	}
	
	public void apagar(int id) {
		repositorio.apagar(id);
	}

	public void salvar(Audiencia audiencia) {
		repositorio.inserir(audiencia);
	}	
}
