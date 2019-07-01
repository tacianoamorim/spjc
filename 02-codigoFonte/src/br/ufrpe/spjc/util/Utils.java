package br.ufrpe.spjc.util;

public class Utils {

	public static String getId(String texto, String separador) {
		String id= "";
		if ( texto != null && separador!= null) {
			String[] arrayText= texto.split(separador);
			id= arrayText[0];
		}
		return id;
	}
	
}
