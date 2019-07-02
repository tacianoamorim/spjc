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
	
	public void salvar(Representante entity) {
		// Verifica se o cpe ja esta cadastrado
		if ( enderecoDAO.findById(entity.getEndereco().getCep()) == null ) {
			enderecoDAO.inserir(entity.getEndereco());
		}
		
		if ( repositorio.findById(entity.getCpf()) == null ) {
			repositorio.inserir(entity);
		} else {
			repositorio.update(entity);
		}
	}
	
	public Representante findById(String id) {
		
		Representante representante= repositorio.findById(id);
		representante.setEndereco(enderecoDAO.findById(representante.getEndereco().getCep()));
		
		return representante;
	}
	
	public List<Representante> findByFilter(Representante filtro) {
		
		List<Representante> representantes= repositorio.findByFilter(filtro);
		for (Representante representante : representantes) {
			representante.setEndereco(enderecoDAO.findById(representante.getEndereco().getCep()));
		}
		
		return representantes;
	}

	public void apagar(Representante representante) {
		repositorio.apagar(representante);		
	}
	
}
