package model.tables;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import model.entities.ComicCollection;

/**
 * Modelo de la tabla de colecciones
 * 
 * @author Sergio Fraga
 */
public class CollectionTableModel extends AbstractTableModel{

	private String[] columns = {"Nombre", "Descripción"};
	private ArrayList<ComicCollection> collectionsList;
	
	@Override
	public int getRowCount() {
		return collectionsList.size();
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(rowIndex != -1 && collectionsList.size() > rowIndex) {
			ComicCollection comicCollection = collectionsList.get(rowIndex);
			switch(columnIndex) {
				case 0:
					return comicCollection.getName();
				case 1:
					return comicCollection.getDescription();
				default:
					return null;
			}
		} else return null;
	}
	
	@Override
	public boolean isCellEditable(int rowIndex,  int columnIndex) {
		return false;
	}
	
	/**
	 * Devuelve el nombre de una columna del modelo en base a un índice
	 * 
	 * @param col índice de la columna.
	 * @return String nombre de la columna.
	 */
	public String getColumnName(int col) {
		return columns[col];	
	}
	
	/**
	 * Especifica el nombre de una columna
	 * 
	 * @param name nuevo nombre que se le quiere dar a la columna
	 * @param index índice de la columna a modificar
	 */
	public void setColumnName(String name, int index) {
		columns[index] = name;		
	}
	
}
