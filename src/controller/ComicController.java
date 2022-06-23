package controller;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
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
	 * @param language idioma en el que se muestra el mensaje de error
	 */
	public void showComicReport(String language) {
		ResourceBundle rb = ResourceBundle.getBundle(language);
	
		try (Connection connection = Pool.getConection()){
			String reportPath = "./reports/ComicReport.jrxml";
			
			JasperReport report = JasperCompileManager.compileReport(reportPath);
			JasperPrint visor = JasperFillManager.fillReport(report, null, connection);
			JasperViewer.viewReport(visor, false);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, rb.getString("errorMsgCollectionReport"), "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	/**
	 * Muestra un informe de cómics por colección
	 * 
	 * @param language idioma en el que se muestra el mensaje de error
	 */
	public void showComicReportByCollection(String language, String collectionName) {
		ResourceBundle rb = ResourceBundle.getBundle(language);
		
		try (Connection connection = Pool.getConection()){
			String reportPath = "./reports/ComicsByCollection.jrxml";
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("CollectionName", collectionName);
			JasperReport report = JasperCompileManager.compileReport(reportPath);
			JasperPrint visor = JasperFillManager.fillReport(report, map, connection);
			JasperViewer.viewReport(visor, false);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, rb.getString("errorMsgCollectionReport"), "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	/**
	 * Traduce una tabla
	 * 
	 * @param language idioma
	 * @param comicTable tabla
	 */
	public void traducirTable(String language, JTable comicTable) {
		ResourceBundle rb = ResourceBundle.getBundle(language);
		
		JTableHeader th = comicTable.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		TableColumn tc = tcm.getColumn(1);
		tc.setHeaderValue( rb.getString("lblDescription"));
		tc = tcm.getColumn(2);
		tc.setHeaderValue( rb.getString("lblReleaseDate"));
		th.repaint();
	}

}
