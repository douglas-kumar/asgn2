package asgn2GUIs;

import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;
import java.io.File;
import java.text.DecimalFormat;
import java.util.*;

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
 * Currently it is a �dummy� class which extends JFrame and implements Runnable and ActionLister. 
 * It should contain an instance of an asgn2Restaurant.PizzaRestaurant object which you can use to 
 * interact with the rest of the system. You may choose to implement this class as you like, including changing 
 * its class signature � as long as it  maintains its core responsibility of acting as a GUI for the rest of the system. 
 * You can also use this class and asgn2Wizards.PizzaWizard to test your system as a whole
 * 
 * 
 * @author Person A and Person B
 *
 */
public class PizzaGUI extends javax.swing.JFrame implements Runnable, ActionListener {
	private static final int SIXTH_SEGMENT = 5, FIFTH_SEGMENT = 4, 
			FOURTH_SEGMENT = 3, THIRD_SEGMENT = 2, SECOND_SEGMENT = 1, 
			FIRST_SEGMENT = 0, MAX_DATA_SEGMENT = 9, FONT_SIZE = 12;
	private static final long serialVersionUID = -7031008862559936404L;
	public static final int WIDTH = 500, HEIGHT = 500;
	private PizzaRestaurant pr;
	private JButton btnLoad, btnDisplayInfo, btnCalc, btnReset;
	private JPanel pnlDisplay, pnlTop, 
	pnlBottom, pnlRight, pnlLeft;
	private JTextArea tempData; // remove
	private JTable dataDisplay;
	private JScrollPane scroller;
	private JComboBox filter;
	private JFileChooser fc;
	private String quickDir = "C:\\Users\\calum\\Documents\\GitHub\\asgn2\\logs", 
			filterString;
	private String[] columnNamesCustomer = {"Name", "Number", "Type", "X-Location", "Y-Location", "Distance"},
			columnNamesPizza = {"Type", "Quantity", "Order Price", "Order Cost", "Order Profit"},
			filterChoice = {"Customer Info", "Pizza Info"};

	String[][] logData = {
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
			{"", "", "", "", "", ""},
	};

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

		tempData = createTextArea(); // remove

		filter = new JComboBox(filterChoice);

		pnlDisplay.setLayout(new BorderLayout());
		pnlRight.setLayout(new BorderLayout());
		pnlRight.add(filter, BorderLayout.CENTER); // edit


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

	private void clearTable(JTable table) {
		for (int i = 0; i < table.getRowCount(); i++) {
			for(int j = 0; j < table.getColumnCount(); j++) {
				table.setValueAt("", i, j);
			}
		}
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
				btnReset.setEnabled(true);
				btnLoad.setEnabled(false);
				logFile = fc.getSelectedFile();
				try {
					pr.processLog(logFile.getName());
					/*					if (src == btnDisplayInfo) {
					pnlDisplay.setVisible(false);
					dataDisplay = new JTable(sampleData, columnNamesCustomer);
				    dataDisplay.setPreferredScrollableViewportSize(new Dimension(500,50));
				    dataDisplay.setFillsViewportHeight(true);
				    scroller = new JScrollPane(dataDisplay);
				    add(scroller);
					dataDisplay.setVisible(true);
					scroller.setVisible(true);
					}*/
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

		if (src == btnDisplayInfo) {
			pnlDisplay.setVisible(false);
			btnCalc.setEnabled(true);
			pnlDisplay.setVisible(false);

			filterString = (String) filter.getSelectedItem();

			if (filterString.contentEquals("Customer Info")) {
				dataDisplay = new JTable(logData, columnNamesCustomer);
				dataDisplay.setPreferredScrollableViewportSize(new Dimension(500,50)); // add constants
				dataDisplay.setFillsViewportHeight(true);
				scroller = new JScrollPane(dataDisplay);
				add(scroller);

				for (int dataLine = 0; dataLine < pr.getNumCustomerOrders(); dataLine++){
					for (int dataSegment = 0; dataSegment < MAX_DATA_SEGMENT; dataSegment++) {
						switch (dataSegment) {
						case FIRST_SEGMENT:
							try {
								logData[dataLine][dataSegment] = pr.getCustomerByIndex(dataLine).getName();
							} catch (CustomerException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							break;
						case SECOND_SEGMENT:
							try {
								logData[dataLine][dataSegment] = pr.getCustomerByIndex(dataLine).getMobileNumber();
							} catch (CustomerException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							break;
						case THIRD_SEGMENT:
							try {
								logData[dataLine][dataSegment] = pr.getCustomerByIndex(dataLine).getCustomerType();
							} catch (CustomerException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							break;
						case FOURTH_SEGMENT:
							try {
								String xLocation = Integer.toString(pr.getCustomerByIndex(dataLine).getLocationX());
								logData[dataLine][dataSegment] = xLocation;
							} catch (CustomerException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							break;
						case FIFTH_SEGMENT:
							try {
								String yLocation = Integer.toString(pr.getCustomerByIndex(dataLine).getLocationY());
								logData[dataLine][dataSegment] = yLocation;
							} catch (CustomerException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							break;
						default:
							break;
						}
					}
				} // end of first for loop
			} else if (filterString.contentEquals("Pizza Info")) {
				dataDisplay = new JTable(logData, columnNamesPizza);
				dataDisplay.setPreferredScrollableViewportSize(new Dimension(500,50)); // add constants
				dataDisplay.setFillsViewportHeight(true);
				scroller = new JScrollPane(dataDisplay);
				add(scroller);

				for (int dataLine = 0; dataLine < pr.getNumPizzaOrders(); dataLine++){
					for (int dataSegment = 0; dataSegment < MAX_DATA_SEGMENT; dataSegment++) {
						switch (dataSegment) {
						case FIRST_SEGMENT:
							try {
								logData[dataLine][dataSegment] = pr.getPizzaByIndex(dataLine).getPizzaType();
							} catch (PizzaException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							break;
						case SECOND_SEGMENT:
							try {
								String quantity = Integer.toString(pr.getPizzaByIndex(dataLine).getQuantity());
								logData[dataLine][dataSegment] = quantity;
							} catch (PizzaException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							break;
						case THIRD_SEGMENT:
							try {
								String orderPrice = Double.toString(pr.getPizzaByIndex(dataLine).getOrderPrice());
								logData[dataLine][dataSegment] = orderPrice;
							} catch (PizzaException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							break;
						case FOURTH_SEGMENT:
							try {
								String orderCost = Double.toString(pr.getPizzaByIndex(dataLine).getOrderCost());
								logData[dataLine][dataSegment] = orderCost;
							} catch (PizzaException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							break;
						default:
							break;
						}
					}
				} // end of first for loop
			}
		}

		if (src == btnCalc) {
			if (filterString.contentEquals("Customer Info")) {
				for (int dataLine = 0; dataLine < pr.getNumCustomerOrders(); dataLine++){
					for (int dataSegment = 0; dataSegment < MAX_DATA_SEGMENT; dataSegment++) {
						switch (dataSegment) {
						case SIXTH_SEGMENT:
							try {
								String distance = Double.toString(pr.getCustomerByIndex(dataLine).getDeliveryDistance());
								logData[dataLine][dataSegment] = distance;
							} catch (CustomerException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							break;
						default:
							break;
						}
					}
				}
			} else if (filterString.contentEquals("Pizza Info")) {
				for (int dataLine = 0; dataLine < pr.getNumPizzaOrders(); dataLine++){
					for (int dataSegment = 0; dataSegment < MAX_DATA_SEGMENT; dataSegment++) {
						switch (dataSegment) {
						case FIFTH_SEGMENT:
							try {
								String orderProfit = Double.toString(pr.getPizzaByIndex(dataLine).getOrderProfit());
								logData[dataLine][dataSegment] = orderProfit;
							} catch (PizzaException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							break;
						default:
							break;
						}
					}
				}
			}
			//btnCalc.setEnabled(false); --> Needs to be disabled when pressed?
		}

		if (src == btnReset) {
			btnDisplayInfo.setEnabled(false);
			btnCalc.setEnabled(false);
			btnReset.setEnabled(false);
			btnLoad.setEnabled(true);
			dataDisplay.removeAll(); // Decide if better method \/
			clearTable(dataDisplay); // Decide if better method ^
			scroller.removeAll();
			pnlDisplay.setVisible(true);
			tempData.setText(null); // remove
			pr.resetDetails();
		}

	}
}


