package asgn2GUIs;

import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;
import java.io.File;
import java.text.DecimalFormat;

import javax.swing.text.DefaultCaret;

import asgn2Customers.Customer;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Restaurant.LogHandler;
import asgn2Restaurant.PizzaRestaurant;

import java.awt.*;
import javax.swing.*;


/**
 * This class is the graphical user interface for the rest of the system. 
 * Currently it is a ‘dummy’ class which extends JFrame and implements Runnable and ActionLister. 
 * It should contain an instance of an asgn2Restaurant.PizzaRestaurant object which you can use to 
 * interact with the rest of the system. You may choose to implement this class as you like, including changing 
 * its class signature – as long as it  maintains its core responsibility of acting as a GUI for the rest of the system. 
 * You can also use this class and asgn2Wizards.PizzaWizard to test your system as a whole
 * 
 * 
 * @author Person A and Person B
 *
 */
public class PizzaGUI extends javax.swing.JFrame implements Runnable, ActionListener {
	private static final long serialVersionUID = -7031008862559936404L;
	public static final int WIDTH = 300, HEIGHT = 200;
	private PizzaRestaurant pizzaRestaurant;
	private JButton btnLoad;
	private JPanel pnlDisplay, pnlTop, 
		pnlBottom, pnlRight, pnlLeft;
	private JTextArea areDisplay; 
	private JFileChooser fc;
	private StringBuffer sb;
	
	/**
	 * Creates a new Pizza GUI with the specified title 
	 * @param title - The title for the supertype JFrame
	 */
	public PizzaGUI(String title) throws HeadlessException {
		super(title);
		pizzaRestaurant = new PizzaRestaurant();
	}
	
	private void createGUI() {
		setSize(WIDTH, HEIGHT);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLayout(new BorderLayout());
	    
	    btnLoad = createButton("Load File");
	    pnlDisplay = createPanel(Color.WHITE);
	    pnlTop = createPanel(Color.LIGHT_GRAY);
	    pnlBottom = createPanel(Color.LIGHT_GRAY);
	    pnlRight = createPanel(Color.LIGHT_GRAY);
	    pnlLeft = createPanel(Color.LIGHT_GRAY);
	    areDisplay = createTextArea();

	    pnlDisplay.setLayout(new BorderLayout());
	    pnlDisplay.add(areDisplay, BorderLayout.CENTER);
	    
	    this.getContentPane().add(pnlDisplay,BorderLayout.CENTER);
	    this.getContentPane().add(pnlTop,BorderLayout.NORTH);
	    this.getContentPane().add(pnlBottom,BorderLayout.SOUTH);
	    this.getContentPane().add(pnlRight,BorderLayout.EAST);
	    this.getContentPane().add(pnlLeft,BorderLayout.WEST);
	    
	    layoutButtonPanel();
	    
	    repaint();
	    this.setVisible(true);
	}
	
	private JButton createButton(String text) {
		JButton btn = new JButton(text); 
		btn.addActionListener(this);
		return btn; 
	}
	
	private JPanel createPanel(Color c) {
		JPanel pnl = new JPanel();
		pnl.setBackground(c);
		return pnl;
	}
	

	
	private JTextArea createTextArea() {
		JTextArea txtArea = new JTextArea(); 
		txtArea.setEditable(false);
		txtArea.setLineWrap(true);
		txtArea.setFont(new Font("Arial",Font.BOLD,24));
		txtArea.setBorder(BorderFactory.createEtchedBorder());
		return txtArea;
	}
	
	private void layoutButtonPanel() {
		GridBagLayout layout = new GridBagLayout();
	    pnlBottom.setLayout(layout);
	    
	    //add components to grid
	    GridBagConstraints constraints = new GridBagConstraints(); 
	    
	    //Defaults
	    constraints.fill = GridBagConstraints.NONE;
	    constraints.anchor = GridBagConstraints.CENTER;
	    constraints.weightx = 100;
	    constraints.weighty = 100;
	    
	    addToPanel(pnlBottom, btnLoad,constraints,0,0,2,1);
	}
	
	private void addToPanel(JPanel jp,Component c, GridBagConstraints constraints, int x, int y, int w, int h) {  
	      constraints.gridx = x;
	      constraints.gridy = y;
	      constraints.gridwidth = w;
	      constraints.gridheight = h;
	      jp.add(c, constraints);
	   }
	
	@Override
	public void run() {
		createGUI();
	}

	// need to load log files properly, just a dummy load
	@Override
	public void actionPerformed(ActionEvent e) {
		int returnVal;
		File file;
		fc = new JFileChooser();
		sb = new StringBuffer();
		
		// TODO Auto-generated method stub
		//Get event source 
		Object src = e.getSource(); 		      
		//Consider the alternatives - not all active at once. 
		if (src == btnLoad) {
			JButton btn = ((JButton) src);
			returnVal = fc.showOpenDialog(PizzaGUI.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				file = fc.getSelectedFile();
				sb.append("Opening: " + file.getName() + "\n");
				areDisplay.setText(sb.toString());
			} else {
				sb.append("Open command cancelled by user." + "\n");
				areDisplay.setText(sb.toString());
			}
		}
	}

}
