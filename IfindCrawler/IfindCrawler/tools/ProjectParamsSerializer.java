package tools;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import bo.ProjectParamsBO;

public class ProjectParamsSerializer {
	private String pathName;
	
	/**
	 * sets the path
	 * @param pathName
	 * 		the new path
	 */
	public ProjectParamsSerializer(String pathName) {
		super();
		this.pathName = pathName;
	}
	
	/**
	 * serializes a project
	 * @param pp
	 * 		a project
	 */
	public void serialize(ProjectParamsBO pp) {
	try {
		FileOutputStream file = new FileOutputStream(pathName);
		ObjectOutputStream out = new ObjectOutputStream(file);
		out.writeObject(pp);
		out.close();
	}
	catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		}
	}
	
	/**
	 * unserializes a project
	 * @return a project
	 * 
	 * @see ProjectParamsBO
	 */
	public ProjectParamsBO unSerialize(){
		ProjectParamsBO pp = new ProjectParamsBO();
			try {
				FileInputStream file = new FileInputStream(pathName);
				ObjectInputStream in = new ObjectInputStream(file);
				pp = (ProjectParamsBO)in.readObject();
				in.close();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return pp;
	 }
}
