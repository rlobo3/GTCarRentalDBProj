package rentalcar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * @author Rochelle Lobo
 *	This class is the login screen where the user enters the system.
 */

public class LoginPanel extends JPanel{
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
        signIn.addActionListener(new SignInButtonListener());
        cancel = new JButton("Cancel and Exit");
        cancel.addActionListener(new CancelButtonListener());

        this.add(userN);
        this.add(userName);
        this.add(passw);
        this.add(password);
        this.add(signIn);
        this.add(cancel);
    }

    private class SignInButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String usernameEntered, passwordEntered;
            usernameEntered = userName.getText();
            passwordEntered = password.getText();
            if(usernameEntered == null || passwordEntered == null){
                JDialog lDialog = new JDialog(new JFrame(), "Login in Error");
                lDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                //lDialog.add(new LoginPanel());
                lDialog.setVisible(true);
            }else{

            }
        }
    }

    private class CancelButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {

        }
    }
}
