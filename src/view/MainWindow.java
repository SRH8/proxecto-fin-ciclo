package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import controller.MainWindowController;
import controller.Pool;
import thread.ClientThread;

import java.awt.BorderLayout;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.help.HelpSetException;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.border.EmptyBorder;

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
	private JMenu fileMenu;
	private JLabel lblWelcomeMessage;
	private JButton btnViewCollections;
	private JButton btnExit;

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
		try {
			Pool.launchPool();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Inicializa los contenidos de la pantalla
	 */
	private void initialize() {
		MainWindowController mainWindowController = new MainWindowController();
		
		frmProyectoFinDe = new JFrame();
		frmProyectoFinDe.setMinimumSize(new Dimension(800, 400));
		frmProyectoFinDe.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
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
		bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		btnExit = new JButton("Salir");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainWindowController.exitApp(language);
			}
		});
		bottomPanel.add(btnExit);
		
		JPanel actionPanel = new JPanel();
		windowPanel.add(actionPanel, BorderLayout.CENTER);
		actionPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel tagPanel = new JPanel();
		tagPanel.setBorder(new EmptyBorder(150, 0, 0, 0));
		actionPanel.add(tagPanel, BorderLayout.CENTER);
		tagPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblWelcomeMessage = new JLabel("\u00A1Bienvenido a la librer\u00EDa! Hecha un vistazo a nuestros c\u00F3mics y colecciones.");
		lblWelcomeMessage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tagPanel.add(lblWelcomeMessage);
		
		JPanel buttonPanel = new JPanel();
		actionPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		JButton btnViewComics = new JButton("Ver c\u00F3mics");
		btnViewComics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ComicWindow comicWindow = new ComicWindow();
				comicWindow.setVisible(true);
			}
		});
		buttonPanel.add(btnViewComics);
		
		btnViewCollections = new JButton("Ver colecciones");
		btnViewCollections.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CollectionWindow collectionWindow = new CollectionWindow();
				collectionWindow.setVisible(true);
			}
		});
		buttonPanel.add(btnViewCollections);
		
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
		
		showHelp(language);
	}

	/**
	 * Traduce la pantalla al idioma especificado
	 * 
	 * @param language idioma al que va a ser traducida la pantalla
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
		btnViewCollections.setText(rb.getString("btnViewCollections"));
		btnExit.setText(rb.getString("mntmExit"));
		
	}
	
	/**
	 * Muestra la ayuda de la aplicación
	 */
	private void showHelp(String language) {
		ResourceBundle rb = ResourceBundle.getBundle(language);
		try {
			File fichero = new File("./src/help/help.hs");
			URL hsUrl = fichero.toURI().toURL();
			HelpSet helpset = new HelpSet(null,hsUrl);
			HelpBroker hb = helpset.createHelpBroker();
			
			hb.enableHelpOnButton(mntmShowHelp, "manual", helpset);
		} catch (HelpSetException | MalformedURLException e) {
			JOptionPane.showMessageDialog(null, rb.getString("showHelpErrorMessage"),rb.getString("helpMenu"), JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}
