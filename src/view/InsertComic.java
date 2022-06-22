package view;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Image;
import javax.swing.JLabel;
import javax.swing.JTextField;
import controller.ImagePicker;
import controller.InsertComicCollectionController;
import thread.ClientThread;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;

/**
 * Pantalla para insertar un cómic
 * 
 * @author Sergio Fraga
 */
public class InsertComic extends JDialog {
	private JTextField txtTitle;
	private JTextField txtReleaseDate;
	private String imgPath;
	private JLabel lblShowImage;
	private String language = MainWindow.language;
	private JLabel lblReleaseDate;
	private JLabel lblDescription;
	private JLabel lblImage;
	private JTextField txtCollection;
	private JTextField txtStatus;
	private JTextArea textArea;
	private Socket clientSocket;

	/**
	 * Crea el diálogo
	 */
	public InsertComic() {
		setTitle("Insertar c\u00F3mic");
		setBounds(100, 100, 736, 510);
		
		JPanel centralPanel = new JPanel();
		getContentPane().add(centralPanel, BorderLayout.CENTER);
		centralPanel.setLayout(null);
		
		lblImage = new JLabel("Imagen");
		lblImage.setBounds(24, 45, 45, 13);
		centralPanel.add(lblImage);
		
		JButton btnSelectImage = new JButton("...");
		btnSelectImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImagePicker imagePicker = new ImagePicker();
				imgPath = imagePicker.loadImage();
				Image img  = new ImageIcon(imgPath).getImage();
				Image newimg = img.getScaledInstance(182, 220,  java.awt.Image.SCALE_SMOOTH);
				ImageIcon imageIcon = new ImageIcon(newimg); 
				lblShowImage.setIcon(imageIcon);
				System.out.println(lblShowImage.getText()); 
			}
		});
		btnSelectImage.setBounds(77, 41, 33, 21);
		centralPanel.add(btnSelectImage);
		
		JLabel lblTitle = new JLabel("T\u00EDtulo");
		lblTitle.setBounds(261, 45, 45, 13);
		centralPanel.add(lblTitle);
		
		lblReleaseDate = new JLabel("Fecha de estreno");
		lblReleaseDate.setBounds(261, 100, 116, 13);
		centralPanel.add(lblReleaseDate);
		
		txtTitle = new JTextField();
		txtTitle.setBounds(387, 42, 108, 19);
		centralPanel.add(txtTitle);
		txtTitle.setColumns(10);
		
		txtReleaseDate = new JTextField();
		txtReleaseDate.setBounds(387, 97, 108, 19);
		centralPanel.add(txtReleaseDate);
		txtReleaseDate.setColumns(10);
		
		lblShowImage = new JLabel("");
		lblShowImage.setBounds(24, 84, 126, 158);
		centralPanel.add(lblShowImage);
		
		JLabel lblCoverType = new JLabel("Tapa");
		lblCoverType.setBounds(261, 153, 72, 13);
		centralPanel.add(lblCoverType);
		
		JComboBox cmbCoverType = new JComboBox();
		cmbCoverType.setModel(new DefaultComboBoxModel(new String[] {"Dura", "Blanda"}));
		cmbCoverType.setBounds(387, 149, 108, 21);
		centralPanel.add(cmbCoverType);
		
		JLabel lblCollection = new JLabel("Colecci\u00F3n");
		lblCollection.setBounds(261, 202, 95, 13);
		centralPanel.add(lblCollection);
		
		JLabel lblStatus = new JLabel("Estado");
		lblStatus.setBounds(261, 253, 72, 13);
		centralPanel.add(lblStatus);
		
		lblDescription = new JLabel("Descripci\u00F3n");
		lblDescription.setBounds(261, 294, 72, 13);
		centralPanel.add(lblDescription);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(387, 294, 307, 131);
		centralPanel.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
		
		txtCollection = new JTextField();
		txtCollection.setBounds(387, 199, 108, 19);
		centralPanel.add(txtCollection);
		txtCollection.setColumns(10);
		
		txtStatus = new JTextField();
		txtStatus.setBounds(387, 250, 108, 19);
		centralPanel.add(txtStatus);
		txtStatus.setColumns(10);
		
		JPanel buttonPanel = new JPanel();
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton btnInsert = new JButton("Insertar");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtTitle.getText().isBlank() || txtReleaseDate.getText().isBlank() || txtCollection.getText().isBlank() || txtStatus.getText().isBlank() || textArea.getText().isBlank()) {
					InsertComicCollectionController insertComicCollectionController = new InsertComicCollectionController();
					insertComicCollectionController.showWarningMessage(language);
				}
				
				try {
						clientSocket = new Socket("192.168.56.101", 8080);
					} catch (UnknownHostException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				
				byte[] fileContent = null;
				
				if(imgPath != null) {
					File file = new File(imgPath);
					try {
					fileContent = Files.readAllBytes(file.toPath());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				String titulo = txtTitle.getText().trim();
				String descripcion= textArea.getText().trim();
				String releaseDate = txtReleaseDate.getText().trim();
				String tapa = (String) cmbCoverType.getSelectedItem();
				String coleccion = txtCollection.getText().trim();
				String estado = txtStatus.getText().trim();
				
				Object[] command = {"insertarComic", titulo, descripcion, releaseDate, tapa, fileContent, coleccion, estado};
				
				ClientThread clientThread = new ClientThread(clientSocket, command, null);
			
				clientThread.start();
					
			}
		});
		buttonPanel.add(btnInsert);
		
		JButton btnCancel = new JButton("Cancelar");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonPanel.add(btnCancel);

		translate(language);
	}
	

	/**
	 * Traduce la pantalla al idioma especificado
	 * 
	 * @param language idioma al que va a ser traducida la pantalla
	 */
	private void translate(String language) {
	  ResourceBundle rb = ResourceBundle.getBundle(language);
	  this.setTitle(rb.getString("insertComicTitle"));
	  lblDescription.setText(rb.getString("lblDescription"));
	  lblReleaseDate.setText(rb.getString("lblReleaseDate"));
	  lblImage.setText(rb.getString("lblImage"));
	  
	}
}
