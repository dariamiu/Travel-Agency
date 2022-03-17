package validator;
import model.User;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserValidator {

    public void validateCredentials(String s1, String s2) {
        if (s1.isBlank() || s2.isBlank()) {
            throw new RuntimeException("One of the fields is empty");
        }
    }

    public void validateInputsCreate(String name, String email, String password, String phoneNumber) {
        if (name.isBlank()) {
            throw new RuntimeException("Name is empty");
        }else
        if (email.isBlank()) {
            throw new RuntimeException("Email is empty");
        }else
        if (password.isBlank()) {
            throw new RuntimeException("Password is empty");
        }else
        if (phoneNumber.isBlank()) {
            throw new RuntimeException("Phone number is empty");
        }
    }

    public void validatePassword(String s1, String s2){
        if (!s1.equals(s2)) {
            throw new RuntimeException("Wrong password");
        }
    }


    public void validateNewUser(User newUser){
        if(newUser != null){
            throw new RuntimeException("Account with this email already exists");
        }
    }
    public void validateDates(LocalDate start, LocalDate end){
        if(end.isBefore(start)){
            throw new RuntimeException("End date is before start date!");
        }
    }


    public Float validateFloat(String s) {
        Float number;
        try {
            number = Float.parseFloat(s);
        } catch (NumberFormatException e) {
            throw new RuntimeException("price must be a float number");
        }
        return number;
    }

    public String validateString(String s) {
        if (s.equals("null")) {
            return "###";
        }else {
            return s;
        }
    }

    public Float validatePrice(String s) {
        if (s.isBlank()) {
            return (float)-1;
        }else {
            return validateFloat(s);
        }
    }

    public void validatePrices(Float min, Float max){
        if(min > max) {
            throw new RuntimeException("Min price is greater than max price!");
        }
    }

    public LocalDate validateDate(String date){
        if(date.isBlank()){
            return LocalDate.of(1800,1,1);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate okDate;
        try{
             okDate = LocalDate.parse(date, formatter);
        }catch (DateTimeException e) {
            throw new RuntimeException("Wrong date format");
        }
        return okDate;
    }
}
