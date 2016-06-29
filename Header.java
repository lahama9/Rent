package View;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * 
 * HeaderCard
 * A class that creates header cards to be used with a frame layout. 
 * @author LA Hamaker -- lahama9@uw.edu
 * @version 1.3
 * @date 08 AUG 2015
 *
 */
public class Header extends JPanel{
	
	//Class Members
	private String imageAddy, title;
	private JLabel logo, headerText;
	
	public static final int PAD = 10;
	public static final int FONT_SIZE = 80;
	public static final String FONT_TYPE = "Serif";
	public static final Color FONT_COLOR = Color.WHITE;
	public static final Color BACK_COLOR = Color.BLACK;
	public static final int LOGO_WIDTH = 50;
	public static final int LOGO_HEIGHT = 50;
	
	//Class Constructor
	public Header(String title){ 
		this.title = title;
	
		//Set layout for this card
		//this.setLayout(new FlowLayout(FlowLayout.LEFT, PAD, PAD));
		
		//JLabel with the icon
		logo = new JLabel(new ImageIcon("house.png"));
		logo.setSize(LOGO_WIDTH, LOGO_HEIGHT);
		
		//JLabel with the header text
		headerText = new JLabel(title);
		headerText.setFont(new Font(FONT_TYPE, Font.BOLD, FONT_SIZE));
		headerText.setForeground(FONT_COLOR);
		this.setBackground(BACK_COLOR);
		this.add(logo);
		this.add(headerText);
		this.setBorder(new EmptyBorder(10, 10, 10, 10) );
	}
}