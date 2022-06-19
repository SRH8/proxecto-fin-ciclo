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

import controller.ImagePicker;
import model.entities.ComicCollection;
import model.entities.ComicStatus;

import java.awt.event.ActionListener;
import java.net.Socket;
import java.util.ArrayList;
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

	/**
	 * Crea el diálogo
	 */
	public InsertComic(ArrayList<ComicCollection> collectionList, ArrayList<ComicStatus> statusList) {
		System.out.println(collectionList.get(0).toString());
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
		
		JComboBox cmbCollection = new JComboBox();
		cmbCollection.setBounds(387, 198, 108, 21);
		centralPanel.add(cmbCollection);
		cmbCollection.setModel(new DefaultComboBoxModel<ComicCollection>(collectionList.toArray(new ComicCollection[0])));
		
		JLabel lblStatus = new JLabel("Estado");
		lblStatus.setBounds(261, 253, 72, 13);
		centralPanel.add(lblStatus);
		
		JComboBox cmbStatus = new JComboBox();
		cmbStatus.setBounds(387, 249, 108, 21);
		centralPanel.add(cmbStatus);
		cmbStatus.setModel(new DefaultComboBoxModel<ComicStatus>(statusList.toArray(new ComicStatus[0])));
		
		lblDescription = new JLabel("Descripci\u00F3n");
		lblDescription.setBounds(261, 294, 72, 13);
		centralPanel.add(lblDescription);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(387, 294, 307, 131);
		centralPanel.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
		
		JPanel buttonPanel = new JPanel();
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton btnInsert = new JButton("Insertar");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
