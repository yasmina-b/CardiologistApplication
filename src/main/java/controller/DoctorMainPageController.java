package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DoctorMainPageController {

    @FXML
    public void goBack(ActionEvent event) throws IOException {

        Parent view = FXMLLoader.load(getClass().getClassLoader().getResource("view/DoctorLogin.fxml"));
        Scene view2 = new Scene(view);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(view2);
        window.show();
    }

    @FXML
    public void goToScheduledAppointments(ActionEvent event) throws IOException {

        Parent view = FXMLLoader.load(getClass().getClassLoader().getResource("view/DoctorNameAppointments.fxml"));
        Scene view2 = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(view2);
        window.show();

    }
}