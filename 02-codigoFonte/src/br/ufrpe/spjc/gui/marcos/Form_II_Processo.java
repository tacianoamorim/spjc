package br.ufrpe.spjc.gui.marcos;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.ufrpe.spjc.negocio.controlador.FaseControl;
import br.ufrpe.spjc.negocio.controlador.FeitoControl;
import br.ufrpe.spjc.negocio.controlador.JuizadoControl;
import br.ufrpe.spjc.negocio.controlador.ProcessoControl;
import br.ufrpe.spjc.negocio.entidade.Fase;
import br.ufrpe.spjc.negocio.entidade.Feito;
import br.ufrpe.spjc.negocio.entidade.Juizado;
import br.ufrpe.spjc.negocio.entidade.Processo;
import br.ufrpe.spjc.negocio.entidade.ProcessoFase;
import br.ufrpe.spjc.negocio.entidade.ProcessoFeito;
import br.ufrpe.spjc.util.Utils;

public class Form_II_Processo extends JDialog {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2551738506958296384L;
	
	private final JPanel contentPanel = new JPanel();
	private TableProcesso tableModel;
	private JTable tbLista;

	private JTextPane txtObservacao;
	private JComboBox<Juizado> cbxJuizado;
	private JComboBox<Feito> cbxFeito;
	private JComboBox<Fase> cbxFase;
	
	List<Juizado> juizados= new ArrayList<Juizado>();
	List<Fase> fases= new ArrayList<Fase>();
	List<Feito> feitos= new ArrayList<Feito>();
	
	JButton btnSalva = new JButton("Salva");
	JButton btnNovo = new JButton("Novo");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
			Form_II_Processo dialog = new Form_II_Processo();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Form_II_Processo() {
		
		setBounds(100, 100, 679, 482);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 636, 391);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblBairro = new JLabel("Juizado:");
		lblBairro.setBounds(10, 27, 78, 14);
		contentPanel.add(lblBairro);
		
		JLabel lblCidade = new JLabel("Feito");
		lblCidade.setBounds(10, 76, 78, 14);
		contentPanel.add(lblCidade);
		
		JLabel lblNcasa = new JLabel("Fase");
		lblNcasa.setBounds(10, 122, 78, 14);
		contentPanel.add(lblNcasa);
		
		JLabel lblUf = new JLabel("Observação");
		lblUf.setBounds(271, 27, 172, 14);
		contentPanel.add(lblUf);
		btnSalva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					/*
					 * CARREGAR DADOS
					 */
					Processo processo= new Processo();
					ProcessoFase processoFase= new ProcessoFase();
					Fase fase= new Fase();
					fase.setId(( ((Fase) cbxFase.getSelectedItem()).getId() ));
					processoFase.setFase(fase);
					processo.setProcessoFase(processoFase);
					
					ProcessoFeito processoFeito= new ProcessoFeito();
					Feito feito= new Feito();
					feito.setId(( ((Feito) cbxFeito.getSelectedItem()).getId() ));
					processoFeito.setFeito(feito);
					processo.setProcessoFeito(processoFeito);					
					
					Juizado juizado= new Juizado();
					juizado.setId( ((Juizado) cbxJuizado.getSelectedItem()).getId()  );
					processo.setJuizado(juizado);
					
					processo.setObservacao(txtObservacao.getText());
					
					ProcessoControl.getInstance().salvar(processo);
					limpar();
					JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!", "Confirmação de cadastro/atualização", 
							JOptionPane.INFORMATION_MESSAGE);
					carregarTable();
					
					btnNovo.setEnabled(true);
					btnSalva.setEnabled(false);

				} catch (Throwable e1) {
					e1.printStackTrace();
					Utils.msgExcption(e1.getMessage());	
				}
				
			}
		});
		
		btnSalva.setBounds(411, 177, 89, 23);
		contentPanel.add(btnSalva);
		
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpar();
				btnSalva.setEnabled(true);
			}
		});
		
		btnNovo.setBounds(312, 177, 89, 23);
		contentPanel.add(btnNovo);
		
		JLabel texto = new JLabel("Cadastro de Magistrado");
		contentPanel.add(texto);
		
		tableModel = new TableProcesso();
		
		setResizable(false);
		setAutoRequestFocus(false);
		setTitle("Formulário Magistrado");
		setBounds(100, 100, 778, 482);
		setModal(true);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cadastro de Processo");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 174, 240, 23);
		contentPanel.add(lblNewLabel);
		tbLista = new  JTable(tableModel);
		formatarTabela(tbLista);
		tbLista.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                Processo processo = tableModel.get(tbLista.getSelectedRow());
	            carregarDados(processo);
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
		scpLista.setBounds(10, 205, 589, 155);
		contentPanel.add(scpLista);
		tbLista.setFillsViewportHeight(true);
		
		txtObservacao = new JTextPane();
		txtObservacao.setBounds(268, 45, 331, 118);
		contentPanel.add(txtObservacao);
		
		cbxJuizado = new JComboBox<Juizado>();
		cbxJuizado.setBounds(10, 45, 235, 20);
		contentPanel.add(cbxJuizado);
		
		cbxFeito = new JComboBox<Feito>();
		cbxFeito.setBounds(10, 91, 235, 20);
		contentPanel.add(cbxFeito);
		
		cbxFase = new JComboBox<Fase>();
		cbxFase.setBounds(10, 140, 235, 20);
		contentPanel.add(cbxFase);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnNovo.setEnabled(true);
				btnSalva.setEnabled(false);
				limpar();
			}
		});
		btnCancelar.setBounds(510, 177, 89, 23);
		contentPanel.add(btnCancelar);
		
		carregarTable();
		
		// Carregar os juizados
		juizados= JuizadoControl.getInstance().findByFilter(new Juizado());
		for (Juizado juizdo : juizados) {
			cbxJuizado.addItem(juizdo);
		}		
				
		// Carregar os fase
		fases= FaseControl.getInstance().list();
		for (Fase fase : fases) {
			cbxFase.addItem(fase);
		}	
		
		// Carregar os feito
		feitos= FeitoControl.getInstance().list();
		for (Feito feito : feitos) {
			cbxFeito.addItem(feito);
		}
		
		btnSalva.setEnabled(false);
	}
	
	private void formatarTabela(JTable jTable) {
		jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jTable.getColumnModel().getColumn(0).setPreferredWidth(180);
		jTable.getColumnModel().getColumn(1).setPreferredWidth(400);
		}	
	
	private void limpar() {
		txtObservacao.setText("");
		cbxFase.setSelectedIndex(0);
		cbxFeito.setSelectedIndex(0);
		cbxJuizado.setSelectedIndex(0);
	}	
	
	private void carregarDados(Processo processo) {
		txtObservacao.setText(processo.getObservacao());
		
		int idx= 0;
		for (Juizado juizado : juizados) {
			if ( processo.getJuizado().getId() == juizado.getId()) {
				cbxJuizado.setSelectedIndex(idx);
				break;
			}
			idx++;
		}

		idx= 0;
		for (Feito feito : feitos) {
			if ( processo.getProcessoFeito().getFeito().getId() == feito.getId()) {
				cbxFeito.setSelectedIndex(idx);
				break;
			}
			idx++;
		}
		
		idx= 0;
		for (Fase fase : fases) {
			if ( processo.getProcessoFase().getFase().getId() == fase.getId()) {
				cbxFase.setSelectedIndex(idx);
				break;
			}
			idx++;
		}		
		
	}
	
	private void carregarTable() {
		// Carregar lista
		tableModel.limpar();
		List<Processo> lista= ProcessoControl.getInstance().findByFilter(new Processo());
		tableModel.addList(lista);
	}
}
