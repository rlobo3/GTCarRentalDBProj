package rentalcar;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Rochelle Lobo
 * This class helps the user in register in the system.
 */

public class RegisterPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	JLabel userN, passw, confPwd, type;
    JTextField userName, password, confirmP;
    JButton register, cancel;
    @SuppressWarnings("rawtypes")
	JComboBox userType;
    private final int WIDTH = 400, HEIGHT = 500;

    public RegisterPanel() {
        userN = new JLabel("Username: ");
        userName = new JTextField(20);
    
        passw = new JLabel("Password: ");
        password = new JTextField(20);
        
        confPwd = new JLabel("Confirm Password: ");
        confirmP = new JTextField(20);
        
        type = new JLabel("Type of User: ");
        
        cancel = new JButton("Cancel and Exit");
        cancel.addActionListener(this);
        register = new JButton("Register");
        register.addActionListener(this);
        
        this.add(userN);
        this.add(userName);
        this.add(passw);
        this.add(password);
        this.add(confPwd);
        this.add(confirmP);
        this.add(type);
        this.add(register);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == register) {
            //TODO
        }
        else {
            //TODO
        }
    }
}
