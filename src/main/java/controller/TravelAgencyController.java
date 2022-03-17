package controller;

import view.TravelAgencyView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TravelAgencyController {
    private TravelAgencyView travelAgencyView;

    public TravelAgencyController(){
        travelAgencyView = new TravelAgencyView();
        actions();
    }
    private void actions(){
        travelAgencyView.manageDestinationsActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TravelAgencyDestinationController();
            }
        });

        travelAgencyView.manageVacationsActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TravelAgencyVacationController();
            }
        });
    }
}
