package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.ComicCollectionController;
import model.entities.ComicCollection;
import thread.ClientThread;
import view.model.CollectionTableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;


/**
 * Pantalla para las operaciones con las colecciones de c�mics
 * 
 * @author Sergio Fraga
 */
public class CollectionWindow extends JDialog {
	private JTable collectionTable;
	private JButton btnDelete;
	private JButton btnEdit;
	private JButton btnAdd;
	private String language = MainWindow.language;
	private JButton btnClose;
	private Socket clientSocket;
	private JButton btnListCollections;
	public static ArrayList<ComicCollection> collectionList = new ArrayList<>();
	private JMenuItem mntmCollectionsReport;
	public static JRResultSetDataSource dataSource = null;
	
	/**
	 * Lanza la pantalla
	 */
	public static void main(String[] args) {
		try {
			CollectionWindow dialog = new CollectionWindow();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Crea el di�logo
	 */
	public CollectionWindow() {
		setTitle("Colecciones");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel topPanel = new JPanel();
			getContentPane().add(topPanel, BorderLayout.NORTH);
			topPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			{
				btnListCollections = new JButton("Ver colecciones");
				topPanel.add(btnListCollections);
				btnListCollections.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							clientSocket = new Socket("localhost", 8080);
						} catch (UnknownHostException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
						Object[] command = {"listarColeccion", null};
						
						ClientThread clientThread = new ClientThread(clientSocket, command, collectionTable);
						
						clientThread.start();
					}
				});
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			{
				collectionTable = new JTable();
				collectionTable.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int row = collectionTable.rowAtPoint(e.getPoint());
						int col = collectionTable.columnAtPoint(e.getPoint());
						if(row >= 0 && col >= 0) {				
							btnAdd.setVisible(true);
							btnEdit.setVisible(true);
							btnDelete.setVisible(true);
						} else {
							btnAdd.setVisible(false);
							btnEdit.setVisible(false);
							btnDelete.setVisible(false);
						}
					}
				});
				scrollPane.setViewportView(collectionTable);
			}
		}
		{
			JPanel bottomPane = new JPanel();
			getContentPane().add(bottomPane, BorderLayout.SOUTH);
			bottomPane.setLayout(new BorderLayout(0, 0));
			{
				JPanel actionPane = new JPanel();
				bottomPane.add(actionPane, BorderLayout.NORTH);
				{
					btnAdd = new JButton("A\u00F1adir");
					btnAdd.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							InsertComicCollection insertComicCollection = new InsertComicCollection();
							insertComicCollection.setVisible(true);
						}
					});
					btnAdd.setVisible(false);
					actionPane.add(btnAdd);
				}
				{
					btnEdit = new JButton("Editar");
					btnEdit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							int row = collectionTable.getSelectedRow();
							ComicCollection comicCollection = collectionList.get(row);
							
							EditComicCollection editComicCollection = new EditComicCollection(comicCollection);
							editComicCollection.setVisible(true);
						}
					});
					btnEdit.setVisible(false);
					actionPane.add(btnEdit);
				}
				{
					btnDelete = new JButton("Eliminar");
					btnDelete.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							ComicCollectionController collectionController = new ComicCollectionController();
							
							int confirmation = collectionController.showConfirmationMessage(language);
							
							if(confirmation == 0) {
								int row = collectionTable.getSelectedRow();
								
								try {
									clientSocket = new Socket("localhost", 8080);
								} catch (UnknownHostException e1) {
								 	e1.printStackTrace();
								} catch (IOException e1) {
									e1.printStackTrace();
								}
													
								ComicCollection comicCollection = collectionList.get(row);		
																
								Object[] command = {"eliminarColeccion", comicCollection};
													
								ClientThread clientThread = new ClientThread(clientSocket, command, collectionTable);
													
								clientThread.start();
							}				
						}
					});
					btnDelete.setVisible(false);
					actionPane.add(btnDelete);
				}
			}
			{
				JPanel cancelPane = new JPanel();
				bottomPane.add(cancelPane, BorderLayout.SOUTH);
				cancelPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
				{
					btnClose = new JButton("Cerrar");
					btnClose.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							dispose();
						}
					});
					cancelPane.add(btnClose);
				}
			}
		}
		{
			JMenuBar menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			{
				JMenu mnReports = new JMenu("Informes");
				menuBar.add(mnReports);
				{
					mntmCollectionsReport = new JMenuItem("Colecciones");
					mntmCollectionsReport.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							/*
							try {
								clientSocket = new Socket("localhost", 8080);
							} catch (UnknownHostException e1) {
							 	e1.printStackTrace();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
																						
							Object[] command = {"informeColecciones", null};
												
							ClientThread clientThread = new ClientThread(clientSocket, command, collectionTable);
												
							clientThread.start();
							
							String reportPath = "./src/reports/CollectionReport.jrxml";
							
							try {
								JasperReport report = JasperCompileManager.compileReport(reportPath);
								JasperPrint visor = JasperFillManager.fillReport(report, null, dataSource);
								JasperViewer.viewReport(visor, false);
							} catch (JRException e1) {
								
								e1.printStackTrace();
							}*/
						}
					});
					mnReports.add(mntmCollectionsReport);
				}
			}
		}
		
		traducir(language);
		
	}
	
	/**
	 * Traduce la pantalla al idioma especificado
	 * 
	 * @param language idioma al que va a ser traducida la pantalla
	 */
	private void traducir(String language) {
		ResourceBundle rb = ResourceBundle.getBundle(language);
		this.setTitle(rb.getString("collectionTitle"));
		btnClose.setText(rb.getString("btnClose"));	
		btnListCollections.setText(rb.getString("btnListCollections"));
		mntmCollectionsReport.setText(rb.getString("collectionTitle"));
	}
}
