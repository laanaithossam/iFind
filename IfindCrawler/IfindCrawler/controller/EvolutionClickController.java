package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.ToolBarView;

/**
 * click on evolution button
 */
public class EvolutionClickController implements ActionListener {
	private ToolBarView myToolbar;
	
	public EvolutionClickController(ToolBarView myToolbar){
		this.myToolbar=myToolbar;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(myToolbar.parent.isEvolutionDisplayed){
			myToolbar.parent.getThreadPan().setVisible(false);
			myToolbar.parent.isEvolutionDisplayed=false;
			myToolbar.parent.getSplitReporting().setLeftComponent(myToolbar.parent.getThreadPan());
			myToolbar.parent.getSplitReporting().setRightComponent(myToolbar.parent.getStatisticsPan());
			myToolbar.parent.getReportingPan().revalidate();
			myToolbar.parent.getReportingPan().repaint();
			myToolbar.parent.getSplitReporting().revalidate();
			myToolbar.parent.getSplitReporting().repaint();
		}
		else{
			myToolbar.parent.getThreadPan().setVisible(true);
			myToolbar.parent.isEvolutionDisplayed=true;
			myToolbar.parent.getSplitReporting().setLeftComponent(myToolbar.parent.getThreadPan());
			myToolbar.parent.getSplitReporting().setRightComponent(myToolbar.parent.getStatisticsPan());
			myToolbar.parent.getReportingPan().revalidate();
			myToolbar.parent.getReportingPan().repaint();
			myToolbar.parent.getSplitReporting().revalidate();
			myToolbar.parent.getSplitReporting().repaint();
		}
	}
}
