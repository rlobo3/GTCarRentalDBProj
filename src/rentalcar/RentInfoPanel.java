package rentalcar;

import javax.swing.JFrame;
import javax.swing.JPanel;

import core.User.MemberUser;

public class RentInfoPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private MemberUser member;
	private JFrame mainFrame;

	public RentInfoPanel(MemberUser member){
		this.mainFrame = MainFrame.getMain();
		this.member = member;
	}

}
