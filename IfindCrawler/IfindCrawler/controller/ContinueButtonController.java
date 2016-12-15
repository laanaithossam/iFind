package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import bo.ProjectParamsBO;
import bo.TasksCollection;

import thread.ResumeExistingGrabProject;
import thread.StartNewGrabber;
import view.MainView;
import view.ToolBarView;

public class ContinueButtonController implements ActionListener {
	/**
	 * tool bar
	 * @see ToolBarView
	 */
	public ToolBarView myToolbar;
	/**
	 * 
	 * @param myToolbar
	 */
	public ContinueButtonController(ToolBarView myToolbar){
		this.myToolbar=myToolbar;
	}

	/**
	 * @param arg0
	 * 
	 * @see ProjectParamsBO#isAlreadyStarted()
	 * @see MainView#getTextProjectName()
	 * @see MainView#getTextPathName()
	 * @see MainView#getTextUrl()
	 * @see MainView#creerGridLayout(int)
	 * @see TasksCollection#razGUI()
	 * @see TasksCollection#addDiscoveredUrl(String)
	 * @see StartNewGrabber
	 * @see TasksCollection#updateGUI()
	 * @see ProjectParamsBO#getQueueUrlsList()
	 * @see ProjectParamsBO#setAlreadyStarted(boolean)
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(!this.myToolbar.parent.tc.pp.isAlreadyStarted()) {
			if(myToolbar.isBegin){
				String myProjectName = this.myToolbar.parent.getTextProjectName();
				String myProjectPath=this.myToolbar.parent.getTextPathName();
				String url=this.myToolbar.parent.getTextUrl();
				System.out.println(myProjectName);
				this.myToolbar.parent.creerGridLayout(5);
				//Start thread Grabber
				myToolbar.parent.tc.razGUI();
				myToolbar.parent.tc.addDiscoveredUrl(url);
				StartNewGrabber myThread=new StartNewGrabber(this.myToolbar.parent.tc,myProjectPath+"/",url);
				myThread.start();
				myToolbar.isBegin=false;
			} else {
				for(Thread th:myToolbar.parent.tc.listThread)
					th.resume();
			}
		} else {
			if(myToolbar.isBegin){
				String myProjectName = this.myToolbar.parent.getTextProjectName();
				String myProjectPath=this.myToolbar.parent.getTextPathName();
				System.out.println(myProjectName);
				this.myToolbar.parent.creerGridLayout(5);
				//Start thread Grabber
				this.myToolbar.parent.tc.updateGUI();
				ResumeExistingGrabProject myThread=new ResumeExistingGrabProject(this.myToolbar.parent.tc,myProjectPath+"/",this.myToolbar.parent.tc.pp.getQueueUrlsList());
				myThread.start();
				this.myToolbar.parent.tc.pp.setAlreadyStarted(false);
				myToolbar.isBegin=false;
			} else {
				for(Thread th:myToolbar.parent.tc.listThread) {
					th.resume();
				}
			}
		}
		myToolbar.ContinueButton.setEnabled(false);
		myToolbar.PauseButton.setEnabled(true);
		myToolbar.StopButton.setEnabled(true);
		myToolbar.parent.stop=false;
		myToolbar.parent.tc.log.init(this.myToolbar.parent.getTextPathName() + "/","Log " + this.myToolbar.parent.getTextProjectName());
		myToolbar.parent.statusBar.setStatus("En cours", new ImageIcon("downloadstatus.png"));
	}

	/**
	 * gets the tool bar
	 * @return a tool bar
	 */
	public ToolBarView getToolbar(){
		return this.myToolbar;
	}

	
}
