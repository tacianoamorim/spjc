package br.ufrpe.spjc.gui.taciano;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

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

import br.ufrpe.spjc.negocio.controlador.RepresentanteControl;
import br.ufrpe.spjc.negocio.entidade.Endereco;
import br.ufrpe.spjc.negocio.entidade.Representante;
import br.ufrpe.spjc.util.Utils;

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
	private JTextField txtMatricula;
	private JTextField txtLogradouro;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtUF;
	private JTable tbLista;
	private FrmTipoITableModel tableModel;
	private JComboBox<String> cbxTipo, cbxPolo;
	private JTextField txtCEP;
	private JTextField txtSenha;

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
		
		tableModel = new FrmTipoITableModel();
		tbLista = new JTable(tableModel);
		formatarTabela(tbLista);	
		tbLista.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                Representante representante= tableModel.get(tbLista.getSelectedRow());
                limpar();
	            carregarDados(representante);
            }
            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        });
		
		
		
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
		
		cbxPolo = new JComboBox<String>();
		cbxPolo.setBounds(267, 75, 145, 24);
		panelForm.add(cbxPolo);
		
		JLabel lblNEndereo = new JLabel("N. endereço:");
		lblNEndereo.setBounds(615, 104, 107, 15);
		panelForm.add(lblNEndereo);
		
		txtNumero = new JTextField();
		txtNumero.setBounds(615, 123, 107, 25);
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
		
		cbxTipo = new JComboBox<String>();
		cbxTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if ( "A".equals(Utils.getId(cbxTipo.getSelectedItem().toString(), "-"))) {
					txtMatricula.setText("");
					txtMatricula.setEnabled(false);
					txtMatricula.setBackground(Color.GRAY);
				} else {
					txtMatricula.setEnabled(true);
					txtMatricula.setBackground(Color.WHITE);
				}	
			}
		});
		cbxTipo.setBounds(12, 28, 119, 24);
		panelForm.add(cbxTipo);
		
		JLabel lblMatricula = new JLabel("Matrícula");
		lblMatricula.setBounds(12, 104, 66, 15);
		panelForm.add(lblMatricula);
		
		txtMatricula = new JTextField();
		txtMatricula.setEnabled(false);
		txtMatricula.setBounds(12, 123, 89, 25);
		txtMatricula.setColumns(10);
		panelForm.add(txtMatricula);
		
		JLabel lblLogradouro = new JLabel("Logradouro:");
		lblLogradouro.setBounds(277, 104, 326, 15);
		panelForm.add(lblLogradouro);
		
		txtLogradouro = new JTextField();
		txtLogradouro.setBounds(277, 123, 326, 25);
		panelForm.add(txtLogradouro);
		txtLogradouro.setColumns(10);
		
		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(12, 155, 66, 15);
		panelForm.add(lblBairro);
		
		txtBairro = new JTextField();
		txtBairro.setBounds(12, 171, 158, 25);
		panelForm.add(txtBairro);
		txtBairro.setColumns(10);
		
		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(188, 155, 66, 15);
		panelForm.add(lblCidade);
		
		txtCidade = new JTextField();
		txtCidade.setBounds(188, 171, 223, 25);
		panelForm.add(txtCidade);
		txtCidade.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("UF:");
		lblNewLabel_1.setBounds(430, 155, 99, 15);
		panelForm.add(lblNewLabel_1);
		
		txtUF = new JTextField();
		txtUF.setBounds(430, 171, 158, 25);
		panelForm.add(txtUF);
		txtUF.setColumns(10);
		
		JLabel label = new JLabel("CEP:");
		label.setBounds(600, 149, 66, 15);
		panelForm.add(label);
		
		txtCEP = new JTextField();
		txtCEP.setColumns(10);
		txtCEP.setBounds(600, 168, 122, 25);
		panelForm.add(txtCEP);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(114, 104, 66, 15);
		panelForm.add(lblSenha);
		
		txtSenha = new JTextField();
		txtSenha.setColumns(10);
		txtSenha.setBounds(114, 123, 140, 25);
		panelForm.add(txtSenha);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Salvar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						try {
							/*
							 * CARREGAR DADOS
							 */
							Representante representante= new Representante();
							representante.setNome(txtNome.getText());
							representante.setEmail(txtEmail.getText());
							representante.setNumero(txtNumero.getText());
							representante.setOab(txtOAB.getText());
							representante.setTelefone(txtTelefone.getText());
							representante.setCpf(txtCPF.getText());
							representante.setSenha(txtSenha.getText());
	
							String idPolo= Utils.getId(cbxPolo.getSelectedItem().toString(), "-");
							String idTipo= Utils.getId(cbxTipo.getSelectedItem().toString(), "-");
							if ( "D".equals(idTipo) && txtMatricula.getText().length() >0 ) {
								representante.setMatricula( Integer.parseInt( txtMatricula.getText() ) );
							}
							representante.setPolo(idPolo);
							representante.setTipo(idTipo);
							
							Endereco endereco= new Endereco();
							int cep= Integer.parseInt(txtCEP.getText());
							endereco.setCep(cep);
							endereco.setRua(txtLogradouro.getText());
							endereco.setBairro(txtBairro.getText());
							endereco.setCidade(txtCidade.getText());
							endereco.setEstado(txtUF.getText());
							representante.setEndereco(endereco);
							
							if (!validaCampos()) {
								JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Validação de campos", 
										JOptionPane.ERROR_MESSAGE);
								return;
							} else {
								
								RepresentanteControl.getInstance().salvar(representante);
								limpar();
								JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!", "Confirmação de cadastro/atualização", 
										JOptionPane.INFORMATION_MESSAGE);
								
								carregarTable();

							}
						} catch (Throwable e) {
							JOptionPane.showMessageDialog(null, "Ocorreu o seuing erro ao gravar os dados: "
									+ e.getMessage(), "ERROR", 
									JOptionPane.ERROR_MESSAGE);
						}
						
					}
				});
				
				JButton btnNovo = new JButton("Novo");
				btnNovo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						limpar();
					}
				});
				btnNovo.setActionCommand("OK");
				buttonPane.add(btnNovo);
				
				JButton btnExcluir = new JButton("Apagar");
				btnExcluir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
			                Representante representante= tableModel.get(tbLista.getSelectedRow());
			               
			                int selectedOption = JOptionPane.showConfirmDialog(null,"Confirma a exclusão do representante "+
			                representante.getNome() +"?", "Confirmar exclusão", JOptionPane.YES_NO_OPTION);
			        		if(selectedOption == JOptionPane.YES_OPTION){
			        			RepresentanteControl.getInstance().apagar(representante);     
			        			carregarTable();
			        		}	
						} catch (Throwable e) {
							
							if ( e.getMessage().contains("foreign key")) {
								JOptionPane.showMessageDialog(null, "O representante não pode ser excuido pq existem processos vinculados. "
										+ e.getMessage(), "ERROR", 
										JOptionPane.ERROR_MESSAGE);
							} else {							
								JOptionPane.showMessageDialog(null, "Ocorreu o seuing erro ao gravar os dados: "
										+ e.getMessage(), "ERROR", 
										JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				});
				btnExcluir.setForeground(Color.RED);
				btnExcluir.setActionCommand("OK");
				buttonPane.add(btnExcluir);
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
		
		/**
		 * CARREGAR COMBO
		 */
		cbxTipo.addItem("A-Advogado");
		cbxTipo.addItem("D-Defensor Público");
		
		cbxPolo.addItem("A-Ativo");
		cbxPolo.addItem("P-Passivo");
		
		carregarTable();
		
	}

	private void carregarTable() {
		// Carregar lista
		tableModel.limpar();
		List<Representante> lista= RepresentanteControl.getInstance().findByFilter(new Representante());
		tableModel.addList(lista);
	}
	
	private void formatarTabela(JTable jTable) {
		jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jTable.getColumnModel().getColumn(0).setPreferredWidth(170);
		jTable.getColumnModel().getColumn(1).setPreferredWidth(370);
		jTable.getColumnModel().getColumn(2).setPreferredWidth(80);
		jTable.getColumnModel().getColumn(3).setPreferredWidth(80);
	}	
	
	private void limpar() {
		txtNome.setText("");
		txtEmail.setText("");
		txtCidade.setText("");
		txtUF.setText("");
		txtLogradouro.setText("");
		txtMatricula.setText("");
		txtNumero.setText("");
		txtOAB.setText("");
		txtTelefone.setText("");
		txtCPF.setText("");
		txtBairro.setText("");
		cbxPolo.setSelectedIndex(0);
		cbxTipo.setSelectedIndex(0);
		txtCEP.setText("");
		txtSenha.setText("");
	}
	
	private void carregarDados(Representante entity) {
		txtNome.setText(entity.getNome());
		txtEmail.setText(entity.getEmail());
		txtMatricula.setText(entity.getMatricula()+"");
		txtNumero.setText(entity.getNumero());
		txtOAB.setText(entity.getOab());
		txtTelefone.setText(entity.getTelefone());
		txtCPF.setText(entity.getCpf());
		txtSenha.setText(entity.getSenha());
		if ( entity.getEndereco() != null) {
			txtLogradouro.setText(entity.getEndereco().getRua());
			txtBairro.setText(entity.getEndereco().getBairro());
			txtCidade.setText(entity.getEndereco().getCidade());
			txtUF.setText(entity.getEndereco().getEstado());
			txtCEP.setText(entity.getEndereco().getCep()+"");
		}
		
		if ( "A".equals(entity.getPolo())) {
			cbxPolo.setSelectedIndex(0);
		} else {
			cbxPolo.setSelectedIndex(1);
		}
		
		if ( "A".equals(entity.getTipo())) {
			cbxTipo.setSelectedIndex(0);
		} else {
			cbxTipo.setSelectedIndex(1);
		}		
	}
	
	private boolean validaCampos(){
		boolean isValido= true;
		
		String idTipo= Utils.getId(cbxTipo.getSelectedItem().toString(), "-");
		if ( "D".equals(idTipo) && txtMatricula.getText().length() == 0) {
			isValido= false;
		}	
		
		if ( txtNome.getText().length() == 0 || 
			txtEmail.getText().length() == 0 || 
			txtOAB.getText().length() == 0 || 
			txtCPF.getText().length() == 0 ||  
			txtTelefone.getText().length() == 0 || 
			txtLogradouro.getText().length() == 0 || 
			txtNumero.getText().length() == 0 || 
			txtBairro.getText().length() == 0 || 
			txtCidade.getText().length() == 0 || 
			txtCEP.getText().length() == 0 ||
			txtSenha.getText().length() == 0 || 
			txtUF.getText().length() == 0
			) {
			isValido= false;
		}
		
		return isValido;
	}
}
