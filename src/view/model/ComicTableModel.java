package view.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import model.entities.Comic;

/**
 * Modelo de la tabla de cómics
 * 
 * @author Sergio Fraga
 */
public class ComicTableModel extends AbstractTableModel{

	private String[] columns = {"Título", "Descripción", "Fecha de estreno", "Tapa", "Estado", "Colección"};
	private ArrayList<Comic> comicList;
	
	
	
	public ComicTableModel(ArrayList<Comic> comicList) {
		this.comicList = comicList;
	}

	@Override
	public int getRowCount() {
		return comicList.size();
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {	
		if(rowIndex != -1 && comicList.size() > rowIndex) {
			Comic comic = comicList.get(rowIndex);
			switch(columnIndex) {
				case 0:
					return comic.getTitle();
				case 1:
					return comic.getDescription();
				case 2:
					return comic.getReleaseDate();
				case 3:
					return comic.getCover();
				case 4:
					return comic.getStatus();
				case 5:
					return comic.getCollection().getName();
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
