package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class DoctorsAppointments {

    List<Appointment> appointmentList=new ArrayList<Appointment>();
    private StringProperty doctorsName = new SimpleStringProperty("");;

    public String getDoctorsName() { return doctorsName.get(); }

    public StringProperty doctorNameProperty() { return doctorsName; }

    public void setDoctorName(String doctorsName) { this.doctorsName.set(doctorsName); }

    public List<Appointment> getAppointmentList() {
        return this.appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

}