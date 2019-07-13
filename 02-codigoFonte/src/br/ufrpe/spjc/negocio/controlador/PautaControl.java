package br.ufrpe.spjc.negocio.controlador;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.framework.transaction.TransactionProxy;
import br.ufrpe.spjc.negocio.entidade.Audiencia;
import br.ufrpe.spjc.negocio.entidade.Pauta;
import br.ufrpe.spjc.negocio.vo.HorarioVO;
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
		Audiencia audiencia= new Audiencia();
		Pauta pauta= new Pauta();
		pauta.setId(idPauta);
		audiencia.setPauta(pauta);
		
		List<HorarioVO> listHorario= new ArrayList<HorarioVO>();
		List<Audiencia> list= audienciaDAO.findByFilter(audiencia);
		for (Audiencia audcia : list) {
//			HorarioVO horarioVO= new HorarioVO();
//			horarioVO.setPauta(idPauta);
//			horarioVO.setProcesso(audiencia.get);
//			horarioVO.setHorario(horario);
		}
		
		return list;
	}
	
}
