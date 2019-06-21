package br.ufrpe.spjc.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class FrmLogin extends JDialog {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1864210157235754121L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtSenha;
	public static boolean usuarioLogado;
	
	private JButton btnCadastro, tbnLogar;
	

	/**
	 * Create the dialog.
	 */
	public FrmLogin() {
		setTitle("Login");
		setType(Type.POPUP);
		setBounds(100, 100, 398, 194);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		{
			JLabel lblCPF = new JLabel("CPF:");
			lblCPF.setBounds(10, 19, 102, 14);
			contentPanel.add(lblCPF);
		}
		{
			txtCodigo = new JTextField();
			txtCodigo.setText("paulo");
			txtCodigo.setBounds(132, 12, 188, 30);
			txtCodigo.setColumns(10);
			contentPanel.add(txtCodigo);
		}
		{
			txtSenha = new JTextField();
			txtSenha.setText("123");
			txtSenha.setBounds(132, 51, 188, 30);
			txtSenha.setColumns(10);
			contentPanel.add(txtSenha);
		}
		{
			JLabel label = new JLabel("Senha:");
			label.setBounds(10, 58, 88, 14);
			contentPanel.add(label);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnCadastro = new JButton("");
				btnCadastro.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						FrmPrincipal.perfilLogado= null;
					}
				});
				btnCadastro.setIcon(new ImageIcon(FrmLogin.class.getResource("/image/novo_32.png")));
				buttonPane.add(btnCadastro);
				getRootPane().setDefaultButton(btnCadastro);
			}
			{
				tbnLogar = new JButton("");
				tbnLogar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							// Limpa a variavel
							
							if ( txtCodigo.getText() != null && 
									txtCodigo.getText().trim().length() == 0) {
								JOptionPane.showMessageDialog(null,"Informe o usuario de login");
								
							} else if ( txtSenha.getText() != null && 
									txtSenha.getText().trim().length() == 0 ) {
								JOptionPane.showMessageDialog(null,"Informe a senha");
								
							} else {
								
								String tipo= "";
								boolean loginLiberado= 	Fachada.getInstance().login( txtCodigo.getText(), 
										txtSenha.getText(), tipo );
								
								if ( loginLiberado ) {
									setVisible(false);
									FrmPrincipal.perfilLogado= tipo;
									FrmPrincipal window = new FrmPrincipal();
									window.frame.setVisible(true);
								};
							}
							
						} catch (Exception ex) {
							ex.printStackTrace();
							JOptionPane.showMessageDialog(null,"Verifique o usuario e a senha informada.");
						}
						
						
					}
				});
				tbnLogar.setIcon(new ImageIcon(FrmLogin.class.getResource("/image/login_32.png")));
				buttonPane.add(tbnLogar);
			}
		}
		
		grupo = new ButtonGroup();
	}
	


}
