package rentalcar;
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
