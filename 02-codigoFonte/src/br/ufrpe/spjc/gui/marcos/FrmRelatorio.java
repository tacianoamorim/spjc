package br.ufrpe.spjc.gui.marcos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.ufrpe.spjc.negocio.controlador.ServidorControl;
import br.ufrpe.spjc.negocio.vo.RelatorioServidorVO;
import br.ufrpe.spjc.util.JasperReportUtil;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class FrmRelatorio extends JDialog {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5287862733905941387L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtDataInicio;
	private JTextField txtDataFim;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FrmRelatorio dialog = new FrmRelatorio();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FrmRelatorio() {
		setModal(true);
		setTitle("Formulário Tipo II - Gerenciamento de DOCUMENTO");
		setBounds(100, 100, 477, 248);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblRelatrioResumidoDe = new JLabel("Relatório de servidores");
			lblRelatrioResumidoDe.setHorizontalAlignment(SwingConstants.CENTER);
			lblRelatrioResumidoDe.setFont(new Font("Dialog", Font.BOLD, 18));
			lblRelatrioResumidoDe.setBounds(12, 12, 445, 23);
			contentPanel.add(lblRelatrioResumidoDe);
		}
		{
			JLabel lblAno = new JLabel("Data inicio: ");
			lblAno.setHorizontalAlignment(SwingConstants.RIGHT);
			lblAno.setBounds(46, 66, 85, 15);
			contentPanel.add(lblAno);
		}
		{
			JLabel lblSemestre = new JLabel("Data fim:");
			lblSemestre.setHorizontalAlignment(SwingConstants.RIGHT);
			lblSemestre.setBounds(46, 113, 85, 15);
			contentPanel.add(lblSemestre);
		}
		
		txtDataInicio = new JTextField();
		txtDataInicio.setBounds(149, 64, 124, 19);
		contentPanel.add(txtDataInicio);
		txtDataInicio.setColumns(10);
		
		txtDataFim = new JTextField();
		txtDataFim.setBounds(149, 111, 124, 19);
		contentPanel.add(txtDataFim);
		txtDataFim.setColumns(10);
	
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Visualizar relatório");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							URL url= getClass().getResource("listaServidores.jrxml");
							
							Map<String, Object> parametros = new HashMap<String, Object>();
							
							Calendar dataInicio= new GregorianCalendar();
							DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
							Date dti= (java.util.Date)formatter.parse(txtDataInicio.getText());
							dataInicio.setTimeInMillis(dti.getTime());
							
							Calendar dataFinal= new GregorianCalendar();
							Date dtf= (java.util.Date)formatter.parse(txtDataFim.getText());
							dataFinal.setTimeInMillis(dtf.getTime());
							
							List<RelatorioServidorVO> lista= ServidorControl.getInstance().getRelatorioServidor(dataInicio, dataFinal);
							
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
