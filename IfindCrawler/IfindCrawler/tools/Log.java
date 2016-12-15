package tools;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

 public class Log {
	private FileOutputStream fi;
	private String textlog;
	public String logFile;
	public final String Titre1="Titre1";
	public final String Titre2="Titre2";
	public final String ListeAlerte="ListeAlerte";
	public final String ListeNormal="ListeNormal";
	public final String LigneAlerte="LigneAlerte";
	public final String LigneNormal="LigneNormal";
	
	/**
	 * init log file
	 * 
	 * @param logPath
	 * 		log file path
	 * @param logFilename
	 * 		log file name
	 */
	public void init(String logPath,String logFilename){
		this.logFile= logPath + logFilename + ".html";
		try {
			fi=new FileOutputStream(logPath + logFilename + ".html");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		textlog="<html>\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n " +
		  "<head>\n" +
		  "<title> Xtrem Grabber Report </title>\n" +
		  "</head>\n" +
		  "<body bgcolor=\"#C0D9D9\">\n" +
		  "<hr>\n" +
        "<h1 align=\"center\">Grabber Report</h1>\n"+
        "Rapport du "+ new Date().toString() +",\n<br>\n";  
	}

	/**
	 * insert a line in the log file
	 * 
	 * @param args_Content
	 * 		line to insert on the log file
	 * @param args_Style
	 * 		style of the line
	 */
	public void fill(String args_Content,String args_Style){
		String sTempBegin;
		String sTempEnd ;
		
		if(args_Style.equals(Titre1)){
			sTempBegin = "<p><h1>";
	        sTempEnd = "</h1>";
		}
		else if(args_Style.equals(Titre2)){
			sTempBegin = "<p><h2>";
	        sTempEnd = "</h2>";
		}
		else if(args_Style.equals(ListeAlerte)){
			sTempBegin = "<UL><font color=\"#FF0000\"><LI type=\"square\">";
	        sTempEnd = "</font></UL>";
		}
		else if(args_Style.equals(ListeNormal)){
			sTempBegin = "<UL><LI type=\"circle\">";
	        sTempEnd = "</UL>";
		}
		else if(args_Style.equals(LigneAlerte)){
			sTempBegin = "&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp <font color=\"#FF0000\">";
	        sTempEnd = "</font><br>";
		}
		else if(args_Style.equals(LigneNormal)){
			sTempBegin = "&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp ";
	        sTempEnd = "<br>";
		}
		else{
			 sTempBegin = "<UL><LI>";
		     sTempEnd = "</UL>";
		}
		
		textlog+=sTempBegin + args_Content + sTempEnd;
	}

	/**
	 * closure of the log file
	 */
	public void end(){
		textlog+=
         "<table width=\"100%\">\n"+
         "  <tr>\n"+
         "    <td valign=bottom>\n"+
         "      <font size=\"1\"> Fin du programme � "+new Date().toString() + "</font>\n"+
         "    </td>\n"+
         "  </tr>\n"+ 
         "</table>";
		
		try {
			fi.write(textlog.getBytes());
			fi.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
