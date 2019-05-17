package br.ufrpe.spjc.negocio.cadastro;

import br.ufrpe.spjc.dados.RepositorioJuizado;

public class CadastroJuizado {
	@SuppressWarnings("unused")
	private RepositorioJuizado repositorio;
	private static CadastroJuizado instance;
	
	private CadastroJuizado() {
		repositorio= new RepositorioJuizado();
	}
	
	public static CadastroJuizado getInstance() {
		if ( instance == null )
			instance= new CadastroJuizado();
		return instance;
	}
}
