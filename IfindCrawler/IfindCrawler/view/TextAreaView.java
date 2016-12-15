package view;

import java.util.ArrayList;

import javax.swing.JTextArea;

public class TextAreaView extends JTextArea{
	private static final long serialVersionUID = 2337242045140791063L;
	
	public TextAreaView(){
		super(12,40);
	}

	/**
	 * Adds a discovered url to the queue list
	 * 
	 * @param myList
	 * 		list of discovered urls
	 */
	public void AddUrls(ArrayList<String> myList){
		String[] myTab = this.getText().split("\\r\\n");
		for(String s:myTab){
			if(!myList.contains(s))
				myList.add(s);
		}
	}
}
