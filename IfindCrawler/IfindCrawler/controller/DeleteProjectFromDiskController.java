package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;

import tools.JTabbedPaneWithCloseIcons;

import model.Model;

/**
 * Deletes a project from the disk
 */
public class DeleteProjectFromDiskController implements ActionListener{
	private JTree treeView;

	public DeleteProjectFromDiskController(JTree treeView) {
		this.treeView = treeView;
	}

	/**
	 * @see Model
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(Model.grabMapDir.containsKey(treeView.getLastSelectedPathComponent().toString())){
			String grabFileName = Model.grabMapDir.get(treeView.getLastSelectedPathComponent().toString());
			String pathToDelete = grabFileName.substring(0,grabFileName.length()-(5+treeView.getLastSelectedPathComponent().toString().length()));
			File file = new File(pathToDelete);
			if(!file.isDirectory())
			file.delete();
			else { 
				supprRep(file);
				file.delete();
			}
		}
		
		if(Model.grabMapFiles.containsKey(treeView.getLastSelectedPathComponent().toString())){
			File dir = new File(Model.grabMapFiles.get(treeView.getLastSelectedPathComponent().toString()));
			dir.delete();
		}
		
        int i = getPosition(treeView.getLastSelectedPathComponent().toString(),Model.projectTabID);
        JTabbedPaneWithCloseIcons.tabPane.setSelectedIndex(i);
        JTabbedPaneWithCloseIcons.tabPane.revalidate();
        if (i != -1) {
        	Model.projectTabID.remove(JTabbedPaneWithCloseIcons.tabPane.getSelectedIndex());
        	Model.mainViewList.remove(JTabbedPaneWithCloseIcons.tabPane.getSelectedIndex());
        	JTabbedPaneWithCloseIcons.tabPane.remove(JTabbedPaneWithCloseIcons.tabPane.getSelectedIndex());
        }
		((DefaultTreeModel) treeView.getModel()).removeNodeFromParent((MutableTreeNode) treeView.getSelectionPath().getLastPathComponent());

	
	//returns the position of a string key in a arraylist
	}
	
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
	

	  public void supprRep(File r){
	    File [] fileList = r.listFiles();
	    for(int i = 0;i<fileList.length;i++){
	      if(fileList[i].isDirectory() ){
	    	  supprRep(fileList[i]);
	        System.out.println(fileList[i].delete());
	      }else{
	        System.out.println(fileList[i].delete());
	      }
	    }
	  }
}
