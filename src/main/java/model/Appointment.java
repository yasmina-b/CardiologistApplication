package model;
import javafx.beans.property.SimpleStringProperty;

public class Appointment {
    private SimpleStringProperty date;
    private SimpleStringProperty hour;


    public Appointment(String date, String hour){
        this.date = new SimpleStringProperty(date);
        this.hour = new SimpleStringProperty(hour);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) { this.date.set(date); }

    public String getHour() { return hour.get(); }

    public SimpleStringProperty hourProperty() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour.set(hour);
    }


}
