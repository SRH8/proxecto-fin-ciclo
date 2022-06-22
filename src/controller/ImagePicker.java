package controller;

import java.io.File;
import javax.swing.JFileChooser;

/**
 * Selector de imagen
 * 
 * @author Sergio Fraga
 */
public class ImagePicker {
	/**
	 * Permite escoger una imagen del sistema de archivos
	 * 
	 * @return ruta de la imagen seleccionada
	 */
	public String loadImage() {
		String path = null;
		String desktopPath = System.getProperty("user.home");		
		
		JFileChooser jfc = new JFileChooser(desktopPath + "\\Desktop");
		jfc.setDialogTitle("Cargar imagen");
	    jfc.showOpenDialog(null);
	   
	    File imagen = jfc.getSelectedFile();
	    path = imagen.getPath(); 
	   
	    
	    return path;	    
	}
}
