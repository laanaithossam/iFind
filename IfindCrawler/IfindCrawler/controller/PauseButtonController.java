package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import view.ToolBarView;

/**
 * controller of the pause button
 */
public class PauseButtonController implements ActionListener {
	/**
	 * a tool bar
	 */
	private ToolBarView myToolbar;
	
	/**
	 * @param myToolbar
	 * 		the toolbar
	 */
	public PauseButtonController(ToolBarView myToolbar){
		this.myToolbar=myToolbar;
	}
	
	/**
	 * action to do when a click is done
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub		
		for(Thread th:myToolbar.parent.tc.listThread){
			th.suspend();
		}
		myToolbar.ContinueButton.setEnabled(true);
		myToolbar.PauseButton.setEnabled(false);
		myToolbar.StopButton.setEnabled(true);
		myToolbar.parent.statusBar.setStatus("En pause", new ImageIcon("pausestatus.png"));
	}
}
