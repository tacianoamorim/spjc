package br.ufrpe.spjc.gui.marcos;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.ufrpe.spjc.negocio.entidade.Processo;

public class TableDocumento extends AbstractTableModel {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7419763598897891865L;
	
	private List<Processo> list;
	private String[] colunas = new String[] { 
		"NPU", "Observação"
	};

	private List<Processo> list1;

	/** Creates a new instance of TableModel */
	public TableDocumento(List<Processo> processo) {
		this.list = processo;
	}

	public TableDocumento() {
		this.list = new ArrayList<Processo>();
	}

	public int getRowCount() {
		return list.size();
	}

	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return colunas[columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	public void setValueAt(Processo aValue, int rowIndex) {
		Processo processo = list.get(rowIndex);
		processo.setNpu( aValue.getNpu() );
		processo.setObservacao(aValue.getObservacao());
		
		fireTableCellUpdated(rowIndex, 0);
		fireTableCellUpdated(rowIndex, 1);

	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Processo processo = list.get(rowIndex);
		System.out.println("setValueAt02");
		switch (columnIndex) {
		case 0:
			processo.setNpu( aValue.toString() );
		case 1:
			processo.setObservacao(aValue.toString());
	
		default:
			System.err.println("indice da coluna invalido");
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Processo processoselecionado = list.get(rowIndex);
		String valueObject = null;
		switch (columnIndex) {
		case 0:
			valueObject = processoselecionado.getNpu();
			break;
		case 1:
			valueObject = processoselecionado.getObservacao();
			break;
		default:
			System.err.println("indice invalido para propriedade do bean Processo.class");
		}
		return valueObject;
	}


	public Object getValueAt1(int rowIndex, int columnIndex) {
		Processo processo = list1.get(rowIndex);
		String valueObject = null;
		switch (columnIndex) {
		case 0:
			valueObject = processo.getNpu();
			break;
		case 1:
			valueObject = processo.getObservacao()+"";
			break;
		default:
			System.err.println("indice invalido para propriedade do bean Processo.class");
		}
		return valueObject;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public Processo get(int indiceLinha) {
		return list.get(indiceLinha);
	}

	public Processo get1(int indiceLinha1) {
		return list1.get(indiceLinha1);
	}

	public void add(Processo processo) {
		list.add(processo);
		int ultimoIndice = getRowCount() - 1;
		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

	public void add1(Processo processo) {
		list1.add(processo);
		int ultimoIndice = getRowCount() - 1;
		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

	public void remove(int indiceLinha) {
		list.remove(indiceLinha);
		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}
	
	public void remove1(int indiceLinha) {
		list1.remove(indiceLinha);
		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}
	public void addList(List<Processo> novosprocesso) {
		int tamanhoAntigo = getRowCount();
		list.addAll(novosprocesso);
		fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
	}
	
	public void addList1(List<Processo> novosprocesso) {
		int tamanhoAntigo = getRowCount();
		list1.addAll(novosprocesso);
		fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
	}
	
	public void limpar() {
		list.clear();
		fireTableDataChanged();
	}
	
	public void limpar1() {
		list1.clear();
		fireTableDataChanged();
	}
	public boolean isEmpty() {
		return list.isEmpty();
	}	
	
	public boolean isEmpty1() {
		return list1.isEmpty();
	}
}
