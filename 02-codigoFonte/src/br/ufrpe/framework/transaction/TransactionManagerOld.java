package br.ufrpe.framework.transaction;

import java.sql.SQLException;

import org.apache.log4j.Logger;

public abstract class TransactionManager {

	private Object session = new Object();
	private Transaction transaction = new Transaction();
	private Logger logg = Logger.getLogger(TransactionManager.class);
	
	private static JDBCTransactionManager instance = new JDBCTransactionManager();

	/**
	 * Abstracted methods
	 */
	protected abstract void doStartTransaction(Object session);

	protected abstract void doFinishTransaction(Object session, boolean sucess);

	protected abstract Object doCreateSession();

	protected abstract void doCloseSession(Object session);

	/**
	 * Retorna uma instancia de uma determinada classe ja com o proxy plugado.
	 */
	public static TransactionManager instance() {
		return instance;
	}

	/**
	 * Retorna uma conexao com o banco de dados (se existir uma transacao corrente).
	 */
	public Object getSession() {
		if (this.session == null) {
			try {
				this.session = doCreateSession();
				logg.info("\tu user create: " + ((java.sql.Connection) this.session).getMetaData().getUserName().toString()
						+ "\r\n");
			} catch (SQLException e) {
				logg.error(e);
			}
		}
		return this.session;
	}

	/**
	 * Libera uma conexcao com o banco de dados.
	 */
	public void closeSession() {
		if (transaction == null) {
			doCloseSession(this.session);
			logg.fatal("\t close connection\r\n");
		}
	}

	/**
	 * Inicia uma transacao com o banco de dados.
	 */
	public void startTransaction() {
		if (transaction != null) {
			transaction.count++;

		} else {
			transaction = new Transaction();
			transaction.count = 1;
			transaction.success = true;
			transaction.session = this.getSession();
			this.doStartTransaction(transaction.session);
		}
	}

	public void finishTransaction() {
		if (transaction == null) {
			return;
		}

		// decrementa o contador
		transaction.count--;

		if (transaction.count == 0) {
			this.doFinishTransaction(transaction.session, transaction.success);
		}
	}

	public void setRollBackOnly() {
		if (transaction != null) {
			transaction.success = false;
		}
	}

}