package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class ConfirmAppointment {
    private List<DoctorsName> doctorName=new ArrayList<>();
    private List<Appointment> appointmentList=new ArrayList<Appointment>();

    public ConfirmAppointment() {}

    public ConfirmAppointment(List<DoctorsName> doctorName,List <Appointment> appointmentList)
    {
        this.doctorName.addAll(doctorName);
        this.appointmentList.addAll(appointmentList);

    }

    public List<DoctorsName> getDoctorName() {
        return this.doctorName;
    }

    public void setDoctorName(List<DoctorsName> doctorName) {
        this.doctorName = doctorName;
    }

    public List<Appointment> getAppointmentList() {
        return this.appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    public boolean equals(Object object) {
        if(object==this)
            return true;

        if(!(object instanceof ConfirmAppointment))
            return false;

        ConfirmAppointment confirmAppointment=(ConfirmAppointment) object;
        return this.doctorName.equals(confirmAppointment.doctorName) && this.appointmentList.equals(confirmAppointment.appointmentList);
    }


}