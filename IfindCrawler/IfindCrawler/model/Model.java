package model;

import java.util.ArrayList;
import java.util.HashMap;
import view.MainView;

public class Model {
	
	//Private constructor to forbid override this data
	private Model(){
		
	}
	//Shared data
	public static HashMap<String, String> grabMapDir = new HashMap<String, String>();
	public static HashMap<String, String> grabMapFiles = new HashMap<String, String>();
	public static ArrayList<String> projectTabID = new ArrayList<String>();
	public static ArrayList<MainView> mainViewList;
	public static ArrayList<String> listUrls;
}
