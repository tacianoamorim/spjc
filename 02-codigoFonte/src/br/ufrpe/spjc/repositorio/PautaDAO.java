package br.ufrpe.spjc.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import br.ufrpe.framework.transaction.SystemException;
import br.ufrpe.framework.transaction.TransactionManager;
import br.ufrpe.spjc.negocio.entidade.Pauta;

public class PautaDAO {

	public List<Pauta> buscarPautaAtivas(int idJuizado) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		List<Pauta> lista= new ArrayList<Pauta>();
		StringBuilder sql= new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();
			
			sql.append("SELECT DISTINCT p.Id AS 'idPauta', p.dataAgendamento, p.qtdeProcesso AS 'qtdeProcesso'   ");
			sql.append("FROM DBSPJC.Pauta p  ");
			sql.append("	INNER JOIN DBSPJC.Audiencia a ON a.pauta= p.id ");
			sql.append("WHERE p.estadoPauta= 1 AND a.Juizado= ?");	
			preStmt = connection.prepareStatement(sql.toString());
			preStmt.setInt(1, idJuizado);
			rs = preStmt.executeQuery();
	
			while (rs.next()) {
				lista.add(carregarEntity(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Codigo: "
					+ e.getErrorCode());
		} finally {
//			transactionManager.closeConnection(connection);
		}
		return lista;		
	}

	private Pauta carregarEntity(ResultSet rs) throws SQLException {
		Pauta pauta= new Pauta();
		pauta.setId(rs.getInt("idPauta"));
		Calendar data= new GregorianCalendar();
		data.setTime(rs.getDate("idPauta"));
		pauta.setDataAgendamento(data);
		return pauta;
	}

	
}
