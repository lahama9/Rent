package View;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Controller.DBConnection;
import Model.Property;

/**
 * 
 * Properties GUI
 * A class that creates the properties GUI for a landlord.
 * @author LA Hamaker - lahama9@uw.edu
 * @version 1.5
 * @date 17 AUG 2015
 * 
 */

public class PropertiesGui extends JFrame{

	private Header headerPanel;
	private MenuPanel menuPanel;
	private JLabel streetLabel, cityLabel,stateLabel, zipCodeLabel, propertyTypeLabel, rateLabel, titleLabel, spacer;
	private JTextField streetText, cityText, zipCodeText, rateText;
	private JRadioButton apartmentRadio, houseRadio;
	private ButtonGroup housingGroup;
	private JComboBox<String> stateBox;
	private JButton submitBtn;
	private JPanel formGrid, formContainer;
	

	
	//Constructor
	public PropertiesGui(){
	
	//Set the Frame properties
	this.setSize(Values.FRAME_WIDTH,Values.FRAME_HEIGHT);
	this.setLocationRelativeTo(null);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setTitle("Rental Finder - Properties");
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
    titleLabel = new JLabel("Add a Property");
    titleLabel.setFont(new Font(Values.FONT_TYPE, Font.BOLD, Values.FONT_SIZE));
    
    //Panel that will hold the form
    formGrid = new JPanel(new GridLayout(10,2,4,4));
	
    //Labels
    streetLabel = new JLabel("Street Address: ");
    cityLabel = new JLabel("City: ");
    stateLabel = new JLabel("State: ");
    zipCodeLabel = new JLabel("Zip Code:");
    propertyTypeLabel = new JLabel("Choose a Property Type");
    rateLabel = new JLabel("Rent Rate: ");
    spacer = new JLabel("               ");
    
    //Text Boxes
    streetText = new JTextField();
    cityText = new JTextField();
    zipCodeText  = new JTextField();
    rateText = new JTextField();
    
    //Radio Group
	Box housingBox = Box.createVerticalBox();
	
	apartmentRadio = new JRadioButton("Apartment");
	apartmentRadio.setActionCommand("apartment");
	apartmentRadio.setSelected(true);
	houseRadio = new JRadioButton("House");
	houseRadio.setActionCommand("house");
	
	housingGroup = new ButtonGroup();
	housingGroup.add(apartmentRadio);
	housingGroup.add(houseRadio);
    
	//Combo Box
	stateBox = new JComboBox<String>(Values.STATES);
	
	submitBtn = new JButton("Submit");
	submitBtn.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
		String street, city, zip, type;
		double rate;
		int landId;
		DBConnection dbc = new DBConnection();
		
		if(!(streetText.getText().equals("")) && !(cityText.getText().equals("")) 
				 && !(zipCodeText.getText().equals("")) && !(rateText.getText().equals(""))){

			street = streetText.getText();
		    city = cityText.getText();
		    zip = zipCodeText.getText();
		    rate = Double.parseDouble(rateText.getText());
		    type = housingGroup.getSelection().getActionCommand();
		    landId = dbc.getLandlordID(LogStatus.accountName());
		    String state = stateBox.getSelectedItem().toString();
		    
		    Property prop = new Property(street, city, state, zip, type, rate, landId);
		    dbc.addProperty(prop);
			
		    clearText();
		    JOptionPane.showMessageDialog (null, "Your Property has been added.", 
					"Property Added", JOptionPane.INFORMATION_MESSAGE);
		   
		}else{
			//Checks for form completeness
			JOptionPane.showMessageDialog (null, "Please complete the entire form", 
					"Enter your information", JOptionPane.INFORMATION_MESSAGE);
		}}});
	
	housingBox.add(apartmentRadio);
	housingBox.add(houseRadio);
	housingBox.setBorder(BorderFactory.createTitledBorder("Choose One"));
	
	//Adding the form components to the grid
	 formGrid.add(titleLabel);
	 formGrid.add(spacer);

	 formGrid.add(streetLabel);
	 formGrid.add(streetText);
	 
	 formGrid.add(cityLabel);
	 formGrid.add(cityText);
	 
	 formGrid.add(stateLabel);
	 formGrid.add(stateBox);
	 
	 formGrid.add(zipCodeLabel);
	 formGrid.add(zipCodeText);
	 
	 formGrid.add(rateLabel);
	 formGrid.add(rateText);
	 
	 formGrid.add(submitBtn);
	
	 JPanel wrapper = new JPanel();
	 wrapper.add(formGrid);
	 wrapper.add(housingBox);
	 
	 formContainer.add(wrapper);
	 
	 this.add(formContainer);
     this.setResizable(false);
	 this.setVisible(true);
	}
	
	private void clearText(){
		streetText.setText("");
	    cityText.setText("");
	    zipCodeText.setText("");
	    rateText.setText("");
	}
}