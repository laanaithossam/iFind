/*
 * 
 */
package org.esgi.ifind.indexer.gui.view;


import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import org.esgi.ifind.indexer.configuration.Configuration;
import org.esgi.ifind.indexer.gui.controller.BrowserFileClickController;
import org.esgi.ifind.indexer.gui.controller.CancelDmozClickController;
import org.esgi.ifind.indexer.gui.controller.ParseDmozClickController;
import org.esgi.ifind.indexer.gui.controller.SubmitDmozClickController;
import org.esgi.ifind.indexer.gui.controller.TestSqlClickController;


// TODO: Auto-generated Javadoc
/**
 * The Class DmozTabView.
 */
public class DmozTabView extends JPanel {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The lbl dmoz path. */
	private JLabel lblDmozPath;
	
	/** The txt dmoz path. */
	private JTextField txtDmozPath;
	
	/** The panel dmoz. */
	private JPanel panelDmoz;
	
	/** The btn browser dmoz. */
	private JButton btnBrowserDmoz;
	
	/** The lbl sql host. */
	private JLabel lblSqlHost;
	
	/** The txt sql host. */
	private JTextField txtSqlHost;
	
	/** The lbl sql username. */
	private JLabel lblSqlUsername;
	
	/** The txt sql username. */
	private JTextField txtSqlUsername;
	
	/** The lbl sql password. */
	private JLabel lblSqlPassword;
	
	/** The txt sql password. */
	private JPasswordField txtSqlPassword;
	
	/** The panel sql. */
	private JPanel panelSql;
	
	/** The btn test connection. */
	private JButton btnTestConnection;
	
	/** The btn parse rdf. */
	private JButton btnParseRdf;

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
	 * Instantiates a new dmoz tab view.
	 */
	public DmozTabView(){
		
		//PATH Dmoz file
		lblDmozPath=new JLabel("Dmoz File name : ");
		txtDmozPath=new JTextField();
		txtDmozPath.setPreferredSize(new Dimension(200,25));
		txtDmozPath.setText(Configuration.getInstance().getIndexPath());
		panelDmoz=new JPanel();
		panelDmoz.setBorder(BorderFactory.createTitledBorder("Rdf File"));
		btnBrowserDmoz=new JButton("...");
		btnBrowserDmoz.addActionListener(new BrowserFileClickController(this));
		btnParseRdf=new JButton("Extarct to Mysql");
		btnParseRdf.addActionListener(new ParseDmozClickController(this));
		panelDmoz.add(lblDmozPath);
		panelDmoz.add(txtDmozPath);
		panelDmoz.add(btnBrowserDmoz);
		panelDmoz.add(btnParseRdf);
		//Sql Configuration
		JPanel panHost=new JPanel();
		lblSqlHost=new JLabel("MySql Host & Database (//hostname/databasename) : ");
		txtSqlHost=new JTextField();
		txtSqlHost.setPreferredSize(new Dimension(200,25));
		txtSqlHost.setText(Configuration.getInstance().getDataPath());
		panHost.add(lblSqlHost);
		panHost.add(txtSqlHost);
		
		JPanel panId=new JPanel();
		lblSqlUsername=new JLabel("Database User name : ");
		txtSqlUsername=new JTextField();
		txtSqlUsername.setPreferredSize(new Dimension(100,25));
		txtSqlUsername.setText(Configuration.getInstance().getDataPath());
		
		lblSqlPassword=new JLabel("Database Password : ");
		txtSqlPassword=new JPasswordField();
		txtSqlPassword.setPreferredSize(new Dimension(100,25));
		txtSqlPassword.setText(Configuration.getInstance().getDataPath());
		
		panId.add(lblSqlUsername);
		panId.add(txtSqlUsername);
		panId.add(lblSqlPassword);
		panId.add(txtSqlPassword);
		
		
		JSplitPane splitSql=new JSplitPane(JSplitPane.VERTICAL_SPLIT,panHost,panId);
		
		panelSql=new JPanel();
		panelSql.setBorder(BorderFactory.createTitledBorder("Congiguration Mysql"));
		
		panelSql.add(splitSql);
		
		splitPath=new JSplitPane(JSplitPane.VERTICAL_SPLIT,panelDmoz,panelSql);
		//Init button cancel & save & Test
		btnTestConnection=new JButton("Test connection");
		btnTestConnection.addActionListener(new TestSqlClickController(this));
		btnSubmit=new JButton("Save");
		btnSubmit.addActionListener(new SubmitDmozClickController(this));
		btnCancel=new JButton("Cancel");
		btnCancel.addActionListener(new CancelDmozClickController(this));
		
		panelButton=new JPanel();
		
		panelButton.add(btnTestConnection);
		panelButton.add(btnSubmit);
		panelButton.add(btnCancel);
		
		splitMain=new JSplitPane(JSplitPane.VERTICAL_SPLIT,splitPath,panelButton);
		
		this.add(splitMain);
		raz();
		this.setVisible(true);
	}
	
	/**
	 * Sets the text dmoz path name.
	 *
	 * @param txtPath the new text dmoz path name
	 */
	public void setTextDmozPathName(String txtPath){
		txtDmozPath.setText(txtPath);
	}
	
	/**
	 * Sets the text sql host name.
	 *
	 * @param txtHost the new text sql host name
	 */
	public void setTextSqlHostName(String txtHost){
		txtSqlHost.setText(txtHost);
	}
	
	/**
	 * Sets the text sql username.
	 *
	 * @param txtUsername the new text sql username
	 */
	public void setTextSqlUsername(String txtUsername){
		txtSqlUsername.setText(txtUsername);
	}
	
	/**
	 * Sets the text sql password.
	 *
	 * @param txtPassword the new text sql password
	 */
	public void setTextSqlPassword(String txtPassword){
		txtSqlPassword.setText(txtPassword);
	}
	
	/**
	 * Gets the text dmoz path name.
	 *
	 * @return the text dmoz path name
	 */
	public String getTextDmozPathName(){
		return txtDmozPath.getText();
	}
	
	/**
	 * Gets the text sql host name.
	 *
	 * @return the text sql host name
	 */
	public String getTextSqlHostName(){
		return txtSqlHost.getText();
	}
	
	/**
	 * Gets the text sql username.
	 *
	 * @return the text sql username
	 */
	public String getTextSqlUsername(){
		return txtSqlUsername.getText();
	}
	
	/**
	 * Gets the text sql password.
	 *
	 * @return the text sql password
	 */
	public String getTextSqlPassword(){
		return new String(txtSqlPassword.getPassword());
	}
	
	/**
	 * Save.
	 */
	public void save(){
		
		Configuration.getInstance().setDmozPath(txtDmozPath.getText());
		Configuration.getInstance().setSqlHost(txtSqlHost.getText());
		Configuration.getInstance().setSqlUsername(txtSqlUsername.getText());
		Configuration.getInstance().setSqlPassword(new String(txtSqlPassword.getPassword()));
		Configuration.getInstance().savePathProperties();
	}
	
	/**
	 * Raz.
	 */
	public void raz(){
		txtDmozPath.setText(Configuration.getInstance().getDmozPath());
		txtSqlHost.setText(Configuration.getInstance().getSqlHost());
		txtSqlUsername.setText(Configuration.getInstance().getSqlUsername());
		txtSqlPassword.setText(Configuration.getInstance().getSqlPassword());
	}
	
	/**
	 * Gets the btn parse rdf.
	 *
	 * @return the btn parse rdf
	 */
	public JButton getBtnParseRdf() {
		return btnParseRdf;
	}
}
