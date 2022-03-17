package validator;

public class DestinationValidator {

    public String validateNonEmptyStringField(String name){
        if (name.isBlank()) {
            throw new RuntimeException("Name is empty");
        }else {
            return name;
        }
    }
}
