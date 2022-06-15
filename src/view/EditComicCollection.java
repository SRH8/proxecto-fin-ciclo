package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.ImagePicker;
import controller.InsertComicCollectionController;
import model.entities.ComicCollection;
import thread.ClientThread;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

/**
 * Pantalla para editar una colección
 * 
 * @author Sergio Fraga
 */
public class EditComicCollection extends JDialog {
	private JTextField txtName;
	private String imgPath;
	private JLabel lblShowImage;
	private JLabel lblImage;
	private JLabel lblName;
	private JLabel lblDescription;
	private JButton btnEdit;
	private String language = MainWindow.language;
	private JScrollPane scrollPane;
	private Socket clientSocket;
	private JTextArea textArea;

	/**
	 * Crea el diálogo
	 */
	public EditComicCollection(ComicCollection comicCollection) {
		setTitle("Editar colecci\u00F3n");
		setBounds(100, 100, 677, 349);
		
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
			}
		});
		btnSelectImage.setBounds(77, 41, 33, 21);
		centralPanel.add(btnSelectImage);
		
		lblName = new JLabel("Nombre");
		lblName.setBounds(237, 89, 72, 13);
		centralPanel.add(lblName);
		
		lblDescription = new JLabel("Descripci\u00F3n");
		lblDescription.setBounds(237, 156, 72, 13);
		centralPanel.add(lblDescription);
		
		txtName = new JTextField();
		txtName.setBounds(351, 86, 108, 19);
		centralPanel.add(txtName);
		txtName.setColumns(10);
		txtName.setText(comicCollection.getName());
		
		lblShowImage = new JLabel("");
		lblShowImage.setBounds(24, 84, 126, 158);
		centralPanel.add(lblShowImage);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(350, 156, 282, 115);
		centralPanel.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
		textArea.setText(comicCollection.getDescription());
		
		JPanel buttonPanel = new JPanel();
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		btnEdit = new JButton("Editar");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtName.getText().isBlank() && !textArea.getText().isBlank()) {
					
					try {
						clientSocket = new Socket("localhost", 8080);
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
					comicCollection.setName(txtName.getText().trim());
					comicCollection.setDescription(textArea.getText());
					comicCollection.setImage(fileContent);
					
					Object[] command = {"editarColeccion", comicCollection};
					
					ClientThread clientThread = new ClientThread(clientSocket, command, null);
					
					clientThread.start();
				} else {
					InsertComicCollectionController inserController = new InsertComicCollectionController();
					inserController.showWarningMessage(language);
				}
			}
			
		});
		buttonPanel.add(btnEdit);
		
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
	  
	  lblImage.setText(rb.getString("lblImage"));
	  lblName.setText(rb.getString("lblName"));
	  lblDescription.setText(rb.getString("lblDescription"));
	}
}

