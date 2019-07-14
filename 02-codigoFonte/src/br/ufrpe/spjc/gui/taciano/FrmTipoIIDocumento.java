package br.ufrpe.spjc.gui.taciano;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.ufrpe.spjc.negocio.controlador.DocumentoControl;
import br.ufrpe.spjc.negocio.controlador.MagistradoControl;
import br.ufrpe.spjc.negocio.controlador.ProcessoControl;
import br.ufrpe.spjc.negocio.controlador.ServidorControl;
import br.ufrpe.spjc.negocio.controlador.TipoDocumentoControl;
import br.ufrpe.spjc.negocio.entidade.Documento;
import br.ufrpe.spjc.negocio.entidade.Entity;
import br.ufrpe.spjc.negocio.entidade.Processo;
import br.ufrpe.spjc.negocio.entidade.Servidor;
import br.ufrpe.spjc.negocio.entidade.TipoDocumento;
import br.ufrpe.spjc.util.Utils;

public class FrmTipoIIDocumento extends JDialog {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5287862733905941387L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNpu;
	private JTextArea txtProcesso;
	private JComboBox<TipoDocumento> cbxTipoDocumento;
	private JLabel lblEditor;
	private JComboBox<Entity> cbxEditor;
	private JTextArea txtTexto;
	private JComboBox<Documento> cbxDocumentos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FrmTipoIIDocumento dialog = new FrmTipoIIDocumento();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FrmTipoIIDocumento() {
		setTitle("Formulário Tipo II - Gerenciamento de DOCUMENTO");
		setBounds(100, 100, 765, 467);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblMinutartipoIi = new JLabel("Minutar documento (Tipo II)");
			lblMinutartipoIi.setHorizontalAlignment(SwingConstants.CENTER);
			lblMinutartipoIi.setFont(new Font("Dialog", Font.BOLD, 18));
			lblMinutartipoIi.setBounds(12, 12, 731, 23);
			contentPanel.add(lblMinutartipoIi);
		}
		
		JLabel lblTipoDocumento = new JLabel("Tipo de documento:");
		lblTipoDocumento.setBounds(12, 99, 216, 15);
		contentPanel.add(lblTipoDocumento);
		
		cbxTipoDocumento = new JComboBox<TipoDocumento>();
		cbxTipoDocumento.setBounds(12, 115, 216, 30);
		contentPanel.add(cbxTipoDocumento);
		cbxTipoDocumento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				carregarEditor();
			}
		});
		
		{
			lblEditor = new JLabel("Editor");
			lblEditor.setBounds(12, 157, 196, 15);
			contentPanel.add(lblEditor);
		}
		{
			cbxEditor = new JComboBox<Entity>();
			cbxEditor.setBounds(12, 173, 216, 30);
			contentPanel.add(cbxEditor);
		}
		
		JLabel label = new JLabel("Número do processo:");
		label.setBounds(12, 215, 172, 15);
		contentPanel.add(label);
		
		txtNpu = new JTextField();
		txtNpu.setColumns(10);
		txtNpu.setBounds(12, 233, 172, 25);
		contentPanel.add(txtNpu);
		
		JButton btnPesquisar = new JButton("");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					pesquisarProcesso(txtNpu.getText());
					
				} catch (Exception e) {
					e.printStackTrace();
					Utils.msgExcption(e.getMessage());				
				}				
			}

		});
		btnPesquisar.setBackground(new Color(255, 255, 255));
		btnPesquisar.setIcon(new ImageIcon(FrmTipoIIDocumento.class.getResource("/image/pesquisar_32.png")));
		btnPesquisar.setBounds(186, 215, 42, 43);
		contentPanel.add(btnPesquisar);
		
		txtProcesso = new JTextArea();
		txtProcesso.setEditable(false);
		txtProcesso.setBounds(12, 283, 210, 104);
		contentPanel.add(txtProcesso);

		JLabel label_1 = new JLabel("Informaçõesdo processo:");
		label_1.setBounds(12, 265, 216, 15);
		contentPanel.add(label_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(240, 99, 497, 288);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		txtTexto = new JTextArea();
		txtTexto.setBounds(0, 0, 497, 288);
		panel.add(txtTexto);
		
		cbxDocumentos = new JComboBox<Documento>();
		cbxDocumentos.setBounds(12, 47, 722, 30);
		cbxDocumentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if ( cbxDocumentos.getItemCount() > 0) {
					int idDocumento= ((Documento)cbxDocumentos.getSelectedItem()).getId();
					if ( idDocumento > 0 ) {
						
						Documento doc= (Documento)cbxDocumentos.getSelectedItem();
						txtTexto.setText(doc.getTexto());
						
						pesquisarProcesso(doc.getProcesso());
						
						Entity entity= new Entity();
						entity.setCpf("0");
						entity.setNome(" ESCOLHA UMA OPÇÃO -");
						cbxEditor.addItem(entity);
						
						List<Entity> lista=new ArrayList<Entity>();
						for (Entity ent : lista) {
							cbxEditor.addItem(ent);
						}
						
						cbxEditor.setEnabled(false);
						cbxTipoDocumento.setEnabled(false);
					}
				}
			}
		});
		contentPanel.add(cbxDocumentos);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Salvar");
				okButton.setBackground(new Color(0, 128, 0));
				okButton.setForeground(new Color(255, 255, 255));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int idTipoDocumento= ((TipoDocumento)cbxTipoDocumento.getSelectedItem()).getId();
						int idDocumento= ((Documento)cbxDocumentos.getSelectedItem()).getId();
						String idEditor= ((Entity) cbxEditor.getSelectedItem()).getCpf();
						if ( idDocumento>0 || (idTipoDocumento > 0 && !idEditor.equals("0") &&
								txtProcesso.getText().trim().length() > 0) ) {
							
							Documento documento= new Documento();
							if (idDocumento > 0) {
								documento.setId(idDocumento);
							}
							documento.setDataCriacao(new GregorianCalendar());
							documento.setTexto(txtTexto.getText());
							documento.setTipoDocumento(idTipoDocumento);
							documento.setProcesso(txtNpu.getText());
							documento.setTexto(txtTexto.getText());		
							
							if ( idTipoDocumento == 6 || idTipoDocumento == 5 ) {
								documento.setServidor(idEditor);
							} else {
								documento.setMagistrado(idEditor);
							}

							DocumentoControl.getInstance().salvar(documento);
							
							JOptionPane.showMessageDialog(null, "Documento gravado com sucesso", "Aviso", 
									JOptionPane.INFORMATION_MESSAGE);
							
							carregarListaDocumento();
							
						} else {
							JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Atenção", 
									JOptionPane.INFORMATION_MESSAGE);
							return;
						}								
					}
				});
				
				JButton btnNovo = new JButton("Novo documento");
				btnNovo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						carregarListaDocumento();
					}
				});
				btnNovo.setForeground(Color.WHITE);
				btnNovo.setBackground(Color.BLUE);
				btnNovo.setActionCommand("OK");
				buttonPane.add(btnNovo);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Fechar");
				cancelButton.setBackground(new Color(255, 255, 255));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				
				JButton btnCancelar = new JButton("Apagar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							if (cbxDocumentos.getItemCount() > 0 ) {
								int idDoc= ( (Documento) cbxDocumentos.getSelectedItem()).getId();
								if (idDoc > 0 ) {
									DocumentoControl.getInstance().apagar(idDoc);
	
									carregarListaDocumento();
								}
							}
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Ocorreu um erro ao apagar o registro.", "Erro", 
									JOptionPane.INFORMATION_MESSAGE);
							return;
						}							
					}
				});
				btnCancelar.setForeground(Color.WHITE);
				btnCancelar.setBackground(Color.RED);
				btnCancelar.setActionCommand("OK");
				buttonPane.add(btnCancelar);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		List<TipoDocumento> lista= TipoDocumentoControl.getInstance().list();
		
		TipoDocumento tipoDoc= new TipoDocumento();
		tipoDoc.setId(0);
		tipoDoc.setNome(" ESCOLHA UMA OPÇÃO -");
		cbxTipoDocumento.addItem(tipoDoc);
		for (TipoDocumento tipoDocumento : lista) {
			cbxTipoDocumento.addItem(tipoDocumento);
		}
		
		carregarListaDocumento();
		
	}

	private void pesquisarProcesso(String npu) {
		Processo processo= ProcessoControl.getInstance().findById(npu);
		if (processo == null) {
			JOptionPane.showMessageDialog(null, "Processo não localizado", "Atenção", 
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		txtNpu.setText(npu);
		String txt= processo.getNpu() + "\n"
				+ "Feito: "+ processo.getProcessoFeito().getFeito().getNome();
		
		txtProcesso.setText(txt);
	}
	
	public void carregarListaDocumento() {
		cbxDocumentos.removeAllItems();
		List<Documento> listaDoc= DocumentoControl.getInstance().list();
		Documento doc= new Documento();
		doc.setId(0);
		doc.setProcesso(" ESCOLHA UM DOCUMENTO -");
		cbxDocumentos.addItem(doc);
		for (Documento documento : listaDoc) {
			cbxDocumentos.addItem(documento);
		}
		limpar();
	}
	
	private void limpar() {
		cbxEditor.setEnabled(true);
		cbxTipoDocumento.setEnabled(true);
		
		lblEditor.setText("Editor:");
		cbxTipoDocumento.setSelectedIndex(0);
		cbxEditor.removeAllItems();
		txtNpu.setText("");
		txtTexto.setText("");
		txtProcesso.setText("");
		cbxDocumentos.setSelectedIndex(0);
	}
	
	private void carregarEditor() {
		cbxEditor.removeAllItems();;
		int idTipoDocumento= ((TipoDocumento)cbxTipoDocumento.getSelectedItem()).getId();
		if ( idTipoDocumento > 0 ) {
			Entity entity= new Entity();
			entity.setCpf("0");
			entity.setNome(" ESCOLHA UMA OPÇÃO -");
			cbxEditor.addItem(entity);
			
			List<Entity> lista=new ArrayList<Entity>();
			Servidor filtro= new Servidor();
			if ( idTipoDocumento == 6 ) {
				lblEditor.setText("Conciliador:");
				filtro.setTipoServidor("C");
				lista= ServidorControl.getInstance().findByFilter(filtro);
				txtTexto.setText("");
				txtTexto.setText("TERMO DE CONCILIACAO \n\n");
				
			} else if ( idTipoDocumento == 5 ) {
				lblEditor.setText("Analista:");
				filtro.setTipoServidor("A");
				lista= ServidorControl.getInstance().findByFilter(filtro);
				txtTexto.setText("");
				txtTexto.setText("DESPACHO \n\n");
				
			} else if ( idTipoDocumento == 3 ) {
				lblEditor.setText("Magistrado:");
				lista= MagistradoControl.getInstance().list();
				txtTexto.setText("");
				txtTexto.setText("SENTECA \n\n");
				
			} else {
				lblEditor.setText("Magistrado:");
				lista= MagistradoControl.getInstance().list();
				txtTexto.setText("");
				txtTexto.setText("DESCISÃO \n\n");
			}
			
			for (Entity ent : lista) {
				cbxEditor.addItem(ent);
			}
		} 		
	}	
}
