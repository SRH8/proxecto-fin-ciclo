package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.MainWindowController;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;

import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Dimension;

/**
 * Pantalla principal de la aplicación
 * 
 * @author Sergio Fraga
 */
public class MainWindow {

	private JFrame frmProyectoFinDe;
	public static String language = "preferences.lang_es_ES";
	private JMenuItem mntmExit;
	private JMenuItem mntmGalician;
	private JMenu helpMenu;
	private JMenuItem mntmShowHelp;
	private JLabel lblWelcomeMessage;
	private JLabel lblContinueMessage;
	private JMenu fileMenu;

	/**
	 * Lanza la aplicación
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
	 * Crea la aplicación
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Inicializa los contenidos de la pantalla
	 */
	private void initialize() {
		frmProyectoFinDe = new JFrame();
		frmProyectoFinDe.setMinimumSize(new Dimension(800, 400));
		frmProyectoFinDe.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				MainWindowController mainWindowController = new MainWindowController();
				mainWindowController.exitApp(language);
			}
		});
		frmProyectoFinDe.setTitle("Proyecto fin de ciclo");
		frmProyectoFinDe.setBounds(100, 100, 664, 424);
		frmProyectoFinDe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		JPanel windowPanel = new JPanel();
		frmProyectoFinDe.getContentPane().add(windowPanel, BorderLayout.CENTER);
		windowPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel bottomPanel = new JPanel();
		windowPanel.add(bottomPanel, BorderLayout.SOUTH);
		
		JButton btnContinue = new JButton("Continuar");
		bottomPanel.add(btnContinue);
		
		JPanel centralPanel = new JPanel();
		FlowLayout fl_centralPanel = (FlowLayout) centralPanel.getLayout();
		fl_centralPanel.setVgap(100);
		windowPanel.add(centralPanel, BorderLayout.CENTER);
		
		lblWelcomeMessage = new JLabel("\u00A1Bienvenido a la librer\u00EDa!");
		lblWelcomeMessage.setFont(new Font("Tahoma", Font.PLAIN, 18));
		centralPanel.add(lblWelcomeMessage);
		
		lblContinueMessage = new JLabel("Para ver el cat\u00E1logo de c\u00F3mics pulsa en continuar");
		lblContinueMessage.setFont(new Font("Tahoma", Font.PLAIN, 18));
		centralPanel.add(lblContinueMessage);
		
		JMenuBar menuBar = new JMenuBar();
		frmProyectoFinDe.setJMenuBar(menuBar);
		
		fileMenu = new JMenu("Archivo");
		menuBar.add(fileMenu);
		
		mntmExit = new JMenuItem("Salir");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindowController mainWindowController = new MainWindowController();
				mainWindowController.exitApp(language);
			}
		});
		fileMenu.add(mntmExit);
		
		JMenu configMenu = new JMenu("Configuraci\u00F3n");
		menuBar.add(configMenu);
		
		JMenu mnLanguage = new JMenu("Idioma");
		configMenu.add(mnLanguage);
		
		JMenuItem mntmSpanish = new JMenuItem("Espa\u00F1ol");
		mntmSpanish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				language = "preferences.lang_es_ES";
				translate(language);
			}
		});
		mnLanguage.add(mntmSpanish);
		
		mntmGalician = new JMenuItem("Gallego");
		mntmGalician.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				language = "preferences.lang_gl_ES";
				translate(language);
			}
		});
		mnLanguage.add(mntmGalician);
		
		helpMenu = new JMenu("Ayuda");
		menuBar.add(helpMenu);
		
		mntmShowHelp = new JMenuItem("Ver ayuda");
		helpMenu.add(mntmShowHelp);
	}

	/**
	 * Traduce la pantalla al idioma especificado
	 * 
	 * @param Idioma al que va a ser traducida la pantalla
	 */
	private void translate(String language) {
		ResourceBundle rb = ResourceBundle.getBundle(language);
		frmProyectoFinDe.setTitle(rb.getString("mainWindowTitle"));
		mntmExit.setText(rb.getString("mntmExit"));
		fileMenu.setText(rb.getString("fileMenu"));
		helpMenu.setText(rb.getString("helpMenu"));
		mntmExit.setText(rb.getString("mntmExit"));
		mntmGalician.setText(rb.getString("mntmGalician"));
		mntmShowHelp.setText(rb.getString("mntmShowHelp"));
		lblWelcomeMessage.setText(rb.getString("lblWelcomeMessage"));
		lblContinueMessage.setText(rb.getString("lblContinueMessage"));
		
	}
	
}
