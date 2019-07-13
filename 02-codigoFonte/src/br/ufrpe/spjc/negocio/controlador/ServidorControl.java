package br.ufrpe.spjc.negocio.controlador;

import java.util.List;

import br.ufrpe.framework.transaction.TransactionProxy;
import br.ufrpe.spjc.negocio.entidade.Entity;
import br.ufrpe.spjc.negocio.entidade.Servidor;
import br.ufrpe.spjc.repositorio.ServidorDAO;

public class ServidorControl {
	
	private ServidorDAO repositorio;
	private static ServidorControl instance;
	
	public ServidorControl() {
		repositorio= new ServidorDAO();
	}
	
	public static ServidorControl getInstance() {
		if ( instance == null )
			instance= (ServidorControl) TransactionProxy
					.getInstance(ServidorControl.class);
		return instance;
	}
	
	public Servidor findById(String id) {
		Servidor servidor= repositorio.findById(id);
		return servidor;
	}
	
	public List<Entity> findByFilter(Servidor filtro) {
		return repositorio.findByFilter(filtro);
	}
}
