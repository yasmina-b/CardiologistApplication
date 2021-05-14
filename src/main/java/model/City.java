package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class City {
    private StringProperty cityName = new SimpleStringProperty("");

    public String getCityName() {
        return cityName.get();
    }

    public StringProperty cityNameProperty() { return cityName; }

    public void setCityName(String cityName) {
        this.cityName.set(cityName);
    }
}