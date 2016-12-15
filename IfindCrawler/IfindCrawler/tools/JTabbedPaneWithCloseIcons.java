package tools;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.plaf.basic.BasicButtonUI;

import view.ToolBarView;
import model.Model;

public class JTabbedPaneWithCloseIcons extends JTabbedPane{
	private static final long serialVersionUID = 1L;
	public static JTabbedPaneWithCloseIcons tabPane; //Get access at this element in inner class
	ToolBarView toolbar;
	public JTabbedPaneWithCloseIcons() {
		super();
        tabPane=this;
	}

	/**
	 * adds a tab in the tab bar
	 * 
	 * @param title
	 * 		tab's title
	 * @param component
	 * 		the tab to add
	 * @param pos
	 * 		tab's position
	 * 
	 * @see JTabbedPaneWithCloseIcons.CloseTabPanel
	 */
	public void addTab(String title, Component component,int pos) {
        super.addTab(title, component); //It adds a tab at JTabbedPane
        super.setTabComponentAt(pos, new CloseTabPanel(title)); //we apply the closeTabPanel at the position pos
	}
        
	/**
	 * Displays the close button
	 * @param pos
	 * 
	 * @see JTabbedPaneWithCloseIcons.CloseTabPanel#afficheIcon(boolean)
	 */
    public void afficheIconAt(int pos){
        ((CloseTabPanel)tabPane.getTabComponentAt(pos)).afficheIcon(true);
    }
    
	/**
	 * Removes the close button
	 * @param pos
	 * 
	 * @see JTabbedPaneWithCloseIcons.CloseTabPanel#afficheIcon(boolean)
	 */
    public void cacheIconAt(int pos){
        ((CloseTabPanel)tabPane.getTabComponentAt(pos)).afficheIcon(false);
    }
    
    //Inner class
    class CloseTabPanel extends JPanel{
		private static final long serialVersionUID = 1L;
		JButton button; 
        
		/**
		 * Constructor
		 * 
		 * @param titre
		 * 		tab title
		 * 
		 * @see JTabbedPaneWithCloseIcons.TabButton
		 */
        public CloseTabPanel(String titre) {
	        super(new FlowLayout(FlowLayout.LEFT, 0, 0));
	        setOpaque(false);
	        JLabel label = new JLabel(titre);
	        add(label);
	        button = new TabButton();
	        add(button);
	        setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
        }
    
    	/**
    	 * Override constructor
    	 * 
    	 * @param titre
    	 * 		tab title
    	 * @param b
    	 * 		if true, adds the tab
    	 * 
    	 * @see JTabbedPaneWithCloseIcons.TabButton
    	 */
    	public CloseTabPanel(String titre, boolean b){
            super(new FlowLayout(FlowLayout.LEFT, 0, 0));
            setOpaque(false);
            JLabel label = new JLabel(titre);
            add(label);
            button = new TabButton();
            if(b){
            	add(button);
            }
            //add more space to the top of the component
            setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
        }
    	
        /**
         * displays or closes  a button
         * 
         * @param b
         * 		if true, adds the button, else it is removed
         */
        public void afficheIcon(boolean b){
            if(b){
                if(this.getComponentCount()==1)
                    this.add(button);
            }else{
                if(this.getComponentCount()>1)
                    this.remove(button);
            }
        }
    }
    
    //Inner class
    class TabButton extends JButton implements ActionListener {
		private static final long serialVersionUID = 1L;

		/**
		 * Constructor
		 */
		public TabButton() {
    	        int size = 17;
    	        setPreferredSize(new Dimension(size, size));
    	        setToolTipText("Fermer cet onglet");
    	        //Make the button looks the same for all Laf's
    	        setUI(new BasicButtonUI());
    	        //Make the button transparent
    	        setContentAreaFilled(false);
    	        setFocusable(false);
    	        setBorder(BorderFactory.createEtchedBorder());
    	        setBorderPainted(false);
    	        addActionListener(this);            
    	    }

		/**
		 * Close the selected tab
		 * 
		 * @see JTabbedPaneWithCloseIcons.TabButton#getPosition(String, ArrayList)
		 * @see Model
		 */
    	public void actionPerformed(ActionEvent e) {
	        JButton buttonSelected = (JButton)e.getSource();
	        CloseTabPanel selectedTab = (CloseTabPanel)buttonSelected.getParent();
	        JLabel projectName = (JLabel)selectedTab.getComponents()[0];
	        
	        int i = getPosition(projectName.getText(),Model.projectTabID);
	        tabPane.setSelectedIndex(i);
	        tabPane.revalidate();
	        if (i != -1) {
	        	Model.projectTabID.remove(tabPane.getSelectedIndex());
	        	Model.mainViewList.remove(tabPane.getSelectedIndex());
	        	tabPane.remove(tabPane.getSelectedIndex());
	        }
	    }
    	
		/**
		 * Returns the position of a String key in String list
		 * 
		 * @param key
		 * 		String key to search
		 * @param list
		 * 		lit of Strings
		 * @return position of a String key in String list else -1
		 */
    	public int getPosition(String key,ArrayList<String> list) {
    		if (list.contains(key))
    		{
    			for (int i=0;i<list.size();i++)
    			{
    				if (list.get(i).equals(key))
    					return i;
    			}
    		}
    		return -1;
    	}

	    //we don't want to update UI for this button
	    public void updateUI() {
	    }

	    /**
	     * draw the cross
	     */
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        Graphics2D g2 = (Graphics2D) g.create();
	        //shift the image for pressed buttons
	        if (getModel().isPressed()) {
	            g2.translate(1, 1);
	        } 
	        g2.setStroke(new BasicStroke(2));
	        g2.setColor(Color.BLACK);
	        if (getModel().isRollover()) {
	            g2.setColor(Color.MAGENTA);
	        }            
	        int delta = 6;
	        g2.drawLine(delta, delta, getWidth() - delta - 1, getHeight() - delta - 1);
	        g2.drawLine(getWidth() - delta - 1, delta, delta, getHeight() - delta - 1);
	        g2.dispose();
	    }
	}
}     


 

