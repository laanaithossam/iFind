package org.esgi.ifind.indexer.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.esgi.ifind.indexer.gui.view.DmozTabView;

/**
 * The Class TestSqlClickController.
 */
public class TestSqlClickController implements ActionListener {

	/** The parent. */
	DmozTabView parent;
	
	/**
	 * Instantiates a new test sql click controller.
	 *
	 * @param parent the parent
	 */
	public TestSqlClickController(DmozTabView parent){
		this.parent=parent;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			@SuppressWarnings("unused")
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql:"+ parent.getTextSqlHostName(),parent.getTextSqlUsername(),parent.getTextSqlPassword());
			JOptionPane.showMessageDialog(parent, "Connection OK");
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(parent, e1.getMessage());
		} catch (InstantiationException e1) {
			JOptionPane.showMessageDialog(parent, e1.getMessage());
		} catch (IllegalAccessException e1) {
			JOptionPane.showMessageDialog(parent, e1.getMessage());
		} catch (ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(parent, e1.getMessage());
		}
	}

}
