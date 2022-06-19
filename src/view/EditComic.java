package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.ComicCollectionController;
import controller.ImagePicker;
import controller.InsertComicCollectionController;
import model.entities.Comic;
import model.entities.ComicCollection;
import model.entities.ComicStatus;
import thread.ClientThread;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;

/**
 * Pantalla para editar un cómic
 * 
 * @author Sergio Fraga
 */
public class EditComic extends JDialog {
	private String imgPath;
	private JLabel lblShowImage;
	private String language = MainWindow.language;
	private JLabel lblDescription;
	private JLabel lblImage;
	private JTextField txtStatus;
	private JTextArea textArea;
	private Socket clientSocket;

	/**
	 * Crea el diálogo
	 */
	public EditComic(Comic comic) {
		setTitle("Editar c\u00F3mic");
		setBounds(100, 100, 736, 469);
		
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
		
		lblShowImage = new JLabel("");
		lblShowImage.setBounds(24, 84, 126, 158);
		centralPanel.add(lblShowImage);
		
		JLabel lblCoverType = new JLabel("Tapa");
		lblCoverType.setBounds(261, 45, 72, 13);
		centralPanel.add(lblCoverType);
		
		JComboBox cmbCoverType = new JComboBox();
		cmbCoverType.setModel(new DefaultComboBoxModel(new String[] {"Dura", "Blanda"}));
		cmbCoverType.setBounds(387, 41, 108, 21);
		centralPanel.add(cmbCoverType);
		
		JLabel lblStatus = new JLabel("Estado");
		lblStatus.setBounds(261, 95, 72, 13);
		centralPanel.add(lblStatus);
		
		lblDescription = new JLabel("Descripci\u00F3n");
		lblDescription.setBounds(261, 156, 72, 13);
		centralPanel.add(lblDescription);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(387, 150, 307, 131);
		centralPanel.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
		textArea.setText(comic.getDescription());
		
		txtStatus = new JTextField();
		txtStatus.setBounds(387, 92, 108, 19);
		centralPanel.add(txtStatus);
		txtStatus.setColumns(10);
		txtStatus.setText(comic.getStatus().getDescription());
		
		JPanel buttonPanel = new JPanel();
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton btnCancel = new JButton("Cancelar");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JButton btnEditComic = new JButton("Editar");
		btnEditComic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textArea.getText().isBlank() || txtStatus.getText().isBlank()) {
					InsertComicCollectionController inserController = new InsertComicCollectionController();
					inserController.showWarningMessage(language);
				} else {
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
					
					String descripcion= textArea.getText().trim();
					String estado = txtStatus.getText().trim();
					String tapa = (String) cmbCoverType.getSelectedItem();
					
					Object[] command = {"editarComic", comic, descripcion, tapa, fileContent, estado};
					
					ClientThread clientThread = new ClientThread(clientSocket, command, null);
				
					clientThread.start();
				}
			}
		});
		buttonPanel.add(btnEditComic);
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
	  lblDescription.setText(rb.getString("lblDescription"));
	  lblImage.setText(rb.getString("lblImage"));
	  
	}
}
