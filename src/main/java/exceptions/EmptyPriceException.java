package exceptions;

public class EmptyPriceException extends Exception{
    String price;

    public EmptyPriceException(String price) {

        super("The price field is mandatory!");
        this.price=price;
    }
}
