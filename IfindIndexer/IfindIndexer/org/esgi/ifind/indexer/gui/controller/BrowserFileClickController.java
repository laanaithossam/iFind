package org.esgi.ifind.indexer.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import org.esgi.ifind.indexer.gui.view.DmozTabView;

/**
 * The Class BrowserFileClickController.
 */
public class BrowserFileClickController implements ActionListener {
	
	/** The parent. */
	DmozTabView parent;
	
	/**
	 * Instantiates a new browser file click controller.
	 *
	 * @param parent the parent
	 */
	public BrowserFileClickController(DmozTabView parent){
		this.parent=parent;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser c = new JFileChooser();
		c.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		c.setFileFilter(new FileFilter() {
	        public boolean accept(File f) {
	          String name = f.getName().toLowerCase();
	          
	          return name.endsWith(".rdf.u8");
	        }

	        public String getDescription() {
	          return "rdf files";
	        }
	      });
		int result = c.showOpenDialog(parent);
		if(result==JFileChooser.APPROVE_OPTION) {
				parent.setTextDmozPathName((c.getSelectedFile().getAbsolutePath()));
		}
	}
	
}
