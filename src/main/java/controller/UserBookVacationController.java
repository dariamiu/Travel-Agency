package controller;

import model.User;
import model.VacationPackage;
import service.DestinationService;
import service.UserService;
import service.VacationPackageService;
import service.VacationUserService;
import validator.UserValidator;
import view.UserBookVacationView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserBookVacationController {

   private User user;
   private VacationPackageService vacationPackageService;
   private UserBookVacationView userBookVacationView;
   private DestinationService destinationService;
   private List<VacationPackage> vacationPackages;
   private VacationUserService vacationUserService;
   private UserService userService;
   private UserValidator userValidator;

    public UserBookVacationController(User user) {
        vacationPackageService = new VacationPackageService();
        userBookVacationView = new UserBookVacationView();
        destinationService = new DestinationService();
        vacationUserService = new VacationUserService();
        userService = new UserService();
        userValidator = new UserValidator();
        this.user = user;
        loadVacations();
        setDestinations();
        actions();
    }

    private void loadVacations(){
        this.vacationPackages = vacationPackageService.getAll();
        userBookVacationView.initializeTable(vacationPackages.stream().filter(v-> !v.getStatus().equals("BOOKED")).collect(Collectors.toList()));
    }

    private void setDestinations(){
        userBookVacationView.setComboBoxDestinations(destinationService.getAll());
    }

    public List<VacationPackage> search(String destination,Float  priceMin,Float  priceMax, LocalDate startDate,
    LocalDate endDate){
        System.out.println(destination);
        System.out.println(priceMin);
        System.out.println(priceMax);
        System.out.println(startDate);
        System.out.println(endDate);
        List<VacationPackage> result = new ArrayList<>();
        result  = vacationPackages.stream().filter(!destination.equals("###") ? m-> m.getDestination().getName().equals(destination) : m -> true ).
                filter(!priceMin.equals((float)-1)? m -> m.getPrice() >= priceMin: m -> true).
                filter(!priceMax.equals((float)-1)? m -> m.getPrice() <= priceMax: m -> true).
                filter(!startDate.equals(LocalDate.of(1800,1,1))? m -> m.getStartDate().isAfter(startDate) || m.getStartDate().isEqual(startDate): m -> true).
                filter(!endDate.equals(LocalDate.of(1800,1,1))? m -> m.getEndDate().isBefore(endDate) || m.getEndDate().isEqual(endDate) : m -> true).collect(Collectors.toList());

        return result;
    }
    private void actions() {
        userBookVacationView.addVacationActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user = userService.findUserByEmail(user.getEmail());
                userBookVacationView.setRows(userBookVacationView.getVacations().getSelectedRows());
                int[] rows = userBookVacationView.getRows();
                if (rows.length == 0) throw new RuntimeException("no rows selected!");
                String[] row = userBookVacationView.getRowAt(rows[0],userBookVacationView.getVacations());
                VacationPackage vacationPackage = vacationPackageService.getByName(row[0]);
                List<VacationPackage> vacationPackages = user.getBookedVacationsList();
                Boolean canBook = true;
                for (VacationPackage vacationPackage1 : vacationPackages) {
                    System.out.println("------------------" + vacationPackage1.getName());
                        if (vacationPackage1.getName().equals(row[0])){
                            canBook = false;
                        }
                    }
                    if(canBook){
                        String status;
                        Integer takenPlaces = vacationPackage.getTakenPlaces() + 1;
                        if(takenPlaces == vacationPackage.getAvailablePlaces()){
                            status = "BOOKED";
                        }else {
                            status = "IN_PROGRESS";
                        }
                        vacationPackageService.updateTakenPlacesAndStatus(row[0],takenPlaces,status);
                        vacationUserService.bookVacation(user.getEmail(),vacationPackage.getName());
                        userBookVacationView.displayInformationMessage("Vacation Booked!");
                    }else{
                        userBookVacationView.displayInformationMessage("Vacation already booked!");
                    }
                    loadVacations();
                }
        });

        userBookVacationView.filterActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    Float minPrice = userValidator.validatePrice(userBookVacationView.getPrice());
                    Float  maxPrice = userValidator.validatePrice(userBookVacationView.getPrice2());
                    LocalDate startDate = userValidator.validateDate(userBookVacationView.getStartDate());
                    LocalDate endDate = userValidator.validateDate(userBookVacationView.getEndDate());
                    userValidator.validateDates(startDate,endDate);
                    String destinationName = userValidator.validateString(userBookVacationView.getSelectedDestination());
                    List<VacationPackage> filteredVacations = search(destinationName,minPrice,maxPrice,startDate,endDate);
                    userBookVacationView.initializeTable(filteredVacations);
                }catch (RuntimeException ex){
                    userBookVacationView.displayErrorMessage(ex);
                }


            }
        });



    }
}
