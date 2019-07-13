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
import br.ufrpe.spjc.negocio.entidade.Representante;

public class RepresentanteDAO {

	public Representante findById(String id) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		Representante representante= null;

		try {
			connection = (Connection) transactionManager.getConnection();
			preStmt = connection.prepareStatement("SELECT cpf, nome, oab, email, "
					+ "polo, senha, telefone, numeroEndereco, tipoRepresentante, matricula, endereco "
					+ " FROM DBSPJC.Representante WHERE cpf= ? ");
			preStmt.setString(1, id);
			rs = preStmt.executeQuery();

			while (rs.next()) {
				representante = carregarRepresentante(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Codigo: "
					+ e.getErrorCode());
		} finally {
//			transactionManager.closeConnection(connection);
		}
		return representante;		
	}

	private Representante carregarRepresentante(ResultSet rs) throws SQLException {
		Representante representante= new Representante();
		representante.setMatricula(rs.getInt("matricula"));
		representante.setCpf(rs.getString("cpf"));
		representante.setNome(rs.getString("nome"));
		representante.setOab(rs.getString("oab"));
		representante.setEmail(rs.getString("email"));
		representante.setPolo(rs.getString("polo"));
		representante.setTelefone(rs.getString("telefone"));
		representante.setNumero(rs.getString("numeroEndereco"));
		representante.setTipo(rs.getString("tipoRepresentante"));
		representante.setTelefone(rs.getString("telefone"));		
		representante.setSenha(rs.getString("senha"));
		
		Endereco endereco= new Endereco();
		endereco.setCep(rs.getInt("endereco"));
		representante.setEndereco(endereco);
		
		return representante;
	}
	
	public List<Representante> findByFilter(Representante filtro) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		List<Representante> representantes= new ArrayList<Representante>();
		StringBuilder sql= new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();
			
			if (filtro != null) {
				sql.append("SELECT cpf, nome, oab, email, polo, senha, telefone, ");
				sql.append(" numeroEndereco, tipoRepresentante, matricula, endereco ");
				sql.append(" FROM DBSPJC.Representante WHERE 0= 0 ");
				if ( filtro.getNome() != null )
					sql.append("AND nome like '%?%'");
				
				preStmt = connection.prepareStatement(sql.toString());
				int idx= 1;
				
				if ( filtro.getNome() != null )
					preStmt.setString(idx++, filtro.getNome());
				
				rs = preStmt.executeQuery();
	
				while (rs.next()) {
					representantes.add(carregarRepresentante(rs));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Codigo: "
					+ e.getErrorCode());
		} 
		return representantes;		
	}
	
	public void inserir(Representante entity) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		StringBuilder sql= new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();
			
			sql.append("INSERT INTO DBSPJC.Representante ");
			sql.append(" (cpf, nome, oab, email, polo, senha, telefone, numeroEndereco, ");
			sql.append(" tipoRepresentante, matricula, endereco) ");
			sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");

			preStmt= connection.prepareStatement(sql.toString());
			preStmt.setString(1, entity.getCpf());
			preStmt.setString(2, entity.getNome());			
			preStmt.setString(3, entity.getOab());
			preStmt.setString(4, entity.getEmail());
			preStmt.setString(5, entity.getPolo());
			preStmt.setString(6, entity.getSenha());
			preStmt.setString(7, entity.getTelefone());
			preStmt.setString(8, entity.getNumero());
			preStmt.setString(9, entity.getTipo());
			preStmt.setInt(10, entity.getMatricula());
			preStmt.setInt(11, entity.getEndereco().getCep());
			
			preStmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Codigo: "
					+ e.getErrorCode());
		} 
	}
	
	public void update(Representante entity) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		StringBuilder sql= new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();
			
			sql.append("UPDATE DBSPJC.Representante SET ");
			sql.append(" nome=?, oab=?, email=?, polo=?, senha=?, ");
			sql.append(" telefone=?, numeroEndereco=?, tipoRepresentante=?, matricula=?, endereco=? ");
			sql.append(" WHERE cpf= ? ");

			preStmt= connection.prepareStatement(sql.toString());
			preStmt.setString(1, entity.getNome());			
			preStmt.setString(2, entity.getOab());
			preStmt.setString(3, entity.getEmail());
			preStmt.setString(4, entity.getPolo());
			preStmt.setString(5, entity.getSenha());
			
			preStmt.setString(6, entity.getTelefone());
			preStmt.setString(7, entity.getNumero());
			preStmt.setString(8, entity.getTipo());
			preStmt.setInt(9, entity.getMatricula());
			preStmt.setInt(10, entity.getEndereco().getCep());
			preStmt.setString(11, entity.getCpf());
			
			preStmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Codigo: "
					+ e.getErrorCode());
		}
	}

	public void apagar(Representante representante) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		StringBuilder sql= new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();
			
			sql.append("DELETE FROM DBSPJC.Representante WHERE cpf= ? ");
			preStmt= connection.prepareStatement(sql.toString());
			preStmt.setString(1, representante.getCpf());
			
			preStmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Codigo: "
					+ e.getErrorCode());
		} 
	}	

}
