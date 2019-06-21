package br.ufrpe.spjc.gui.taciano;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class FrmRelatorioResumido extends JDialog {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5287862733905941387L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtAno;
	private JTextField txtsemestre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FrmRelatorioResumido dialog = new FrmRelatorioResumido();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FrmRelatorioResumido() {
		setModal(true);
		setTitle("Formulário Tipo II - Gerenciamento de DOCUMENTO");
		setBounds(100, 100, 477, 248);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblRelatrioResumidoDe = new JLabel("Relatório resumido de Pautas");
			lblRelatrioResumidoDe.setHorizontalAlignment(SwingConstants.CENTER);
			lblRelatrioResumidoDe.setFont(new Font("Dialog", Font.BOLD, 18));
			lblRelatrioResumidoDe.setBounds(12, 12, 445, 23);
			contentPanel.add(lblRelatrioResumidoDe);
		}
		{
			JLabel lblAno = new JLabel("Ano:");
			lblAno.setHorizontalAlignment(SwingConstants.RIGHT);
			lblAno.setBounds(46, 66, 85, 15);
			contentPanel.add(lblAno);
		}
		{
			JLabel lblSemestre = new JLabel("Semestre:");
			lblSemestre.setHorizontalAlignment(SwingConstants.RIGHT);
			lblSemestre.setBounds(46, 113, 85, 15);
			contentPanel.add(lblSemestre);
		}
		
		txtAno = new JTextField();
		txtAno.setBounds(149, 64, 124, 19);
		contentPanel.add(txtAno);
		txtAno.setColumns(10);
		
		txtsemestre = new JTextField();
		txtsemestre.setBounds(149, 111, 124, 19);
		contentPanel.add(txtsemestre);
		txtsemestre.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, e.getMessage());
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
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
}
