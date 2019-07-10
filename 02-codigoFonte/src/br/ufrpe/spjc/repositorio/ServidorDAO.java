package br.ufrpe.spjc.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ufrpe.framework.transaction.SystemException;
import br.ufrpe.framework.transaction.TransactionManager;
import br.ufrpe.spjc.negocio.entidade.Juizado;
import br.ufrpe.spjc.negocio.entidade.Servidor;

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

	
}
