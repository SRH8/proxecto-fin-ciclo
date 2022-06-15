package controller;

import java.util.ResourceBundle;

import javax.swing.JOptionPane;

/**
 * Controlador para la pantalla de colecciones
 * 
 * @author Sergio Fraga
 */
public class ComicCollectionController {
	
	/**
	 * Muestra un mensaje de confirmaci�n
	 * 
	 * @param language idioma
	 * @return opci�n escogida
	 */
	public int showConfirmationMessage(String language) {
		ResourceBundle rb = ResourceBundle.getBundle(language);
		
		String[] options = {rb.getString("msgYes"), rb.getString("msgNo")};
		
		int n = JOptionPane.showOptionDialog(null,
				rb.getString("msgDelete"), "Eliminar colecci�n", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, options,  options[0]);
		
		return n;
	}
}
