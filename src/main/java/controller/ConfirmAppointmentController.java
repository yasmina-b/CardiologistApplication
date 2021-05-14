package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Appointment;
import model.ConfirmAppointment;
import model.Doctor;
import model.DoctorsName;
import service.AppointmentService;

import java.io.IOException;
import java.util.List;

public class ConfirmAppointmentController <prList>{

    public TableView<Appointment> table;
    public TableColumn<Appointment, String> prDate;
    public TableColumn<Appointment, String> prHour;
    public TextField total;
    public Button confirm;
    Alert confirmation = new Alert(Alert.AlertType.NONE);

    public void goBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/DoctorNameList.fxml"));
        Parent view = loader.load();
        Scene view2 = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(view2);
        window.show();
    }

    public void init(List<Appointment> app, List<DoctorsName> currentDoctor){

        ObservableList<Appointment> data = FXCollections.observableArrayList(app);
        table.setEditable(true);
        prDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        prHour.setCellValueFactory(new PropertyValueFactory<>("hour"));
        table.setItems(data);
        totalNrOfApp(table,total);
        confirm.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e)
            {
                confirmation.setAlertType(Alert.AlertType.INFORMATION);
                confirmation.setTitle("Confirmation");
                confirmation.setHeaderText("Appointment scheduled!");
                confirmation.show();
            }
        });

        ConfirmAppointment appointment=new ConfirmAppointment(currentDoctor, app);
        AppointmentService.addAppointment(appointment);
    }

    public void totalNrOfApp(TableView<Appointment> table, TextField total ){
        int totalapp=0;
        for (Appointment appointment : table.getItems()) {
            totalapp = totalapp + 1;
        }

        total.setText(String.valueOf(totalapp));
        total.setAlignment(Pos.CENTER);
        total.setDisable(true);

    }
}