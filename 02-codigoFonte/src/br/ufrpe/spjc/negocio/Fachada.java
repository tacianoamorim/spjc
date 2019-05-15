package br.ufrpe.spjc.negocio;

public class Fachada {

	private static Fachada instance;

	private Fachada() {
	}
	
	public static Fachada getInstance() {
		if ( instance == null ) {
			instance= new Fachada();
		}
		return instance;
	}

}
