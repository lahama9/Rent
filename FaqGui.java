package View;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * FaqGui
 * A class that serves as the GUI where an established user may log in. 
 * 
 * @author LA Hamaker -- lahama9@uw.edu
 * @date 17 AUG 2015
 * @version 1.4
 * 
 */
public class FaqGui extends JFrame{
	//Class Members
	private Header headerPanel;
	private MenuPanel menuPanel;
	private JPanel faqPanel;
	private JLabel faqLabel;

	
	//Constructor
	public FaqGui(){
		
		//Set the Frame properties
		this.setSize(Values.FRAME_WIDTH, Values.FRAME_HEIGHT);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Rental Finder - FAQ");
		this.setLayout(new BorderLayout());

		///////////HEADER PANEL////////////////////
		headerPanel = new Header("RENT FINDER   ");
		this.add(headerPanel, BorderLayout.NORTH);
		
		//////////////MENU PANEL//////////////////
		menuPanel = new MenuPanel(this);
		this.add(menuPanel, BorderLayout.WEST);
		
		////////////MAIN PANEL/////////////
		faqPanel = new JPanel();
		faqLabel = new JLabel();
		faqLabel.setText("Frequently Asked Questions Page");
		faqLabel.setFont(new Font(Values.FONT_TYPE, Font.BOLD, Values.HEADER_SIZE));
		faqPanel.add(faqLabel);
		
		this.add(faqPanel, BorderLayout.CENTER);
		this.setResizable(false);
		this.setVisible(true);
	
	}
}
	

