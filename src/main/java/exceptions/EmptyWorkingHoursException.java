package exceptions;

public class EmptyWorkingHoursException extends Exception{
    String workingHours;

    public EmptyWorkingHoursException(String workingHours) {

        super("The Working Hours field is mandatory!");
        this.workingHours=workingHours;
    }
}
