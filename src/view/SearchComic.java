package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import controller.SearchComicCollectionController;
import model.entities.Comic;
import thread.ClientThread;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * Pantalla para las operaciones con los cómics
 * 
 * @author Sergio Fraga
 */
public class SearchComic extends JDialog {
	private JTable comicTable;
	private Socket clientSocket;
	private String language = MainWindow.language;
	private JButton btnClose;
	public static ArrayList<Comic> comicList = new ArrayList<>();
	private JTextField txtComicName;
	private JTextField txtCollectionName;

	/**
	 * Lanza la pantalla
	 */
	public static void main(String[] args) {
		try {
			SearchComic dialog = new SearchComic();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Crea el diálogo
	 */
	public SearchComic() {
		setTitle("Buscar c\u00F3mic");
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
						if(!txtComicName.getText().isBlank() && txtCollectionName.getText().isBlank()) {
							try {
								clientSocket = new Socket("192.168.56.101", 8080);
							} catch (UnknownHostException e1) {
								e1.printStackTrace();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							
							Object[] command = {"buscarComicPorNombre", txtComicName.getText().trim()};
							
							ClientThread clientThread = new ClientThread(clientSocket, command, comicTable);
							
							clientThread.start();
							
						} else if (txtComicName.getText().isBlank() && !txtCollectionName.getText().isBlank()) {
							try {
								clientSocket = new Socket("192.168.56.101", 8080);
							} catch (UnknownHostException e1) {
								e1.printStackTrace();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							
							Object[] command = {"buscarComicsPorColeccion", txtCollectionName.getText().trim()};
							
							ClientThread clientThread = new ClientThread(clientSocket, command, comicTable);
							
							clientThread.start();
							
						} else {
							SearchComicCollectionController searchComicCollectionController = new SearchComicCollectionController();
							searchComicCollectionController.showWarningMessage(language);
						}
					}
				});
				{
					JPanel labelPane = new JPanel();
					topPanel.add(labelPane);
					labelPane.setLayout(new BoxLayout(labelPane, BoxLayout.Y_AXIS));
					{
						JLabel lblComicName = new JLabel("C\u00F3mic");
						lblComicName.setBorder(new EmptyBorder(0, 0, 2, 0));
						labelPane.add(lblComicName);
					}
					{
						JLabel lblCollectionName = new JLabel("Colecci\u00F3n");
						lblCollectionName.setBorder(new EmptyBorder(1, 0, 0, 0));
						labelPane.add(lblCollectionName);
					}
				}
				{
					JPanel inputPane = new JPanel();
					topPanel.add(inputPane);
					inputPane.setLayout(new BoxLayout(inputPane, BoxLayout.Y_AXIS));
					{
						txtComicName = new JTextField();
						inputPane.add(txtComicName);
						txtComicName.setColumns(10);
					}
					{
						txtCollectionName = new JTextField();
						inputPane.add(txtCollectionName);
						txtCollectionName.setColumns(10);
					}
				}
				topPanel.add(btnSearch);
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			{
				comicTable = new JTable();
				scrollPane.setViewportView(comicTable);
			}
		}
		{
			JPanel bottomPane = new JPanel();
			getContentPane().add(bottomPane, BorderLayout.SOUTH);
			bottomPane.setLayout(new BorderLayout(0, 0));
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
