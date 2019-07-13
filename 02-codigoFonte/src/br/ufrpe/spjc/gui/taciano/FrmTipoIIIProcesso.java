package br.ufrpe.spjc.gui.taciano;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private JComboBox<Audiencia> cbxTipo;
	private JComboBox<Pauta> cbxPauta;
	private JComboBox<Audiencia> cbxHorario;
	private JTextArea txtProcesso;
	private JComboBox<Juizado> cbxJuizado;
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
		    	carregarHorarios();
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
		
		cbxTipo = new JComboBox<Audiencia>();
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
		juizado.setNome("--- ESCOLHA UM JUIZADO ---");
		cbxJuizado.addItem(juizado);
		for (Juizado juizdo : juizados) {
			cbxJuizado.addItem(juizdo);
		}
	}
	
	private void limpar() {
		txtNPU.setText("");
		txtProcesso.setText("");
		txtSala.setText("");
		cbxPauta.setSelectedIndex(0);
		cbxHorario.setSelectedIndex(0);
		cbxTipo.setSelectedIndex(0);
		cbxJuizado.setSelectedIndex(0);
	}
	
	private void carregarPauta() {	
		cbxPauta.removeAllItems();
		int idJuizado= Integer.parseInt(Utils.getId(cbxJuizado.getSelectedItem().toString(), "-"));
		if ( idJuizado > 0 ) {
			List<Pauta> lista= PautaControl.getInstance().buscarPautaAtivas(idJuizado);	
			for (Pauta pauta : lista) {	
				cbxPauta.addItem(pauta);	
			}
		}
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
	
	private void carregarHorarios() {	
		//if ( )
		
		cbxHorario.removeAllItems();
		int idPauta= Integer.parseInt(Utils.getId(cbxPauta.getSelectedItem().toString(), "-"));
		
		Map<Integer, Audiencia> mapHorario= new HashMap<Integer, Audiencia>();
		mapHorario.put(13, null);
		mapHorario.put(14, null);
		mapHorario.put(15, null);
		mapHorario.put(16, null);
		mapHorario.put(17, null);
		mapHorario.put(18, null);
		
		if ( idPauta > 0 ) {
			List<Audiencia> lista= PautaControl.getInstance().buscarHorarioPautaDisponival(idPauta);	
			Pauta pauta= PautaControl.getInstance().findById(idPauta);
			
			for (Audiencia audiencia : lista) {
				
				int hora= audiencia.getHora().get(Calendar.HOUR);
				if ( mapHorario.containsKey(hora) ) {
					mapHorario.put(hora, audiencia);

				} else {
					Audiencia aud= new Audiencia();
					Processo processo= new Processo();
					processo.setNpu(" ");
					aud.setProcesso(processo);
					
					pauta.getDataAgendamento().set(Calendar.HOUR, hora);
					pauta.getDataAgendamento().set(Calendar.MINUTE, 0);
					aud.setHora(pauta.getDataAgendamento());
					aud.setSala( ((Juizado)cbxJuizado.getSelectedItem()).getSalaAudiencia() );
					mapHorario.put(hora, aud);
				}
			}
			
			for (Audiencia audiencia2 : mapHorario.values()) {
				cbxHorario.addItem(audiencia2);	
			}
		}
	}
}
