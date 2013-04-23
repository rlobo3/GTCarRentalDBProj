package rentalcar;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * 
 * @author Rochelle Lobo
 *	This class is the login screen where the user enters the system.
 */

public class LoginPanel extends JPanel{
    private static final long serialVersionUID = 1L;
    final static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    JLabel userN, passw;
    JTextField userName, password;
    JButton login, newUser;

    public LoginPanel() {
        this.setLayout(new FlowLayout());
        setBounds(screenSize.width/2-200, screenSize.height/2-100, 
                350, 130);
        
        userN = new JLabel("Username");
        userName = new JTextField(20);

        passw = new JLabel("Password");
        password = new JTextField(20);

        login = new JButton("Login");
        login.addActionListener(new LoginButtonListener());
        newUser = new JButton("New User?");
        newUser.addActionListener(new NewUserButtonListener());

        this.add(userN);
        this.add(userName);
        this.add(passw);
        this.add(password);
        this.add(newUser);
        this.add(login);
    }

    private class LoginButtonListener implements ActionListener {
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

    private class NewUserButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            JFrame mainFrame = MainFrame.getMain();
            mainFrame.setContentPane(new RegisterPanel());
            mainFrame.setBounds(mainFrame.getContentPane().getBounds());
            mainFrame.setVisible(true);
            mainFrame.repaint();
        }
    }
}
