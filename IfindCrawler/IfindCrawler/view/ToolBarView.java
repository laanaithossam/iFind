package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import controller.ContinueButtonController;
import controller.EvolutionClickController;
import controller.PauseButtonController;
import controller.StatisticsClickController;
import controller.StopButtonController;

public class ToolBarView extends JToolBar {
	private static final long serialVersionUID = 7612998101768636753L;
	/**
	 * the parent view
	 */
	public MainView parent;
	/**
	 * the stop button
	 * 
	 * permits to stop the grab
	 */
	public JButton StopButton;
	/**
	 * the pause button
	 * 
	 * permits to pause the grab
	 */
	public JButton PauseButton;
	/**
	 * the download button
	 * 
	 * permits to start grabbing, or to continue it after a pause or a stop
	 */
	public JButton ContinueButton;
	/**
	 * the delete button
	 * 
	 * permits to delete a project on the tree view
	 */
	public JButton DeleteButton;
	/**
	 * the statistics button
	 * 
	 * permits to hide or unhide the statistics block
	 */
	public JButton StatisticsButton;
	/**
	 * the evolution button
	 * 
	 * permits to hide or unhide the evolution block
	 */
	public JButton EvolutionButton;
	/**
	 * set to true if the grab has begun
	 */
	public boolean isBegin=true;
	
	/**
	 * Creation of the toolbar
	 * <ul>
	 * 		<li>Continue/Start button</li>
	 * 		<li>Stop button</li>
	 * 		<li>Pause button</li>
	 * 		<li>Statistics button</li>
	 * 		<li>Evolution button</li>
	 * </ul>
	 * @param parent
	 * 		parent panel
	 * 
	 * @see MainView
	 * @see ContinueButtonController#ContinueButtonController(ToolBarView)
	 * @see StopButtonController#StopButtonController(ToolBarView)
	 * @see PauseButtonController#PauseButtonController(ToolBarView)
	 * @see StatisticsClickController#StatisticsClickController(ToolBarView)
	 * @see EvolutionClickController#EvolutionClickController(ToolBarView)
	 */
	public ToolBarView(MainView parent){
		super("Tools Bar", JToolBar.HORIZONTAL);
		this.parent=parent;
		this.setFloatable(false);
		this.setRollover(true);
		ContinueButton=new JButton(new ImageIcon("img/download.png"));
		ContinueButton.setToolTipText("Resume grab");
		StopButton=new JButton(new ImageIcon("img/stop.png"));
		StopButton.setToolTipText("Stop");
		PauseButton=new JButton(new ImageIcon("img/pause.png"));
		PauseButton.setToolTipText("Pause");
		StatisticsButton=new JButton(new ImageIcon("img/statistics.png"));
		StatisticsButton.setToolTipText("Statistics");
		EvolutionButton=new JButton(new ImageIcon("img/evolution.png"));
		EvolutionButton.setToolTipText("Evolution");
		//Set enabled
		ContinueButton.setEnabled(true);
		//Set disabled
		PauseButton.setEnabled(false);
		StopButton.setEnabled(false);
		//Affect the button's actions when receive an event
		ContinueButton.addActionListener(new ContinueButtonController(this));
		StopButton.addActionListener(new StopButtonController(this));
		PauseButton.addActionListener(new PauseButtonController(this));
		StatisticsButton.addActionListener(new StatisticsClickController(this));
		EvolutionButton.addActionListener(new EvolutionClickController(this));
		//Add buttons to the toolbar
		this.add(ContinueButton);
		this.add(PauseButton);
		this.add(StopButton);
		this.addSeparator();
		this.add(StatisticsButton);
		this.add(EvolutionButton);	
	}
}
