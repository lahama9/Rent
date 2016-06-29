package View;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Calendar;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Controller.DBConnection;
import Model.Request;

/**
 * 
 * Properties GUI
 * A class that creates the properties GUI for a landlord.
 * @author LA Hamaker - lahama9@uw.edu
 * @version 1.5
 * @date 17 AUG 2015
 */

public class RequestGui extends JFrame{

	private Header headerPanel;
	private MenuPanel menuPanel;
	private JLabel cityLabel,stateLabel, zipCodeLabel, detailsLabel,titleLabel, spacer;
	private JTextField streetText, cityText, zipCodeText;
	private JComboBox<String> stateBox;
	private JButton submitBtn;
	private JPanel formGrid, formContainer;
	private JTextArea detailsArea;
	private String details;
	

	
	//Constructor
	public RequestGui(){
	
	//Set the Frame properties
	this.setSize(Values.FRAME_WIDTH,Values.FRAME_HEIGHT);
	this.setLocationRelativeTo(null);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setTitle("Rental Finder - Make Request");
	this.setLayout(new BorderLayout(10,10));
	
	//HEADER PANEL
	headerPanel = new Header("RENT FINDER   ");
	this.add(headerPanel, BorderLayout.NORTH);
	
	//MENU PANEL
	menuPanel = new MenuPanel(this);
	this.add(menuPanel, BorderLayout.WEST);
	
	//MAIN PANEL
	BorderLayout bl = new BorderLayout();
	bl.setVgap(Values.V_GAP);
	bl.setVgap(Values.H_GAP);
	
	//Title of container Panel
 	formContainer = new JPanel(bl);
    titleLabel = new JLabel("Add a Request");
    titleLabel.setFont(new Font(Values.FONT_TYPE, Font.BOLD, Values.FONT_SIZE));
    
    //Panel that will hold the form
    formGrid = new JPanel(new GridLayout(0,2,2,2));
	
    //Labels
    cityLabel = new JLabel("City: ");
    stateLabel = new JLabel("State: ");
    zipCodeLabel = new JLabel("Zip Code:");
    detailsLabel = new JLabel("Details: ");
    spacer = new JLabel("                               ");
    
    //Text Boxes
    streetText = new JTextField();
    cityText = new JTextField();
    zipCodeText  = new JTextField();
    
    //Text Area
    detailsArea = new JTextArea();
    detailsArea.setColumns(30);
    detailsArea.setRows(5);
    detailsArea.setLineWrap(true);
    
	//Combo Box
	stateBox = new JComboBox<String>(Values.STATES);
	
	submitBtn = new JButton("Submit");
	submitBtn.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
		String city, zip, state;
		int tenId;
		
		Calendar currDate = Calendar.getInstance();
		Date d = new Date((currDate.getTime()).getTime());
		
		DBConnection dbc = new DBConnection();
		
		if(!(cityText.getText().equals("")) && !(zipCodeText.getText().equals(""))){
		    city = cityText.getText();
		    zip = zipCodeText.getText();
		   
		    tenId = dbc.getTenantID(LogStatus.accountName());
		    state = stateBox.getSelectedItem().toString();
		    details = detailsArea.getText();
		    
		    Request req = new Request(null, city, state, zip, details, d,tenId);
		    dbc.addRequest(req);
			
		    clearText();
		    JOptionPane.showMessageDialog (null, "Your Request has been added.", 
					"Request Added", JOptionPane.INFORMATION_MESSAGE);
		   
		}else{
			//Checks for form completeness
			JOptionPane.showMessageDialog (null, "Please complete the entire form", 
					"Enter your information", JOptionPane.INFORMATION_MESSAGE);
		}}});
	
	 //Adding the form components to the grid
	 
	 formGrid.add(cityLabel);
	 formGrid.add(cityText);
	 
	 formGrid.add(stateLabel);
	 formGrid.add(stateBox);
	 
	 formGrid.add(zipCodeLabel);
	 formGrid.add(zipCodeText);
	 
	 formGrid.add(detailsLabel);
	 
	 Box b = Box.createVerticalBox();
	 b.add(titleLabel);
	 b.add(formGrid);
	 b.add(detailsArea);
	 b.add(submitBtn);
	
	 JPanel wrapper = new JPanel(new FlowLayout());
	 wrapper.add(b);

	 formContainer.add(wrapper, BorderLayout.WEST);
	 
	 this.add(formContainer);
     this.setResizable(false);
	 this.setVisible(true);
	}
	
	private void clearText(){
		streetText.setText("");
	    cityText.setText("");
	    zipCodeText.setText("");
	}
}