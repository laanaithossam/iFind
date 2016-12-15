package tools;

import java.io.File;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import model.Model;

public class TreeGenerator{
		//attributes
		private static DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Liste des projets ouverts");

		/**
		 * 
		 * @param fileName
		 * 		name of the file to be associated to the project
		 * 
		 * @see TreeGenerator#listFile(File, DefaultMutableTreeNode)
		 * @see TreeGenerator#findNode(DefaultMutableTreeNode, DefaultMutableTreeNode)
		 */
		public static void addProject(String fileName){
			File file = new File(fileName); 
			
			//associate a project name to a project file
			Model.grabMapDir.put(file.getName(), file.getAbsolutePath()+"/"+file.getName()+".grab");
			
			//If the project is not already open we add the new project in projectTabID
			if(!Model.projectTabID.contains(file.getName()))
				Model.projectTabID.add(file.getName());
			
			//Create the project node
			DefaultMutableTreeNode projectNode = new DefaultMutableTreeNode(file.getName());

			//Check for all the nodes and subnodes
			try {
				for(File nom : file.listFiles()){
					DefaultMutableTreeNode node = new DefaultMutableTreeNode(nom.getName()+"\\");
					projectNode.add(listFile(nom, node));
				}
			} catch (NullPointerException e) {}
			
			//Check if the project doesn't already exists
			/*if(!compareNode(rootNode, projectNode)) {
				rootNode.add(projectNode);
			}*/
			if(!compareNode(rootNode, projectNode)) {
				rootNode.add(projectNode);
			} else {
				DefaultMutableTreeNode tmp = findNode(rootNode, projectNode);
				rootNode.remove(tmp);
				rootNode.add(projectNode);
			}
	}
	
	/**
	 * 
	 * @param file
	 * @param node
	 * @return child
	 * 
	 * @see Model
	 */
	private static DefaultMutableTreeNode listFile(File file, DefaultMutableTreeNode node){
		//If isFile, it doesn't have any child 
		//So let's add to the grapMapFiles and return it
		if(file.isFile()) {
			Model.grabMapFiles.put(file.getName(), file.getAbsolutePath());
			return new DefaultMutableTreeNode(file.getName());
		}
		//Else check for the child
		else{
			for(File nom : file.listFiles()){
					DefaultMutableTreeNode subNode;
					if(nom.isDirectory()){
						subNode = new DefaultMutableTreeNode(nom.getName()+"\\");
						node.add(listFile(nom, subNode));
					}else{
						subNode = new DefaultMutableTreeNode(nom.getName());
						Model.grabMapFiles.put(nom.getName(), nom.getAbsolutePath());
					}
					node.add(subNode);
			}
			return node;
		}
	}
	
	//Check two nodes given in parameters
	public static boolean compareNode(DefaultMutableTreeNode rootNode,DefaultMutableTreeNode projectNode) {
		DefaultMutableTreeNode tmp;
		boolean result = false;
		rootNode.getChildCount();
		
		for(int i=0;i<rootNode.getChildCount();i++){
			tmp= (DefaultMutableTreeNode)rootNode.getChildAt(i);
			if (tmp.getUserObject().toString().equals(projectNode.getUserObject().toString()))
				result = true;
		}
		return result;
	}
	
	public static DefaultMutableTreeNode findNode(DefaultMutableTreeNode rootNode,DefaultMutableTreeNode projectNode) {
		DefaultMutableTreeNode tmp;
		rootNode.getChildCount();
		
		for(int i=0;i<rootNode.getChildCount();i++){
			tmp= (DefaultMutableTreeNode)rootNode.getChildAt(i);
			if (tmp.getUserObject().toString().equals(projectNode.getUserObject().toString()))
				return tmp;
		}
		return null;
	}
	
	/**
	 * gets a tree node
	 * @return a tree node
	 */
	public static JTree getTree(){
		return new JTree(rootNode);
	}
}
