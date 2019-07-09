package br.ufrpe.spjc.negocio.controlador;

import java.util.List;

import br.ufrpe.framework.transaction.TransactionProxy;
import br.ufrpe.spjc.negocio.vo.RelatorioVO;
import br.ufrpe.spjc.repositorio.RelatorioDAO;

public class RelatorioControl {
	
	private RelatorioDAO repositorio;
	private static RelatorioControl instance;
	
	public RelatorioControl() {
		repositorio= new RelatorioDAO();
	}
	
	public static RelatorioControl getInstance() {
		if ( instance == null )
			instance= (RelatorioControl) TransactionProxy
					.getInstance(RelatorioControl.class);
		return instance;
	}
	
	public List<RelatorioVO> list(int mesInicio, int mesFim, int ano) {
		return repositorio.list(mesInicio, mesFim, ano);
	}
	
}
