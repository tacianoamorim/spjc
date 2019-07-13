package br.ufrpe.spjc.repositorio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.ufrpe.framework.transaction.SystemException;
import br.ufrpe.framework.transaction.TransactionManager;
import br.ufrpe.spjc.negocio.entidade.Entity;
import br.ufrpe.spjc.negocio.entidade.Juizado;
import br.ufrpe.spjc.negocio.entidade.Servidor;
import br.ufrpe.spjc.negocio.vo.RelatorioServidorVO;

public class ServidorDAO {

	public Servidor findById(String cpf) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		Servidor servidor= null;
		StringBuilder sql= new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();
			
			sql.append("SELECT Cpf, Nome, Senha, TipoServidor, Matricula, Juizado ");
			sql.append("FROM DBSPJC.Servidor WHERE Cpf= ? ");
			
			preStmt = connection.prepareStatement(sql.toString());
			preStmt.setString(1, cpf);
			
			rs = preStmt.executeQuery();

			while (rs.next()) {
				servidor= new Servidor();
				servidor.setMatricula(rs.getInt("matricula"));
				servidor.setCpf(rs.getString("cpf"));
				servidor.setNome(rs.getString("nome"));
				servidor.setTipoServidor(rs.getString("TipoServidor"));
				servidor.setSenha(rs.getString("senha"));
				
				Juizado juizado= new Juizado();
				juizado.setId(rs.getInt("Juizado"));
				servidor.setJuizado(juizado);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Codigo: "
					+ e.getErrorCode());
		} finally {
//			transactionManager.closeConnection(connection);
		}
		return servidor;		
	}

	public List<Entity> findByFilter(Servidor filtro) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		List<Entity> servidores= new ArrayList<Entity>();
		StringBuilder sql= new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();
			
			if (filtro != null) {
				sql.append("SELECT Cpf, Nome, Senha, TipoServidor, Matricula, Juizado ");
				sql.append("FROM DBSPJC.Servidor  ");
				sql.append(" WHERE 0= 0 ");
				if ( filtro.getTipoServidor() != null )
					sql.append("AND TipoServidor= ? ");
				sql.append(" ORDER BY Nome ");
				System.out.println(filtro);
				preStmt = connection.prepareStatement(sql.toString());
				int idx= 1;
				
				if ( filtro.getTipoServidor() != null )
					preStmt.setString(idx++, filtro.getTipoServidor());
				
				rs = preStmt.executeQuery();
	
				while (rs.next()) {
					Entity servidor= new Servidor();
					servidor.setCpf(rs.getString("cpf"));
					servidor.setNome(rs.getString("nome"));
					servidores.add(servidor);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Codigo: "
					+ e.getErrorCode());
		} 
		return servidores;		
	}
	
	
	public List<RelatorioServidorVO> getRelatorioServidor(Calendar dataInicio, Calendar dataFinal) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		List<RelatorioServidorVO> servidores= new ArrayList<RelatorioServidorVO>();
		StringBuilder sql= new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();
			
			sql.append("SELECT *  ");
			sql.append("FROM DBSPJC.listarServidores  ");
			sql.append("WHERE dataInicio BETWEEN ? AND ? ");
			preStmt = connection.prepareStatement(sql.toString());
			
			Date dataIni= new Date(dataInicio.getTimeInMillis());
//			dataIni.setHours(0);
//			dataIni.setMinutes(0);
			Date dataFim= new Date(dataFinal.getTimeInMillis());
//			dataFim.setHours(23);
//			dataFim.setMinutes(59);
			
			preStmt.setDate(1, dataIni);
			preStmt.setDate(2, dataFim);
			rs = preStmt.executeQuery();

			while (rs.next()) {
				RelatorioServidorVO servidor= new RelatorioServidorVO();
				servidor.setCpf(rs.getString("cpf"));
				servidor.setNomeJuizado(rs.getString("nomeJuizado"));
				servidor.setNomeServidor(rs.getString("nomeServidor"));
				servidores.add(servidor);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Codigo: "
					+ e.getErrorCode());
		} 
		return servidores;		
	}
	
}
