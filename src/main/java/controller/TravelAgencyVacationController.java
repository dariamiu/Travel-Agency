package controller;

import model.Destination;
import model.VacationPackage;
import service.DestinationService;
import service.VacationPackageService;
import validator.VacationPackageValidator;
import view.TravelAgencyVacationView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TravelAgencyVacationController {

    private TravelAgencyVacationView travelAgencyVacationView;
    private VacationPackageService vacationPackageService;
    private List<VacationPackage> vacationPackages = new ArrayList<>();
    private DestinationService destinationService;
    private VacationPackageValidator vacationPackageValidator;

    public TravelAgencyVacationController(){
        travelAgencyVacationView = new TravelAgencyVacationView();
        vacationPackageService = new VacationPackageService();
        destinationService = new DestinationService();
        vacationPackageValidator = new VacationPackageValidator();
        setDestinations();
        loadTable();
        actions();
    }
    private void loadTable(){
        vacationPackages = vacationPackageService.getAll();
        travelAgencyVacationView.initializeTable(vacationPackages);
    }
    private void setDestinations(){
        travelAgencyVacationView.setComboBoxDestinations(destinationService.getAll());
    }
    private void actions()
    {
        travelAgencyVacationView.addVacationActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                String name = vacationPackageValidator.validateNonExistentName(vacationPackages,travelAgencyVacationView.getName());
                Float price = vacationPackageValidator.validateFloat(travelAgencyVacationView.getPrice());
                Integer availablePlaces = vacationPackageValidator.validateInt(travelAgencyVacationView.getAvailablePlaces());
                LocalDate startDate = vacationPackageValidator.validateDate(travelAgencyVacationView.getStartDate(),"d/MM/yyyy");
                LocalDate endDate = vacationPackageValidator.validateDate(travelAgencyVacationView.getEndDate(),"d/MM/yyyy");
                String destinationName = vacationPackageValidator.validateString(travelAgencyVacationView.getSelectedDestination());
                Destination destination = destinationService.getByName(destinationName);

                vacationPackageValidator.validateDates(startDate,endDate);
                vacationPackageService.createVacation(new VacationPackage(name,price, availablePlaces, 0,
                        startDate, endDate, "NOT_BOOKED", destination));
                loadTable();
                }catch (RuntimeException ex){
                    travelAgencyVacationView.displayErrorMessage(ex);
                }
            }
        });


        travelAgencyVacationView.deleteVacationActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                travelAgencyVacationView.setRows(travelAgencyVacationView.getVacations().getSelectedRows());
                int[] rows = travelAgencyVacationView.getRows();
                if (rows.length == 0) throw new RuntimeException("no rows selected!");
                for(int i : rows){
                    String[] row = travelAgencyVacationView.getRowAt(i,travelAgencyVacationView.getVacations());
                    vacationPackageService.deleteByName(row[0]);
                }
                loadTable();

            }
        });

        travelAgencyVacationView.updateVacationActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    travelAgencyVacationView.setRows(travelAgencyVacationView.getVacations().getSelectedRows());
                    int[] rows = travelAgencyVacationView.getRows();
                    if (rows.length == 0) throw new RuntimeException("no rows selected!");
                    String[] row = travelAgencyVacationView.getRowAt(travelAgencyVacationView.getRows()[0], travelAgencyVacationView.getVacations());
                    Destination destination = destinationService.getByName(row[1]);
                    LocalDate startDate = vacationPackageValidator.validateDate(row[2],"yyyy-MM-dd");
                    LocalDate endDate = vacationPackageValidator.validateDate(row[3],"yyyy-MM-dd");
                    String newName = vacationPackageValidator.validateNonEmptyStringField(row[0]);
                    if(!vacationPackages.get(rows[0]).getName().equals(newName)){
                        vacationPackageValidator.validateNonExistentName(vacationPackages,newName);
                    }
                    vacationPackageValidator.validateDates(startDate,endDate);
                    Integer availablePlaces = vacationPackageValidator.validateInt(row[5]);
                    Integer takenPlaces = vacationPackageValidator.validateInt(row[6]);
                    String status = "";
                    if(availablePlaces > takenPlaces && takenPlaces > 0){
                        status = "IN_PROGRESS";
                    }
                    if(availablePlaces.equals(takenPlaces)){
                        status = "BOOKED";
                    }
                    if(availablePlaces < takenPlaces){
                        throw new RuntimeException("Available places smaller than already taken places!");
                    }
                    VacationPackage vacationPackage = new VacationPackage(
                            vacationPackageValidator.validateNonEmptyStringField(row[0]),
                            vacationPackageValidator.validateFloat(row[4]),
                            availablePlaces,
                            takenPlaces,
                            startDate,
                            endDate,
                            status,
                            destination);
                    vacationPackageService.updateAllVacation(vacationPackages.get(rows[0]).getName(),vacationPackage);
                    loadTable();

                }catch (RuntimeException exception){
                    travelAgencyVacationView.displayErrorMessage(exception);
                }
            }
        });

      /*  travelAgencyVacationView.searchByDestinationActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<VacationPackage> vacationPackages1 = new ArrayList<>();
                vacationPackages1 = vacationPackageService.getByDestinationName(travelAgencyVacationView.getSearchNameText());
                travelAgencyVacationView.initializeTable(vacationPackages1);
            }
        });*/

    }

}
