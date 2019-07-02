package br.ufrpe.spjc.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ufrpe.framework.transaction.SystemException;
import br.ufrpe.framework.transaction.TransactionManager;
import br.ufrpe.spjc.negocio.entidade.Magistrado;

public class MagistradoDAO {

	public Magistrado findById(String cpf) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		Magistrado magistrado= null;
		StringBuilder sql= new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();
			
			sql.append("SELECT Cpf, Nome, Senha, TipoMagistrado, Matricula ");
			sql.append("FROM DBSPJC.Magistrado WHERE Cpf= ? ");
			
			preStmt = connection.prepareStatement(sql.toString());
			preStmt.setString(1, cpf);
			
			rs = preStmt.executeQuery();

			while (rs.next()) {
				magistrado= new Magistrado();
				magistrado.setMatricula(rs.getInt("matricula"));
				magistrado.setCpf(rs.getString("cpf"));
				magistrado.setNome(rs.getString("nome"));
				magistrado.setSenha(rs.getString("senha"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Codigo: "
					+ e.getErrorCode());
		} finally {
//			transactionManager.closeConnection(connection);
		}
		return magistrado;		
	}

	
}
