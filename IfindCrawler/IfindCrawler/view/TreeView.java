package view;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTree;
import controller.TreeClickController;
import tools.TreeGenerator;

public class TreeView extends JPanel{
	//Attributes
	private static final long serialVersionUID = 1L;
	/**
	 * Contains the project's tree
	 */
	private JTree grabTree;
	/**
	 * base element of the window
	 * 
	 * @see RootView
	 */
	private RootView rootFrame;
	
	/**
	 * Constructor the tree view
	 * 
	 * @param rootFrame
	 * 		frame where to build the tree
	 * 
	 * @see TreeGenerator#TreeGenerator()
	 */
	public TreeView(RootView rootFrame) {
		this.rootFrame = rootFrame;
		this.setBackground(Color.white);
		grabTree=TreeGenerator.getTree();
		this.add(grabTree);
	}
	
	/**
	 * Refresh the tree display
	 * 
	 * @see TreeGenerator#TreeGenerator()
	 * @see TreeClickController#TreeClickController(RootView, JTree)
	 */
	public void updateTree(){
		this.grabTree=TreeGenerator.getTree();
		grabTree.getModel();
		this.removeAll();
		this.add(grabTree);
		this.revalidate();
		this.repaint();
		grabTree.addMouseListener(new TreeClickController(this.rootFrame,this.grabTree));
	}
}
