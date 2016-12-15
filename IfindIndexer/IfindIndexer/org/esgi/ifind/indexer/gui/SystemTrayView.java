package org.esgi.ifind.indexer.gui;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.esgi.ifind.indexer.gui.controller.ConfigClickController;
import org.esgi.ifind.indexer.gui.controller.ExitClickController;
import org.esgi.ifind.indexer.gui.controller.IfindDanceClickController;
import org.esgi.ifind.indexer.gui.controller.MonitorClickController;
import org.esgi.ifind.indexer.gui.controller.StartClickController;
import org.esgi.ifind.indexer.gui.controller.StopClickController;
import org.esgi.ifind.indexer.gui.view.MonitoringView;
import org.esgi.ifind.indexer.indexer.IndexerService;

/**
 * The Class SystemTrayView.
 */
public class SystemTrayView {
	
	/** The indexer service. 
	 * @see IndexerService#IndexerService()*/
	private IndexerService indexerService;
	
	/** The stop item. 
	 * @see StopClickController#StopClickController(SystemTrayView)
	 * */
	private MenuItem stopItem;
	
	/** The start item. 
	 * @see StartClickController#StartClickController(SystemTrayView)*/
	private MenuItem startItem;
	
	/** The config item. 
	 * @see ConfigClickController#ConfigClickController(SystemTrayView)*/
	private MenuItem configItem;
	
	/** The Monitor item. 
	 * @see MonitorClickController#MonitorClickController(SystemTrayView)*/
	private MenuItem MonitorItem;
	
	/** The exit item. 
	 * @see ExitClickController#ExitClickController(SystemTrayView)*/
	private MenuItem exitItem;
	
	/** The ifind dance item. 
	 * @see #IfindDanceClickController#IfindDanceClickController(SystemTrayView)*/
	private MenuItem ifindDanceItem;
	
	/** The tray icon. */
	private TrayIcon trayIcon;
	
	/**
	 * Instantiates a new system tray view.
	 */
	public SystemTrayView(){
	}

	/**
	 * Inits the.
	 *
	 * @return true, if successful
	 */
	public boolean init(){
		if (!SystemTray.isSupported())
			return false;
		
		SystemTray tray = SystemTray.getSystemTray();
		Image image = Toolkit.getDefaultToolkit().getImage("img/indexer.png");
		
		MouseListener mouseListener = new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				//System.out.println("Tray Icon - Mouse clicked!");   
			}
			public void mouseEntered(MouseEvent e) {
				//System.out.println("Tray Icon - Mouse entered!");                 
			}
			public void mouseExited(MouseEvent e) {
				//System.out.println("Tray Icon - Mouse exited!");
			}
			public void mousePressed(MouseEvent e) {
				//System.out.println("Tray Icon - Mouse pressed!");                 
			}
			public void mouseReleased(MouseEvent e) {
				//System.out.println("Tray Icon - Mouse released!");                 
			}
		};
		
		//Popup
		PopupMenu popup=new PopupMenu();
		//Item Stop indexing service
		stopItem = new MenuItem("Stop indexing service");
		stopItem.setEnabled(false);
		stopItem.addActionListener(new StopClickController(this));
		popup.add(stopItem);
		//Item Configuration
		startItem = new MenuItem("Start indexing service");
		startItem.addActionListener(new StartClickController(this));
		popup.add(startItem);
		//Item Configuration
		configItem = new MenuItem("Configuration");
		configItem.addActionListener(new ConfigClickController(this));
		popup.add(configItem);
		//Item Ifind Danse
		ifindDanceItem = new MenuItem("Ifind Dance : Calculate PageRank");
		ifindDanceItem.addActionListener(new IfindDanceClickController(this));
		popup.add(ifindDanceItem);
		//Item Monitoring
		MonitorItem= new MenuItem("Indexer Monitoring");
		MonitorItem.addActionListener(new MonitorClickController(this));
		popup.add(MonitorItem);
		//Item Exit
		exitItem = new MenuItem("Exit");
		exitItem.addActionListener(new ExitClickController(this));
		popup.add(exitItem);
	
		trayIcon = new TrayIcon(image, "IFind Indexer", popup);
		trayIcon.setImageAutoSize(true);
		trayIcon.addMouseListener(mouseListener);
		
		try {
			tray.add(trayIcon);
		} catch (AWTException e) {
			System.err.println("TrayIcon could not be added.");
		}
		//Monitor
		MonitoringView.getInstance().setTextAreaMonitoring("Initialisation de l'application : OK");
		return true;
	}
	
	/**
	 * Update gui.
	 *
	 * @param isStarted the is started
	 */
	public void updateGui(boolean isStarted){
		if(isStarted){
			startItem.setEnabled(false);
			stopItem.setEnabled(true);
		}
		else{
			startItem.setEnabled(true);
			stopItem.setEnabled(false);
		}		
	}
	
	/**
	 * Gets the indexer service.
	 *
	 * @return the indexer service
	 */
	public IndexerService getIndexerService() {
		return indexerService;
	}
	
	/**
	 * Inits the indexer service.
	 *
	 * @return the indexer service
	 */
	public IndexerService initIndexerService(){
		indexerService=new IndexerService();
		return indexerService;
	}
	
	/**
	 * Gets the ifind dance item.
	 *
	 * @return the ifind dance item
	 */
	public MenuItem getIfindDanceItem(){
		return ifindDanceItem;
	}
}
