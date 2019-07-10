package br.ufrpe.spjc.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.ufrpe.spjc.gui.taciano.FrmRelatorioResumido;
import br.ufrpe.spjc.gui.taciano.FrmTipoIIDocumento;
import br.ufrpe.spjc.gui.taciano.FrmTipoIIIProcesso;
import br.ufrpe.spjc.gui.taciano.FrmTipoIRepresentante;

public class FrmPrincipal {

	JFrame frmSpjcSistema;
	private JDesktopPane desktop;
	
	/**
	 * Create the application.
	 */
	public FrmPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSpjcSistema = new JFrame();
		frmSpjcSistema.setResizable(true);
		frmSpjcSistema.setBounds(100, 100, 828, 490);
		frmSpjcSistema.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSpjcSistema.setTitle("SPJC - Sistema Judicial de Juizados Cíveis");
		frmSpjcSistema.getContentPane().setLayout(new BoxLayout(frmSpjcSistema.getContentPane(), BoxLayout.X_AXIS));
		
		desktop = new JDesktopPane();
		desktop.setBackground(Color.WHITE);
		frmSpjcSistema.setContentPane(desktop);

		//Make dragging a little faster but perhaps uglier.
	    desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
	    desktop.setLayout(null);
   
	    JMenuBar menuBar = new JMenuBar();
	    menuBar.setBackground(Color.WHITE);
	    menuBar.setBounds(0, 0, 254, 21);
	    menuBar.setSize(800, 35);
	    desktop.add(menuBar);
	    
	    JMenu mnCrudTipoI = new JMenu("CRUD Tipo I");
	    menuBar.add(mnCrudTipoI);
	    
	    JMenuItem mntmRepresentante = new JMenuItem("Representante");
	    mntmRepresentante.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		FrmTipoIRepresentante window=new FrmTipoIRepresentante();
	    		window.setVisible(true);
	    	}
	    });
	    mnCrudTipoI.add(mntmRepresentante);
	    
	    JMenuItem mntmParte = new JMenuItem("Parte");
	    mnCrudTipoI.add(mntmParte);
	    
	    JMenuItem mntmMagistrado = new JMenuItem("Magistrado");
	    mnCrudTipoI.add(mntmMagistrado);
	    
	    JMenu mnCrudTipoII = new JMenu("CRUD Tipo II");
	    menuBar.add(mnCrudTipoII);
	    
	    JMenuItem mnDocumento = new JMenuItem("Documento");
	    mnDocumento.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		FrmTipoIIDocumento window=new FrmTipoIIDocumento();
	    		window.setVisible(true);
	    	}
	    });
	    mnCrudTipoII.add(mnDocumento);
	    
	    JMenuItem mntmComunicao = new JMenuItem("Comunicação");
	    mnCrudTipoII.add(mntmComunicao);
	    
	    JMenuItem mntmProcesso = new JMenuItem("Processo");
	    mnCrudTipoII.add(mntmProcesso);
	    
	    JMenu mnCrudTipoIII = new JMenu("CRUD Tipo III");
	    menuBar.add(mnCrudTipoIII);
	    
	    JMenuItem mntmProcessoTipoIII = new JMenuItem("Processo");
	    mntmProcessoTipoIII.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		if ( FrmLogin.TIPO_USUARIO.equals(FrmLogin.SOU_SERVIDOR)) {
	    			FrmTipoIIIProcesso window=new FrmTipoIIIProcesso();
	    			window.setVisible(true);
	    			
	    		} else {
	    			JOptionPane.showMessageDialog(null, "Para acessar essa opção vc deve logar como servidor.");
	    		}
	    		
	    	}
	    });
	    mnCrudTipoIII.add(mntmProcessoTipoIII);
	    
	    JMenuItem mntmDocumento = new JMenuItem("Documento");
	    mnCrudTipoIII.add(mntmDocumento);
	    
	    JMenuItem mntmJuizado = new JMenuItem("Juizado");
	    mnCrudTipoIII.add(mntmJuizado);
	    
	    JMenu mnRelatorio = new JMenu("Relatorio");
	    menuBar.add(mnRelatorio);
	    
	    JMenuItem mntmRelatrioResumido = new JMenuItem("Relatório Resumido Processo");
	    mntmRelatrioResumido.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		FrmRelatorioResumido window= new FrmRelatorioResumido();
	    		window.setVisible(true);
	    	}
	    });
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
		
	}
	
	public static String completeToLeft(String value, char c, int size) {
		String result = value;
		while (result.length() < size) {
			result = c + result;
		}
		return result;
	}
}
