package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Appointment;
import model.ConfirmAppointment;
import model.DoctorsAppointments;
import model.DoctorsName;

import java.io.IOException;

public class ChooseNameAppointmentEventHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/DoctorsScheduledAppointments.fxml"));
            Parent view = loader.load();
            Scene view2 = new Scene(view);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(view2);

            ViewDoctorAppointmentsController controller = loader.getController();
            ViewDoctorAppointmentsController controller2 =loader.getController();

            DoctorsName doctorsName= new DoctorsName();
            Appointment appointment=new Appointment("","");

            controller.init(appointment);
            controller2.initTable(doctorsName);

            window.show();
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

}