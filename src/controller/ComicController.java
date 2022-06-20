package controller;

import java.sql.Connection;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

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
	
	/**
	 * Muestra un informe de cómics
	 * 
	 * @param language
	 */
	public void showComicReport(String language) {
		ResourceBundle rb = ResourceBundle.getBundle(language);
	
		try (Connection connection = Pool.getConection()){
			String reportPath = "./src/reports/Comic.jrxml";
			
			JasperReport report = JasperCompileManager.compileReport(reportPath);
			JasperPrint visor = JasperFillManager.fillReport(report, null, connection);
			JasperViewer.viewReport(visor, false);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, rb.getString("errorMsgCollectionReport"), "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

}
