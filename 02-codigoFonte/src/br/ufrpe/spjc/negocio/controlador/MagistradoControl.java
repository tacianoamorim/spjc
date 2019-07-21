package br.ufrpe.spjc.negocio.controlador;

import java.util.List;

import br.ufrpe.framework.transaction.TransactionProxy;
import br.ufrpe.spjc.negocio.entidade.Entity;
import br.ufrpe.spjc.negocio.entidade.Magistrado;
import br.ufrpe.spjc.repositorio.EnderecoDAO;
import br.ufrpe.spjc.repositorio.MagistradoDAO;

public class MagistradoControl {

	private MagistradoDAO repositorio;
	private EnderecoDAO enderecoDAO;
	private static MagistradoControl instance;

	public MagistradoControl() {
		repositorio = new MagistradoDAO();
		enderecoDAO = new EnderecoDAO();
	}

	public static MagistradoControl getInstance() {
		if (instance == null)
			instance = (MagistradoControl) TransactionProxy.getInstance(MagistradoControl.class);
		return instance;
	}


	public List<Entity> list() {
		return repositorio.List();
	}

	public void salvar(Magistrado entity) {

		// Verifica se o cep ja esta cadastrado
		if (enderecoDAO.findById(entity.getEndereco().getCep()) == null) {
			enderecoDAO.inserir(entity.getEndereco());
		}

		if (repositorio.findById(entity.getCpf()) == null) {
			repositorio.inserir(entity);
		} else {
			repositorio.update(entity);
		}
	}

	public Magistrado findById(String id) {

		Magistrado magistrado = repositorio.findById(id);
		magistrado.setEndereco(enderecoDAO.findById(magistrado.getEndereco().getCep()));

		return magistrado;
	}

	public List<Magistrado> findByFilter(Magistrado filtro) {

		List<Magistrado> magistrados = repositorio.findByFilter(filtro);
		for (Magistrado magistrado : magistrados) {
			magistrado.setEndereco(enderecoDAO.findById(magistrado.getEndereco().getCep()));
		}

		return magistrados;
	}

	public void apagar(Magistrado magistrado) {
		repositorio.apagar(magistrado);
	}

	public void update(Magistrado magistrado) {
		repositorio.update(magistrado);
	}
}
