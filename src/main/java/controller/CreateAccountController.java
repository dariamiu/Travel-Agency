package controller;

import model.User;
import service.UserService;
import validator.UserValidator;
import view.CreateAccountView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAccountController {
    private CreateAccountView createView;
    private UserValidator userValidator;
    private UserService userService;

    public CreateAccountController(){
        createView = new CreateAccountView();
        userValidator = new UserValidator();
        userService = new UserService();
        actions();
    }

    private void actions(){
        createView.createButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    userValidator.validateInputsCreate(createView.getFullName(),createView.getEmail(),
                            String.valueOf(createView.getPasswordText()), createView.getPhoneNumber());
                    User newUser = userService.findUserByEmail(createView.getEmail());
                    userValidator.validateNewUser(newUser);
                    userService.createUser(new User(createView.getFullName(),createView.getEmail(),
                            String.valueOf(createView.getPasswordText()),createView.getPhoneNumber()));
                    createView.displayInformationMessage("Account created");
                } catch (RuntimeException exception) {
                    createView.displayErrorMessage(exception);
                }


            }
        });
    }
}
