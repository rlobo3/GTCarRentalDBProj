package rentalcar;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame {
    static JFrame main;
    static JPanel mainPanel;
    static JButton login;
    static JButton register;
    
    final static int WIDTH = 400, HEIGHT = 500;
    final static int DIALOGWIDTH = 300, DIALOGHEIGHT = 400;
    final static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    
    public static void init(){
        mainPanel = new LoginPanel();

//        login = new JButton("Login");
//        login.addActionListener(new ButtonListener());
//        
//        register = new JButton("Register");
//        register.addActionListener(new ButtonListener());
    }

//    private static class ButtonListener implements ActionListener {
//        public void actionPerformed(ActionEvent e){
//            if(e.getSource() == login){
//                JDialog lDialog = new JDialog(new JFrame(), "Sign in");
//                lDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//                lDialog.setBounds(screenSize.width/3, screenSize.height/3, DIALOGWIDTH, DIALOGHEIGHT);
//                lDialog.add(new LoginPanel());
//                lDialog.setVisible(true);
//            }
//            else if(e.getSource() == register){
//                JDialog regDialog = new JDialog(new JFrame(), "Register");
//                regDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//                regDialog.setBounds(screenSize.width/3, screenSize.height/3, DIALOGWIDTH, DIALOGHEIGHT);
//                regDialog.add(new RegisterPanel());
//                regDialog.setVisible(true);
//            }
//        }
//    }
    public static void main(String[] args) {
        main = new JFrame("GT Car Rental");
//        main.setBounds(0, 0, screenSize.width, screenSize.height);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        main.setBounds(mainPanel.getBounds());
        main.setContentPane(mainPanel);
        main.setVisible(true);
    }

    /**
     * @return the main
     */
    public static JFrame getMain() {
        return main;
    }
}