/*
 * 
 */
package org.esgi.ifind.indexer.gui.view;


import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import org.esgi.ifind.indexer.configuration.Configuration;
import org.esgi.ifind.indexer.gui.controller.BrowserClickController;
import org.esgi.ifind.indexer.gui.controller.CancelPathClickController;
import org.esgi.ifind.indexer.gui.controller.SubmitPathClickController;


// TODO: Auto-generated Javadoc
/**
 * The Class PathTabView.
 */
public class PathTabView extends JPanel {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The lbl indexer path. */
	private JLabel lblIndexerPath;
	
	/** The txt indexer path. */
	private JTextField txtIndexerPath;
	
	/** The panel indexer. */
	private JPanel panelIndexer;
	
	/** The btn browser indexer. */
	private JButton btnBrowserIndexer;
	
	/** The lbl data path. */
	private JLabel lblDataPath;
	
	/** The txt data path. */
	private JTextField txtDataPath;
	
	/** The panel data. */
	private JPanel panelData;
	
	/** The btn browser data. */
	private JButton btnBrowserData;
	
	/** The split path. */
	private JSplitPane splitPath;
	
	/** The btn submit. */
	private JButton btnSubmit;
	
	/** The btn cancel. */
	private JButton btnCancel;
	
	/** The split main. */
	private JSplitPane splitMain;
	
	/** The panel button. */
	private JPanel panelButton;
	
	/**
	 * Instantiates a new path tab view.
	 */
	public PathTabView(){
		
		//PATH Index directory
		lblIndexerPath=new JLabel("Indexes destination : ");
		txtIndexerPath=new JTextField();
		txtIndexerPath.setPreferredSize(new Dimension(200,25));
		txtIndexerPath.setText(Configuration.getInstance().getIndexPath());
		panelIndexer=new JPanel();
		panelIndexer.setBorder(BorderFactory.createTitledBorder("Indexation"));
		btnBrowserIndexer=new JButton("...");
		btnBrowserIndexer.addActionListener(new BrowserClickController(this,1));
		panelIndexer.add(lblIndexerPath);
		panelIndexer.add(txtIndexerPath);
		panelIndexer.add(btnBrowserIndexer);
		
		//PATH Data directory
		lblDataPath=new JLabel("Datas directory : ");
		txtDataPath=new JTextField();
		txtDataPath.setPreferredSize(new Dimension(200,25));
		txtDataPath.setText(Configuration.getInstance().getDataPath());
		panelData=new JPanel();
		panelData.setBorder(BorderFactory.createTitledBorder("Data"));
		btnBrowserData=new JButton("...");
		btnBrowserData.addActionListener(new BrowserClickController(this,2));
		panelData.add(lblDataPath);
		panelData.add(txtDataPath);
		panelData.add(btnBrowserData);
		
		//Init button cancel & save
		btnSubmit=new JButton("Save");
		btnSubmit.addActionListener(new SubmitPathClickController(this));
		btnCancel=new JButton("Cancel");
		btnCancel.addActionListener(new CancelPathClickController(this));
		
		splitPath=new JSplitPane(JSplitPane.VERTICAL_SPLIT,panelData,panelIndexer);
		panelButton=new JPanel();
		panelButton.add(btnSubmit);
		panelButton.add(btnCancel);
		
		splitMain=new JSplitPane(JSplitPane.VERTICAL_SPLIT,splitPath,panelButton);
		
		this.add(splitMain);
		razTextPath();
		this.setVisible(true);
	}
	
	/**
	 * Sets the text indexer path name.
	 *
	 * @param txtPath the new text indexer path name
	 */
	public void setTextIndexerPathName(String txtPath){
		txtIndexerPath.setText(txtPath);
	}
	
	/**
	 * Sets the text data path name.
	 *
	 * @param txtPath the new text data path name
	 */
	public void setTextDataPathName(String txtPath){
		txtDataPath.setText(txtPath);
	}
	
	/**
	 * Save text path.
	 */
	public void saveTextPath(){
		Configuration.getInstance().setDataPath(txtDataPath.getText());
		Configuration.getInstance().setIndexPath(txtIndexerPath.getText());
		Configuration.getInstance().savePathProperties();
	}
	
	/**
	 * Raz text path.
	 */
	public void razTextPath(){
		txtDataPath.setText(Configuration.getInstance().getDataPath());
		txtIndexerPath.setText(Configuration.getInstance().getIndexPath());
	}
	
}
