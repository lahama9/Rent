package View;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * MenPanel Class
 * Create a menu panel based on LogStatus
 * @author LA Hamaker -- lahama9@uw.edu
 * @version 1.3
 * @date 17 AUG 2015
 */
public class MenuPanel extends JPanel{

	//Class variables
	private DefaultListModel<String> dlm;
	private JList<String> menuItems;
	private String[] guestMenuItems, tenantMenuItems, landlordMenuItems;
	public static final int PAD = 5;
	public static final int FONT_SIZE = 20;
	public static final String FONT_TYPE = "Serif";
	private JFrame frame;
	
	//constructor
	public MenuPanel(JFrame frame){
		this.frame =frame;
		dlm = new DefaultListModel<String>();
		int i = LogStatus.getStatus();
		
		guestMenuItems  = new String[]{"HOME", "SIGNUP", "LOGIN", "FAQ", "EXIT"};
		tenantMenuItems = new String[]{"HOME", "REQUEST", "SEARCH", "FAQ", "LOGOUT", "EXIT"};
		landlordMenuItems  = new String[]{"HOME", "PROPERTY", "FIND", "FAQ", "LOGOUT", "EXIT"};
		
		if(i == 1){
			for(String item: tenantMenuItems){
				dlm.addElement(item);
			}
		}else if(i==2){
			for(String item: landlordMenuItems){
				dlm.addElement(item);
			}
		}else{
			for(String item: guestMenuItems){
				dlm.addElement(item);
			}
		}

		//Fill JList with menu items
		menuItems = new JList<String>(dlm);
		menuItems.setVisibleRowCount(dlm.size());
		menuItems.setFixedCellHeight(30);
		menuItems.setFixedCellWidth(130);
		menuItems.setFont(new Font(FONT_TYPE, Font.BOLD, FONT_SIZE));
		menuItems.addMouseListener(new MouseListener(){
			@Override
			public void mouseReleased(MouseEvent e) {
				if(e.getSource() == menuItems){
					String choice = (String) menuItems.getSelectedValue();
					//System.out.println(choice);
					findNewWindow(choice);
				}
			}
			@Override
			public void mouseClicked(MouseEvent e) {	
			}
			@Override
			public void mousePressed(MouseEvent e) {	
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		this.add(menuItems);
		this.setBorder(new EmptyBorder(10, 20, 10, 10) );
	}
	
	
	//finds the new GUI view based on the user menu choice
	private void findNewWindow(String s){
		switch (s){
		
		case "HOME": 
			frame.setVisible(false);
			frame.dispose();
			new WelcomeGui();
			break;
		
		case "SIGNUP":
			frame.setVisible(false);
			frame.dispose();
			new SignUpGui();
			break;
			
		case "FIND":
			frame.setVisible(false);
			frame.dispose();
			new FindTenantGui();
			break;
			
		case "SEARCH":
			frame.setVisible(false);
			frame.dispose();
			new SearchGui();
			break;
		
		case "REQUEST":
			frame.setVisible(false);
			frame.dispose();
			new RequestGui();
			break;
			
		case "PROPERTY":
			frame.setVisible(false);
			frame.dispose();
			new PropertiesGui();
			break;	
		
		case "LOGIN":
			frame.setVisible(false);
			frame.dispose();
			new LogInGui();
			break;
			
		case "FAQ":
			frame.setVisible(false);
			frame.dispose();
			new FaqGui();
			break;
			
		case "LOGOUT":
			frame.setVisible(false);
			frame.dispose();
			LogStatus.setStatus(0);
			LogStatus.setAccountName("");
			new WelcomeGui();
			break;
			
		case "EXIT":
			frame.setVisible(false);
			frame.dispose();
			break;
			
		default:
			new WelcomeGui();
			break;
		}
	}
}