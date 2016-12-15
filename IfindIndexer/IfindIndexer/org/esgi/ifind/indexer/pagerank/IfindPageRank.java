package org.esgi.ifind.indexer.pagerank;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.esgi.ifind.indexer.gui.view.MonitoringView;

import Jama.Matrix;

/**
 * The Class IfindPageRank.
 */
public class IfindPageRank {
	
	/** The DAMPIN g_ factor. */
	private final double DAMPING_FACTOR = 0.85;
	
	/** The outbound map. */
	private HashMap<String, ArrayList<String>> outboundMap;
	
	/** The inbound map. */
	private HashMap<String, ArrayList<String>> inboundMap;
	
	/** The Constant outboundPath. */
	static final String outboundPath="config/outbound.ser";
	
	/** The params. */
	private ArrayList<String> params = new ArrayList<String>();

	/**
	 * Instantiates a new ifind page rank.
	 */
	public IfindPageRank(){
		outboundMap=getOutboundMap();
		inboundMap=new HashMap<String, ArrayList<String>>();
	}

	/**
	 * Serialize outbound map.
	 */
	public void serializeOutboundMap() {
		try {
			FileOutputStream file = new FileOutputStream(outboundPath);
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(outboundMap);
			out.close();
		}
		catch (Exception e) {
			MonitoringView.getInstance().setTextAreaMonitoring("* Erreur : " +e.getMessage());
		}
	}

	/**
	 * Gets the outbound map.
	 *
	 * @return the outbound map
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, ArrayList<String>> getOutboundMap(){
		HashMap<String, ArrayList<String>> map = new  HashMap<String, ArrayList<String>>();
		try {
			FileInputStream file = new FileInputStream(outboundPath);
			ObjectInputStream in = new ObjectInputStream(file);
			map = (HashMap<String, ArrayList<String>>) in.readObject();
			in.close();
		}catch (Exception e) {
		}
		return map;
	}

	/**
	 * Sets the outbound map.
	 *
	 * @param url the url
	 * @param file the file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void setOutboundMap(String url,File file) throws IOException{
		Matcher m;
		String line;
		ArrayList<String> urls=new ArrayList<String>();
		BufferedReader br=new BufferedReader(new FileReader(file));
		Pattern p;
		p = 
			Pattern.compile(
					"((src|href|url)=?\\(?(\"|')?([^\"';\\,\\=]+)(\3)?\\)?)", 
					Pattern.CASE_INSENSITIVE);
		while (null != (line = br.readLine())){
			m = p.matcher(line);
			while (m.find())
				if (m.group(4).startsWith("http"))
					if(!urls.contains(m.group(4)))
						urls.add(m.group(4));
		}
		if(!outboundMap.containsKey(url)){
			outboundMap.put(url,urls);
			serializeOutboundMap();
		}
	}

	/**
	 * Solve the equation of ax=b, which : a is the generated matrix based on
	 * the parameter constants. x is the page ranks matrix. b is a n*1 matrix
	 * which all the values are equal to the damping factor.
	 *
	 * @return the hash map
	 */
	public HashMap<String, Double> rankUrls(){
		setInboundMap(outboundMap);

		double pr;
		if(null!=inboundMap){
			HashMap<String,Double>map=new HashMap<String, Double>();
			for(Entry<String, ArrayList<String>>entry:getOutboundMap().entrySet()){
				pr=rank(entry.getKey());
				if(pr>0)
					map.put(entry.getKey(),pr );
			}
			return map;
		}
		return null;
	}

	/**
	 * Rank.
	 *
	 * @param pageId the page id
	 * @return Solve the equation and return the page ranks
	 */
	public double rank(String pageId) {

		if(!generateParamList(pageId))
			return 0;

		Matrix a = new Matrix(generateMatrix());
		double[][] arrB = new double[params.size()][1];

		for (int i = 0; i < params.size(); i++) {

			arrB[i][0] = 1 - DAMPING_FACTOR;

		}

		Matrix b = new Matrix(arrB);

		Matrix x = a.solve(b);

		int ind = 0;
		int cnt = 0;

		for (String curPage:params) {
			if (curPage.equals(pageId))
				ind = cnt;
			cnt++;
		}

		return x.getArray()[ind][0];
	}

	/**
	 * This method generates the matrix of the linear equations. The generated
	 * matrix is a n*n matrix where n is number of the related pages.
	 *
	 * @return the double[][]
	 */
	private double[][] generateMatrix() {

		double[][] arr = new double[params.size()][params.size()];

		for (int i = 0; i < params.size(); i++) {

			for (int j = 0; j < params.size(); j++) {

				arr[i][j] = getMultiFactor(params.get(i),params.get(j));
			}

		}

		return arr;

	}

	/**
	 * This method returns the constant of the given variable in the linear equation.
	 *
	 * @param sourceId the source id
	 * @param linkId the link id
	 * @return the multi factor
	 */
	private double getMultiFactor(String sourceId, String linkId) {

		if (sourceId.equals(linkId))
			return 1;
		else {
			String[] inc = getInboundLinks(sourceId);
			if(null==inc)
				return 0;
			for (int i = 0; i < inc.length; i++) {
				if (inc[i].equals(linkId)) {
					return -1 * (DAMPING_FACTOR / getOutboundLinks(linkId).length);
				}
			}
		}
		return 0;
	}

	/**
	 * This method returns list of the related pages. This list is also the
	 * parameters in the linear equation
	 *
	 * @param pageId the page id
	 * @return true, if successful
	 */
	private boolean generateParamList(String pageId) {

		// Add the starting page.
		if (!params.contains(pageId))
			params.add(pageId);
		// Get list of the inbound pages
		String[] inc = getInboundLinks(pageId);
		if(null==inc)
			return false;
		// Add the inbound links to the params list and do same for inbound
		// links
		for (int i = 0; i < inc.length; i++) {

			if (!params.contains(inc[i]))
				generateParamList(inc[i]);
		}
		return true;

	}

	/**
	 * Gets the inbound links.
	 *
	 * @param pageId the page id
	 * @return list of the inbound links to a given page
	 */
	private String[] getInboundLinks(String pageId) {
		if(null!=inboundMap.get(pageId)){
			ArrayList<String>list=inboundMap.get(pageId);
			return ((String[])list.toArray(new String[list.size()]));
		}

		else
			return null;
	}

	/**
	 * Gets the outbound links.
	 *
	 * @param pageId the page id
	 * @return list of the outbound links from a page.
	 */
	private String[] getOutboundLinks(String pageId) {
		if(null!=outboundMap.get(pageId)){
			ArrayList<String>list=outboundMap.get(pageId);
			return ((String[])list.toArray(new String[list.size()]));
		}
		else
			return null;
	}
	
	/**
	 * Generate the inbound links's list.
	 *
	 * @param map : List of the outbound links
	 */
	private void setInboundMap(HashMap<String, ArrayList<String>> map){
		String reverseKey;
		ArrayList<String>reverseValues;
		for(Entry<String, ArrayList<String>>entry:map.entrySet()){
			reverseKey=entry.getKey();
			reverseValues=getReverseValues(reverseKey,map);
			if(!inboundMap.containsKey(reverseKey))
				if (reverseValues.size()>0)
					inboundMap.put(reverseKey, reverseValues);
		}
	}
	
	/**
	 * Gets the reverse values.
	 *
	 * @param reverseKey the reverse key
	 * @param map the map
	 * @return the reverse values
	 */
	private  ArrayList<String> getReverseValues(String reverseKey,HashMap<String, ArrayList<String>> map){
		ArrayList<String>reverseValues=new ArrayList<String>();
		ArrayList<String>tmpList=new ArrayList<String>();

		for(Entry<String, ArrayList<String>> entry:map.entrySet()){
			tmpList=entry.getValue();
			if(tmpList.contains(reverseKey))
				reverseValues.add(entry.getKey());
		}
		return reverseValues;
	}
}
