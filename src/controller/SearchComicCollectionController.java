package controller;

import java.util.ResourceBundle;
import javax.swing.JOptionPane;

/**
 * Controlador para la b�squeda de c�mics
 * 
 * @author Sergio Fraga
 */
public class SearchComicCollectionController {
	
	/**
	 * Muestra un mensaje de aviso cuando los campos est�n vac�os antes de una operaci�n
	 * 
	 * @param language idioma en el que se muestra el aviso
	 */
	public void showWarningMessage(String language) {
		ResourceBundle rb = ResourceBundle.getBundle(language);
		JOptionPane.showMessageDialog(null, rb.getString("msgSearchComic"), "Aviso", JOptionPane.WARNING_MESSAGE);
	}
}
