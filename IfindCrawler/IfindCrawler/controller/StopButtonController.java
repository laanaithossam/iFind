package controller;

import grabberLayer.threadpool.ThreadWorker;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import view.ToolBarView;

/**
 * actions to perform when the stop button is clicked
 */
public class StopButtonController implements ActionListener {
	private ToolBarView myToolbar;
	
	public StopButtonController(ToolBarView myToolbar){
		this.myToolbar=myToolbar;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {

		// TODO Auto-generated method stub
		myToolbar.parent.statusBar.setStatus("Edition de la log en cours...", new ImageIcon("stopstatus.png"));
		Cursor c = new Cursor(Cursor.WAIT_CURSOR);
		myToolbar.parent.setCursor(c);

		for(ThreadWorker th:myToolbar.parent.tc.listThread){
				th.stopWorker();
		}
		
		while(myToolbar.parent.tc.tcAvtiveThread!=0){
		}
		
		myToolbar.parent.tc.populateLog();
		
		c = new Cursor(Cursor.DEFAULT_CURSOR);
		myToolbar.parent.setCursor(c);
		myToolbar.parent.statusBar.setStatus("XtremGrabber 1.5", new ImageIcon("offlinestatus.png"));
		
		myToolbar.ContinueButton.setEnabled(true);
		myToolbar.PauseButton.setEnabled(false);
		myToolbar.StopButton.setEnabled(false);
		myToolbar.parent.stop=true;
		myToolbar.isBegin=true;
		myToolbar.parent.tc.deleteAllItemPanel();
		
		
	}
}
