package br.ufrpe.spjc.negocio.controlador;

import java.util.List;

import br.ufrpe.framework.transaction.TransactionProxy;
import br.ufrpe.spjc.negocio.entidade.Juizado;
import br.ufrpe.spjc.repositorio.JuizadoDAO;

public class JuizadoControl {
	
	private JuizadoDAO repositorio;
	private static JuizadoControl instance;
	
	public JuizadoControl() {
		repositorio= new JuizadoDAO();
	}
	
	public static JuizadoControl getInstance() {
		if ( instance == null )
			instance= (JuizadoControl) TransactionProxy
					.getInstance(JuizadoControl.class);
		return instance;
	}
	
	public void inserir() {
		System.out.println("Inserir Controlador");
		repositorio.inserir();
	}
	
	public List<Juizado> findByFilter(Juizado filtro) {
		return repositorio.findByFilter(filtro);
	}
	
}
