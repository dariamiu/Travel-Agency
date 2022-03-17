package controller;

import model.User;
import view.UserView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserController {
    private User user;
    private UserView userView;

    public UserController(User user) {
        this.user = user;
        this.userView = new UserView();
        System.out.println(user.getFullName());
        actions();
    }

    private void actions(){
        userView.bookVacationListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserBookVacationController(user);
            }
        });

        userView.viewBookedVacationsActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserViewBookedController(user);
            }
        });
    }
}
