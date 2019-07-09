package br.ufrpe.spjc.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufrpe.framework.transaction.SystemException;
import br.ufrpe.framework.transaction.TransactionManager;
import br.ufrpe.spjc.negocio.vo.RelatorioVO;

public class RelatorioDAO {

	public List<RelatorioVO> list(int mesInicio, int mesFim, int ano) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		StringBuilder sql= new StringBuilder();
		List<RelatorioVO>  lista= new ArrayList<RelatorioVO>();

		try {
			connection = (Connection) transactionManager.getConnection();

			sql.append("SELECT jpa.nome, jpa.dataAgendamento, jpa.hora, jpa.processo, ");
			sql.append("	prp.polo, prp.Tipo, prp.nomePTR, jpa.dataHora  ");
			sql.append("FROM DBSPJC.juizadoPautaAudiencia jpa  ");
			sql.append("	INNER JOIN DBSPJC.parteRepresentanteProcesso prp ON  ");
			sql.append("	 prp.processo = jpa.processo  ");
			sql.append("WHERE ");
			sql.append("	MONTH(jpa.dataAgendamento) BETWEEN ? AND ?  ");
			sql.append("    AND YEAR(jpa.dataAgendamento) = ?  ");
			sql.append("ORDER BY  ");
			sql.append("	jpa.nome DESC, jpa.dataAgendamento DESC, jpa.hora DESC, ");
			sql.append("	jpa.processo DESC, prp.polo, prp.Tipo, jpa.nome ");	
			
			preStmt = connection.prepareStatement(sql.toString());
			preStmt.setInt(1, mesInicio);
			preStmt.setInt(2, mesFim);
			preStmt.setInt(3, ano);
			
			rs = preStmt.executeQuery();

			while (rs.next()) {
				RelatorioVO relatorio= new RelatorioVO();
				relatorio.setJuizado(rs.getString("nome"));
				relatorio.setDataHoraAudiencia(rs.getString("dataHora"));
				relatorio.setProcesso(rs.getString("processo"));
				relatorio.setPolo(rs.getString("polo"));
				relatorio.setParte(rs.getString("nomePTR"));
				lista.add(relatorio);
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
