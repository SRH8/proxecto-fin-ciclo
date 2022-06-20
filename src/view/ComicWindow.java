package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import controller.ComicController;
import model.entities.Comic;
import model.entities.ComicCollection;
import model.entities.ComicStatus;
import thread.ClientThread;
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

/**
 * Pantalla para las operaciones con los cómics
 * 
 * @author Sergio Fraga
 */
public class ComicWindow extends JDialog {
	private JTable comicTable;
	private JButton btnDelete;
	private JButton btnEdit;
	private JButton btnAdd;
	private Socket clientSocket;
	private String language = MainWindow.language;
	private JButton btnClose;
	public static ArrayList<Comic> comicList = new ArrayList<>();

	/**
	 * Lanza la pantalla
	 */
	public static void main(String[] args) {
		try {
			ComicWindow dialog = new ComicWindow();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Crea el diálogo
	 */
	public ComicWindow() {
		setTitle("C\u00F3mics");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel topPanel = new JPanel();
			getContentPane().add(topPanel, BorderLayout.NORTH);
			topPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			{
				JButton btnSearch = new JButton("Buscar");
				btnSearch.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						SearchComic searchComic = new SearchComic();
						searchComic.setVisible(true);
					}
				});
				topPanel.add(btnSearch);
			}
			{
				JButton btnListComics = new JButton("Ver comics");
				btnListComics.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							clientSocket = new Socket("localhost", 8080);
						} catch (UnknownHostException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
						Object[] command = {"listarComics", null};
						
						ClientThread clientThread = new ClientThread(clientSocket, command, comicTable);
						
						clientThread.start();
					}
				});
				topPanel.add(btnListComics);
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			{
				comicTable = new JTable();
				comicTable.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int row = comicTable.rowAtPoint(e.getPoint());
						int col = comicTable.columnAtPoint(e.getPoint());
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
				scrollPane.setViewportView(comicTable);
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
							InsertComic insertComic = new InsertComic();
							insertComic.setVisible(true);
						}
					});
					btnAdd.setVisible(false);
					actionPane.add(btnAdd);
				}
				{
					btnEdit = new JButton("Editar");
					btnEdit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							int row = comicTable.getSelectedRow();
							Comic comic = comicList.get(row);
							
							EditComic editComic = new EditComic(comic);
							editComic.setVisible(true);
						}
					});
					btnEdit.setVisible(false);
					actionPane.add(btnEdit);
				}
				{
					btnDelete = new JButton("Eliminar");
					btnDelete.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							ComicController comicController = new ComicController();
							
							int confirmation = comicController.showConfirmationMessage(language);
							
							if(confirmation == 0) {
								try {
									clientSocket = new Socket("localhost", 8080);
								} catch (UnknownHostException e1) {
								 	e1.printStackTrace();
								} catch (IOException e1) {
									e1.printStackTrace();
								}
								
								Comic comic = comicList.get(comicTable.getSelectedRow());
								
								Object[] command = {"eliminarComic", comic};
								
								ClientThread clientThread = new ClientThread(clientSocket, command, comicTable);
								
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
					JMenuItem mntmComicReport = new JMenuItem("C\u00F3mics");
					mntmComicReport.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							ComicController comicController = new ComicController();
							comicController.showComicReport(language);
						}
					});
					mnReports.add(mntmComicReport);
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
	private void traducir (String language) {
		ResourceBundle rb = ResourceBundle.getBundle(language);
		btnClose.setText(rb.getString("btnClose"));	
	}
}
