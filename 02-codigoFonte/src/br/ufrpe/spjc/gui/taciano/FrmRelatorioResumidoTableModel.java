package br.ufrpe.spjc.gui.taciano;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.ufrpe.spjc.negocio.entidade.Representante;

public class FrmRelatorioResumidoTableModel extends AbstractTableModel {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7419763598897891865L;
	
	private List<Representante> representantes;
	private String[] colunas = new String[] { 
		"CPF", "Nome", "Tipo", "Polo" 
	};

	/** Creates a new instance of TableModel */
	public FrmRelatorioResumidoTableModel(List<Representante> representantes) {
		this.representantes = representantes;
	}

	public FrmRelatorioResumidoTableModel() {
		this.representantes = new ArrayList<Representante>();
	}

	public int getRowCount() {
		return representantes.size();
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
		Representante representante = representantes.get(rowIndex);

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
		Representante representante = representantes.get(rowIndex);

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
		Representante representanteSelecionado = representantes.get(rowIndex);
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

	public Representante getRepresentante(int indiceLinha) {
		return representantes.get(indiceLinha);
	}

	public void addRepresentante(Representante representante) {
		representantes.add(representante);
		int ultimoIndice = getRowCount() - 1;
		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

	public void removeRepresentante(int indiceLinha) {
		representantes.remove(indiceLinha);
		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}

	public void addListaDeRepresentantes(List<Representante> novosRepresentantes) {
		int tamanhoAntigo = getRowCount();
		representantes.addAll(novosRepresentantes);
		fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
	}

	public void limpar() {
		representantes.clear();
		fireTableDataChanged();
	}

	public boolean isEmpty() {
		return representantes.isEmpty();
	}	
	
}
