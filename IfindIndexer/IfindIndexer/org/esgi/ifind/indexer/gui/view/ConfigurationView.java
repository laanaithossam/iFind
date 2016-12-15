/*
 * 
 */
package org.esgi.ifind.indexer.gui.view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

// TODO: Auto-generated Javadoc
/**
 * The Class ConfigurationView.
 */
public class ConfigurationView extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The tabbed pane. */
	private JTabbedPane tabbedPane;
	
	/** The instance. */
	private static ConfigurationView instance=null;
	
	/** The path tab view. */
	private PathTabView pathTabView;
	
	/** The page rank tab view. */
	private PageRankTabView pageRankTabView;
	
	/** The dmoz tab view. */
	private DmozTabView dmozTabView;
	
	/**
	 * Instantiates a new configuration view.
	 */
	private ConfigurationView(){
		super("Indexer configuration");
		pathTabView=new PathTabView();
		pageRankTabView=new PageRankTabView();
		dmozTabView=new DmozTabView();
		
		tabbedPane=new JTabbedPane();
		tabbedPane.addTab("Path",pathTabView);
		tabbedPane.addTab("PageRank",pageRankTabView);
		tabbedPane.addTab("Dmoz",dmozTabView);
		this.getContentPane().add(tabbedPane);
		this.setVisible(false);
		this.setSize(new Dimension(650,320));
	}
	
	/**
	 * Gets the single instance of ConfigurationView.
	 *
	 * @return single instance of ConfigurationView
	 */
	public static  ConfigurationView getInstance(){
		if(null==instance)
			instance=new ConfigurationView();
		return instance;
	}
}