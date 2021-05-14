package exceptions;

public class EmptyNameException extends Exception{
    String name;

    public EmptyNameException(String name) {

        super("The name field is mandatory!");
        this.name=name;
    }
}
