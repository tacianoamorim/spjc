package br.ufrpe.spjc.gui.taciano;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.ufrpe.spjc.negocio.entidade.Representante;

public class FrmTipoITableModel extends AbstractTableModel {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7419763598897891865L;
	
	private List<Representante> list;
	private String[] colunas = new String[] { 
		"CPF", "Nome", "Tipo", "Polo" 
	};

	/** Creates a new instance of TableModel */
	public FrmTipoITableModel(List<Representante> representantes) {
		this.list = representantes;
	}

	public FrmTipoITableModel() {
		this.list = new ArrayList<Representante>();
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

	public void setValueAt(Representante aValue, int rowIndex) {
		Representante representante = list.get(rowIndex);
		System.out.println("setValueAt01");
		representante.setCpf( aValue.getCpf() );
		representante.setNome(aValue.getNome());
		representante.setTipo(aValue.getTipo());
		representante.setPolo(aValue.getPolo());
		
		fireTableCellUpdated(rowIndex, 0);
		fireTableCellUpdated(rowIndex, 1);
		fireTableCellUpdated(rowIndex, 2);
		fireTableCellUpdated(rowIndex, 3);

	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Representante representante = list.get(rowIndex);
		System.out.println("setValueAt02");
		switch (columnIndex) {
		case 0:
			representante.setCpf( aValue.toString() );
		case 1:
			representante.setNome(aValue.toString());
		case 2:
			representante.setTipo( aValue.toString() );
		case 3:
			representante.setPolo( aValue.toString() );
			
		default:
			System.err.println("indice da coluna invalido");
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		System.out.println("getValueAt");
		Representante representanteSelecionado = list.get(rowIndex);
		String valueObject = null;
		switch (columnIndex) {
		case 0:
			valueObject = representanteSelecionado.getCpf();
			break;
		case 1:
			valueObject = representanteSelecionado.getNome();
			break;
		case 2:
			valueObject = representanteSelecionado.getTipo();
			break;
		case 3:
			valueObject = representanteSelecionado.getPolo();
			break;
		default:
			System.err.println("indice invalido para propriedade do bean Representante.class");
		}
		return valueObject;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public Representante get(int indiceLinha) {
		return list.get(indiceLinha);
	}

	public void add(Representante representante) {
		list.add(representante);
		int ultimoIndice = getRowCount() - 1;
		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

	public void remove(int indiceLinha) {
		list.remove(indiceLinha);
		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}

	public void addList(List<Representante> novosRepresentantes) {
		int tamanhoAntigo = getRowCount();
		list.addAll(novosRepresentantes);
		fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
	}

	public void limpar() {
		list.clear();
		fireTableDataChanged();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}	
	
}
