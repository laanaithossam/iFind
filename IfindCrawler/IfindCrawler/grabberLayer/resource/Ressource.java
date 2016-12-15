package grabberLayer.resource;

import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import tools.TreeGenerator;
import view.RootView;
import bo.FileInfo;
import bo.TasksCollection;

public class Ressource {
	/**
	 * @uml.property  name="url"
	 */
	URL url;
	/**
	 * @uml.property  name="path"
	 */
	private String path;
	/**
	 * @uml.property  name="relativePath"
	 */
	String relativePath;
	/**
	 * @uml.property  name="urlString"
	 */
	String urlString;
	/**
	 * @uml.property  name="domain"
	 */
	String domain;
	/**
	 * @uml.property  name="connection"
	 */
	URLConnection connection = null;
	/**
	 * @uml.property  name="localFile"
	 */
	File localFile;
	/**
	 * @uml.property  name="isTextFile"
	 */
	boolean isTextFile = false;
	/**
	 * @uml.property  name="discoveredUrls"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="java.lang.String"
	 */
	LinkedList<String> discoveredUrls;
	/**
	 * @uml.property  name="datas"
	 */
	InputStream datas;
	/**
	 * @uml.property  name="tc"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private TasksCollection tc;
	/**
	 * @uml.property  name="fi"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private FileInfo fi;

	public Ressource(String url,TasksCollection tc,String myProjectPath){
		this.tc=tc;
		this.fi=new FileInfo();
		this.urlString = url;
		this.path=myProjectPath;
		Pattern p = Pattern.compile("^(https?://[^/]+)");
		Matcher m = p.matcher(urlString);
		if (m.find())
			domain = m.group(1);
		try {
			this.initialize();
			fi.setUrl(urlString);
			this.save();
			if (isTextFile)
				this.extractAndReplaceUrl();
			
		}
		catch (Exception e){
			synchronized (tc.getErrorUrlsList()) {
				tc.addErrorUrl(url);
			}
		}
	}

	public void initialize() throws Exception{
		Properties properties = System.getProperties();
		properties.put("http.proxyHost", "proxy");
		properties.put("http.proxyPort", "3128");

		url = new URL(urlString);
		connection = url.openConnection();
		connection.setRequestProperty
		("Proxy-Authorization", "Basic ZXNnaTp3aWZp");
		datas = connection.getInputStream();
		isTextFile = connection.
		getHeaderField("Content-Type").contains("text");

	}

	/**
	 * 
	 * @param path
	 * @return
	 */
	public String createParentRootPath(){
		String result ="./";
		for (int i =0; i<relativePath.length(); i++)
			if ('/' == relativePath.charAt(i))
				result+= "../";
		return result;
	}
	/**
	 * Parse the saved file and extract url pattern, replacing it by
	 * the corresponding local path.
	 */
	public void extractAndReplaceUrl() throws Exception{
		Pattern p = 
			Pattern.compile(
					"((src|href|url)=?\\(?(\"|')?([^\"';\\,\\=]+)(\3)?\\)?)", 
					Pattern.CASE_INSENSITIVE);
		BufferedReader in = new BufferedReader(
				new FileReader(localFile));
		String line;
		Matcher m;
		//PrintWriter tmpFile = new PrintWriter(new FileWriter(localFile.toString()+".html"));
		try {
			String urlToSubmit = null;
			String replacement = null;
			while (null != (line = in.readLine())){
				m = p.matcher(line);
				while (m.find()){
					urlToSubmit = null;
					if (m.group(4).startsWith("http")) {
						urlToSubmit = m.group(4);
						// http//www.monsite.com/resource/truc.gif
						// ../../www.monsite.com/resource/truc.gif
						// Compte le nombre de sous r�pertoire.

						replacement = "../"+createParentRootPath()+ formatUrlToLocalPath(m.group(4));
						line = line.replaceAll(Pattern.quote(m.group(4)), replacement);
					}
					else if (!m.group(4).startsWith("/")){
						// http://www.blabla/maresource/monfichier.html ...
						// ../mresource
						Pattern p2 = Pattern.compile("((https?://.+)/?[^/]*)");
						Matcher m2 = p2.matcher(urlString);
						String prefix = "";

						if (m2.find())
							prefix = m2.group(1);
						urlToSubmit = prefix+"/"+m.group(4);

						replacement = createParentRootPath()+ formatUrlToLocalPath("http://"+m.group(4));
						line = line.replaceAll(Pattern.quote(m.group(4)), replacement);
					}
					else if (m.group(4).startsWith("/")) {
						urlToSubmit = domain+m.group(4);
						replacement = createParentRootPath()+ formatUrlToLocalPath("http://"+m.group(4));
						line = line.replaceAll(Pattern.quote(m.group(4)), replacement);
					}
					if (null == discoveredUrls)
						discoveredUrls = new LinkedList<String>();
					if (null != urlToSubmit  
							&& !urlToSubmit.matches(".*\\([^\\)]*\\).*")) {
						discoveredUrls.add(urlToSubmit.replaceAll("#[^#]*$","").replaceAll("/$", "").trim());
						tc.addDiscoveredUrl(urlToSubmit);	
					}
				}
				//tmpFile.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		
		} /*finally{
			tmpFile.flush();
			tmpFile.close();
		}*/
	}
	/**
	 * http://www.monsite.com =>> www.monsite.com
	 * 
	 * @param url
	 * @return
	 */
	private String formatUrlToLocalPath(String url){
		return url.substring(7)
		.replaceAll("[^\\w\\.\\/]+", "-")
		.replaceAll("/$", "");
	}

	public void save(){
		int i = 0;
		relativePath = formatUrlToLocalPath(urlString);
		String localPath = path + relativePath;
		localFile = new File(localPath);
		if(this.connection.getLastModified() > localFile.lastModified()){
			fi.setFilename(localFile.getName());
			JPanel jp=new JPanel(new GridLayout(1, 3));
			JProgressBar jpb = new JProgressBar();
			JLabel jlblFilename=new JLabel(fi.getFilename());
			JLabel jlblPercent=new JLabel(String.valueOf(fi.getPercentDownloaded())+"%");
			/* If has no extension, supposed to be a directory.
		   http://www.societe.com dir.
		   http://www.societe.com/societe.com -> not dir.
		   http://www.societe.com/societe -> dir */
			if (!domain.contains(relativePath) 
					&& localPath.matches(".*\\.[^\\.]+")) {
				// Check that the parent file exist.
				localFile.getParentFile().mkdirs();
			}
			else {
				localFile.mkdirs();
				localFile = new File(localFile, "index.html");
				fi.setFilename("index.html");
			}
			try {
				FileOutputStream fos = new FileOutputStream(localFile);
				byte[] buffer = new byte[1];
				int len;

				this.tc.setListTask(jpb, fi,jlblFilename,jlblPercent,jp);
				this.tc.getProjectViewIdentifier().updatePan();
				RootView test = (RootView)this.tc.getProjectViewIdentifier().getParent().getParent().getParent().getParent().getParent().getParent();
				int size=0;
				while (-1 != (len = datas.read(buffer))) {
					size++;
				}
				datas.close();
				connection = null;
				connection = url.openConnection();
				datas = connection.getInputStream();

				jpb.setMinimum(0);
				jpb.setMaximum(size);
				fi.setFilesize(size);

				while (-1 != (len = datas.read(buffer))) {
					fos.write(buffer,0, len);
					if(size>0)
						tc.getFi().setPercentDownloaded((i*100)/size)  ;
					jlblPercent.setText(String.valueOf(tc.getFi().getPercentDownloaded())+"%");
					jpb.setValue(i);
					i++;
				}
				tc.calculateSize(size);
				tc.addCompletedUrl(urlString);

				TreeGenerator.addProject(tc.pp.getProjectWorkspace());
				test.updateTree();
				this.tc.deleteItemPanel(jp);			
				fos.flush();
				fos.close();
				datas.close();

			} catch (Exception e) {
				this.tc.deleteItemPanel(jp);
				synchronized (tc.getErrorUrlsList()) {
					tc.addErrorUrl(urlString);
				}
				//System.out.println("Opening Fail on : "+localFile+"("+urlString+")"+domain+relativePath);
			}
		}
	}
	public String[] getUrls(){
		return null != discoveredUrls ? discoveredUrls
				.toArray(new String[discoveredUrls.size()]): new String[0]; }
}
