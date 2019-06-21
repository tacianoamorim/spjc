package br.ufrpe.spjc.gui;

import java.awt.Color;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import br.ufrpe.spjc.util.Constantes;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class FrmPrincipal {

	JFrame frmSpjcSistema;
	private JDesktopPane desktop;
	
	public static String acaoInicializacao;
	public static String perfilLogado;
	
	private DefaultMutableTreeNode root;


	/**
	 * Create the application.
	 */
	public FrmPrincipal() {
		initialize();
		
		carregar();
	}

	/**
	 * Carregar a permissoes de cada perfil
	 */
	private void carregar() {
		if (Constantes.PERFIL_ALUNO.equalsIgnoreCase(FrmPrincipal.perfilLogado)) {
			
		} else if (Constantes.PERFIL_PROFESSOR.equalsIgnoreCase(FrmPrincipal.perfilLogado)) {

		} else {
			tbBtnProfessor.setEnabled(true);
			tbBtnAluno.setEnabled(true);
    		tbBtnDisciplina.setEnabled(true);
    		tbBtnTurma.setEnabled(true);
			tbBtnRendimentoEscolar.setEnabled(true);				
		}
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSpjcSistema = new JFrame();
		frmSpjcSistema.setResizable(false);
		frmSpjcSistema.setBounds(100, 100, 828, 490);
		frmSpjcSistema.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSpjcSistema.setTitle("SPJC - Sistema Judicial de Juizados Cíveis");
		frmSpjcSistema.getContentPane().setLayout(new BoxLayout(frmSpjcSistema.getContentPane(), BoxLayout.X_AXIS));
		
		desktop = new JDesktopPane();
		frmSpjcSistema.setContentPane(desktop);

		//Make dragging a little faster but perhaps uglier.
	    desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
	    desktop.setLayout(null);
	    
	    JMenuBar menuBar = new JMenuBar();
	    menuBar.setBounds(0, 0, 254, 21);
	    desktop.add(menuBar);
	    
	    JMenu mnCrudTipoI = new JMenu("CRUD Tipo I");
	    menuBar.add(mnCrudTipoI);
	    
	    JMenuItem mntmRepresentante = new JMenuItem("Representante");
	    mnCrudTipoI.add(mntmRepresentante);
	    
	    JMenuItem mntmParte = new JMenuItem("Parte");
	    mnCrudTipoI.add(mntmParte);
	    
	    JMenuItem mntmMagistrado = new JMenuItem("Magistrado");
	    mnCrudTipoI.add(mntmMagistrado);
	    
	    JMenu mnCrudTipoII = new JMenu("CRUD Tipo II");
	    menuBar.add(mnCrudTipoII);
	    
	    JMenu mnDocumento = new JMenu("Documento");
	    mnCrudTipoII.add(mnDocumento);
	    
	    JMenuItem mntmComunicao = new JMenuItem("Comunicação");
	    mnCrudTipoII.add(mntmComunicao);
	    
	    JMenuItem mntmProcesso = new JMenuItem("Processo");
	    mnCrudTipoII.add(mntmProcesso);
	    
	    JMenu mnCrudTipoIII = new JMenu("CRUD Tipo III");
	    menuBar.add(mnCrudTipoIII);
	    
	    JMenuItem mntmProcesso_1 = new JMenuItem("Processo");
	    mnCrudTipoIII.add(mntmProcesso_1);
	    
	    JMenuItem mntmDocumento = new JMenuItem("Documento");
	    mnCrudTipoIII.add(mntmDocumento);
	    
	    JMenuItem mntmJuizado = new JMenuItem("Juizado");
	    mnCrudTipoIII.add(mntmJuizado);
	    
	    JMenu mnRelatorio = new JMenu("Relatorio");
	    menuBar.add(mnRelatorio);
	    
	    JMenuItem mntmRelatrioResumido = new JMenuItem("Relatório Resumido Processo");
	    mnRelatorio.add(mntmRelatrioResumido);
	    
	    JMenuItem mntmRelatrioDetalhadoComunicacao = new JMenuItem("Relatório Detalhado Comunicacao");
	    mnRelatorio.add(mntmRelatrioDetalhadoComunicacao);
	    
	    JMenuItem mntmRelatrioDetalhadoConciliacao = new JMenuItem("Relatório Detalhado Conciliacao");
	    mnRelatorio.add(mntmRelatrioDetalhadoConciliacao);
	    
	    JPanel pnlCorpo = new JPanel();
	    pnlCorpo.setBounds(0, 51, 822, 410);
	    pnlCorpo.setBackground(new Color(255, 255, 255));
	    desktop.add(pnlCorpo);
	    pnlCorpo.setLayout(null);
        
	    //create the tree by passing in the root node
	    root= new DefaultMutableTreeNode("Turmas");
	    
	    carregar( root );

	}

	private void carregar(DefaultMutableTreeNode root) {
		
//		// Carregar as turmas
//		List<Turma> turmas= new ArrayList<Turma>();
//		if (Constantes.PERFIL_ALUNO.equalsIgnoreCase(FrmPrincipal.perfilLogado)) {
//			turmas= Fachada.getInstance().listarTurmas();
//			
//		} else if (Constantes.PERFIL_PROFESSOR.equalsIgnoreCase(FrmPrincipal.perfilLogado)) {
//			turmas= Fachada.getInstance().listarTurmasProfessor(professorLogado);
//		} 		
//		
//		for (Turma turma : turmas) {
//			DefaultMutableTreeNode node = new DefaultMutableTreeNode(
//					"Turma: "+ FrmPrincipal.completeToLeft(
//							turma.getDisciplina().getId() + "",'0' ,2)
//					 +" " + turma.getDisciplina().getNome());
//			root.add( node );
//			
//			// buscar os alunos da turma
//			List<RendimentoEscolar> rendEscolarres = Fachada.getInstance().listarRendimentoEscolarPorTurma( turma ); 
//			for (RendimentoEscolar rendimentoEscolar : rendEscolarres) {
//				DefaultMutableTreeNode nodeRend = new DefaultMutableTreeNode(
//						"Aluno: "+ FrmPrincipal.completeToLeft(
//								rendimentoEscolar.getAluno().getId() + "",'0' ,2)
//						 +" " + rendimentoEscolar.getAluno().getNome());
//				node.add( nodeRend );
//			}
//		}
		
		// Carregar os rendimentos
		
	}
	
	public static String completeToLeft(String value, char c, int size) {
		String result = value;
		while (result.length() < size) {
			result = c + result;
		}
		return result;
	}
}
