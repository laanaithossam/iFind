package org.esgi.ifind.indexer.dmoz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.esgi.ifind.indexer.configuration.Configuration;

/**
 * The Class DmozDao.
 */
public class DmozDao {
	
	/** The instance. */
	private static  DmozDao instance = null;
	
	/** The connection. */
	private Connection connection = null;
	
	/** The hostname. */
	private String hostname;
	
	/** The username. */
	private String username;
	
	/** The password. */
	private String password;
	
	/**
	 * Instantiates a new dmoz dao.
	 */
	private DmozDao(){
		hostname=Configuration.getInstance().getSqlHost();
		username=Configuration.getInstance().getSqlUsername();
		password=Configuration.getInstance().getSqlPassword();
		  try {
				Class.forName("org.gjt.mm.mysql.Driver").newInstance();
				connection = DriverManager.getConnection(
						"jdbc:mysql:"+hostname,username,password);
				
			} catch (Exception e) {
				e.printStackTrace();
			}	
	}
	
	/**
	 * Gets the single instance of DmozDao.
	 *
	 * @return single instance of DmozDao
	 */
	public static DmozDao getInstance() {
		if (null == instance) {
			instance = new DmozDao();
		}
		return instance;
	}
	
	/**
	 * Gets the description from dmoz.
	 *
	 * @param url the url
	 * @return the description from dmoz
	 */
	public String getDescriptionFromDmoz(String url) {
		  String description = null;
		  String query = "select description from dmoz where url='"+url+"';";
		  try {
				PreparedStatement ps = connection.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				if(rs.first())
				description = rs.getString("description");
		  }catch (Exception e) {
			  e.printStackTrace();
		}
		  return description;
	 }
	
	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	public Connection getConnection(){
		return connection;
	}
}
