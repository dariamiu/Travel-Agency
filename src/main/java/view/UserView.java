package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UserView extends AppFrame{

    private JButton bookVacation = new JButton("Book Vacation");
    private JButton viewBookedVacations = new JButton("View my Booked Vacations");



    public UserView(){
        this.setTitle("User View");
        this.setSize(400, 400);
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


        viewBookedVacations.setBounds(100,50,200,20);
        panel.add(viewBookedVacations);
        bookVacation.setBounds(100,100,200,20);
        panel.add(bookVacation);



    }


    public void bookVacationListener(ActionListener actionListener){
        bookVacation.addActionListener(actionListener);
    }

    public void viewBookedVacationsActionListener(ActionListener actionListener){
        viewBookedVacations.addActionListener(actionListener);
    }



}

