package org.esgi.ifind.indexer.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.esgi.ifind.indexer.dmoz.rdf.DmozSaxParser;
import org.esgi.ifind.indexer.gui.view.DmozTabView;

/**
 * The Class ParseDmozClickController.
 */
public class ParseDmozClickController implements ActionListener {

	/** The parent. */
	DmozTabView parent;
	
	/**
	 * Instantiates a new parses the dmoz click controller.
	 *
	 * @param parent the parent
	 */
	public ParseDmozClickController(DmozTabView parent){
		this.parent=parent;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			DmozSaxParser parser=new DmozSaxParser(parent.getTextDmozPathName(),parent.getBtnParseRdf());
			parser.start();
		} catch (Exception e1) {
			parent.getBtnParseRdf().setEnabled(true);
			JOptionPane.showMessageDialog(parent,e1.getMessage());
		}
	}

}
