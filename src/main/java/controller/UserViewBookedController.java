package controller;

import model.User;
import model.VacationPackage;
import service.UserService;
import view.UserViewBookedView;

public class UserViewBookedController {

    private User user;
    private UserViewBookedView userViewBookedView;
    private UserService userService;

    public UserViewBookedController(User user) {
        userService = new UserService();
        this.user = userService.findUserByEmail(user.getEmail());
        userViewBookedView = new UserViewBookedView();
        loadBookedVacations();
    }

    private void loadBookedVacations(){
        for (VacationPackage vacationPackage : user.getBookedVacationsList()) {
            System.out.println(vacationPackage.getName());
        }
        userViewBookedView.initializeTable(user.getBookedVacationsList());
    }
}
