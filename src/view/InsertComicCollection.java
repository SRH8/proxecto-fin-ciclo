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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Pantalla para insertar una colección
 * 
 * @author Sergio Fraga
 */
public class InsertComicCollection extends JDialog {
	private JTextField txtName;
	private JTextField txtDescription;
	private JTextField txtReleaseDate;
	private String imgPath;
	private JLabel lblShowImage;
	/**
	 * Lanza la aplicación
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertComicCollection dialog = new InsertComicCollection();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Crea el diálogo
	 */
	public InsertComicCollection() {
		setTitle("Insertar colecci\u00F3n");
		setBounds(100, 100, 546, 349);
		
		JPanel centralPanel = new JPanel();
		getContentPane().add(centralPanel, BorderLayout.CENTER);
		centralPanel.setLayout(null);
		
		JLabel lblImage = new JLabel("Imagen");
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
		
		JLabel lblName = new JLabel("Nombre");
		lblName.setBounds(261, 45, 45, 13);
		centralPanel.add(lblName);
		
		JLabel lblDescription = new JLabel("Descripci\u00F3n");
		lblDescription.setBounds(261, 92, 72, 13);
		centralPanel.add(lblDescription);
		
		JLabel lblReleaseDate = new JLabel("Fecha de estreno");
		lblReleaseDate.setBounds(261, 138, 116, 13);
		centralPanel.add(lblReleaseDate);
		
		txtName = new JTextField();
		txtName.setBounds(387, 42, 108, 19);
		centralPanel.add(txtName);
		txtName.setColumns(10);
		
		txtDescription = new JTextField();
		txtDescription.setBounds(387, 89, 108, 19);
		centralPanel.add(txtDescription);
		txtDescription.setColumns(10);
		
		txtReleaseDate = new JTextField();
		txtReleaseDate.setBounds(387, 135, 108, 19);
		centralPanel.add(txtReleaseDate);
		txtReleaseDate.setColumns(10);
		
		lblShowImage = new JLabel("");
		lblShowImage.setBounds(24, 84, 126, 158);
		centralPanel.add(lblShowImage);
		
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

	}
}
