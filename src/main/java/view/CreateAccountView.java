package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CreateAccountView extends AppFrame {
    private JButton createAccountButton;
    private JTextField fullNameTextField;
    private JTextField phoneNumberTextField;
    private JTextField emailTextField;
    private JPasswordField passwordTextField;


    public CreateAccountView() {
        initialize();
    }

    public void initialize() {
        this.setTitle("Create Account");
        this.setSize(500, 400);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(null);

        this.setResizable(false);
        this.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(null);

        initializeForm(panel);

        panel.setBackground(new Color(199,201,255));
        this.setContentPane(panel);
        this.setVisible(true);
    }

    private void initializeForm(JPanel panel) {
        JLabel patientFullNameLabel = new JLabel("Full name:");
        patientFullNameLabel.setBounds(150, 30, 200, 30);

        fullNameTextField = new JTextField();
        fullNameTextField.setBounds(150, 60, 200, 30);

        JLabel patientPhoneLabel = new JLabel("Phone:");
        patientPhoneLabel.setBounds(150, 90, 200, 30);

        phoneNumberTextField = new JTextField();
        phoneNumberTextField.setBounds(150, 120, 200, 30);

        JLabel patientEmailLabel = new JLabel("Email address:");
        patientEmailLabel.setBounds(150,150,200,30);

        emailTextField = new JTextField();
        emailTextField.setBounds(150,180,200,30);

        JLabel patientPasswordLabel = new JLabel("Password:");
        patientPasswordLabel.setBounds(150,210,200,30);

        passwordTextField =  new JPasswordField();
        passwordTextField.setBounds(150,240,200,30);
        passwordTextField.setEchoChar('*');

        createAccountButton = new JButton("Create account");
        createAccountButton.setBounds(150, 300, 200, 30);

        panel.add(createAccountButton);
        panel.add(patientFullNameLabel);
        panel.add(fullNameTextField);
        panel.add(patientPhoneLabel);
        panel.add(phoneNumberTextField);
        panel.add(patientEmailLabel);
        panel.add(emailTextField);
        panel.add(patientPasswordLabel);
        panel.add(passwordTextField);

    }


    public void createButtonActionListener(ActionListener actionListener){
        createAccountButton.addActionListener(actionListener);
    }

    public char[] getPasswordText(){
        return passwordTextField.getPassword();
    }

    public String getEmail(){
        return emailTextField.getText();
    }

    public String getFullName(){
        return fullNameTextField.getText();
    }

    public String getPhoneNumber(){
        return phoneNumberTextField.getText();
    }


}
