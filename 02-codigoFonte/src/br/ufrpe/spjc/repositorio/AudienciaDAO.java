package br.ufrpe.spjc.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import br.ufrpe.framework.transaction.SystemException;
import br.ufrpe.framework.transaction.TransactionManager;
import br.ufrpe.spjc.negocio.entidade.Audiencia;
import br.ufrpe.spjc.util.Utils;

public class AudienciaDAO {

	private Audiencia carregar(ResultSet rs) throws SQLException {
		Audiencia audiencia= new Audiencia();
		audiencia.setId(rs.getInt("id"));
		audiencia.setTipo("tipo");
		audiencia.setPauta(rs.getInt("pauta"));
		audiencia.setProcesso(rs.getString("processo"));
		audiencia.setSala(rs.getString("sala"));
		audiencia.setMagistrado(rs.getString("magistrado"));
		audiencia.setServidor(rs.getString("servidor"));
		
		Time hora= rs.getTime("hora");
		Calendar dataHora= new GregorianCalendar();
		dataHora.setTimeInMillis(hora.getTime());
		audiencia.setHora(dataHora);
		audiencia.setHoraMarcacao(hora.getHours());
		
		return audiencia;
	}
	
	public List<Audiencia> list() {
		Connection connection = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		List<Audiencia> audiencias= new ArrayList<Audiencia>();
		StringBuilder sql= new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();
			
			sql.append("SELECT id, magistrado, servidor, processo, pauta, tipo, hora, sala ");
			sql.append("FROM DBSPJC.Audiencia ");
			sql.append("ORDER BY tipo, processo ");
			preStmt = connection.prepareStatement(sql.toString());
			rs = preStmt.executeQuery();

			while (rs.next()) {
				audiencias.add(carregar(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Codigo: "
					+ e.getErrorCode());
		} 
		return audiencias;		
	}
	
	public List<Audiencia> listPorPauta(int id) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		List<Audiencia> audiencias= new ArrayList<Audiencia>();
		StringBuilder sql= new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();
			
			sql.append("SELECT id, magistrado, servidor, processo, pauta, tipo, hora, sala ");
			sql.append("FROM DBSPJC.Audiencia WHERE pauta= ? ");
			sql.append("ORDER BY hora ");
			preStmt = connection.prepareStatement(sql.toString());
			preStmt.setInt(1, id);	
			rs = preStmt.executeQuery();

			while (rs.next()) {
				audiencias.add(carregar(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Codigo: "
					+ e.getErrorCode());
		} 
		return audiencias;		
	}
	
	public void inserir(Audiencia entity) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		StringBuilder sql= new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();
			
			sql.append("INSERT INTO DBSPJC.Audiencia " + 
						"(pauta, processo, tipo, hora, sala, estadoAudiencia, situacao) " + 
						"VALUES (?, ?, ?, ?, ?, ?, ?) ");
			preStmt= connection.prepareStatement(sql.toString());
			
			
			int hora= entity.getHoraMarcacao();
			java.sql.Time time= new java.sql.Time(hora, 0, 0);
			
			preStmt.setInt(1, entity.getPauta());
			preStmt.setString(2, entity.getProcesso());
			
			String codigo= Utils.getId(entity.getTipo().toString(), "-");
			preStmt.setString(3, codigo);  
			preStmt.setTime(4, time );
			preStmt.setString(5, entity.getSala() );
			preStmt.setInt(6, 1);
			preStmt.setString(7, "I");
			preStmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Codigo: "
					+ e.getErrorCode());
		} 
	}

	public void apagar(int id) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		StringBuilder sql= new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();
			
			sql.append("DELETE FROM DBSPJC.Audiencia WHERE id= ? ");
			preStmt= connection.prepareStatement(sql.toString());
			preStmt.setInt(1, id);
			
			preStmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Codigo: "
					+ e.getErrorCode());
		} 
	}

}
