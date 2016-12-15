package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.ToolBarView;

/**
 * Actions to perform when the statistic button is clicked
 */
public class StatisticsClickController implements ActionListener {
	private ToolBarView myToolbar;
	
	public StatisticsClickController(ToolBarView myToolbar){
		this.myToolbar=myToolbar;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(myToolbar.parent.isStatisticsDisplayed){
			myToolbar.parent.getStatisticsPan().setVisible(false);
			myToolbar.parent.isStatisticsDisplayed=false;
			myToolbar.parent.getSplitReporting().setLeftComponent(myToolbar.parent.getThreadPan());
			myToolbar.parent.getSplitReporting().setRightComponent(myToolbar.parent.getStatisticsPan());
			myToolbar.parent.getReportingPan().revalidate();
			myToolbar.parent.getReportingPan().repaint();
			myToolbar.parent.getSplitReporting().revalidate();
			myToolbar.parent.getSplitReporting().repaint();
		}
		else{
			myToolbar.parent.getStatisticsPan().setVisible(true);
			myToolbar.parent.isStatisticsDisplayed=true;
			myToolbar.parent.getSplitReporting().setLeftComponent(myToolbar.parent.getThreadPan());
			myToolbar.parent.getSplitReporting().setRightComponent(myToolbar.parent.getStatisticsPan());
			myToolbar.parent.getReportingPan().revalidate();
			myToolbar.parent.getReportingPan().repaint();
			myToolbar.parent.getSplitReporting().revalidate();
			myToolbar.parent.getSplitReporting().repaint();
		}
	}
}
