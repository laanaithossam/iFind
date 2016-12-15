/*
 * 
 */
package org.esgi.ifind.indexer.gui.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

// TODO: Auto-generated Javadoc
/**
 * The Class MonitoringView.
 */
public class MonitoringView extends JFrame{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The textarea pan monitoring. */
	private JPanel textareaPanMonitoring;
	
	/** The textarea monitoring. */
	private static JTextArea textareaMonitoring;
	
	/** The instance. */
	private static MonitoringView instance=null;
	
	/**
	 * Instantiates a new monitoring view.
	 */
	private MonitoringView(){
		super("Indexer Monitoring");
		textareaPanMonitoring=new JPanel();
		textareaMonitoring=new JTextArea();
		textareaPanMonitoring.setLayout(new BorderLayout());
		JScrollPane jspanDiscovery=new JScrollPane(textareaMonitoring);
		textareaPanMonitoring.add(jspanDiscovery,BorderLayout.CENTER);
		this.getContentPane().add(textareaPanMonitoring);
		this.setVisible(false);
		this.setSize(new Dimension(600,300));
	}
	
	/**
	 * Gets the single instance of MonitoringView.
	 *
	 * @return single instance of MonitoringView
	 */
	public static  MonitoringView getInstance(){
		if(null==instance)
			instance=new MonitoringView();
		return instance;
	}
	
	/**
	 * Sets the text area monitoring.
	 *
	 * @param s the new text area monitoring
	 */
	public  void setTextAreaMonitoring(String s){
		textareaMonitoring.append(s+"\n");
	}
}
