package br.ufrpe.spjc.repositorio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import br.ufrpe.framework.transaction.SystemException;
import br.ufrpe.framework.transaction.TransactionManager;
import br.ufrpe.spjc.negocio.entidade.Audiencia;
import br.ufrpe.spjc.negocio.entidade.Pauta;
import br.ufrpe.spjc.negocio.entidade.Processo;

public class AudienciaDAO {

	private Audiencia carregar(ResultSet rs) throws SQLException {
		Audiencia audiencia= new Audiencia();
		audiencia.setId(rs.getInt("id"));
		audiencia.setTipo("tipo");
		
		Pauta pauta= new Pauta();
		pauta.setId(rs.getInt("pauta"));
		audiencia.setPauta(pauta);
		
		Processo processo= new Processo();
		processo.setNpu(rs.getString("processo"));
		audiencia.setProcesso(processo);
		
		audiencia.setSala(rs.getString("sala"));
		
		Date data= rs.getDate("horario");
		Calendar dataHora= new GregorianCalendar();
		dataHora.setTimeInMillis(data.getTime());
		audiencia.setHora(dataHora);
		
		return audiencia;
	}
	
	public List<Audiencia> findByFilter(Audiencia filtro) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		List<Audiencia> audiencias= new ArrayList<Audiencia>();
		StringBuilder sql= new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();
			
			if (filtro != null) {
				sql.append("SELECT id, pauta, processo, tipo, horario, sala ");
				sql.append("FROM DBSPJC.Audiencia WHERE 0= 0 ");
				if ( filtro.getPauta() != null )
					sql.append("AND pauta = ? ");
				
				preStmt = connection.prepareStatement(sql.toString());
				int idx= 1;
				
				if ( filtro.getPauta() != null )
					preStmt.setInt(idx++, filtro.getPauta().getId());
				
				rs = preStmt.executeQuery();
	
				while (rs.next()) {
					audiencias.add(carregar(rs));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Codigo: "
					+ e.getErrorCode());
		} 
		return audiencias;		
	}
	

}
