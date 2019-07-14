package br.ufrpe.spjc.gui.taciano;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

import br.ufrpe.spjc.negocio.controlador.AudienciaControl;
import br.ufrpe.spjc.negocio.controlador.JuizadoControl;
import br.ufrpe.spjc.negocio.controlador.PautaControl;
import br.ufrpe.spjc.negocio.controlador.ProcessoControl;
import br.ufrpe.spjc.negocio.entidade.Audiencia;
import br.ufrpe.spjc.negocio.entidade.Juizado;
import br.ufrpe.spjc.negocio.entidade.Pauta;
import br.ufrpe.spjc.negocio.entidade.Processo;
import br.ufrpe.spjc.util.Utils;

public class FrmTipoIIIProcesso extends JDialog {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5287862733905941387L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNPU;
	private JTextField txtSala;
	private JComboBox<String> cbxTipo;
	private JComboBox<Pauta> cbxPauta;
	private JComboBox<Audiencia> cbxHorario;
	private JTextArea txtProcesso;
	private JComboBox<Juizado> cbxJuizado;
	private String ITEM_ESCOLHA= "--- ESCOLHA UM OPCAO ---";
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
		setBounds(100, 100, 684, 426);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblPautasDisponveis = new JLabel("Pautas disponíveis:");
		lblPautasDisponveis.setBounds(12, 99, 152, 15);
		contentPanel.add(lblPautasDisponveis);
		
		cbxPauta = new JComboBox<Pauta>();
		cbxPauta.setBounds(12, 116, 314, 30);
		contentPanel.add(cbxPauta);
		cbxPauta.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	if ( cbxPauta.getSelectedIndex() > 0 ) {
		    		carregarHorarios();
		    	}
		    }
		});	
		
		cbxHorario = new JComboBox<Audiencia>();
		cbxHorario.setBounds(343, 116, 314, 30);
		contentPanel.add(cbxHorario);
		
		JLabel lblHorrios = new JLabel("Horários:");
		lblHorrios.setBounds(343, 100, 314, 15);
		contentPanel.add(lblHorrios);
		
		JLabel lblNmeroDoProcesso = new JLabel("Número do processo:");
		lblNmeroDoProcesso.setBounds(12, 165, 189, 15);
		contentPanel.add(lblNmeroDoProcesso);
		
		txtNPU = new JTextField();
		txtNPU.setBounds(12, 183, 189, 25);
		contentPanel.add(txtNPU);
		txtNPU.setColumns(10);
		
		JButton btnBuscarProcesso = new JButton("");
		btnBuscarProcesso.setBackground(Color.WHITE);
		btnBuscarProcesso.setIcon(new ImageIcon(FrmTipoIIIProcesso.class.getResource("/image/pesquisar_32.png")));
		btnBuscarProcesso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					pesquisarProcesso(txtNPU.getText());
					
				} catch (Exception e) {
					e.printStackTrace();
					Utils.msgExcption(e.getMessage());				
				}	
			}
		});
		btnBuscarProcesso.setBounds(135, 220, 66, 42);
		contentPanel.add(btnBuscarProcesso);
		
		JLabel lblProcesso_1 = new JLabel("Informaçõesdo processo:");
		lblProcesso_1.setBounds(227, 165, 344, 15);
		contentPanel.add(lblProcesso_1);
		
		JLabel lblSala = new JLabel("Sala:");
		lblSala.setBounds(517, 301, 136, 15);
		contentPanel.add(lblSala);
		
		txtSala = new JTextField();
		txtSala.setEditable(false);
		txtSala.setColumns(10);
		txtSala.setBounds(517, 319, 136, 25);
		contentPanel.add(txtSala);
		
		cbxTipo = new JComboBox<String>();
		cbxTipo.setBounds(12, 316, 493, 30);
		contentPanel.add(cbxTipo);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(12, 301, 187, 15);
		contentPanel.add(lblTipo);
		
		txtProcesso = new JTextArea();
		txtProcesso.setEditable(false);
		txtProcesso.setBounds(227, 183, 421, 104);
		contentPanel.add(txtProcesso);
		
		JLabel lblJuizado = new JLabel("Juizado:");
		lblJuizado.setBounds(12, 41, 152, 15);
		contentPanel.add(lblJuizado);
		
		cbxJuizado = new JComboBox<Juizado>();
		cbxJuizado.setBounds(12, 58, 645, 30);
		contentPanel.add(cbxJuizado);
		
		JLabel lblAgendamentoDeProcesso = new JLabel("Agendamento de processo em Pauta (Tipo III)");
		lblAgendamentoDeProcesso.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgendamentoDeProcesso.setFont(new Font("Dialog", Font.BOLD, 18));
		lblAgendamentoDeProcesso.setBounds(12, 6, 645, 23);
		contentPanel.add(lblAgendamentoDeProcesso);
		cbxJuizado.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	carregarPauta();
		    }
		});
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnApagar = new JButton("Apagar");
				btnApagar.setBackground(new Color(255, 0, 0));
				btnApagar.setForeground(new Color(255, 255, 255));
				btnApagar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							int idAud= ( (Audiencia) cbxHorario.getSelectedItem()).getId();
							if (idAud > 0 ) {
								AudienciaControl.getInstance().apagar(idAud);
								//limpar();
								cbxPauta.removeAll();
								cbxHorario.removeAll();
								carregarPauta();
								JOptionPane.showMessageDialog(null, "Pocesso removido da pauta.", "Sucesso", 
										JOptionPane.INFORMATION_MESSAGE);
							} 
							
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Ocorreu um erro ao apagar o registro.", "Erro", 
									JOptionPane.INFORMATION_MESSAGE);
							return;
						}	
					}
				});
				
				JButton btnNova = new JButton("Nova audiência");
				btnNova.setForeground(new Color(255, 255, 255));
				btnNova.setBackground(new Color(0, 0, 255));
				btnNova.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						limpar();
					}
				});
				btnNova.setActionCommand("OK");
				buttonPane.add(btnNova);
				
				JButton btnSalvar = new JButton("Salvar");
				btnSalvar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							if (cbxHorario.getItemCount() > 0) {
								
								if ( cbxTipo.getSelectedIndex() > 0 && txtNPU.getText() != null && txtProcesso.getText() != null ) {
									int idHora= ( (Audiencia) cbxHorario.getSelectedItem() ).getHoraMarcacao();
									int idPauta= ( (Pauta) cbxPauta.getSelectedItem() ).getId();
									String idTipo= cbxTipo.getSelectedItem().toString();
									
									Time time= new Time(idHora, 0, 0);
									
									Audiencia audiencia= new Audiencia();
									audiencia.setPauta(idPauta);
									audiencia.setProcesso(txtNPU.getText());
									audiencia.setSala(txtSala.getText());
									audiencia.setTipo(cbxTipo.getSelectedItem().toString());
									audiencia.setHoraMarcacao(idHora);
									
									Calendar cal= new GregorianCalendar();
									cal.set(Calendar.HOUR, idHora);
									audiencia.setHora(cal);
									AudienciaControl.getInstance().salvar(audiencia);
									JOptionPane.showMessageDialog(null, "Registro salvo", "Atenção", 
											JOptionPane.INFORMATION_MESSAGE);
									
									
								} else {
									JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Atenção", 
											JOptionPane.INFORMATION_MESSAGE);
									return;
								}	
							}
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
				});
				btnSalvar.setBackground(new Color(0, 128, 0));
				btnSalvar.setForeground(new Color(255, 255, 255));
				btnSalvar.setActionCommand("OK");
				buttonPane.add(btnSalvar);
				btnApagar.setActionCommand("OK");
				buttonPane.add(btnApagar);
				getRootPane().setDefaultButton(btnApagar);
			}
			{
				JButton cancelButton = new JButton("Fechar");
				cancelButton.setBackground(new Color(255, 255, 255));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
		
		// Carregar os juizados
		List<Juizado> juizados= JuizadoControl.getInstance().findByFilter(new Juizado());
		Juizado juizado= new Juizado();
		juizado.setId(0);
		juizado.setNome(ITEM_ESCOLHA);
		cbxJuizado.addItem(juizado);
		for (Juizado juizdo : juizados) {
			cbxJuizado.addItem(juizdo);
		}
		
		// Carregar tipo audiencia
		cbxTipo.addItem(ITEM_ESCOLHA);
		cbxTipo.addItem("C- Conciliação");
		cbxTipo.addItem("I- Instrução e Julgamento");
	}
	
	private void limpar() {
		txtNPU.setText("");
		txtProcesso.setText("");
		txtSala.setText("");
		cbxJuizado.setSelectedIndex(0);
		cbxHorario.setSelectedIndex(0);
		cbxTipo.setSelectedIndex(0);
		cbxPauta.setSelectedIndex(0);
	}
	
	private void pesquisarProcesso(String npu) {
		Processo processo= ProcessoControl.getInstance().findById(npu);
		if (processo == null) {
			JOptionPane.showMessageDialog(null, "Processo não localizado", "Atenção", 
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		txtNPU.setText(npu);
		String txt= processo.getNpu() + "\n"
				+ "Feito: "+ processo.getProcessoFeito().getFeito().getNome();
		
		txtProcesso.setText(txt);
	}
	
	private void carregarPauta() {	
		cbxPauta.removeAllItems();
		cbxHorario.removeAllItems();
		int idJuizado= Integer.parseInt(Utils.getId(cbxJuizado.getSelectedItem().toString(), "-"));
		if ( idJuizado > 0 ) {
			Pauta pauta= new Pauta();
			pauta.setId(0);
			cbxPauta.addItem(pauta);
			
			List<Pauta> lista= PautaControl.getInstance().buscarPautaAtivas(idJuizado);	
			for (Pauta pta : lista) {	
				cbxPauta.addItem(pta);	
			}
		}
		
		Juizado juizado= (Juizado) cbxJuizado.getSelectedItem();
		txtSala.setText( juizado.getSalaAudiencia() );
		
	}
	
	private void carregarHorarios() {	
		cbxHorario.removeAllItems();
		int idPauta= Integer.parseInt(Utils.getId(cbxPauta.getSelectedItem().toString(), "-"));
		Map<Integer, Audiencia> mapHorario= new TreeMap<Integer, Audiencia>();
		mapHorario.put(13, new Audiencia(0, 13));
		mapHorario.put(14, new Audiencia(0, 14));
		mapHorario.put(15, new Audiencia(0, 15));
		mapHorario.put(16, new Audiencia(0, 16));
		mapHorario.put(17, new Audiencia(0, 17));
		mapHorario.put(18, new Audiencia(0, 18));
		
		if ( idPauta > 0 ) {
			List<Audiencia> lista= PautaControl.getInstance().buscarHorarioPautaDisponival(idPauta);	
			//Pauta pauta= PautaControl.getInstance().findById(idPauta);
			
			for (Audiencia audiencia : lista) {
				int hora= audiencia.getHoraMarcacao();
				
				audiencia.setHoraMarcacao(hora);
				if ( mapHorario.containsKey(hora) ) {
					mapHorario.remove(hora);
					mapHorario.put(hora, audiencia);
				}
			}
			
			for (Audiencia audiencia2 : mapHorario.values()) {
				cbxHorario.addItem(audiencia2);	
			}
		}
	}
}
