package br.ufrpe.spjc.negocio.controlador;

import java.util.List;

import br.ufrpe.framework.transaction.TransactionProxy;
import br.ufrpe.spjc.negocio.entidade.Audiencia;
import br.ufrpe.spjc.negocio.entidade.Pauta;
import br.ufrpe.spjc.repositorio.AudienciaDAO;
import br.ufrpe.spjc.repositorio.PautaDAO;

public class PautaControl {
	
	private PautaDAO repositorio;
	private AudienciaDAO audienciaDAO;
	private static PautaControl instance;
	
	public PautaControl() {
		repositorio= new PautaDAO();
		audienciaDAO= new AudienciaDAO();
	}
	
	public static PautaControl getInstance() {
		if ( instance == null )
			instance= (PautaControl) TransactionProxy
					.getInstance(PautaControl.class);
		return instance;
	}
	
	public Pauta findById(int id) {
		return repositorio.findById(id);
	}
	
	public List<Pauta> buscarPautaAtivas(int idJuizado) {
		return repositorio.buscarPautaAtivas(idJuizado);
	}
	
	public List<Audiencia> buscarHorarioPautaDisponival(int idPauta) {
//		List<HorarioVO> listHorario= new ArrayList<HorarioVO>();
//		List<Audiencia> list= audienciaDAO.listPorPauta(idPauta);
//		for (Audiencia audcia : list) {
//			
//		}
		return audienciaDAO.listPorPauta(idPauta);
	}
	
}
