package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;

import tools.JTabbedPaneWithCloseIcons;

import model.Model;

/**
 * deletes a project from the tree
 */
public class DeleteProjectFromTreeController implements ActionListener{
	private JTree treeView;

	public DeleteProjectFromTreeController(JTree treeView) {
		this.treeView = treeView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
        int i = getPosition(treeView.getLastSelectedPathComponent().toString(),Model.projectTabID);
        JTabbedPaneWithCloseIcons.tabPane.setSelectedIndex(i);
        JTabbedPaneWithCloseIcons.tabPane.revalidate();
        if (i != -1) {
        	Model.projectTabID.remove(JTabbedPaneWithCloseIcons.tabPane.getSelectedIndex());
        	Model.mainViewList.remove(JTabbedPaneWithCloseIcons.tabPane.getSelectedIndex());
        	JTabbedPaneWithCloseIcons.tabPane.remove(JTabbedPaneWithCloseIcons.tabPane.getSelectedIndex());
        }
		((DefaultTreeModel) treeView.getModel()).removeNodeFromParent((MutableTreeNode) treeView.getSelectionPath().getLastPathComponent());
	}
	
	//returns the position of a string key in a arraylist
	public int getPosition(String key,ArrayList<String> list) {
		if (list.contains(key))
		{
			for (int i=0;i<list.size();i++)
			{
				if (list.get(i).equals(key))
					return i;
			}
		}
		return -1;
	}
}
