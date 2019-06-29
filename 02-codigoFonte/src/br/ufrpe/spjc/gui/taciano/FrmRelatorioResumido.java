package br.ufrpe.spjc.gui.taciano;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.ufrpe.spjc.util.JasperReportUtil;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import java.awt.event.ActionListener;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
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
				JButton okButton = new JButton("Visualizar relatório");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							Map<String, Object> parametros = new HashMap<String, Object>();
							parametros.put("ano", 1);
							parametros.put("semestre", 2);
							
							URL url= getClass().getResource("RelatorioResumidoPautaAudiencia.jrxml");
							
							StringBuilder query= new StringBuilder();
							query.append("SELECT DISTINCT jpa.nome, ")
							.append(" jpa.dataAgendamento, ")
							.append(" jpa.hora, ")
							.append(" jpa.processo, ")
							.append(" prp.tipo,  ")
							.append(" prp.polo, ")
							.append(" prp.nomePTR ")
							.append(" FROM DBSPJC.juizadoPautaAudiencia jpa ")
							.append("   INNER JOIN DBSPJC.parteRepresentanteProcesso prp ON prp.processo= jpa.processo ")
							.append(" ORDER BY 1, 2, 3, 4, 5 ");
							
							//gerando o jasper design
							JasperDesign desenho= JRXmlLoader.load( url.getFile());
							
							JasperReportUtil.gerar(desenho, query.toString(), parametros);
							setVisible(false);
							
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
}
