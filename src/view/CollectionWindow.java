package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import thread.ClientThread;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

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
					btnAdd.setVisible(false);
					{
						JButton btnNewButton = new JButton("New button");
						btnNewButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								try {
									clientSocket = new Socket("localhost", 8080);
								} catch (UnknownHostException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								ClientThread clientThread = new ClientThread(clientSocket, "listarColeccion", collectionTable);
								
								clientThread.start();
							}
						});
						actionPane.add(btnNewButton);
					}
					actionPane.add(btnAdd);
				}
				{
					btnEdit = new JButton("Editar");
					btnEdit.setVisible(false);
					actionPane.add(btnEdit);
				}
				{
					btnDelete = new JButton("Eliminar");
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
					JMenuItem mntmViewReports = new JMenuItem("Ver informes");
					mnReports.add(mntmViewReports);
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
	}
}
