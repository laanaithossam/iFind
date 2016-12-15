package tools;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FilterExtension extends FileFilter {
	/**
	 * Description accepted by the filter
	 */
    private String description;
    /**
     * extension accepted by the filter
     */
    private String extension;
   
    /**
     * Constructor from the description and the extension accepted
     * 
     * @param description
     * @param extension
     */
    public FilterExtension(String description, String extension){
    	if(description == null || extension == null){
    		throw new NullPointerException("La description (ou extension) ne peut être null.");
        }
        this.description = description;
        this.extension = extension;
    }
   
    /**
     * Implementation of FileFilter
     * 
     * @return file name
     */
    public boolean accept(File file){
        if(file.isDirectory()) { 
            return true; 
        } 
        String nomFichier = file.getName().toLowerCase(); 

        return nomFichier.endsWith(extension);
    }

    /**
     * gets the description file
     * 
     * @return file description
     */
    public String getDescription(){
        return description;
    }
}
