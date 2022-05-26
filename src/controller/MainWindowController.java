package controller;

import javax.swing.JOptionPane;

/**
 * Controller for main window
 * 
 * @author Sergio Fraga
 */
public class MainWindowController {

	public void exitApp() {
		String[] options = {"S�", "No"};
		int n = JOptionPane.showOptionDialog(null,
				"�Est�s seguro de que quiere salir de la aplicaci�n?", "Salir", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, options,  options[0]);
		
			if(n == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
		
	}
	
}
