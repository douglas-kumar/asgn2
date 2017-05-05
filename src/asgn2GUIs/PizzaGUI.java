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
	private static final int FONT_SIZE = 12;
	private static final long serialVersionUID = -7031008862559936404L;
	public static final int WIDTH = 400, HEIGHT = 400;
	private PizzaRestaurant pr;
	private JButton btnLoad, btnDisplayInfo, btnCalc, btnReset;
	private JPanel pnlDisplay, pnlTop, 
		pnlBottom, pnlRight, pnlLeft;
	private JTextArea dataDisplay;
	private JFileChooser fc;
	private String quickDir = "C:\\Users\\calum\\Documents\\GitHub\\asgn2\\logs";
	
	/**
	 * Creates a new Pizza GUI with the specified title 
	 * @param title - The title for the supertype JFrame
	 */
	public PizzaGUI(String title) throws HeadlessException {
		super(title);
		pr = new PizzaRestaurant();
	}
	
	private void createGUI() {
		setSize(WIDTH, HEIGHT);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLayout(new BorderLayout());
	    
	    btnLoad = createButton("Load File");
	    btnDisplayInfo = createButton("Display Info");
	    btnCalc = createButton("Perform Calculations");
	    btnReset = createButton("Reset");
	    btnDisplayInfo.setEnabled(false);
	    btnCalc.setEnabled(false);
	    btnReset.setEnabled(false);
	    
	    pnlDisplay = createPanel(Color.WHITE);
	    pnlTop = createPanel(Color.LIGHT_GRAY);
	    pnlBottom = createPanel(Color.LIGHT_GRAY);
	    pnlRight = createPanel(Color.LIGHT_GRAY);
	    pnlLeft = createPanel(Color.LIGHT_GRAY);
	    
	    dataDisplay = createTextArea();
	    
	    pnlDisplay.setLayout(new BorderLayout());
	    pnlDisplay.add(dataDisplay, BorderLayout.CENTER);
	    
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
		txtArea.setFont(new Font("Arial",Font.BOLD,FONT_SIZE));
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
	    addToPanel(pnlBottom, btnDisplayInfo,constraints,6,0,2,1);
	    addToPanel(pnlBottom, btnCalc,constraints,0,6,2,1);
	    addToPanel(pnlBottom, btnReset,constraints,6,6,2,1);
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
	
	// Still implementing - not finished
	@Override
	public void actionPerformed(ActionEvent e) {
		int returnVal;
		File logFile;
		fc = new JFileChooser(quickDir);
		
		//Get event source 
		Object src = e.getSource(); 		      
		//Consider the alternatives - not all active at once. 
		if (src == btnLoad) {
			returnVal = fc.showOpenDialog(PizzaGUI.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				btnDisplayInfo.setEnabled(true);
				logFile = fc.getSelectedFile();
				try {
					pr.processLog(logFile.getName());
					if (src == btnDisplayInfo) {
					dataDisplay.setText("\tLog File: " + logFile.getName()
							+ "\n\n" + "Customer" 
							+ "\n\n" + "Customer Name: "
							+ "\n" + "Mobile Number: " 
							+ "\n" + "Customer Type: "
							+ "\n" + "X and Y Location: "
							+ "\n" + "Distance From Restaurant: "
							+ "\n\n" + "Pizza"
							+ "\n\n" + "Pizza Type: "
							+ "\n" + "Quantity: "
							+ "\n" + "Order Price: "
							+ "\n" + "Order Cost: "
							+ "\n" + "Order Profit: "
							);
					}
				} catch (CustomerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (PizzaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (LogHandlerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} 
		} // end src == btnLoad
	}
}


