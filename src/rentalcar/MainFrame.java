package rentalcar;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame {
    static JFrame main;
    static JPanel mainPanel;
    static JButton login;
    static JButton register;

    public static void init(){
        mainPanel = new JPanel();

        login = new JButton("Login");
        login.addActionListener(new ButtonListener());
        login.setSize(login.getPreferredSize());

        register = new JButton("Register");
        register.addActionListener(new ButtonListener());
        register.setSize(register.getPreferredSize());
        
        mainPanel.add(login);
        mainPanel.add(register);
    }

    private static class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == login){
                JDialog lDialog = new JDialog(new JFrame(), "Sign in");
                lDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                lDialog.add(new LoginPanel());
                lDialog.setVisible(true);
            }
            else if(e.getSource() == register){
                JDialog regDialog = new JDialog(new JFrame(), "Register");
                regDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                regDialog.add(new RegisterPanel());
                regDialog.setVisible(true);
            }
        }
    }
    public static void main(String[] args){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        main = new JFrame("GT Car Rental");
        main.setBounds(0, 0, screenSize.width, screenSize.height);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        main.setContentPane(mainPanel);
        main.setVisible(true);
    }
}
