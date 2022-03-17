package validator;

import model.VacationPackage;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class VacationPackageValidator {

    public String validateNonExistentName(List<VacationPackage> vacationPackageList, String name){
        for (VacationPackage vacationPackage : vacationPackageList) {
            if(vacationPackage.getName().equals(name)){
                throw new RuntimeException("Package with that name already exists!");
            }
        }
        return name;
    }

    public String validateNonEmptyStringField(String destination){
        if (destination.isBlank()) {
            throw new RuntimeException("Destination is empty");
        }else {
            return destination;
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
            throw new RuntimeException("price must be a float number!");
        }
        if(number < 0){
            throw new RuntimeException("Price must be a positive number!");
        }
        return number;
    }

    public Integer validateInt(String s) {
        Integer number;
        try {
            number = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new RuntimeException("price must be a integer number!");
        }
        if(number < 0){
            throw new RuntimeException("The number of places must be a positive number!");
        }
        return number;
    }

    public LocalDate validateDate(String date, String pattern){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDate okDate;
        try{
            okDate = LocalDate.parse(date, formatter);
        }catch (DateTimeException e) {
            throw new RuntimeException("Wrong date format");
        }
        return okDate;
    }

    public String validateString(String s) {
        if (s.equals("null")) {
            throw new RuntimeException("Select Destination!");
        }else {
            return s;
        }
    }


}
