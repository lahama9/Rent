package View;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Controller.DBConnection;
import Model.Account;
import Model.Landlord;
import Model.Tenant;

/**
 * SignUpGui class
 * A class that is responsible for data collection and verification of information to create an account. 
 * @author LA Hamaker -- lahama9
 * @date 13 AUG 2015
 * @version 3
	
 */
public class SignUpGui extends JFrame{

	private JLabel firstLabel, lastLabel, phoneLabel, accountLabel, emailLabel, pwdLabel, pwdLabel2, titleLabel, spacerLabel;
	private JTextField firstText, lastText, phoneText, accountText, emailText; 
	private JButton submitBtn;
	private JPasswordField pwdText, pwdText2;
	private Header headerPanel;
	private MenuPanel menuPanel;
	private JRadioButton tenRadio, landRadio;
	private ButtonGroup userGroup;

	//Class Constructor
	public SignUpGui(){
		
		//Set the Frame properties
		this.setSize(Values.FRAME_WIDTH,Values.FRAME_HEIGHT);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Rental Finder");
		this.setLayout(new BorderLayout());

		//HEADER PANEL
		headerPanel = new Header("RENT FINDER   ");
		this.add(headerPanel, BorderLayout.NORTH);
		
		//MENU PANEL
		menuPanel = new MenuPanel(this);
		this.add(menuPanel, BorderLayout.WEST);
		
		//MAIN PANEL
	 	//Title
	 	JPanel form = new JPanel(new FlowLayout());
        titleLabel = new JLabel("Sign Up");
        titleLabel.setFont(new Font(Values.FONT_TYPE, Font.BOLD, Values.FONT_SIZE));

       //Panel that will hold the form
        JPanel g = new JPanel(new GridLayout(0,2,4,4));
       
        //Labels
        firstLabel = new JLabel("First Name:");
        lastLabel = new JLabel("Last Name: ");
        phoneLabel = new JLabel("Phone:");
        emailLabel = new JLabel("Email");
        accountLabel = new JLabel("Account:");
        pwdLabel = new JLabel("Password:");
        pwdLabel2 = new JLabel("Confirm Password:");
        spacerLabel = new JLabel("           ");
        
        //TextBoxes
        firstText = new JTextField();
        lastText = new JTextField();
        phoneText = new JTextField();
        emailText = new JTextField();
        accountText = new JTextField();
        pwdText = new JPasswordField();
        pwdText2 = new JPasswordField();

        //Radio buttons and Submit button
        Box userBox = Box.createVerticalBox();
        
		tenRadio = new JRadioButton("Tenant");
		tenRadio.setActionCommand("tenant");
		tenRadio.setSelected(true);
		landRadio = new JRadioButton("Landlord");
		landRadio.setActionCommand("landlord");
		
		userGroup = new ButtonGroup();
		userGroup.add(tenRadio);
		userGroup.add(landRadio);
		
        //The button and the listener
        submitBtn = new JButton("Submit");
		submitBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String first, last, phone, email, account, type, pwd, pwd2;
				DBConnection dbc = new DBConnection();
			
				if(e.getSource() == submitBtn){
					//Validate information exists
					if(!(firstText.getText().equals("")) && !(lastText.getText().equals("")) 
							 && !(phoneText.getText().equals("")) && !(emailText.getText().equals("")) &&
							 !(accountText.getText().equals("")) && !(pwdText.getText().equals("")) && 
							 !(pwdText2.getText().equals(""))){
					
							//check to see if passwords entered, match.
							pwd = pwdText.getText();
							pwd2 = pwdText2.getText();
							
							if(pwd.equals(pwd2)){
								//check to see if account name already exists
								account = accountText.getText();
								
								if(dbc.getLandlordID(account) == -1 && dbc.getTenantID(account) == -1){
									first = firstText.getText();
									last = lastText.getText();
									phone = phoneText.getText();
									email = emailText.getText();
									type = userGroup.getSelection().getActionCommand();
									
									 if (type.equals("tenant")) {
										 System.out.println("In tenant");
										 Account tAcct = new Account(account, pwd, type, true);
										 Tenant ten = new Tenant(first, last, phone, email, -1, account);
										 dbc.addAccount(tAcct);
										 dbc.addTenant(ten);
								       } else if(type.equals("landlord")){
								    	   type = userGroup.getSelection().getActionCommand();
								    	   System.out.println("In landlord");
								    	    Account lAcct = new Account(account, pwd, type, true);
											Landlord land = new Landlord(first, last, phone, email, -1, account);
											dbc.addAccount(lAcct);
											dbc.addLandlord(land);
								    	   
								       }
									
									JOptionPane.showMessageDialog (null, "Your " + type + " account has been created.  Please log in.", 
											"Enter your information", JOptionPane.INFORMATION_MESSAGE);
									redirectUser();
									
								} else{
									JOptionPane.showMessageDialog (null, "That account name already exists in the system.", 
											"Enter your information", JOptionPane.INFORMATION_MESSAGE);
									accountText.setText("");
									accountText.requestFocus();
								}
							}else{
								//Checks for matching passwords
								JOptionPane.showMessageDialog (null, "Your passwords do not match.  PLease try again", 
										"Enter your information", JOptionPane.INFORMATION_MESSAGE);
								pwdText.setText("");
								pwdText2.setText("");
								pwdText.requestFocus();
							}
					} else{
						//Checks for form completeness
						JOptionPane.showMessageDialog (null, "Please complete the entire form", 
								"Enter your information", JOptionPane.INFORMATION_MESSAGE);
					}		
				}
			}
		});
		
		//add radio group, buttons to box 
		userBox.add(tenRadio);
		userBox.add(landRadio);
		userBox.add(submitBtn);
		userBox.setBorder(BorderFactory.createTitledBorder("Account"));

		//Add components to GridView Panel
		g.add(titleLabel);
		g.add(spacerLabel);
		
		g.add(spacerLabel);
		g.add(spacerLabel);
		
		g.add(firstLabel);
		g.add(firstText);
		
		g.add(lastLabel);
		g.add(lastText);
		
		g.add(phoneLabel);
		g.add(phoneText);
		
		g.add(emailLabel);
		g.add(emailText);
		
		g.add(accountLabel);
		g.add(accountText);
		
		g.add(pwdLabel);
		g.add(pwdText);
		
		g.add(pwdLabel2);
		g.add(pwdText2);

        //Add to the borderLayout panel
        form.add(g);
        form.add(userBox);
      
        //add to this instance of the MainCard
        this.add(form, BorderLayout.CENTER);
        this.setResizable(false);
		this.setVisible(true);
	}

	private void redirectUser(){
		this.setVisible(false);
		this.dispose();
		new LogInGui();
	}
}