package br.ufrpe.spjc.gui;

import br.ufrpe.spjc.negocio.controlador.ControladorJuizado;

public class Main {
	public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            //java.util.logging.Logger.getLogger(FrmOpcao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
           // java.util.logging.Logger.getLogger(FrmOpcao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            //java.util.logging.Logger.getLogger(FrmOpcao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            //java.util.logging.Logger.getLogger(FrmOpcao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            try {
            	System.out.println("Main Ini");
            	ControladorJuizado.getInstance().inserir();
            	System.out.println("Main Fim");
                //FrmOpcao app= new FrmOpcao();
                //app.setVisible(true);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }); 
    }
}
