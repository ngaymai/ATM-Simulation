package bank.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JButton login, signUp, clear;
    JTextField cardTextField;
    JPasswordField pinTextField;
    Login() {
        setTitle("AUTOMATED TELLER MACHINE");
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        add(label);

        JLabel text = new JLabel("Welcome to ATM");
        text.setFont(new Font("Osward", Font.BOLD, 38));
        text.setBounds(200, 40, 400, 40);
        add(text);

        JLabel cardNo = new JLabel("Card No: ");
        cardNo.setFont(new Font("Raleway", Font.BOLD, 28));
        cardNo.setBounds(120, 150, 150, 30);
        add(cardNo);

        cardTextField = new JTextField();
        cardTextField.setBounds(300, 150, 250, 30);
        cardTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(cardTextField);

        JLabel pin = new JLabel("PIN:");
        pin.setFont(new Font("Raleway", Font.BOLD, 28));
        pin.setBounds(120, 220, 250, 30);
        add(pin);

        pinTextField = new JPasswordField();
        pinTextField.setBounds(300, 220, 250, 30);
        add(pinTextField);

        login = new JButton("SIGN IN");
        login.setBounds(300, 300, 100, 30);
        login.setBackground(Color.black);
        login.setForeground(Color.white);
        login.addActionListener(this::actionPerformed);
        add(login);

        clear = new JButton("CLEAR");
        clear.setBounds(430, 300, 100, 30);
        clear.setBackground(Color.black);
        clear.setForeground(Color.white);
        clear.addActionListener(this::actionPerformed);
        add(clear);

        signUp = new JButton("SIGN UP");
        signUp.setBounds(300, 350, 230, 30);
        signUp.setBackground(Color.black);
        signUp.setForeground(Color.white);
        signUp.addActionListener(this::actionPerformed);
        add(signUp);

        getContentPane().setBackground(Color.white);
        label.setBounds(70, 10, 100, 100);
        setSize(800, 500);
        setVisible(true);
        setLocation(350, 200);
    }
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == clear) {
            cardTextField.setText("");
            pinTextField.setText("");
        } else if (ae.getSource() == login) {
            Conn c1 = new Conn();
            String cardno  = cardTextField.getText();
            String pin  = pinTextField.getText();
            String q  = "select * from login where cardnumber = '"+cardno+"' and pin = '"+pin+"'";
            try {
                ResultSet rs = c1.s.executeQuery(q);
                if(rs.next()){
                    setVisible(false);
                    new Transactions(pin).setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN");
                }
            } catch (Exception e) {
                System.out.println(e);
            }

        } else if (ae.getSource() == signUp) {
            setVisible(false);
            new SignUpOne();
        }
    }

    public static void main(String args[]){
        new Login();
    }
}
