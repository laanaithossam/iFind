package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import tools.ProjectParamsSerializer;
import bo.ProjectParamsBO;
import bo.TasksCollection;

/**
 * Panel containing a project settings
 */
public class MainView extends JPanel{
	
	//Attributes
	private static final long serialVersionUID = 1L;
	/**
	 * Text field containing the project name
	 */
	private JTextField textProjectName;
	/**
	 * Text field containing the path project
	 */
	private JTextField textPathName;
	/**
	 * Text field containing the project's main url
	 */
	private JTextField textUrl;
	/**
	 * Label containing the project name
	 */
	private JLabel lblProjectName;
	/**
	 * Label containing the path project
	 */
	private JLabel lblProjectPath;
	/**
	 * Label containing the project's main url
	 */
	private JLabel lblUrl;
	/**
	 * Panel containing the toolbar
	 */
	private JPanel toolbarPan;
	/**
	 * Panel containing the project properties and the reporting
	 */
	private JPanel propertiesANDreportingPan;
	/**
	 * Panel containing the project properties
	 */
	private JPanel propertiesPan;
	/**
	 * Panel containing the reporting:
	 * 
	 * fetching files names and progress bars with percentages completed
	 */
	private JPanel reportingPan;
	/**
	 * Panel containing the progress bars
	 */
	private JPanel threadPan;
	//private JPanel progressingPan;
	/**
	 * Panel containing the discovered urls
	 */
	private JPanel textareaDiscoveryPan;
	//private JPanel textareaProgressPan;
	/**
	 * Panel containing the completed urls
	 */
	private JPanel textareaCompletePan;
	/**
	 * Panel containing the statistics:
	 * <ul>
	 * 		<li>discovered urls</li>
	 * 		<li>completed urls</li>
	 * </ul>
	 */
	private JPanel statisticsPan;
	/**
	 * Text field containing the discovered urls
	 * 
	 * @see TextAreaView
	 */
	private TextAreaView textAreaDiscovery;
	//private TextAreaView textAreaProgressing;
	/**
	 * Text field containing the completed url
	 * 
	 * @see TextAreaView
	 */
	private TextAreaView textAreaCompleted;
	/**
	 * Permits to place two panels on both sides of it
	 */
	private JSplitPane splitReporting;
	/**
	 * If true, the fetching is stopped
	 */
	public boolean stop;
	/**
	 * If true, statistics are displayed
	 */
	public boolean isStatisticsDisplayed;
	/**
	 * If true, evolution is displayed
	 */
	public boolean isEvolutionDisplayed;
	/**
	 * Bar showing the status of a file being fetched
	 * 
	 * @see JStatusBar
	 */
	public JStatusBar statusBar;
	public TasksCollection tc;
	

	/**
	 * Creates a MainView from a project
	 * 
	 * @param pp
	 * 		the project
	 */
	public MainView(ProjectParamsBO pp) {
		//statusBar
		statusBar=new JStatusBar();
		//Add toolbar
		toolbarPan=new JPanel();
		toolbarPan.add(new ToolBarView(this));
		toolbarPan.setBorder(BorderFactory.createEmptyBorder());
		
		//Add Text area
		statisticsPan=new JPanel();
		statisticsPan.setBorder(BorderFactory.createTitledBorder("Satistics :"));
		
		textareaDiscoveryPan=new JPanel();
		textareaDiscoveryPan.setLayout(new BorderLayout());
		textAreaDiscovery=new TextAreaView();
		JScrollPane jspanDiscovery=new JScrollPane(textAreaDiscovery);
		textareaDiscoveryPan.add(jspanDiscovery,BorderLayout.CENTER);
		
		textareaCompletePan=new JPanel();
		textareaCompletePan.setLayout(new BorderLayout());
		textAreaCompleted =new TextAreaView();
		JScrollPane jspanCompleted=new JScrollPane(textAreaCompleted);
		textareaCompletePan.add(jspanCompleted,BorderLayout.CENTER);
		
		JSplitPane splitPanTextarea=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,textareaDiscoveryPan,textareaCompletePan);
		statisticsPan.add(splitPanTextarea);
		//Text ProjectName
		textProjectName = new JTextField();
		textPathName = new JTextField();
		textUrl=new JTextField();
		textProjectName.setPreferredSize(new Dimension(200, 25));
		textPathName.setPreferredSize(new Dimension(200, 25));
		textUrl.setPreferredSize(new Dimension(200, 25));
		//Label ProjectName
		lblProjectName = new JLabel("Nom du projet : ");
		lblProjectPath = new JLabel("Chemin du projet : ");
		lblUrl = new JLabel("Url du site : ");		
		//Format the properties panel
		propertiesPan=new JPanel();
		propertiesPan.setBorder(BorderFactory.createTitledBorder("Properties :"));
		//propertiesPan.setLayout(new BorderLayout());
		propertiesPan.add(lblProjectName);
		propertiesPan.add(textProjectName);
		propertiesPan.add(lblProjectPath);
		propertiesPan.add(textPathName);
		propertiesPan.add(lblUrl);
		propertiesPan.add(textUrl);
		//Format the reporting panel
		
		reportingPan=new JPanel();
		reportingPan.setBorder(BorderFactory.createTitledBorder("Reporting :"));
		
		threadPan = new JPanel();
		threadPan.setBorder(BorderFactory.createTitledBorder("Evolution :"));
		threadPan.setPreferredSize(new Dimension(550, 100));
		
		splitReporting=new JSplitPane();
		splitReporting.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitReporting.setLeftComponent(threadPan);
		splitReporting.setRightComponent(statisticsPan);
		
		reportingPan.setLayout(new BorderLayout());
		reportingPan.add(splitReporting,BorderLayout.NORTH);
		
		propertiesANDreportingPan=new JPanel();
		JSplitPane splitpropertiesANDreporting=new JSplitPane(JSplitPane.VERTICAL_SPLIT, propertiesPan, reportingPan);
		propertiesANDreportingPan.setLayout(new BorderLayout());
		propertiesANDreportingPan.add(splitpropertiesANDreporting,BorderLayout.NORTH);
		
		JSplitPane splitWindowPan=new JSplitPane(JSplitPane.VERTICAL_SPLIT,toolbarPan,propertiesANDreportingPan);
		splitWindowPan.setBorder(BorderFactory.createEmptyBorder());

		this.setLayout(new BorderLayout());
		this.add(splitWindowPan,BorderLayout.NORTH);
		this.add(statusBar, BorderLayout.SOUTH);
		tc=new TasksCollection(this);
		tc.pp=pp;
		stop=true;

		statisticsPan.setVisible(true);
		isStatisticsDisplayed=true;
		isEvolutionDisplayed=true;
		threadPan.setVisible(true);
	}
	
	/**
	 * 
	 * @return the reporting panel
	 */
	public JPanel getReportingPan() {
		return reportingPan;
	}

	/**
	 * 
	 * @return the statistic panel
	 */
	public JPanel getStatisticsPan() {
		return statisticsPan;
	}

	/**
	 * 
	 * @return the discovered urls list
	 */
	public TextAreaView getTextAreaDiscovery() {
		return textAreaDiscovery;
	}

	/**
	 * 
	 * @return the completed urls list
	 */
	public TextAreaView getTextAreaCompleted() {
		return textAreaCompleted;
	}

	public void updatePan(){
		threadPan.revalidate();
		threadPan.repaint();
	}

	/**
	 * 
	 * @return the properties panel
	 */
	public JPanel getPropertiesPan() {
		return propertiesPan;
	}

	/**
	 * sets the properties panel
	 * @param propertiesPan
	 * 		the new properties panel
	 */
	public void setPropertiesPan(JPanel propertiesPan) {
		this.propertiesPan = propertiesPan;
	}

	/**
	 * 
	 * @param nbRows
	 */
	public void creerGridLayout(int nbRows) {
		splitReporting.remove(threadPan);
		threadPan = new JPanel(new GridLayout(nbRows,2));
		threadPan.setVisible(isEvolutionDisplayed);
		threadPan.setPreferredSize(new Dimension(550, 100));
		threadPan.setBorder(BorderFactory.createTitledBorder("Evolution :"));
		splitReporting.setLeftComponent(threadPan);
	}
	
	/**
	 * 
	 * @return the split element on the reporting
	 */
	public JSplitPane getSplitReporting() {
		return splitReporting;
	}

	public JPanel getThreadPan() {
		return threadPan;
	}
	/**
	 * Updates the project with new data
	 * 
	 * @param sPathname
	 * 		the project path
	 * 
	 * @see ProjectParamsSerializer#ProjectParamsSerializer(String)
	 * @see ProjectParamsBO
	 * @see ProjectParamsBO#getProjectWorkspace()
	 * @see ProjectParamsBO#getProjectName()
	 * @see ProjectParamsBO#getUrlToGrab()
	 */
	public void reloadGrab(String sPathname){
		ProjectParamsSerializer pps = new ProjectParamsSerializer(sPathname);
		ProjectParamsBO pp = pps.unSerialize();
		textPathName.setText(pp.getProjectWorkspace());
		textProjectName.setText(pp.getProjectName());
		textUrl.setText(pp.getUrlToGrab());
	}
	
	/**
	 * sets the projects name
	 * @param Text
	 * 		the new projects name
	 */
	public void setTextProjectName(String Text) {
		this.textProjectName.setText(Text);
	}
	/**
	 * sets the project main url
	 * 
	 * @param Text
	 * 		new url
	 */
	public void settextUrl(String Text) {
		this.textUrl.setText(Text);
	}
	/**
	 * sets the project path
	 * @param Text
	 * 		new path
	 */
	public void setTextPathName(String Text) {
		this.textPathName.setText(Text);
	}
	
	/**
	 * gets the project name
	 * @return project name
	 */
	public String getTextProjectName() {
		return textProjectName.getText();
	}
	/**
	 * gets the project path
	 * @return project path
	 */
	public String getTextPathName() {
		return textPathName.getText();
	}
	/**
	 * gets the project main url
	 * @return project url
	 */
	public String getTextUrl() {
		return textUrl.getText();
	}
}
