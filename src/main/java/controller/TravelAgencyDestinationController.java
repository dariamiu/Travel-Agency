package controller;

import model.Destination;
import service.DestinationService;
import validator.DestinationValidator;
import view.TravelAgencyDestinationView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class TravelAgencyDestinationController {

    private TravelAgencyDestinationView travelAgencyDestinationView;
    private DestinationService destinationService;
    private List<Destination> destinations = new ArrayList<>();
    private DestinationValidator destinationValidator;

    public TravelAgencyDestinationController(){
        travelAgencyDestinationView = new TravelAgencyDestinationView();
        destinationService = new DestinationService();
        destinationValidator = new DestinationValidator();
        loadTable();
        actions();
    }

    private void loadTable(){
        destinations = destinationService.getAll();
        travelAgencyDestinationView.initializeTable(destinations);
    }
    private void actions(){

        travelAgencyDestinationView.addDestinationActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    destinationService.createDestination(new Destination(destinationValidator.validateNonEmptyStringField(travelAgencyDestinationView.getName()),
                            travelAgencyDestinationView.getDetails()));
                    loadTable();
                }catch (RuntimeException ex){
                    travelAgencyDestinationView.displayErrorMessage(ex);
                }


            }
        });

        travelAgencyDestinationView.deleteActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    travelAgencyDestinationView.setRows(travelAgencyDestinationView.getDestinationsTable().getSelectedRows());
                    int[] rows = travelAgencyDestinationView.getRows();
                    if (rows.length == 0) throw new RuntimeException("no rows selected!");
                    for(int i : rows){
                        String[] row = travelAgencyDestinationView.getRowAt(i,travelAgencyDestinationView.getDestinationsTable());
                        destinationService.deleteDestination(destinationService.getByName(row[0]));
                    }
                    loadTable();

            }
        });

        travelAgencyDestinationView.updateDestinationActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    travelAgencyDestinationView.setRows(travelAgencyDestinationView.getDestinationsTable().getSelectedRows());
                    int[] rows = travelAgencyDestinationView.getRows();
                    if (rows.length == 0) throw new RuntimeException("no rows selected!");
                    String[] row = travelAgencyDestinationView.getRowAt(travelAgencyDestinationView.getRows()[0], travelAgencyDestinationView.getDestinationsTable());
                    destinationService.updateDescription(row[0],row[1]);
                    System.out.println();
                    loadTable();

                }catch (RuntimeException exception){
                    travelAgencyDestinationView.displayErrorMessage(exception);
                }
            }
        });
      /*  travelAgencyDestinationView.searchByNameActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Destination> destinations1 = new ArrayList<>();
                destinations1.add(destinationService.getByName(travelAgencyDestinationView.getSearchNameText()));
                travelAgencyDestinationView.initializeTable(destinations1);
            }
        });*/
    }
}
