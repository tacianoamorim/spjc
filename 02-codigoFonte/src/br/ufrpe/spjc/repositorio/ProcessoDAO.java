package br.ufrpe.spjc.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufrpe.framework.transaction.SystemException;
import br.ufrpe.framework.transaction.TransactionManager;
import br.ufrpe.spjc.negocio.entidade.Fase;
import br.ufrpe.spjc.negocio.entidade.Feito;
import br.ufrpe.spjc.negocio.entidade.Juizado;
import br.ufrpe.spjc.negocio.entidade.Processo;
import br.ufrpe.spjc.negocio.entidade.ProcessoFase;
import br.ufrpe.spjc.negocio.entidade.ProcessoFeito;

public class ProcessoDAO {

	public Processo findById(String npu) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		Processo entity= null;
		StringBuilder sql= new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();
			
			sql.append("SELECT p.npu, j.nome AS 'Juizado', f.nome AS 'Fase', ft.nome AS 'Feito'  ");
			sql.append("FROM DBSPJC.Processo p  ");
			sql.append("	INNER JOIN DBSPJC.ProcessoFase pf ON pf.processo= p.npu ");
			sql.append("    INNER JOIN DBSPJC.ProcessoFeito pft ON pft.processo= p.npu ");
			sql.append("    INNER JOIN DBSPJC.Fase f ON pf.fase= f.id ");
			sql.append("    INNER JOIN DBSPJC.Feito ft ON pft.feito= ft.id ");
			sql.append("    INNER JOIN DBSPJC.Juizado j ON j.id= p.juizado ");
			sql.append("WHERE p.npu= ? ");	
			
			preStmt = connection.prepareStatement(sql.toString());
			preStmt.setString(1, npu);
			
			rs = preStmt.executeQuery();

			while (rs.next()) {
				entity= carregarEntity(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Codigo: "
					+ e.getErrorCode());
		} finally {
//			transactionManager.closeConnection(connection);
		}
		return entity;		
	}	
	
	public List<Processo> findByFilter(Processo filtro) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		List<Processo> processos= new ArrayList<Processo>();
		StringBuilder sql= new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();
			
			sql.append("SELECT p.npu, j.nome AS 'Juizado', f.nome AS 'Fase', ft.nome AS 'Feito'  ");
			sql.append("FROM DBSPJC.Processo p  ");
			sql.append("	INNER JOIN DBSPJC.ProcessoFase pf ON pf.processo= p.npu ");
			sql.append("    INNER JOIN DBSPJC.ProcessoFeito pft ON pft.processo= p.npu ");
			sql.append("    INNER JOIN DBSPJC.Fase f ON pf.fase= f.id ");
			sql.append("    INNER JOIN DBSPJC.Feito ft ON pft.feito= ft.id ");
			sql.append("    INNER JOIN DBSPJC.Juizado j ON j.id= p.juizado ");
			sql.append("WHERE 0= 0 ");			
			
			if (filtro != null) {
				
				if ( filtro.getNpu() != null )
					sql.append("	AND p.npu= ? ");
				
				preStmt = connection.prepareStatement(sql.toString());
				int idx= 1;
				
				if ( filtro.getNpu() != null )
					preStmt.setString(idx++, filtro.getNpu());
				
				rs = preStmt.executeQuery();
	
				while (rs.next()) {
					processos.add(carregarEntity(rs));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Codigo: "
					+ e.getErrorCode());
		} finally {
//			transactionManager.closeConnection(connection);
		}
		return processos;		
	}

	private Processo carregarEntity(ResultSet rs) throws SQLException {
		Processo processo= new Processo();
		processo.setNpu(rs.getString("npu"));
		
		Juizado juizado= new Juizado();
		juizado.setNome(rs.getString("Juizado"));
		processo.setJuizado(juizado);
		
		Fase fase= new Fase();
		fase.setNome(rs.getString("Fase"));
		
		Feito feito= new Feito();
		feito.setNome(rs.getString("Feito"));		
		
		ProcessoFeito processoFeito= new ProcessoFeito();
		processoFeito.setFeito(feito);
		processo.setProcessoFeito(processoFeito);
		
		ProcessoFase processoFase= new ProcessoFase();
		processoFase.setFase(fase);
		processo.setProcessoFase(processoFase);
		return processo;
	}
	
}
