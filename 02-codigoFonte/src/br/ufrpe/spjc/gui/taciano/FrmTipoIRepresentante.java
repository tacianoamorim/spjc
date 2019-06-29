package br.ufrpe.spjc.gui.taciano;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class FrmTipoIRepresentante extends JDialog {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5287862733905941387L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNome;
	private JTextField txtOAB;
	private JTextField txtEmail;
	private JTextField txtTelefone;
	private JTextField txtNumero;
	private JTextField txtCPF;
	private JTextField txtCEP;
	private JTextField txtLogradouro;
	private JTextField textField;
	private JTextField txtCidade;
	private JTextField txtUF;
	private JTable tbLista;
	private FrmRelatorioResumidoTableModel resumidoTableModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FrmTipoIRepresentante dialog = new FrmTipoIRepresentante();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FrmTipoIRepresentante() {
		setResizable(false);
		setAutoRequestFocus(false);
		setTitle("Formulário Tipo I - Gerenciamento de REPRESENTANTE");
		setBounds(100, 100, 778, 482);
		setModal(true);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cadastro de Representante (Tipo I)");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel.setBounds(12, 8, 746, 23);
		contentPanel.add(lblNewLabel);
		
		JPanel panelList = new JPanel();
		panelList.setBounds(12, 35, 746, 155);
		contentPanel.add(panelList);
		panelList.setLayout(new BoxLayout(panelList, BoxLayout.X_AXIS));		
		
		resumidoTableModel = new FrmRelatorioResumidoTableModel();
		
		tbLista = new JTable(resumidoTableModel);
		formatarTabela(tbLista);	
		
		JScrollPane scpLista = new JScrollPane(tbLista);
		tbLista.setFillsViewportHeight(true);
		panelList.add(scpLista);
		
		JPanel panelForm = new JPanel();
		panelForm.setBounds(12, 202, 746, 205);
		contentPanel.add(panelForm);
		panelForm.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(149, 12, 66, 15);
		panelForm.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(149, 28, 298, 24);
		panelForm.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblOab = new JLabel("OAB:");
		lblOab.setBounds(465, 12, 66, 15);
		panelForm.add(lblOab);
		
		txtOAB = new JTextField();
		txtOAB.setBounds(465, 28, 109, 24);
		panelForm.add(txtOAB);
		txtOAB.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(430, 58, 66, 15);
		panelForm.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(430, 75, 292, 24);
		panelForm.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(12, 58, 66, 15);
		panelForm.add(lblTelefone);
		
		txtTelefone = new JTextField();
		txtTelefone.setBounds(12, 75, 243, 24);
		panelForm.add(txtTelefone);
		txtTelefone.setColumns(10);
		
		JLabel lblPolo = new JLabel("Polo:");
		lblPolo.setBounds(267, 58, 145, 15);
		panelForm.add(lblPolo);
		
		JComboBox cbxPolo = new JComboBox();
		cbxPolo.setBounds(267, 75, 145, 24);
		panelForm.add(cbxPolo);
		
		JLabel lblNEndereo = new JLabel("N. endereço:");
		lblNEndereo.setBounds(564, 104, 99, 15);
		panelForm.add(lblNEndereo);
		
		txtNumero = new JTextField();
		txtNumero.setBounds(564, 123, 158, 25);
		panelForm.add(txtNumero);
		txtNumero.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(586, 12, 66, 15);
		panelForm.add(lblCpf);
		
		txtCPF = new JTextField();
		txtCPF.setBounds(586, 28, 136, 24);
		panelForm.add(txtCPF);
		txtCPF.setColumns(10);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(12, 12, 66, 15);
		panelForm.add(lblTipo);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(12, 28, 119, 24);
		panelForm.add(comboBox);
		
		JLabel lblCep = new JLabel("CEP:");
		lblCep.setBounds(12, 104, 66, 15);
		panelForm.add(lblCep);
		
		txtCEP = new JTextField();
		txtCEP.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				// https://viacep.com.br/ws/50920135/json/
				JOptionPane.showMessageDialog(null, "Fr");
				
			}
		});
		txtCEP.setBounds(12, 123, 109, 25);
		panelForm.add(txtCEP);
		txtCEP.setColumns(10);
		
		JLabel lblLogradouro = new JLabel("Logradouro:");
		lblLogradouro.setBounds(149, 104, 93, 15);
		panelForm.add(lblLogradouro);
		
		txtLogradouro = new JTextField();
		txtLogradouro.setBounds(149, 123, 393, 25);
		panelForm.add(txtLogradouro);
		txtLogradouro.setColumns(10);
		
		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(12, 155, 66, 15);
		panelForm.add(lblBairro);
		
		textField = new JTextField();
		textField.setBounds(12, 171, 253, 25);
		panelForm.add(textField);
		textField.setColumns(10);
		
		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(283, 155, 66, 15);
		panelForm.add(lblCidade);
		
		txtCidade = new JTextField();
		txtCidade.setBounds(283, 171, 259, 25);
		panelForm.add(txtCidade);
		txtCidade.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("UF:");
		lblNewLabel_1.setBounds(564, 155, 99, 15);
		panelForm.add(lblNewLabel_1);
		
		txtUF = new JTextField();
		txtUF.setBounds(564, 171, 158, 25);
		panelForm.add(txtUF);
		txtUF.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Salvar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
					}
				});
				
				JButton btnNovo = new JButton("Novo");
				btnNovo.setActionCommand("OK");
				buttonPane.add(btnNovo);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
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
	
	private void formatarTabela(JTable jTable) {
		jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jTable.getColumnModel().getColumn(0).setPreferredWidth(170);
		jTable.getColumnModel().getColumn(1).setPreferredWidth(370);
		jTable.getColumnModel().getColumn(2).setPreferredWidth(100);
		jTable.getColumnModel().getColumn(3).setPreferredWidth(100);
	}	
}
