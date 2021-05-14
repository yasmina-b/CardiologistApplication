package exceptions;

public class EmptyCityException extends Exception{
    String city;

    public EmptyCityException(String city) {

        super("The city field is mandatory!");
        this.city=city;
    }
}
