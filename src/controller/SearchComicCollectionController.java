package controller;

import java.util.ResourceBundle;
import javax.swing.JOptionPane;

/**
 * Controlador para la búsqueda de cómics
 * 
 * @author Sergio Fraga
 */
public class SearchComicCollectionController {
	
	/**
	 * Muestra un mensaje de aviso cuando los campos están vacíos antes de una operación
	 * 
	 * @param language idioma en el que se muestra el aviso
	 */
	public void showWarningMessage(String language) {
		ResourceBundle rb = ResourceBundle.getBundle(language);
		JOptionPane.showMessageDialog(null, rb.getString("msgSearchComic"), "Aviso", JOptionPane.WARNING_MESSAGE);
	}
}
