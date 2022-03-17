package controller;

import model.User;
import service.UserService;
import validator.UserValidator;
import view.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {

    private UserService userService;
    private LoginView loginView;
    private UserValidator userValidator;

    public LoginController(){
        userService = new UserService();
        loginView = new LoginView();
        userValidator = new UserValidator();
        actions();
    }

    private void actions() {
        loginView.createActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateAccountController();
            }
        });

        loginView.loginActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    try{
                        userValidator.validateCredentials(loginView.getUsername(), String.valueOf(loginView.getPasswordText()));
                        User user = userService.findUserByEmail(loginView.getUsername());
                        userValidator.validatePassword(user.getPassword(),String.valueOf(loginView.getPasswordText()));
                        new UserController(user);
                    }catch (RuntimeException exception){
                        loginView.displayErrorMessage(exception);
                    }

            }
        });
    }

}
