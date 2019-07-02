package br.ufrpe.spjc.negocio.entidade;

import java.util.Calendar;

public class Pauta {
	private int id;
	private Calendar dataAgendamento;
	private int qtdeProcesso;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Calendar getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(Calendar dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public int getQtdeProcesso() {
		return qtdeProcesso;
	}

	public void setQtdeProcesso(int qtdeProcesso) {
		this.qtdeProcesso = qtdeProcesso;
	}

	@Override
	public String toString() {
		return id + "- " 
				+ dataAgendamento.get(Calendar.DATE) +"/"+ dataAgendamento.get(Calendar.MONTH) +"/"+
					dataAgendamento.get(Calendar.YEAR)  
				+ " (" + qtdeProcesso + ")";
	}

	
}
