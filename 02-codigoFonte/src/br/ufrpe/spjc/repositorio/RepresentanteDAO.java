package br.ufrpe.spjc.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ufrpe.framework.transaction.SystemException;
import br.ufrpe.framework.transaction.TransactionManager;
import br.ufrpe.spjc.negocio.entidade.Representante;

public class RepresentanteDAO {

	public Representante findById(int id) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		Representante Representante= null;

		try {
			connection = (Connection) transactionManager.getConnection();
			//preStmt = connection.prepareStatement("SELECT matricula, nome, senha FROM Representante WHERE matricula= ? ");
			preStmt.setInt(1, id);
			
			rs = preStmt.executeQuery();

			while (rs.next()) {
				Representante = carregarRepresentante(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Codigo: "
					+ e.getErrorCode());
		} finally {
			transactionManager.closeConnection(connection);
		}
		return Representante;		
	}

	private Representante carregarRepresentante(ResultSet rs) throws SQLException {
		Representante Representante= new Representante();
		Representante.setMatricula(rs.getInt("matricula"));
		Representante.setNome(rs.getString("nome"));
		Representante.setSenha(rs.getString("senha"));
		return Representante;
	}
	
	public Representante list(Representante filtro) {
		return findByFilter(filtro);
	}
	
	public Representante findByFilter(Representante filtro) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		Representante Representante= null;
		StringBuilder sql= new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();
			
			if (filtro != null) {
			
				sql.append("SELECT matricula, nome, senha FROM Representante WHERE 0= 0 ");
				if ( filtro.getNome() != null )
					sql.append("AND nome like '%?%'");
				
				preStmt = connection.prepareStatement(sql.toString());
				int matriculax= 1;
				
				if ( filtro.getNome() != null )
					preStmt.setString(matriculax++, filtro.getNome());
				
				rs = preStmt.executeQuery();
	
				while (rs.next()) {
					Representante = carregarRepresentante(rs);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Codigo: "
					+ e.getErrorCode());
		} finally {
			transactionManager.closeConnection(connection);
		}
		return Representante;		
	}
	
	
	public void inserir(Representante entity) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		StringBuilder sql= new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();
			
			sql.append("INSERT INTO DBSPJC.Representante ");
			sql.append(" (cpf, nome, oab, email, polo, senha, telefone, numeroEndereco, tipoRepresentante, matricula) ");
			sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");

			preStmt= connection.prepareStatement(sql.toString());
			preStmt.setString(1, entity.getCpf());
			preStmt.setString(2, entity.getRua());			
			preStmt.setString(3, entity.getBairro());
			preStmt.setString(4, entity.getEstado());
			preStmt.setString(5, entity.getCidade());
			preStmt.setString(6, entity.getPais());
			
			preStmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Codigo: "
					+ e.getErrorCode());
		} finally {
			transactionManager.closeConnection(connection);
		}
	}

}
