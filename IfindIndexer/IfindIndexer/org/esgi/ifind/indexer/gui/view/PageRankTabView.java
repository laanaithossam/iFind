/*
 * 
 */
package org.esgi.ifind.indexer.gui.view;


import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;

import org.esgi.ifind.indexer.configuration.Configuration;
import org.esgi.ifind.indexer.gui.controller.CancelPRClickController;
import org.esgi.ifind.indexer.gui.controller.SubmitPRClickController;


// TODO: Auto-generated Javadoc
/**
 * The Class PageRankTabView.
 */
public class PageRankTabView extends JPanel {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The btn google pr. */
	private JRadioButton  btnGooglePR;
	
	/** The btn ifind pr. */
	private JRadioButton btnIfindPR;

	/** The split pr. */
	private JSplitPane splitPR;
	
	/** The btn submit. */
	private JButton btnSubmit;
	
	/** The btn cancel. */
	private JButton btnCancel;
	
	/** The split main. */
	private JSplitPane splitMain;
	
	/** The panel button. */
	private JPanel panelButton;
	
	/**
	 * Instantiates a new page rank tab view.
	 */
	public PageRankTabView(){
		
		//Radiobutton GooglePR & IfindPR
		btnGooglePR=new JRadioButton("Use google PageRank");
		btnGooglePR.setSelected(Boolean.valueOf(Configuration.getInstance().getGooglePr()));
		btnIfindPR=new JRadioButton("Use Ifind Algorithm PageRank : (You have to execute the Ifind Danse, to update PageRank)");
		btnIfindPR.setSelected(Boolean.valueOf(Configuration.getInstance().getIfindPr()));
		//Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    group.add(btnGooglePR);
	    group.add(btnIfindPR);
	    
		//Init button cancel & save
		btnSubmit=new JButton("Save");
		btnSubmit.addActionListener(new SubmitPRClickController(this));
		btnCancel=new JButton("Cancel");
		btnCancel.addActionListener(new CancelPRClickController(this));
	    
		splitPR=new JSplitPane(JSplitPane.VERTICAL_SPLIT,btnGooglePR,btnIfindPR);
		panelButton=new JPanel();
		panelButton.add(btnSubmit);
		panelButton.add(btnCancel);
		
		splitMain=new JSplitPane(JSplitPane.VERTICAL_SPLIT,splitPR,panelButton);
		
		this.add(splitMain);
		this.setVisible(true);
	}
	
	/**
	 * Save text path.
	 */
	public void saveTextPath(){
		Configuration.getInstance().setGooglePr(String.valueOf(btnGooglePR.isSelected()));
		Configuration.getInstance().setIfindPr(String.valueOf(btnIfindPR.isSelected()));
		Configuration.getInstance().savePathProperties();
	}
	
	/**
	 * Raz text path.
	 */
	public void razTextPath(){
		btnGooglePR.setSelected(Boolean.parseBoolean(Configuration.getInstance().getGooglePr()));
		btnIfindPR.setSelected(Boolean.parseBoolean(Configuration.getInstance().getIfindPr()));
	}
	
}
