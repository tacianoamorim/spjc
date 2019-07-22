package br.ufrpe.spjc.negocio.controlador;

import java.util.List;

import br.ufrpe.framework.transaction.TransactionProxy;
import br.ufrpe.spjc.negocio.entidade.Documento;
import br.ufrpe.spjc.repositorio.DocumentoDAO;

public class DocumentoControl {
	
	private DocumentoDAO repositorio;
	private static DocumentoControl instance;
	
	public DocumentoControl() {
		repositorio= new DocumentoDAO();
	}
	
	public static DocumentoControl getInstance() {
		if ( instance == null )
			instance= (DocumentoControl) TransactionProxy
					.getInstance(DocumentoControl.class);
		return instance;
	}

	public List<Documento> list() {
		return repositorio.list(null);
	}
	
	public List<Documento> list(String npu) {
		return repositorio.list(npu);
	}
	
	public void apagar(int id) {
		repositorio.apagar(id);
	}

	public void salvar(Documento documento) {
		if (documento.getId() == 0) {
			repositorio.inserir(documento);
		} else {
			repositorio.update(documento);
		}
	}	
}
