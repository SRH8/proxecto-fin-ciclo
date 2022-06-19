package controller;

import java.util.ResourceBundle;

import javax.swing.JOptionPane;

/**
 * Controlador para la pantalla de cómics
 * 
 * @author Sergio Fraga
 */
public class ComicController {
	
	/**
	 * Muestra un mensaje de confirmación
	 * 
	 * @param language idioma
	 * @return opción escogida
	 */
	public int showConfirmationMessage(String language) {
		ResourceBundle rb = ResourceBundle.getBundle(language);
		
		String[] options = {rb.getString("msgYes"), rb.getString("msgNo")};
		
		int n = JOptionPane.showOptionDialog(null,
				rb.getString("msgDeleteComic"), "Eliminar cómic", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, options,  options[0]);
		
		return n;
	}

}
