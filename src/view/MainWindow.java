package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MainWindow {

	private JFrame frmProyectoFinDe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmProyectoFinDe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmProyectoFinDe = new JFrame();
		frmProyectoFinDe.setTitle("Proyecto fin de ciclo");
		frmProyectoFinDe.setBounds(100, 100, 450, 300);
		frmProyectoFinDe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel windowPanel = new JPanel();
		frmProyectoFinDe.getContentPane().add(windowPanel, BorderLayout.CENTER);
		windowPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel bottomPanel = new JPanel();
		windowPanel.add(bottomPanel, BorderLayout.SOUTH);
		
		JButton btnConnect = new JButton("Conectar");
		bottomPanel.add(btnConnect);
		
		JMenuBar menuBar = new JMenuBar();
		frmProyectoFinDe.setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("Archivo");
		menuBar.add(fileMenu);
		
		JMenuItem mntmExit = new JMenuItem("Salir");
		fileMenu.add(mntmExit);
		
		JMenu configMenu = new JMenu("Configuraci\u00F3n");
		menuBar.add(configMenu);
		
		JMenuItem mntmLanguage = new JMenuItem("Idioma");
		configMenu.add(mntmLanguage);
		
		JMenu helpMenu = new JMenu("Ayuda");
		menuBar.add(helpMenu);
		
		JMenuItem mntmShowHelp = new JMenuItem("Ver ayuda");
		helpMenu.add(mntmShowHelp);
	}

}
