package View;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.DBConnection;
import Model.Landlord;
import Model.Property;
import Model.Tenant;

/**
 * SearchGui Class
 * A class that handles the GUI for the tenant search page.
 * 
 * @author LA Hamaker -- lahama9@uw.edu
 * @date 17 AUG 2015
 * @version 1.4
 * 
 */
public class SearchGui extends JFrame{
	
	private JPanel container, panelSearch, panelResults, panelSelection, gridSearch, buttonPanel;
	private JButton searchBtn, selectBtn, backBtn, againBtn;
	private JLabel searchLabel, cityLabel, zipLabel, displayLabel, spacerLabel;
	private JTextField cityText, zipText;
	private JComboBox<String> statesBox;
	private Header headerPanel;
	private MenuPanel menuPanel;
	private List<Property> pArray;
	private DefaultListModel<String> listModel;
	private JList<String> userSearchResults;
	
	public static final int FONT_SIZE_RESULTS = 14;
	
	public SearchGui(){
		DBConnection dbc = new DBConnection();
		
		//Parent Frame 
		this.setSize(Values.FRAME_WIDTH,Values.FRAME_HEIGHT);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Rental Finder - Search Properties");
		this.setLayout(new BorderLayout(10,10));
		
		//HEADER PANEL (TOP)
		headerPanel = new Header("RENT FINDER   ");
		this.add(headerPanel, BorderLayout.NORTH);
		
		//MENU PANEL (LEFT)
		menuPanel = new MenuPanel(this);
		this.add(menuPanel, BorderLayout.WEST);
		
		////Level 1 Panel
		CardLayout c = new CardLayout();
		container = new JPanel(new BorderLayout());
		container.setLayout(c);
		
		//////////Search Panel and components (Card 1, Level 2)////////////////
		panelSearch = new JPanel(new FlowLayout());
		gridSearch = new JPanel(new GridLayout(20,2,4,0));
		
		searchLabel = new JLabel("Search for rental properties by city, state and zipcode.\n");
		searchLabel.setFont(new Font(Values.FONT_TYPE, Font.BOLD, Values.FONT_SIZE));
		
		cityLabel = new JLabel("City: ");
		cityText = new JTextField();
		spacerLabel = new JLabel("           ");
		
		statesBox = new JComboBox<String>(Values.STATES);
		
		zipLabel = new JLabel("Zip Code:  ");
		zipText = new JTextField();
		
		searchBtn = new JButton("Search");
		searchBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == searchBtn){
					String state = (String) statesBox.getSelectedItem();
					pArray = dbc.searchProperties(cityText.getText(), state, zipText.getText()); 
					if(!pArray.isEmpty()){
					listModel = new DefaultListModel<String>();
					userSearchResults = new JList<String>(listModel);
					
						for(Property p: pArray){
							listModel.addElement(p.getStreet());
						}
					
					//format the list of results
					userSearchResults.setVisibleRowCount(listModel.size());
					userSearchResults.setFixedCellHeight(20);
					userSearchResults.setFixedCellWidth(200);
					userSearchResults.setFont(new Font(Values.FONT_TYPE, Font.BOLD, FONT_SIZE_RESULTS));
					
					//Add to the parent panel and the card layout
					panelResults.add(userSearchResults, BorderLayout.CENTER);
					c.show(container, "2");}
					
					else{
						JOptionPane.showMessageDialog (null, "No rentals returned.  Please try again.", 
								"No Results", JOptionPane.INFORMATION_MESSAGE);
					}
				}}});
		
		gridSearch.add(searchLabel);
		gridSearch.add(spacerLabel);
		gridSearch.add(cityLabel);
		gridSearch.add(cityText);
		gridSearch.add(zipLabel);
		gridSearch.add(zipText);
		gridSearch.add(spacerLabel);
		gridSearch.add(statesBox);
		gridSearch.add(spacerLabel);
		gridSearch.add(searchBtn);
		
		panelSearch.add(gridSearch);
		
		//Add the card to the card Layout
		//DO NOT CHANGE
		container.add(panelSearch, "1");

		////////Select Panel and components (Card 2, Level 2)////////////////////////
		panelResults = new JPanel(new BorderLayout());
		buttonPanel = new JPanel(new FlowLayout());
	
		//Select button and listener
		selectBtn = new JButton("Select");
		selectBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
					int selectedStreet = userSearchResults.getSelectedIndex();
					Property prop = pArray.get(selectedStreet);
					Landlord land = dbc.searchLandlord(prop.getLandlordID());
					
					StringBuilder sb = new StringBuilder("<html>Street Address: " + prop.getStreet()+ "<BR>");
					sb.append("City: " + prop.getCity() + "<BR>");
					sb.append("State: " + prop.getState() + "<BR>");
					sb.append("Type of Housing: " + prop.getType() + "<BR>");
					sb.append("Rent Rate: " + prop.getRate() + "<BR><BR>");
					
					sb.append("Landlord Information:" + "<BR>");
					sb.append("Name: " + land.getFirstName() + " " + land.getLastName() + "<BR>");
					sb.append("Phone: " + land.getPhoneNum() + "<BR>");
					sb.append("Email: " + land.getEmail());
					sb.append("</html>");
					
					String s = new String(sb.toString());
					displayLabel = new JLabel(s);
					panelSelection.add(displayLabel);
					c.show(container, "3");
				}});
		
		//Search Again button and listener
		againBtn = new JButton("Search Again");
		againBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
					new SearchGui();
				}});

		//Add buttons to the button panel
		buttonPanel.add(againBtn);
		buttonPanel.add(selectBtn);

		//Add button panel to this cards panel and add to card layout
		panelResults.add(buttonPanel, BorderLayout.SOUTH);
		container.add(panelResults, "2");

		//Result Panel and Components(Card 3, Level 2)///////////////////////
		panelSelection = new JPanel();
		Box resultBox = Box.createVerticalBox();
		
		//Back button and listener
		backBtn = new JButton("Back to Results");
		backBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
					displayLabel.setText("");
					c.show(container, "2");
				}});
		resultBox.add(backBtn);
		
		panelSelection.add(resultBox);
		
		//Add the card to the card Layout
		//DO NOT CHANGE
		container.add(panelSelection, "3");
		
		//Add to the Parent frame and show
		this.add(container);
		this.setVisible(true);
	}
}