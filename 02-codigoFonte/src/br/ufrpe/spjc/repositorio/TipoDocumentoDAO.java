package br.ufrpe.spjc.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufrpe.framework.transaction.SystemException;
import br.ufrpe.framework.transaction.TransactionManager;
import br.ufrpe.spjc.negocio.entidade.TipoDocumento;

public class TipoDocumentoDAO {

	public List<TipoDocumento> list() {
		Connection connection = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		StringBuilder sql= new StringBuilder();
		List<TipoDocumento> lista= new ArrayList<TipoDocumento>();

		try {
			connection = (Connection) transactionManager.getConnection();

			sql.append("SELECT id, nome  ");
			sql.append("FROM DBSPJC.TipoDocumento WHERE id <> 1 and id <>2 ");
			sql.append("ORDER BY nome ");
			preStmt = connection.prepareStatement(sql.toString());
			rs = preStmt.executeQuery();

			while (rs.next()) {
				TipoDocumento entity= new TipoDocumento();
				entity.setId(rs.getInt("id"));
				entity.setNome(rs.getString("nome"));
				lista.add(entity);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Codigo: "
					+ e.getErrorCode());
		} finally {
		}
		return lista;		
	}

}
