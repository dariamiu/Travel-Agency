package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends AppFrame{
    private JButton createAccountButton;
    private JButton loginButton;
    private JTextField userName;
    private JPasswordField passwordTextField;

    public LoginView(){
        this.setTitle("Login");
        this.setSize(500, 400);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        initializeForm(panel);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        panel.setBackground(new Color(199,201,255));
        this.setContentPane(panel);
        this.setVisible(true);
    }
    /**
     * Method to initialize all the components in the panel, set their sizes, positions and color
     * @param panel the panel in the JFrame
     */
    private void initializeForm(JPanel panel){
        JLabel emailLabel = new JLabel("username");
        emailLabel.setBounds(150, 50, 200, 30);

        userName = new JTextField();
        userName.setBounds(150, 80, 200, 30);

        JLabel passwordLabel = new JLabel("password");
        passwordLabel.setBounds(150, 110, 200, 30);

        passwordTextField = new JPasswordField();
        passwordTextField.setBounds(150, 140, 200, 30);
        passwordTextField.setEchoChar('*');

        createAccountButton = new JButton("Create account");
        createAccountButton.setBounds(150, 250, 200, 30);

        loginButton = new JButton("LOGIN");
        loginButton.setBounds(200, 200, 100, 30);

        panel.add(createAccountButton);
        panel.add(emailLabel);
        panel.add(userName);
        panel.add(passwordLabel);
        panel.add(passwordTextField);
        panel.add(loginButton);



    }

    public void loginActionListener(ActionListener actionListener){
        loginButton.addActionListener(actionListener);
    }
    public void createActionListener(ActionListener actionListener){
        createAccountButton.addActionListener(actionListener);
    }
    public char[] getPasswordText(){
        return passwordTextField.getPassword();
    }

    public String getUsername(){
        return userName.getText();
    }
}
