package br.ufrpe.spjc.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufrpe.framework.transaction.SystemException;
import br.ufrpe.framework.transaction.TransactionManager;
import br.ufrpe.spjc.negocio.entidade.Endereco;
import br.ufrpe.spjc.negocio.entidade.Entity;
import br.ufrpe.spjc.negocio.entidade.Magistrado;
import br.ufrpe.spjc.negocio.entidade.Servidor;

public class MagistradoDAO {

	public Magistrado findById(String cpf) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		Magistrado magistrado = null;
		StringBuilder sql = new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();

			sql.append("SELECT Cpf, Nome, senha, numeroEndereco, matricula, endereco ");
			sql.append("FROM DBSPJC.Magistrado WHERE Cpf= ? ");

			preStmt = connection.prepareStatement(sql.toString());
			preStmt.setString(1, cpf);

			rs = preStmt.executeQuery();

			while (rs.next()) {
				magistrado = carregarMagistrado(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Codigo: " + e.getErrorCode());
		}
		return magistrado;
	}

	public List<Entity> List() {
		Connection connection = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		List<Entity> entities = new ArrayList<Entity>();
		StringBuilder sql = new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();

			sql.append("SELECT Cpf, Nome ");
			sql.append("FROM DBSPJC.Magistrado  ");
			sql.append(" ORDER BY Nome ");
			preStmt = connection.prepareStatement(sql.toString());
			rs = preStmt.executeQuery();

			while (rs.next()) {
				Entity entity = new Servidor();
				entity.setCpf(rs.getString("cpf"));
				entity.setNome(rs.getString("nome"));
				entities.add(entity);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Codigo: " + e.getErrorCode());
		}
		return entities;
	}

	private Magistrado carregarMagistrado(ResultSet rs) throws SQLException {
		Magistrado magistrado = new Magistrado();
		magistrado.setMatricula(rs.getInt("matricula"));
		magistrado.setCpf(rs.getString("cpf"));
		magistrado.setNome(rs.getString("nome"));
		magistrado.setNumero(rs.getString("numeroEndereco"));
		magistrado.setSenha(rs.getString("senha"));

		Endereco endereco = new Endereco();
		endereco.setCep(rs.getInt("endereco"));
		magistrado.setEndereco(endereco);

		return magistrado;
	}

	public List<Magistrado> findByFilter(Magistrado filtro) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		List<Magistrado> magistrado = new ArrayList<Magistrado>();
		StringBuilder sql = new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();

			if (filtro != null) {

				sql.append("SELECT cpf, nome, senha, ");
				sql.append(" numeroEndereco, matricula, endereco ");
				sql.append(" FROM DBSPJC.magistrado WHERE 0= 0 ");
				if (filtro.getNome() != null)
					sql.append("AND nome like '%?%'");

				preStmt = connection.prepareStatement(sql.toString());
				int idx = 1;

				if (filtro.getNome() != null)
					preStmt.setString(idx++, filtro.getNome());

				rs = preStmt.executeQuery();

				while (rs.next()) {
					magistrado.add(carregarMagistrado(rs));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Codigo: " + e.getErrorCode());
		} finally {
//			transactionManager.closeConnection(connection);
		}
		return magistrado;
	}

	public void inserir(Magistrado entity) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		StringBuilder sql = new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();

			sql.append("INSERT INTO dbspjc.magistrado ");
			sql.append(" (cpf, endereco, matricula, nome, senha, numeroEndereco )");
			sql.append(" VALUES (?, ?, ?, ?, ?, ?) ");

			preStmt = connection.prepareStatement(sql.toString());
			preStmt.setString(1, entity.getCpf());
			preStmt.setInt(2, entity.getEndereco().getCep());
			preStmt.setInt(3, entity.getMatricula());
			preStmt.setString(4, entity.getNome());
			preStmt.setString(5, entity.getSenha());
			preStmt.setString(6, entity.getNumero());

			preStmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Codigo: " + e.getErrorCode());
		} finally {
//				transactionManager.closeConnection(connection);
		}
		return;
	}

	public void update(Magistrado entity) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		StringBuilder sql = new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();

			sql.append("UPDATE DBSPJC.magistrado SET ");
			sql.append("nome=?, senha=?, ");
			sql.append("numeroEndereco=?, matricula=?, endereco=? ");
			sql.append(" WHERE cpf= ? ");

			preStmt = connection.prepareStatement(sql.toString());
			preStmt.setString(1, entity.getNome());
			preStmt.setString(2, entity.getSenha());
			preStmt.setString(3, entity.getNumero());
			preStmt.setInt(4, entity.getMatricula());
			preStmt.setInt(5, entity.getEndereco().getCep());
			preStmt.setString(6, entity.getCpf());

			preStmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Codigo: " + e.getErrorCode());
		} finally {
//				transactionManager.closeConnection(connection);
		}
	}

	public void apagar(Magistrado magistradodel) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		StringBuilder sql = new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();

			sql.append("DELETE FROM DBSPJC.magistrado WHERE cpf= ? ");

			preStmt = connection.prepareStatement(sql.toString());
			preStmt.setString(1, magistradodel.getCpf());

			preStmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Codigo: " + e.getErrorCode());
		} finally {
//				transactionManager.closeConnection(connection);
		}

	}
}
