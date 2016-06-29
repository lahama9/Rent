package View;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Controller.DBConnection;

/**
 * 
 * WelcomeGui class
 * A class that is the initial screen when the program starts.
 * @author LA Hamaker -- lahama9@uw.edu
 * @date 17 AUG 2015
 * @version 2.2
 *
 */
public class WelcomeGui extends JFrame{
	
	//Class Members
	private Header headerPanel;
	private MenuPanel menuPanel;
	private JLabel welcomeLabel;
	private JTextArea messageLabel;
	
		public static void main(String[] args) {
			
			DBConnection.createConnection();
			new WelcomeGui();
			
		}
		
		//Constructor
		public WelcomeGui(){
			
			//Set the Frame properties
			this.setSize(Values.FRAME_WIDTH,Values.FRAME_HEIGHT);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setTitle("Rental Finder - Welcome!");
			this.setLayout(new BorderLayout());

			//HeaderPanel
			headerPanel = new Header("RENT FINDER   ");
			this.add(headerPanel, BorderLayout.NORTH);
			
			//Menu Panel
			menuPanel = new MenuPanel(this);	
			this.add(menuPanel, BorderLayout.WEST);
			
			//Main Panel
			JPanel welcomePanel = new JPanel();
			welcomeLabel = new JLabel();
			welcomeLabel.setText("Welcome to Rent Finder");
			welcomeLabel.setFont(new Font(Values.FONT_TYPE, Font.BOLD, Values.HEADER_SIZE));
			welcomePanel.add(welcomeLabel);
			
			//Add components to the frame
			this.add(welcomePanel, BorderLayout.CENTER);
			this.setResizable(false);
			this.setVisible(true);
		}
}

