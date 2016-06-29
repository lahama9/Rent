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
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.DBConnection;
import Model.Property;
import Model.Request;
import Model.Tenant;

/**
 * FindTenantGui
 * A class that serves as the GUI where a landlord can search for a tenant. 
 * 
 * @author LA Hamaker -- lahama9@uw.edu
 * @date 17 AUG 2015
 * @version 1.4
 * 
 */

public class FindTenantGui extends JFrame{
		
		private JPanel container, panelSearch, panelResults, panelSelection, gridSearch, buttonPanel;
		private JButton searchBtn, selectBtn, backBtn, againBtn;
		private JLabel searchLabel, cityLabel, zipLabel, spacerLabel;
		private JLabel displayLabel1, displayLabel2, displayLabel3, displayLabel4,displayLabel5,displayLabel6,displayLabel7,displayLabel8;
		private JTextField cityText, zipText;
		private JComboBox<String> statesBox;
		
		private Header headerPanel;
		private MenuPanel menuPanel;
		
		private List<Request> pArray;
		private DefaultListModel<String> listModel;
		private JList<String> userSearchResults;
		
		public static final int PAD = 5;
		public static final int FONT_SIZE_RESULTS = 14;
		
		public FindTenantGui(){
			DBConnection dbc = new DBConnection();
		
			//Parent Frame 
			this.setSize(Values.FRAME_WIDTH,Values.FRAME_HEIGHT);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setTitle("Rental Finder - Search Tenants");
			this.setLayout(new BorderLayout(10,10));
			
			//HEADER PANEL (TOP)
			headerPanel = new Header("RENT FINDER   ");
			this.add(headerPanel, BorderLayout.NORTH);
			
			//MENU PANEL (LEFT)
			menuPanel = new MenuPanel(this);
			this.add(menuPanel, BorderLayout.WEST);
			
			////Level 1 Panel
			CardLayout c = new CardLayout();
			container = new JPanel(new FlowLayout());
			container.setLayout(c);
			
			//////////Search Panel and components (Card 1, Level 2)////////////////
			panelSearch = new JPanel(new FlowLayout());
			gridSearch = new JPanel(new GridLayout(20,2,4,0));
			
			searchLabel = new JLabel("Search for potential tenants by city, state and zipcode.\n");
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
						//pArray = dbc.getProperties(LogStatus.getAccountName());
						pArray = dbc.searchRequests(cityText.getText(), state, zipText.getText()); 
						listModel = new DefaultListModel<String>();
						userSearchResults = new JList<String>(listModel);
						
							//Lists the zip code and detail of the request
							for(Request r: pArray){
								listModel.addElement(r.getZipCode() + "     " + r.getPostDate() + "     " + r.getDetail());
							}
						
						//format the list of results
						userSearchResults.setVisibleRowCount(listModel.size());
						userSearchResults.setFixedCellHeight(20);
						userSearchResults.setFixedCellWidth(200);
						userSearchResults.setFont(new Font(Values.FONT_TYPE, Font.BOLD, FONT_SIZE_RESULTS));
						
						//Add to the parent panel and the cardlayout
						panelResults.add(userSearchResults, BorderLayout.CENTER);
						c.show(container, "2");
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
			buttonPanel = new JPanel();
		
			//Select button and listener
			selectBtn = new JButton("Select");
			selectBtn.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					
						int selectedRequest = userSearchResults.getSelectedIndex();
						Request req = pArray.get(selectedRequest);
						Tenant ten = dbc.searchTenant(req.getTenantID());
						
						Box b = Box.createVerticalBox();
						
						displayLabel1 = new JLabel("Date: " + req.getPostDate());
						displayLabel2 = new JLabel("City: " + req.getCity()); 
						displayLabel3 = new JLabel("State: " + req.getState());
						displayLabel4 = new JLabel("Tenant Information:");
						displayLabel5 = new JLabel("Name: " + ten.getFirstName() + " " + ten.getLastName());
						displayLabel6 = new JLabel("Phone: " + ten.getPhoneNum());
						displayLabel7 = new JLabel("Email: " + ten.getEmail());
						displayLabel8 = new JLabel("Details: " + req.getDetail());
						
						b.add(displayLabel1);
						b.add(displayLabel2);
						b.add(displayLabel3);
						b.add(displayLabel4);
						b.add(displayLabel5);
						b.add(displayLabel6);
						b.add(displayLabel7);
						b.add(displayLabel8);
				
						panelSelection.add(b);
						c.show(container, "3");
					}});
			
			//Search Again button and listener
			againBtn = new JButton("Search Again");
			againBtn.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
						c.show(container, "1");
					}});

			//Add buttons to the button panel
			buttonPanel.add(againBtn);
			buttonPanel.add(selectBtn);

			//Add button panel to this cards panel and add to card layout
			panelResults.add(buttonPanel, BorderLayout.SOUTH);
			container.add(panelResults, "2");

			//Result Panel and Components(Card 3, Level 2)///////////////////////
			panelSelection = new JPanel();
			
			//Back button and listener
			backBtn = new JButton("Back to Results");
			backBtn.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {

						displayLabel1.setText("");
						displayLabel2.setText(""); 
						displayLabel3.setText(""); 
						displayLabel4.setText("");
						displayLabel5.setText("");
						displayLabel6.setText("");
						displayLabel7.setText("");
						displayLabel8.setText("");
						c.show(container, "2");
					}});
			panelSelection.add(backBtn);
			
			//Add the card to the card Layout
			//DO NOT CHANGE
			container.add(panelSelection, "3");
			
			//Add to the Parent frame and show
			this.add(container);
			this.setVisible(true);
		}
	}

