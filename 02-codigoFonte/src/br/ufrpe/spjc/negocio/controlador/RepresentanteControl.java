package br.ufrpe.spjc.negocio.controlador;

import java.util.List;

import br.ufrpe.framework.transaction.TransactionProxy;
import br.ufrpe.spjc.negocio.entidade.Representante;
import br.ufrpe.spjc.repositorio.EnderecoDAO;
import br.ufrpe.spjc.repositorio.RepresentanteDAO;

public class RepresentanteControl {
	
	private RepresentanteDAO repositorio;
	private EnderecoDAO enderecoDAO;
	private static RepresentanteControl instance;
	
	public RepresentanteControl() {
		repositorio= new RepresentanteDAO();
		enderecoDAO= new EnderecoDAO();
	}
	
	public static RepresentanteControl getInstance() {
		if ( instance == null )
			instance= (RepresentanteControl) TransactionProxy
					.getInstance(RepresentanteControl.class);
		return instance;
	}
	
	public void inserir(Representante entity) {
		// Verifica se o cpe ja esta cadastrado
		if ( enderecoDAO.findById(entity.getEndereco().getCep()) == null ) {
			enderecoDAO.inserir(entity.getEndereco());
		}
		repositorio.inserir(entity);
	}
	
	public Representante findById(int id) {
		return repositorio.findById(id);
	}
	
	public List<Representante> findByFilter(Representante filtro) {
		return repositorio.findByFilter(filtro);
	}
	
	public List<Representante> list(Representante filtro) {
		return repositorio.list(filtro);
	}
	
}
