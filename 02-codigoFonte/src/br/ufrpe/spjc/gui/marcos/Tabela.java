package br.ufrpe.spjc.gui.marcos;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.ufrpe.spjc.negocio.entidade.Magistrado;
import br.ufrpe.spjc.negocio.entidade.Processo;

public class Tabela extends AbstractTableModel {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7419763598897891865L;
	
	private List<Magistrado> list;
	private String[] colunas = new String[] { 
		"CPF", "Nome", "Matricula"
	};

	private List<Processo> list1;
	private String[] colunas1 = new String[] { 
		"NPUp", "Juizado", "NPUf"
	};

	/** Creates a new instance of TableModel */
	public Tabela(List<Magistrado> magistrado) {
		this.list = magistrado;
	}

	public Tabela() {
		this.list = new ArrayList<Magistrado>();
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

	public void setValueAt(Magistrado aValue, int rowIndex) {
		Magistrado magistrado = list.get(rowIndex);
		System.out.println("setValueAt01");
		magistrado.setCpf( aValue.getCpf() );
		magistrado.setNome(aValue.getNome());
		magistrado.setMatricula(aValue.getMatricula());
		magistrado.setEndereco(aValue.getEndereco());
		
		fireTableCellUpdated(rowIndex, 0);
		fireTableCellUpdated(rowIndex, 1);
		fireTableCellUpdated(rowIndex, 2);
		fireTableCellUpdated(rowIndex, 3);

	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Magistrado magistrado = list.get(rowIndex);
		System.out.println("setValueAt02");
		switch (columnIndex) {
		case 0:
			magistrado.setCpf( aValue.toString() );
		case 1:
			magistrado.setNome(aValue.toString());
		case 2:
			magistrado.setMatricula(Integer.parseInt(aValue.toString()));  //  aValue.toString().hashCode() );
	
		default:
			System.err.println("indice da coluna invalido");
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Magistrado magistradoselecionado = list.get(rowIndex);
		String valueObject = null;
		switch (columnIndex) {
		case 0:
			valueObject = magistradoselecionado.getCpf();
			break;
		case 1:
			valueObject = magistradoselecionado.getNome();
			break;
		case 2:
			valueObject = magistradoselecionado.getMatricula()+"";
			break;
		default:
			System.err.println("indice invalido para propriedade do bean Magistrado.class");
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
			valueObject = processo.getJuizado()+"";
			break;
		case 2:
			valueObject = processo.getProcessoFase()+"";
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

	public Magistrado get(int indiceLinha) {
		return list.get(indiceLinha);
	}

	public Processo get1(int indiceLinha1) {
		return list1.get(indiceLinha1);
	}

	public void add(Magistrado magistrado) {
		list.add(magistrado);
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
	public void addList(List<Magistrado> novosmagistrado) {
		int tamanhoAntigo = getRowCount();
		list.addAll(novosmagistrado);
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
