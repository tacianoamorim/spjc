package br.ufrpe.spjc.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufrpe.framework.transaction.SystemException;
import br.ufrpe.framework.transaction.TransactionManager;
import br.ufrpe.spjc.negocio.entidade.Feito;

public class FeitoDAO {

	public List<Feito> list() {
		Connection connection = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		List<Feito> entitys = new ArrayList<Feito>();
		StringBuilder sql = new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();

			sql.append("SELECT id, nome ");
			sql.append("FROM DBSPJC.Feito ORDER BY nome ");
			preStmt = connection.prepareStatement(sql.toString());
			rs = preStmt.executeQuery();

			while (rs.next()) {
				Feito entity = new Feito();
				entity.setId(rs.getInt("id"));
				entity.setNome(rs.getString("nome"));
				entitys.add(entity);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Codigo: " + e.getErrorCode());
		}
		return entitys;
	}

	public Feito findById(int id) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		Feito entity = null;
		StringBuilder sql = new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();

			sql.append("SELECT nome ");
			sql.append("FROM DBSPJC.Feito WHERE id= ? ");

			preStmt = connection.prepareStatement(sql.toString());
			preStmt.setInt(1, id);

			rs = preStmt.executeQuery();

			while (rs.next()) {
				entity = new Feito();
				entity.setId(id);
				entity.setNome(rs.getString("nome"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Codigo: " + e.getErrorCode());
		}
		return entity;
	}
}
