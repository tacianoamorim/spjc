package br.ufrpe.spjc.gui.marcos;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.ufrpe.spjc.negocio.controlador.MagistradoControl;
import br.ufrpe.spjc.negocio.controlador.ProcessoControl;
import br.ufrpe.spjc.negocio.entidade.Magistrado;
import br.ufrpe.spjc.negocio.entidade.Processo;
import br.ufrpe.spjc.negocio.entidade.ProcessoFase;
import br.ufrpe.spjc.util.Utils;

public class FormProcesso extends JFrame {

	private JPanel contentPane;

	private JTable tbLista;
	private JTextField texnpu,texjuizado,texobservacao,texdataAjuizamento,texdataBaixa,textipoBaixa,texfase,texnpuf;
	Tabela tableModel = new Tabela();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormProcesso frame = new FormProcesso();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormProcesso() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 711, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane((Component) null);
		contentPane.add(scrollPane, BorderLayout.CENTER);
	
		JLabel texto = new JLabel("Cadastro de Magistrado");
		contentPane.add(texto);
		
		 tableModel = new Tabela();
		
		setResizable(false);
		setAutoRequestFocus(false);
		setTitle("Formulário Magistrado");
		setBounds(100, 100, 778, 482);
		//setModal(true);
		
		getContentPane().setLayout(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPane, BorderLayout.CENTER);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cadastro de Magistrado");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel.setBounds(146, 174, 569, 23);
		contentPane.add(lblNewLabel);
		tbLista = new  JTable(tableModel);// table;
		formatarTabela(tbLista);
		tbLista.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                Magistrado magistrado = tableModel.get(tbLista.getSelectedRow());
                
                limpar();
	            //carregarDados(magistrado);
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
		
	}

		private void formatarTabela(JTable jTable) {
			jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			jTable.getColumnModel().getColumn(0).setPreferredWidth(170);
			jTable.getColumnModel().getColumn(1).setPreferredWidth(370);
			jTable.getColumnModel().getColumn(2).setPreferredWidth(80);
			}	
		
		private void limpar() {
			texdataAjuizamento.setText("");
			texdataBaixa.setText("");
			texfase.setText("");
			texjuizado.setText("");
			texnpu.setText("");
			texnpuf.setText("");
				
		}	
		
		private void carregarTable() {
			// Carregar lista
			tableModel.limpar();
			List<Processo> lista= ProcessoControl.getInstance().findByFilter(new Processo());
			tableModel.addList1(lista);
		}
		
		private boolean validaCampos(){
			boolean isValido= true;	
			
			if ( texnpu.getText().length() == 0 || 
				texjuizado.getText().length() == 0 || 
				texdataAjuizamento.getText().length() == 0 || 
				texdataBaixa.getText().length() == 0 ||  
				texobservacao.getText().length() == 0 || 
				textipoBaixa.getText().length() == 0  
				) {
				isValido= false;
			}
			
			return isValido;

	}

}
