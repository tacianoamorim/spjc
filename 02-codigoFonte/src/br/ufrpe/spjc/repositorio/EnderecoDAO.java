package br.ufrpe.spjc.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ufrpe.framework.transaction.SystemException;
import br.ufrpe.framework.transaction.TransactionManager;
import br.ufrpe.spjc.negocio.entidade.Endereco;

public class EnderecoDAO {

	public Endereco findById(int cep) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		Endereco endereco= null;
		StringBuilder sql= new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();
			
			sql.append("SELECT Rua, Bairro, Estado, Cidade ");
			sql.append("FROM DBSPJC.Endereco WHERE Cep= ? ");
			
			preStmt = connection.prepareStatement(sql.toString());
			preStmt.setInt(1, cep);
			
			rs = preStmt.executeQuery();

			while (rs.next()) {
				endereco= new Endereco();
				endereco.setCep(cep);
				endereco.setRua(rs.getString("Rua"));
				endereco.setCidade(rs.getString("Cidade"));
				endereco.setBairro(rs.getString("Bairro"));
				endereco.setEstado(rs.getString("Estado"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Codigo: "
					+ e.getErrorCode());
		} finally {
//			transactionManager.closeConnection(connection);
		}
		return endereco;		
	}

	
	public void inserir(Endereco entity) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		StringBuilder sql= new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();
			
			sql.append("INSERT INTO DBSPJC.Endereco (Cep, Rua, Bairro, Estado, Cidade) ");
			sql.append("VALUES(?, ?, ?, ?, ?) ");

			preStmt= connection.prepareStatement(sql.toString());
			preStmt.setInt(1, entity.getCep());
			preStmt.setString(2, entity.getRua());			
			preStmt.setString(3, entity.getBairro());
			preStmt.setString(4, entity.getEstado());
			preStmt.setString(5, entity.getCidade());
			
			preStmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Codigo: "
					+ e.getErrorCode());
		} finally {
//			transactionManager.closeConnection(connection);
		}
	}

}
