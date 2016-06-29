package View;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controller.DBConnection;
import Model.Account;

/**
 * LogInGui
 * A class that serves as the GUI where an established user may log in. 
 * 
 * @author LA Hamaker -- lahama9@uw.edu
 * @date 17 AUG 2015
 * @version 1.4
 * 
 */
public class LogInGui extends JFrame{

	private Account userAcct;
	private JLabel titleLabel, accountNameLabel, passwordLabel, spacer;
	private JTextField accountNameText; 
	private JPasswordField passwordText;
	private JButton submitBtn;
	private Header headerPanel;
	private MenuPanel menuPanel;
	
	//Constructor - creates GUI layout
	public LogInGui(){

		//Set the Frame properties
		this.setSize(Values.FRAME_WIDTH,Values.FRAME_HEIGHT);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Rental Finde - Login");
		this.setLayout(new BorderLayout(10,10));

		///////////HEADER PANEL////////////////////
		headerPanel = new Header("RENT FINDER   ");
		this.add(headerPanel, BorderLayout.NORTH);
			
		//////////////MENU PANEL//////////////////
		menuPanel = new MenuPanel(this);
		this.add(menuPanel, BorderLayout.WEST);
			
		////////////MAIN PANEL/////////////
		//Border Layout
	 	BorderLayout bl = new BorderLayout();
	 	bl.setVgap(Values.V_GAP);
	 	bl.setVgap(Values.H_GAP);
		 	
	 	//Title
	 	JPanel form = new JPanel(bl);
        titleLabel = new JLabel("Sign Up");
        titleLabel.setFont(new Font(Values.FONT_TYPE, Font.BOLD, Values.FONT_SIZE));
        spacer = new JLabel("             ");
        
        JPanel flowPanel = new JPanel(new FlowLayout());

       //Panel that will hold the form
        JPanel logGrid = new JPanel(new GridLayout(0,2,4,4));
	       
        //Labels
        accountNameLabel = new JLabel("Account Name:");
        passwordLabel = new JLabel("Password: ");
        
        //TextFields
        accountNameText = new JTextField();
        passwordText = new JPasswordField();
        
        //Button
        submitBtn = new JButton("Log In");
        submitBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == submitBtn){
					DBConnection dbc = new DBConnection();
					if(!(accountNameText.getText().equals("")) && !(passwordText.getText().equals(""))){
						//Send info to ConnMgr
						String name = accountNameText.getText();
						String pwd = passwordText.getText();
						
						userAcct = dbc.getAccount(name,pwd);
						if(userAcct != null){
							//Get account name
							LogStatus.setAccountName(name);
							String type = userAcct.getType();
							
							//Set the type of account logged into. 
							if(type.equals("tenant")){
								LogStatus.setStatus(1);
							}else{
								LogStatus.setStatus(2);
							}
							
							JOptionPane.showMessageDialog (null, "The " + type + " account for " + name + 
									"\nhas logged on.", "Logged in!", JOptionPane.INFORMATION_MESSAGE);
							//redirect the user
							redirect();
						}else{
							accountNameText.setText("");
							passwordText.setText("");
							accountNameText.requestFocus();
							JOptionPane.showMessageDialog (null, "The user name and password "
									+ "you entered does\n not match our system records. Please try again.", 
									"No account record found", JOptionPane.INFORMATION_MESSAGE);
						}
					}else{
						JOptionPane.showMessageDialog (null, "Please enter both your user name and password", 
								"Enter your information", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
        
        //Labels and Text fields for form
        logGrid.add(titleLabel);
        logGrid.add(spacer);
        logGrid.add(accountNameLabel);
        logGrid.add(accountNameText);
        logGrid.add(passwordLabel);
        logGrid.add(passwordText);
        logGrid.add(submitBtn);
        
        //Add to parents
        flowPanel.add(logGrid);
        form.add(flowPanel);
        
        //finish frame properties
        this.add(form, BorderLayout.CENTER);
        this.setResizable(false);
		this.setVisible(true);
	}
	
	//A private method that closes this frame and redirects the user. 
	private void redirect(){
		this.setVisible(false);
		this.dispose();
		new WelcomeGui();
	}

}
