package br.ufrpe.spjc.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufrpe.framework.transaction.SystemException;
import br.ufrpe.framework.transaction.TransactionManager;
import br.ufrpe.spjc.negocio.entidade.Juizado;

public class JuizadoDAO {

	public void inserir() {
		System.out.println("Inserir Repositorio");
	}

	public List<Juizado> findByFilter(Juizado filtro) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		List<Juizado> juizados= new ArrayList<Juizado>();
		StringBuilder sql= new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();
			
			if (filtro != null) {
			
				sql.append("SELECT id, nome, salaAudiencia ");
				sql.append("FROM DBSPJC.Juizado WHERE 0= 0 ");
				if ( filtro.getNome() != null )
					sql.append("AND nome like '%?%'");
				
				preStmt = connection.prepareStatement(sql.toString());
				int idx= 1;
				
				if ( filtro.getNome() != null )
					preStmt.setString(idx++, filtro.getNome());
				
				rs = preStmt.executeQuery();
	
				while (rs.next()) {
					Juizado juizado= new Juizado();
					juizado.setId(rs.getInt("id"));
					juizado.setNome(rs.getString("nome"));
					juizado.setSalaAudiencia(rs.getString("salaAudiencia"));
					juizados.add(juizado);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Codigo: "
					+ e.getErrorCode());
		} 
		return juizados;	
	}

}
