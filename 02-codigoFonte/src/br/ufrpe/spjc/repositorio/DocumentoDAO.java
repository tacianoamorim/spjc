package br.ufrpe.spjc.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufrpe.framework.transaction.SystemException;
import br.ufrpe.framework.transaction.TransactionManager;
import br.ufrpe.spjc.negocio.entidade.Documento;

public class DocumentoDAO {
	
	public void inserir(Documento entity) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		StringBuilder sql= new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();
			
			sql.append("INSERT INTO DBSPJC.Documento " + 
					"(magistrado,servidor,processo,tipoDocumento,dataCriacao,texto) " + 
					"VALUES (?, ?, ?, ?, ?, ?) ");

			preStmt= connection.prepareStatement(sql.toString());
			if ( entity.getMagistrado() != null) {
				preStmt.setString(1, entity.getMagistrado());			
				preStmt.setString(2, null);
			} else {
				preStmt.setString(1, null);			
				preStmt.setString(2, entity.getServidor());
			}
			
			System.out.println("DOCUPROCESSO "+ entity.getProcesso());
			preStmt.setString(3, entity.getProcesso());
			preStmt.setInt(4, entity.getTipoDocumento());
			preStmt.setDate(5, new java.sql.Date( entity.getDataCriacao().getTimeInMillis()) );
			preStmt.setString(6, entity.getTexto());
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
			
			sql.append("DELETE FROM DBSPJC.Documento WHERE id= ? ");
			preStmt= connection.prepareStatement(sql.toString());
			preStmt.setInt(1, id);
			
			preStmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Codigo: "
					+ e.getErrorCode());
		} 
	}

	public List<Documento> list(String npu) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		List<Documento> entities= new ArrayList<Documento>();
		StringBuilder sql= new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();
			sql.append("SELECT id, processo, magistrado, servidor, texto, tipoDocumento ");
			sql.append("FROM DBSPJC.Documento  ");
			
			if ( npu != null )
				sql.append("WHERE processo= ?  ");
			
			sql.append("ORDER BY tipoDocumento,  processo ");
			preStmt = connection.prepareStatement(sql.toString());
			
			if ( npu != null )
				preStmt.setString(1, npu);
			
			rs = preStmt.executeQuery();

			while (rs.next()) {
				Documento entity= new Documento();
				entity.setId(rs.getInt("id"));
				entity.setProcesso(rs.getString("processo"));
				entity.setTexto(rs.getString("texto"));
				entity.setTipoDocumento(rs.getInt("tipoDocumento"));
				entity.setMagistrado(rs.getString("magistrado"));
				entity.setServidor(rs.getString("servidor"));

				entities.add(entity);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Codigo: "
					+ e.getErrorCode());
		} 
		return entities;	
	}
	
	public void update(Documento entity) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		StringBuilder sql= new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();
			
			sql.append("UPDATE DBSPJC.Documento " + 
					" SET texto= ? " + 
					"WHERE id= ?");

			preStmt= connection.prepareStatement(sql.toString());
			preStmt.setString(1, entity.getTexto());
			preStmt.setInt(2, entity.getId());
			preStmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Codigo: "
					+ e.getErrorCode());
		} 
	}

}
