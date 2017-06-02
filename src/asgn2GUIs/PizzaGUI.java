package asgn2GUIs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Restaurant.PizzaRestaurant;

/**
 * This class is the graphical user interface for the rest of the system.
 * Currently it is a ‘dummy’ class which extends JFrame and implements Runnable
 * and ActionLister. It should contain an instance of an
 * asgn2Restaurant.PizzaRestaurant object which you can use to interact with the
 * rest of the system. You may choose to implement this class as you like,
 * including changing its class signature – as long as it maintains its core
 * responsibility of acting as a GUI for the rest of the system. You can also
 * use this class and asgn2Wizards.PizzaWizard to test your system as a whole
 * 
 * 
 * @author Person A and Person B
 *
 */
public class PizzaGUI extends javax.swing.JFrame implements Runnable, ActionListener {
    private static final int SIXTH_SEGMENT = 5, FIFTH_SEGMENT = 4, FOURTH_SEGMENT = 3, THIRD_SEGMENT = 2,
            SECOND_SEGMENT = 1, FIRST_SEGMENT = 0, MAX_DATA_SEGMENT = 9, FONT_SIZE = 12;
    private static final long serialVersionUID = -7031008862559936404L;
    public static final int WIDTH = 700, HEIGHT = 600, TABLE_HEIGHT = 50, TABLE_WIDTH = 500;
    private PizzaRestaurant pizzaRestaurant;
    private JButton btnLoad, btnDisplayInfo, btnCalc, btnReset;
    private JPanel pnlDisplay, pnlTop, pnlBottom, pnlRight, pnlLeft;
    private DefaultTableModel tableModel;
    private JTable dataDisplay;
    private JScrollPane scroller;
    private JTextField totals;
    private JComboBox filter;
    private JFileChooser fileChooser;
    private Font font;
    private String quickDir = "C:\\Users\\dougm\\OneDrive\\BIT - Semester 3\\CAB302 Software Development\\git\\asgn2\\logs",
            filterString;
    private String[] columnNamesCustomer = { "Name", "Number", "Type", "X-Location", "Y-Location", "Distance" },
            columnNamesPizza = { "Type", "Quantity", "Order Price", "Order Cost", "Order Profit" },
            filterChoice = { "Customer Info", "Pizza Info" };

    /**
     * Creates a new Pizza GUI with the specified title
     * 
     * @param title
     *            - The title for the supertype JFrame
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

        filter = new JComboBox(filterChoice);
        totals = new JTextField();
        font = new Font(totals.getText(), Font.BOLD, 12);
        totals.setEditable(false);
        totals.setFont(font);

        pnlDisplay.setLayout(new BorderLayout());
        pnlRight.setLayout(new BorderLayout());
        pnlRight.add(filter, BorderLayout.CENTER); // edit
        pnlTop.setLayout(new BorderLayout());
        pnlTop.add(totals, BorderLayout.CENTER);

        this.getContentPane().add(pnlDisplay, BorderLayout.CENTER);
        this.getContentPane().add(pnlTop, BorderLayout.NORTH);
        this.getContentPane().add(pnlBottom, BorderLayout.SOUTH);
        this.getContentPane().add(pnlRight, BorderLayout.EAST);
        this.getContentPane().add(pnlLeft, BorderLayout.WEST);

        layoutButtonPanel();
        tableModel = new DefaultTableModel();

        dataDisplay = new JTable(tableModel);
        dataDisplay.setPreferredScrollableViewportSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));

        dataDisplay.setFillsViewportHeight(true);
        scroller = new JScrollPane(dataDisplay);
        add(scroller);

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

    private void layoutButtonPanel() {
        GridBagLayout layout = new GridBagLayout();
        pnlBottom.setLayout(layout);

        // add components to grid
        GridBagConstraints constraints = new GridBagConstraints();

        // Defaults
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 100;
        constraints.weighty = 100;

        addToPanel(pnlBottom, btnLoad, constraints, 0, 0, 2, 1);
        addToPanel(pnlBottom, btnDisplayInfo, constraints, 6, 0, 2, 1);
        addToPanel(pnlBottom, btnCalc, constraints, 0, 6, 2, 1);
        addToPanel(pnlBottom, btnReset, constraints, 6, 6, 2, 1);
    }

    private void addToPanel(JPanel jp, Component c, GridBagConstraints constraints, int x, int y, int w, int h) {
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = w;
        constraints.gridheight = h;
        jp.add(c, constraints);
    }

    private void clearTable(DefaultTableModel model) {
        for (int row = 0; row < model.getRowCount(); row++) {
            model.removeRow(row);
        }
    }

    @Override
    public void run() {
        createGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int returnVal;
        File logFile;
        fileChooser = new JFileChooser(quickDir);

        // Get event source
        Object src = e.getSource();
        // Consider the alternatives - not all active at once.
        if (src == btnLoad) {
            returnVal = fileChooser.showOpenDialog(PizzaGUI.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                logFile = fileChooser.getSelectedFile();
                try {

                    if (pizzaRestaurant.processLog(logFile.getName())) {
                        JOptionPane.showMessageDialog(this, "Log file loaded successfully", "Load Success",
                                JOptionPane.INFORMATION_MESSAGE);
                        btnDisplayInfo.setEnabled(true);
                        btnReset.setEnabled(true);
                        btnLoad.setEnabled(false);
                    } else {
                        JOptionPane.showMessageDialog(this, "Cannot load file, please choose a valid file",
                                "Load Failed", JOptionPane.INFORMATION_MESSAGE);
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

        if (src == btnDisplayInfo) {
            this.clearTable(tableModel);
            totals.setText("");

            filterString = (String) filter.getSelectedItem();

            if (filterString.contentEquals("Customer Info")) {

                tableModel.setColumnIdentifiers(columnNamesCustomer);

                for (int dataLine = 0; dataLine < pizzaRestaurant.getNumCustomerOrders(); dataLine++) {

                    if (tableModel.getRowCount() < pizzaRestaurant.getNumCustomerOrders()) {
                        tableModel.addRow(new Object[MAX_DATA_SEGMENT]);
                    }

                    for (int dataSegment = 0; dataSegment < MAX_DATA_SEGMENT; dataSegment++) {
                        switch (dataSegment) {
                        case FIRST_SEGMENT:
                            try {
                                tableModel.setValueAt(pizzaRestaurant.getCustomerByIndex(dataLine).getName(), dataLine,
                                        dataSegment);
                            } catch (CustomerException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            break;
                        case SECOND_SEGMENT:
                            try {
                                tableModel.setValueAt(pizzaRestaurant.getCustomerByIndex(dataLine).getMobileNumber(),
                                        dataLine, dataSegment);
                            } catch (CustomerException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            break;
                        case THIRD_SEGMENT:
                            try {
                                tableModel.setValueAt(pizzaRestaurant.getCustomerByIndex(dataLine).getCustomerType(),
                                        dataLine, dataSegment);
                            } catch (CustomerException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            break;
                        case FOURTH_SEGMENT:
                            try {
                                String xLocation = Integer
                                        .toString(pizzaRestaurant.getCustomerByIndex(dataLine).getLocationX());
                                tableModel.setValueAt(xLocation, dataLine, dataSegment);
                            } catch (CustomerException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            break;
                        case FIFTH_SEGMENT:
                            try {
                                String yLocation = Integer
                                        .toString(pizzaRestaurant.getCustomerByIndex(dataLine).getLocationY());
                                tableModel.setValueAt(yLocation, dataLine, dataSegment);

                            } catch (CustomerException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            break;
                        case SIXTH_SEGMENT:
                            try {
                                String distance = Double
                                        .toString(pizzaRestaurant.getCustomerByIndex(dataLine).getDeliveryDistance());
                                tableModel.setValueAt(distance, dataLine, dataSegment);
                            } catch (CustomerException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                        default:
                            break;
                        }
                    }
                } // end of first for loop

            } else if (filterString.contentEquals("Pizza Info")) {

                tableModel.setColumnIdentifiers(columnNamesPizza);

                for (int dataLine = 0; dataLine < pizzaRestaurant.getNumPizzaOrders(); dataLine++) {

                    if (tableModel.getRowCount() < pizzaRestaurant.getNumPizzaOrders()) {
                        tableModel.addRow(new Object[MAX_DATA_SEGMENT]);
                    }

                    for (int dataSegment = 0; dataSegment < MAX_DATA_SEGMENT; dataSegment++) {
                        switch (dataSegment) {
                        case FIRST_SEGMENT:
                            try {
                                tableModel.setValueAt(pizzaRestaurant.getPizzaByIndex(dataLine).getPizzaType(),
                                        dataLine, dataSegment);
                            } catch (PizzaException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            break;
                        case SECOND_SEGMENT:
                            try {
                                String quantity = Integer
                                        .toString(pizzaRestaurant.getPizzaByIndex(dataLine).getQuantity());
                                tableModel.setValueAt(quantity, dataLine, dataSegment);
                            } catch (PizzaException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            break;
                        case THIRD_SEGMENT:
                            try {
                                String orderPrice = Double
                                        .toString(pizzaRestaurant.getPizzaByIndex(dataLine).getOrderPrice());
                                tableModel.setValueAt(orderPrice, dataLine, dataSegment);
                            } catch (PizzaException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            break;
                        case FOURTH_SEGMENT:
                            try {
                                String orderCost = Double
                                        .toString(pizzaRestaurant.getPizzaByIndex(dataLine).getOrderCost());
                                tableModel.setValueAt(orderCost, dataLine, dataSegment);
                            } catch (PizzaException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            break;
                        case FIFTH_SEGMENT:
                            try {
                                String orderProfit = Double
                                        .toString(pizzaRestaurant.getPizzaByIndex(dataLine).getOrderProfit());
                                tableModel.setValueAt(orderProfit, dataLine, dataSegment);
                            } catch (PizzaException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                        default:
                            break;
                        }
                    }
                } // end of first for loop
            }

            dataDisplay.setModel(tableModel);
            pnlDisplay.updateUI();
            btnCalc.setEnabled(true);
        }

        if (src == btnCalc) {
            if (filterString.contentEquals("Customer Info")) {
                totals.setText("Total deliver distance: " + pizzaRestaurant.getTotalDeliveryDistance());
            } else if (filterString.contentEquals("Pizza Info")) {
                totals.setText("Total profit: " + pizzaRestaurant.getTotalProfit());
            }
            dataDisplay.setModel(tableModel);
            pnlDisplay.updateUI();
            btnCalc.setEnabled(false);
            btnDisplayInfo.setEnabled(true);
        }

        if (src == btnReset) {
            btnDisplayInfo.setEnabled(false);
            btnCalc.setEnabled(false);
            btnReset.setEnabled(false);
            btnLoad.setEnabled(true);
            tableModel = new DefaultTableModel();
            dataDisplay.setModel(tableModel);
            scroller.removeAll();
            this.remove(scroller);

            scroller = new JScrollPane(dataDisplay);
            this.add(scroller);

            pnlDisplay.updateUI();
            totals.setText("");

            this.repaint();

            pizzaRestaurant.resetDetails();
        }

    }
}
