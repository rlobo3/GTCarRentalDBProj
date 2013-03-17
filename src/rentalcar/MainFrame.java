package rentalcar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainFrame {
    static JFrame main;
    static JPanel mainPanel;
    static JButton login;
    static JButton register;
    
    public static void init(){
        mainPanel = new JPanel();
        
        login = new JButton("Login");
        login.addActionListener(new ButtonListener());
        
        register = new JButton("Register");
        register.addActionListener(new ButtonListener());
        
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
        main = new JFrame("GT Car Rental");
        init();
        main.setContentPane(mainPanel);
        main.setVisible(true);
    }
}
