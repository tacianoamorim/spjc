package br.ufrpe.spjc.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.ufrpe.spjc.negocio.controlador.MagistradoControl;
import br.ufrpe.spjc.negocio.controlador.RepresentanteControl;
import br.ufrpe.spjc.negocio.controlador.ServidorControl;
import br.ufrpe.spjc.negocio.entidade.Magistrado;
import br.ufrpe.spjc.negocio.entidade.Representante;
import br.ufrpe.spjc.negocio.entidade.Servidor;

public class FrmLogin extends JDialog {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6234032704127284755L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCpf;
	private JTextField txtSenha;
	private JComboBox<String> cbxTipo;

	public static String TIPO_USUARIO;
	public static Object USUARIO_LOGADO;
	public static String SOU_MAGISTRADO= "Sou magistrado";
	public static String SOU_SERVIDOR= "Sou servidor";
	public static String SOU_REPRESENTANTE= "Sou representante";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FrmLogin dialog = new FrmLogin();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FrmLogin() {
		setTitle("Sistema Processual de Juizados Cíveis");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Login");
			lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(12, 10, 418, 24);
			contentPanel.add(lblNewLabel);
		}
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(58, 84, 66, 15);
		contentPanel.add(lblCpf);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(40, 126, 66, 15);
		contentPanel.add(lblSenha);
		
		txtCpf = new JTextField();
		txtCpf.setText("012");
		txtCpf.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtCpf.setBounds(122, 77, 222, 24);
		contentPanel.add(txtCpf);
		txtCpf.setColumns(10);
		
		txtSenha = new JTextField();
		txtSenha.setText("012");
		txtSenha.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtSenha.setBounds(120, 119, 224, 24);
		contentPanel.add(txtSenha);
		txtSenha.setColumns(10);
		
		cbxTipo = new JComboBox<String>();
		cbxTipo.setBounds(122, 161, 222, 25);
		cbxTipo.addItem(SOU_MAGISTRADO);
		cbxTipo.addItem(SOU_REPRESENTANTE);
		cbxTipo.addItem(SOU_SERVIDOR);
		contentPanel.add(cbxTipo);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						try {
							
							String senha= "";
							
							if ( SOU_MAGISTRADO.equals(cbxTipo.getSelectedItem()) ) {
								Magistrado entity= MagistradoControl.getInstance().findById(txtCpf.getText());
								senha= entity.getSenha();
								TIPO_USUARIO= SOU_MAGISTRADO;
								USUARIO_LOGADO= entity;
								
							} else if ( SOU_REPRESENTANTE.equals(cbxTipo.getSelectedItem()) ) {
								Representante entity= RepresentanteControl.getInstance().findById(txtCpf.getText());
								senha= entity.getSenha();
								TIPO_USUARIO= SOU_REPRESENTANTE;
								USUARIO_LOGADO= entity;
								
							} else {
								Servidor entity= ServidorControl.getInstance().findById(txtCpf.getText());
								senha= entity.getSenha();		
								TIPO_USUARIO= SOU_SERVIDOR;
								USUARIO_LOGADO= entity;
							}
							
							if ( senha.equals(txtSenha.getText()) ) {
								setVisible(false);
								FrmPrincipal window= new FrmPrincipal();
								window.frmSpjcSistema.setVisible(true);
								
							} else {
								JOptionPane.showMessageDialog(null, "Usuário ou senha inválido.");
							}
							
						} catch (Throwable e) {
							JOptionPane.showMessageDialog(null, e.getMessage(), "Mensagem", JOptionPane.ERROR_MESSAGE);
							System.out.println(e);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
