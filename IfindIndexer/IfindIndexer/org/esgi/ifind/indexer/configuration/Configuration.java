package org.esgi.ifind.indexer.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.esgi.ifind.indexer.gui.view.MonitoringView;

/**
 * The Class Configuration.
 */
public final class Configuration {
	
	/** The instance. */
	private static Configuration instance=null;
	
	/** The PROPERTIE s_ dir. */
	private final String PROPERTIES_DIR="config/config.properties";
	
	/** The properties. */
	private Properties properties;
	
	/** The fos. */
	FileOutputStream fos;
	
	/**
	 * Instantiates a new configuration.
	 */
	private Configuration(){
		properties = new Properties();
		try {
			properties.load(new FileInputStream(new File(PROPERTIES_DIR)));
		} catch (IOException e) {
			MonitoringView.getInstance().setTextAreaMonitoring("* Exception: Class Configuration - " +
					"Method Configuration() : " +e.getMessage());
		}
	};

	/**
	 * Gets the single instance of Configuration.
	 *
	 * @return single instance of Configuration
	 */
	public final synchronized static Configuration getInstance(){
		if(Configuration.instance==null)
			Configuration.instance=new Configuration();
		return instance;
	}
	
	/**
	 * Gets the data path.
	 *
	 * @return the data path
	 */
	public String getDataPath() {
		return properties.getProperty("datadir");
	}

	/**
	 * Sets the data path.
	 *
	 * @param dataPath the new data path
	 */
	public void setDataPath(String dataPath) {
		properties.setProperty("datadir", dataPath);
	}

	/**
	 * Gets the index path.
	 *
	 * @return the index path
	 */
	public String getIndexPath() {
		return properties.getProperty("indexdir");
	}

	/**
	 * Sets the index path.
	 *
	 * @param indexPath the new index path
	 */
	public void setIndexPath(String indexPath) {
		properties.setProperty("indexdir", indexPath);
	}
	
	/**
	 * Gets the dmoz path.
	 *
	 * @return the dmoz path
	 */
	public String getDmozPath() {
		return properties.getProperty("dmozfilename");
	}

	/**
	 * Sets the dmoz path.
	 *
	 * @param dmozFilename the new dmoz path
	 */
	public void setDmozPath(String dmozFilename) {
		properties.setProperty("dmozfilename", dmozFilename);
	}
	
	/**
	 * Gets the sql host.
	 *
	 * @return the sql host
	 */
	public String getSqlHost() {
		return properties.getProperty("sqlhostname");
	}

	/**
	 * Sets the sql host.
	 *
	 * @param hostName the new sql host
	 */
	public void setSqlHost(String hostName) {
		properties.setProperty("sqlhostname", hostName);
	}
	
	/**
	 * Gets the sql username.
	 *
	 * @return the sql username
	 */
	public String getSqlUsername() {
		return properties.getProperty("sqlusername");
	}

	/**
	 * Sets the sql username.
	 *
	 * @param userName the new sql username
	 */
	public void setSqlUsername(String userName) {
		properties.setProperty("sqlusername", userName);
	}
	
	/**
	 * Gets the sql password.
	 *
	 * @return the sql password
	 */
	public String getSqlPassword() {
		return properties.getProperty("sqlpassword");
	}

	/**
	 * Sets the sql password.
	 *
	 * @param password the new sql password
	 */
	public void setSqlPassword(String password) {
		properties.setProperty("sqlpassword", password);
	}
	
	
	/**
	 * Gets the google pr.
	 *
	 * @return the google pr
	 */
	public String getGooglePr() {
		return properties.getProperty("googlepr");
	}

	/**
	 * Sets the google pr.
	 *
	 * @param googlepr the new google pr
	 */
	public void setGooglePr(String googlepr) {
		properties.setProperty("googlepr", googlepr);
	}
	
	/**
	 * Gets the ifind pr.
	 *
	 * @return the ifind pr
	 */
	public String getIfindPr() {
		return properties.getProperty("ifindpr");
	}

	/**
	 * Sets the ifind pr.
	 *
	 * @param ifindpr the new ifind pr
	 */
	public void setIfindPr(String ifindpr) {
		properties.setProperty("ifindpr", ifindpr);
	}
	
	
	/**
	 * Save path properties.
	 */
	public void savePathProperties(){
		try {
			fos = new FileOutputStream(PROPERTIES_DIR);
			properties.store(fos, null);
			fos.flush();
			fos.close();
		} catch (IOException e) {
			MonitoringView.getInstance().setTextAreaMonitoring("* Exception: Class Configuration - " +
					"Method savePathProperties() : " +e.getMessage());
			e.printStackTrace();
		}
	}
}
