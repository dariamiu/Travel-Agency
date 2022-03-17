package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class TravelAgencyView extends AppFrame{


    private JButton manageDestinations = new JButton("Manage Destinations");
    private JButton manageVacations = new JButton("Manage Vacations");



    public TravelAgencyView(){
        this.setTitle("Agency View");
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


        manageVacations.setBounds(100,50,200,20);
        panel.add(manageVacations);
        manageDestinations.setBounds(100,100,200,20);
        panel.add(manageDestinations);



    }


    public void manageDestinationsActionListener(ActionListener actionListener){
        manageDestinations.addActionListener(actionListener);
    }

    public void manageVacationsActionListener(ActionListener actionListener){
        manageVacations.addActionListener(actionListener);
    }



}

