package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import model.City;
import model.Doctor;
import model.DoctorTable;

import javax.print.Doc;
import java.io.IOException;


public class ChooseCityEventHandler implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/DoctorsList.fxml"));
            Parent view = loader.load();
            Scene view2 = new Scene(view);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(view2);

            DoctorsListController controller = loader.getController();
            City city= new City();
            city.setCityName(((Button)event.getTarget()).getText());
            controller.init(city);
            window.show();

        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
}