package view;

import controller.LoginController;
import controller.TravelAgencyController;

import javax.swing.*;
import java.awt.*;

public class StartView extends AppFrame {

        private JButton user;
        private JButton travelAgency;

        public StartView(){
            initialize();
        }

        public void initialize(){
            this.setTitle("Get started");
            this.setSize(400,400);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
        private void initializeForm(JPanel panel){
            user = new JButton("User");
            user.setBounds(100,50,200,20);
            travelAgency = new JButton("Travel Agency");
            travelAgency.setBounds(100,100,200,20);


            user.addActionListener(e -> {
                new LoginController();
            });

            travelAgency.addActionListener(e -> {
                new TravelAgencyController();
            });

            panel.add(user);
            panel.add(travelAgency);

        }



}
