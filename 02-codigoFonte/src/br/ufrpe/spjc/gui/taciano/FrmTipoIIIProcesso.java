package br.ufrpe.spjc.gui.taciano;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.ufrpe.spjc.negocio.controlador.ProcessoControl;
import br.ufrpe.spjc.negocio.entidade.Audiencia;
import br.ufrpe.spjc.negocio.entidade.Pauta;
import br.ufrpe.spjc.negocio.entidade.Processo;
import br.ufrpe.spjc.negocio.entidade.Representante;
import br.ufrpe.spjc.util.Utils;
import javax.swing.JTextArea;

public class FrmTipoIIIProcesso extends JDialog {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5287862733905941387L;
	
	private final JPanel contentPanel = new JPanel();
//	private FrmTipoIIITableModel tableModel;
	private JTextField txtNPU;
	private JTextField txtSala;
	private JComboBox<Audiencia> cbxTipo;
	private JComboBox<Pauta> cbxPauta;
	private JComboBox<Processo> cbxHorario;
	private JTextArea txtProcesso;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FrmTipoIIIProcesso dialog = new FrmTipoIIIProcesso();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FrmTipoIIIProcesso() {
		setTitle("Formulário Tipo II - PROCESSO (Tabela Pai) e AUDIENCIA (Tabela Filha)");
		setBounds(100, 100, 680, 397);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblPautasDisponveis = new JLabel("Pautas disponíveis:");
		lblPautasDisponveis.setBounds(12, 12, 152, 15);
		contentPanel.add(lblPautasDisponveis);
		
		cbxPauta = new JComboBox<Pauta>();
		cbxPauta.setBounds(12, 29, 641, 30);
		contentPanel.add(cbxPauta);
		
		cbxHorario = new JComboBox<Processo>();
		cbxHorario.setBounds(12, 87, 641, 30);
		contentPanel.add(cbxHorario);
		
		JLabel lblHorrios = new JLabel("Horários:");
		lblHorrios.setBounds(12, 71, 152, 15);
		contentPanel.add(lblHorrios);
		
		JLabel lblNmeroDoProcesso = new JLabel("Número do processo:");
		lblNmeroDoProcesso.setBounds(12, 136, 189, 15);
		contentPanel.add(lblNmeroDoProcesso);
		
		txtNPU = new JTextField();
		txtNPU.setBounds(12, 154, 189, 25);
		contentPanel.add(txtNPU);
		txtNPU.setColumns(10);
		
		JButton btnBuscarProcesso = new JButton("Buscar => ");
		btnBuscarProcesso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Processo processo= ProcessoControl.getInstance().findById(txtNPU.getText());
					if (processo == null) {
						JOptionPane.showMessageDialog(null, "Processo não localizado", "Atenção", 
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					limpar();
					txtProcesso.setText(processo.toString());
					
				} catch (Exception e) {
					e.printStackTrace();
					Utils.msgExcption(e.getMessage());				
				}
			}
		});
		btnBuscarProcesso.setBounds(64, 186, 136, 25);
		contentPanel.add(btnBuscarProcesso);
		
		JLabel lblProcesso_1 = new JLabel("Informaçõesdo processo:");
		lblProcesso_1.setBounds(227, 136, 344, 15);
		contentPanel.add(lblProcesso_1);
		
		JLabel lblSala = new JLabel("Sala:");
		lblSala.setBounds(517, 272, 136, 15);
		contentPanel.add(lblSala);
		
		txtSala = new JTextField();
		txtSala.setEditable(false);
		txtSala.setColumns(10);
		txtSala.setBounds(517, 290, 136, 25);
		contentPanel.add(txtSala);
		
		cbxTipo = new JComboBox<Audiencia>();
		cbxTipo.setBounds(12, 287, 469, 30);
		contentPanel.add(cbxTipo);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(12, 272, 187, 15);
		contentPanel.add(lblTipo);
		
		txtProcesso = new JTextArea();
		txtProcesso.setEditable(false);
		txtProcesso.setBounds(227, 154, 421, 104);
		contentPanel.add(txtProcesso);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnApagar = new JButton("Apagar");
				btnApagar.setForeground(Color.RED);
				btnApagar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
					}
				});
				
				JButton btnNova = new JButton("Nova audiência");
				btnNova.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						limpar();
					}
				});
				btnNova.setActionCommand("OK");
				buttonPane.add(btnNova);
				
				JButton btnSalvar = new JButton("Salvar");
				btnSalvar.setForeground(new Color(32, 178, 170));
				btnSalvar.setActionCommand("OK");
				buttonPane.add(btnSalvar);
				btnApagar.setActionCommand("OK");
				buttonPane.add(btnApagar);
				getRootPane().setDefaultButton(btnApagar);
			}
			{
				JButton cancelButton = new JButton("Fechar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
	}
	
	private void limpar() {
		txtNPU.setText("");
		txtProcesso.setText("");
		txtSala.setText("");
		cbxPauta.setSelectedIndex(0);
		cbxHorario.setSelectedIndex(0);
		cbxTipo.setSelectedIndex(0);
	}
	
	private void carregarDados(Representante entity) {
//		txtNome.setText(entity.getNome());
//		txtEmail.setText(entity.getEmail());
//		txtMatricula.setText(entity.getMatricula()+"");
//		txtNumero.setText(entity.getNumero());
//		txtOAB.setText(entity.getOab());
//		txtTelefone.setText(entity.getTelefone());
//		txtCPF.setText(entity.getCpf());
//		txtSenha.setText(entity.getSenha());
//		if ( entity.getEndereco() != null) {
//			txtLogradouro.setText(entity.getEndereco().getRua());
//			txtBairro.setText(entity.getEndereco().getBairro());
//			txtCidade.setText(entity.getEndereco().getCidade());
//			txtUF.setText(entity.getEndereco().getEstado());
//			txtCEP.setText(entity.getEndereco().getCep()+"");
//		}
//		
//		if ( "A".equals(entity.getPolo())) {
//			cbxPolo.setSelectedIndex(0);
//		} else {
//			cbxPolo.setSelectedIndex(1);
//		}
//		
//		if ( "A".equals(entity.getTipo())) {
//			cbxTipo.setSelectedIndex(0);
//		} else {
//			cbxTipo.setSelectedIndex(1);
//		}		
	}
}
