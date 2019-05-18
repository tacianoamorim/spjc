package br.ufrpe.framework.transaction;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCTransactionManager extends TransactionManager {
	private Properties dataBaseProperties = new Properties();

	protected JDBCTransactionManager() {
		loadConfiguration();
	}

	protected void loadConfiguration() {
		try {
			InputStream input = getClass().getClassLoader().getResourceAsStream("database.properties");
			dataBaseProperties.load(input);
			dataBaseProperties.list(System.out);

		} catch (IOException ex) {
			throw new SystemException("Arquivo database.properties nao encontrado.", ex);
		}
	}

	public void doStartTransaction(Object session) {
		Connection con = (Connection) session;
		try {
			con.setAutoCommit(false);
		} catch (SQLException ex) {
			throw new SystemException("Nao foi possivel iniciar um transacao", ex);
		}
	}

	/**
	 * Cria uma conexcao com o banco de dados.
	 */
	public Object doCreateSession() {
		DataSource dataSource = createUserPool();
		try {
			return dataSource.getConnection();
		} catch (SQLException ex) {
			throw new SystemException("Nao foi possivel criar uma conexao", ex);
		}
	}

	protected DataSource createUserPool() {
		// cria um Data Source
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		try {

		// seta o Driver e a URL de conexao
			cpds.setDriverClass(dataBaseProperties.getProperty("driverClassName"));
			cpds.setJdbcUrl(dataBaseProperties.getProperty("url"));
			cpds.setUser(dataBaseProperties.getProperty("use"));
			cpds.setPassword(dataBaseProperties.getProperty("use"));

		
		} catch (PropertyVetoException ex) {
			throw new SystemException(ex);
		}

		cpds.setAcquireRetryAttempts(3);
		cpds.setAcquireIncrement(1);
		cpds.setMinPoolSize(0);
		cpds.setInitialPoolSize(1);
		cpds.setMaxPoolSize(3);
		cpds.setMaxIdleTime(3600);

		return cpds;
	}

	/**
	 * Libera uma conexao com o banco de dados.
	 */
	public void doCloseSession(Object session) {
		Connection con = (Connection) session;

		try {
			if (con != null && !con.isClosed()) {
				con.close();
			}
		} catch (SQLException ex) {
			throw new SystemException("Nao foi possivel fechar uma conexao", ex);
		} catch (Error er) {
			throw new SystemException("Nao foi possivel fechar uma conexao");
		}
	}

	public void doFinishTransaction(Object session, boolean sucess) {
		Connection con = (Connection) session;
		try {
			if (sucess) {
				con.commit();
			} else {
				con.rollback();
			}
			con.setAutoCommit(true);

		} catch (SQLException e) {
			throw new SystemException("Nao foi possivel finalizar uma transacao", e);
		} finally {
			doCloseSession(con);
		}
	}
}