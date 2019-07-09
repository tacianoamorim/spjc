package br.ufrpe.spjc.gui.taciano;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.ufrpe.spjc.negocio.controlador.RelatorioControl;
import br.ufrpe.spjc.negocio.vo.RelatorioVO;
import br.ufrpe.spjc.util.JasperReportUtil;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class FrmRelatorioResumido extends JDialog {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5287862733905941387L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtAno;
	private JComboBox<Integer> cbxSemestre;

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
		
		cbxSemestre = new JComboBox<Integer>();
		cbxSemestre.setBounds(149, 108, 124, 24);
		cbxSemestre.addItem(1);
		cbxSemestre.addItem(2);
		contentPanel.add(cbxSemestre);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Visualizar relatório");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							URL url= getClass().getResource("RelatorioResumidoPautaAudiencia.jrxml");
							String semestre;
							int ano, mesInicio, mesFim;
							
							Map<String, Object> parametros = new HashMap<String, Object>();
							ano= Integer.parseInt(txtAno.getText());
							parametros.put("ANO", txtAno.getText());
							
							if (cbxSemestre.getSelectedIndex() == 0) {
								semestre= "1";
								mesInicio= 1;
								mesFim= 6;
							} else {
								semestre= "2";
								mesInicio= 7;
								mesFim= 12;							
							}
							parametros.put("SEMESTRE", semestre);
							
							List<RelatorioVO> lista= RelatorioControl.getInstance().list(mesInicio, mesFim, ano);
							
							JRBeanCollectionDataSource dataSource = 
									new JRBeanCollectionDataSource(lista);

							//gerando o jasper design
							JasperDesign desenho= JRXmlLoader.load( url.getFile());
							
							JasperReportUtil.gerar(desenho, dataSource, parametros);
							
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
