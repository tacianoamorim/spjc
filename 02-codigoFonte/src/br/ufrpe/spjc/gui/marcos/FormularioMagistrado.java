package br.ufrpe.spjc.gui.marcos;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
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
import br.ufrpe.spjc.negocio.controlador.RepresentanteControl;
import br.ufrpe.spjc.negocio.entidade.Endereco;
import br.ufrpe.spjc.negocio.entidade.Magistrado;
import br.ufrpe.spjc.negocio.entidade.Processo;
import br.ufrpe.spjc.negocio.entidade.Representante;
import br.ufrpe.spjc.repositorio.MagistradoDAO;
import br.ufrpe.spjc.util.Utils;

public class FormularioMagistrado extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField texcpf;
	private JTextField texcep;
	private JTextField texmatricula;
	private JTextField texnome;
	private JTextField texsenha;
	private JTextField texbairro;
	private JTextField texcidade;
	private JTextField texnumendereco;
	private JTextField texlogradoro;
	private JTextField texestado;
	private Tabela tableModel;
	private JTable tbLista;


	
	int flag = 0;
	
	Magistrado mod= new Magistrado();
	JButton btnSalva = new JButton("Salva");
	JButton btnEditar = new JButton("Editar");
	JButton btnExcluir = new JButton("Excluir");
	JButton btnNovo = new JButton("Novo");
	MagistradoDAO control = new MagistradoDAO();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
			FormularioMagistrado dialog = new FormularioMagistrado();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FormularioMagistrado() {
		
		
		setBounds(100, 100, 820, 482);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		texcpf = new JTextField();
		texcpf.setBounds(136, 11, 136, 20);
		contentPanel.add(texcpf);
		texcpf.setColumns(10);
		
		texcep = new JTextField();
		texcep.setBounds(136, 42, 86, 20);
		contentPanel.add(texcep);
		texcep.setColumns(10);
		
		texmatricula = new JTextField();
		texmatricula.setBounds(136, 73, 86, 20);
		contentPanel.add(texmatricula);
		texmatricula.setColumns(10);
		
		texnome = new JTextField();
		texnome.setBounds(136, 104, 136, 20);
		contentPanel.add(texnome);
		texnome.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(34, 14, 46, 14);
		contentPanel.add(lblCpf);
		
		JLabel lblNewLabel_1 = new JLabel("Matricula");
		lblNewLabel_1.setBounds(34, 76, 92, 14);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(34, 107, 92, 14);
		contentPanel.add(lblNome);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(34, 138, 92, 14);
		contentPanel.add(lblSenha);
		
		texsenha = new JTextField();
		texsenha.setBounds(136, 135, 86, 20);
		contentPanel.add(texsenha);
		texsenha.setColumns(10);
		
		texbairro = new JTextField();
		texbairro.setBounds(457, 8, 142, 20);
		contentPanel.add(texbairro);
		texbairro.setColumns(10);
		
		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setBounds(369, 14, 78, 14);
		contentPanel.add(lblBairro);
		
		texcidade = new JTextField();
		texcidade.setBounds(457, 42, 86, 20);
		contentPanel.add(texcidade);
		texcidade.setColumns(10);
		
		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(369, 45, 78, 14);
		contentPanel.add(lblCidade);
		
		texnumendereco = new JTextField();
		texnumendereco.setBounds(457, 73, 86, 20);
		contentPanel.add(texnumendereco);
		texnumendereco.setColumns(10);
		
		JLabel lblNcasa = new JLabel("N°Casa");
		lblNcasa.setBounds(369, 76, 78, 14);
		contentPanel.add(lblNcasa);
		
		texlogradoro = new JTextField();
		texlogradoro.setBounds(457, 104, 86, 20);
		contentPanel.add(texlogradoro);
		texlogradoro.setColumns(10);
		
		JLabel lblLogradoro = new JLabel("Rua");
		lblLogradoro.setBounds(369, 107, 46, 14);
		contentPanel.add(lblLogradoro);
		
		texestado = new JTextField();
		texestado.setBounds(457, 135, 86, 20);
		contentPanel.add(texestado);
		texestado.setColumns(10);
		
		JLabel lblUf = new JLabel("Estado");
		lblUf.setBounds(369, 138, 46, 14);
		contentPanel.add(lblUf);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				texbairro.setEnabled(true);
				texcep.setEnabled(true);
				texcidade.setEnabled(true);
				texcpf.setEnabled(true);
				texestado.setEnabled(true);
				texlogradoro.setEnabled(true);
				texmatricula.setEnabled(true);
				texnome.setEnabled(true);
				texnumendereco.setEnabled(true);
				texsenha.setEnabled(true);
				
				btnSalva.setEnabled(true);
				btnExcluir.setEnabled(false);
				btnNovo.setEnabled(false);
				btnEditar.setEnabled(false);	
			
				btnNovo.setEnabled(true);
				
			}
		});
		
		btnSalva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					/*
					 * CARREGAR DADOS
					 */
					Magistrado magistrado= new Magistrado();
					magistrado.setNome(texnome.getText());
					magistrado.setNumero(texnumendereco.getText());
					magistrado.setMatricula(Integer.parseInt(texmatricula.getText()));
					magistrado.setCpf(texcpf.getText());
					magistrado.setSenha(texsenha.getText());

					Endereco endereco= new Endereco();
					int cep= Integer.parseInt(texcep.getText());
					endereco.setCep(cep);
					endereco.setRua(texlogradoro.getText());
					endereco.setBairro(texbairro.getText());
					endereco.setCidade(texcidade.getText());
					endereco.setEstado(texestado.getText());
					magistrado.setEndereco(endereco);
					
					if (!validaCampos()) {
						JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Validação de campos", 
								JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						
						MagistradoControl.getInstance().salvar(magistrado);
						limpar();
						JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!", "Confirmação de cadastro/atualização", 
								JOptionPane.INFORMATION_MESSAGE);
						carregarTable();
					}
				} catch (Throwable e1) {
					e1.printStackTrace();
					Utils.msgExcption(e1.getMessage());	
				}
				
			}
		});
		
		btnSalva.setBounds(10, 208, 89, 23);
		contentPanel.add(btnSalva);
		
		btnEditar.setBounds(10, 242, 89, 23);
		contentPanel.add(btnEditar);
		
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					if (tbLista.getSelectedRow() < 0) {
						JOptionPane.showMessageDialog(null, "Selecione um registro na tabela. "
							, "ERROR", JOptionPane.ERROR_MESSAGE);	
						return;
					} 
					
	                Magistrado magistrado= tableModel.get(tbLista.getSelectedRow());
	               
	                int selectedOption = JOptionPane.showConfirmDialog(null,"Confirma a exclusão do representante "+
	                magistrado.getNome() +"?", "Confirmar exclusão", JOptionPane.YES_NO_OPTION);
	        		if(selectedOption == JOptionPane.YES_OPTION){
	        			MagistradoControl.getInstance().apagar(magistrado);     
	        			carregarTable();
	        			JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!", "Confirmação de cadastro/atualização", 
								JOptionPane.INFORMATION_MESSAGE);
	        		limpar();
	        		}	
				} catch (Throwable e) {
						e.printStackTrace();
						Utils.msgExcption(e.getMessage());	
				}
				
			}
		});
		
		btnExcluir.setBounds(10, 276, 89, 23);
		contentPanel.add(btnExcluir);
		
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpar();
				texbairro.setEnabled(true);
				texcep.setEnabled(true);
				texcidade.setEnabled(true);
				texcpf.setEnabled(true);
				texestado.setEnabled(true);
				texlogradoro.setEnabled(true);
				texmatricula.setEnabled(true);
				texnome.setEnabled(true);
				texnumendereco.setEnabled(true);
				texsenha.setEnabled(true);
				
				btnEditar.setEnabled(true);
				btnSalva.setEnabled(true);
				btnExcluir.setEnabled(true);
			
			}
		});
		
		btnNovo.setBounds(10, 174, 89, 23);
		contentPanel.add(btnNovo);
		
		JLabel texto = new JLabel("Cadastro de Magistrado");
		contentPanel.add(texto);
		
		tableModel = new Tabela();
		
		setResizable(false);
		setAutoRequestFocus(false);
		setTitle("Formulário Magistrado");
		setBounds(100, 100, 778, 482);
		setModal(true);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cadastro de Magistrado");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel.setBounds(146, 174, 569, 23);
		contentPanel.add(lblNewLabel);
		tbLista = new  JTable(tableModel);// table;
		formatarTabela(tbLista);
		tbLista.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                Magistrado magistrado = tableModel.get(tbLista.getSelectedRow());
                
                limpar();
	            carregarDados(magistrado);
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
		scpLista.setBounds(136, 205, 463, 155);
		contentPanel.add(scpLista);
		tbLista.setFillsViewportHeight(true);
		
		JLabel lblCep = new JLabel("CEP");
		lblCep.setBounds(34, 45, 46, 14);
		contentPanel.add(lblCep);
		
		carregarTable();
	}
	
	private void formatarTabela(JTable jTable) {
		jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jTable.getColumnModel().getColumn(0).setPreferredWidth(170);
		jTable.getColumnModel().getColumn(1).setPreferredWidth(370);
		jTable.getColumnModel().getColumn(2).setPreferredWidth(80);
		}	
	
	private void limpar() {
		texnome.setText("");
		texcidade.setText("");
		texestado.setText("");
		texlogradoro.setText("");
		texmatricula.setText("");
		texnumendereco.setText("");
		texcpf.setText("");
		texbairro.setText("");
		texcep.setText("");
		texsenha.setText("");
	}	
	private void carregarDados(Magistrado entity) {
		texnome.setText(entity.getNome());
		texmatricula.setText(entity.getMatricula()+"");
		texnumendereco.setText(entity.getNumero());
		texcpf.setText(entity.getCpf());
		texsenha.setText(entity.getSenha());
		if ( entity.getEndereco() != null) {
			texlogradoro.setText(entity.getEndereco().getRua());
			texbairro.setText(entity.getEndereco().getBairro());
			texcidade.setText(entity.getEndereco().getCidade());
			texestado.setText(entity.getEndereco().getEstado());
			texcep.setText(entity.getEndereco().getCep()+"");
		}
	}
	
	
	public void actionPerformed(ActionEvent arg0) {
		
		try {
			/*
			 * CARREGAR DADOS
			 */
			Magistrado magistrado= new Magistrado();
			magistrado.setNome(texnome.getText());
			magistrado.setCpf(texcpf.getText());
			magistrado.setNumero(texnumendereco.getText());
			magistrado.setMatricula(Integer.parseInt(texmatricula.getText()));
			magistrado.setCpf(texcpf.getText());
			magistrado.setSenha(texsenha.getText());
		
			Endereco endereco= new Endereco();
			int cep= Integer.parseInt(texcep.getText());
			endereco.setCep(cep);
			endereco.setRua(texlogradoro.getText());
			endereco.setBairro(texbairro.getText());
			endereco.setCidade(texcidade.getText());
			endereco.setEstado(texestado.getText());
			magistrado.setEndereco(endereco);
			
			if (!validaCampos()) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Validação de campos", 
						JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				
				MagistradoControl.getInstance().salvar(magistrado);
				limpar();
				JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!", "Confirmação de cadastro/atualização", 
						JOptionPane.INFORMATION_MESSAGE);
				
				carregarTable();

			}
		} catch (Throwable e) {
			e.printStackTrace();
			Utils.msgExcption(e.getMessage());	
		}
		
	}


	private void carregarTable() {
		// Carregar lista
		tableModel.limpar();
		List<Magistrado> lista= MagistradoControl.getInstance().findByFilter(new Magistrado());
		tableModel.addList(lista);
	}
	
	private boolean validaCampos(){
		boolean isValido= true;	
		
		if ( texnome.getText().length() == 0 || 
			texmatricula.getText().length() == 0 || 
			texnumendereco.getText().length() == 0 || 
			texcpf.getText().length() == 0 ||  
			texlogradoro.getText().length() == 0 || 
			texbairro.getText().length() == 0 || 
			texcep.getText().length() == 0 || 
			texcidade.getText().length() == 0 || 
			texestado.getText().length() == 0 || 
			texsenha.getText().length() == 0 
			) {
			isValido= false;
		}
		
		return isValido;
	}
}
