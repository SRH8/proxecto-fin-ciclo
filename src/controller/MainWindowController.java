package controller;

import java.util.ResourceBundle;

import javax.swing.JOptionPane;

/**
 * Controlador para la pantalla principal
 * 
 * @author Sergio Fraga
 */
public class MainWindowController {

	/**
	 * Muestra un mensaje de confirmación al salir de la aplicación 
	 * 
	 * @param language Idioma en el que se va a mostrar el mensaje
	 */
	public void exitApp(String language) {
		ResourceBundle rb = ResourceBundle.getBundle(language);
		
		String[] options = {rb.getString("msgYes"), rb.getString("msgNo")};
		
		int n = JOptionPane.showOptionDialog(null,
				rb.getString("msgExit"), rb.getString("mntmExit"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, options,  options[0]);
		
			if(n == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	
}
