package rentalcar;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * 
 * @author Rochelle Lobo
 *	This class is the login screen where the user enters the system.
 */

public class LoginPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	JLabel userN, passw;
    JTextField userName, password;
    JButton signIn, cancel;
    private final int WIDTH = 400, HEIGHT = 500;
    
    public LoginPanel() {
        userN = new JLabel("Username");
        userName = new JTextField(20);
    
        passw = new JLabel("Password");
        password = new JTextField(20);
        
        signIn = new JButton("Sign in");
        signIn.addActionListener(this);
        cancel = new JButton("Cancel and Exit");
        cancel.addActionListener(this);
        
        this.add(userN);
        this.add(userName);
        this.add(passw);
        this.add(password);
        this.add(signIn);
        this.add(cancel);
		this.setSize(new Dimension(WIDTH, HEIGHT));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == signIn) {
            //TODO
        }
        else {
            //TODO
        }
    }
}
