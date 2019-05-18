package br.ufrpe.framework.transaction;

public class Transaction {
	
    //conexao com o banco
    public Object session;
    
    //contador para marcar quantos begins foram dados
    public int count;
    
    //marca se a transacao deve ser confirmada (commit)
    public boolean success = true;
    
    //momento de inicio da transacao
    public long time;
}
