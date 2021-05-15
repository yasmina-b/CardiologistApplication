package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Appointment;
import model.ConfirmAppointment;
import model.DoctorsAppointments;
import model.DoctorsName;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import service.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ViewDoctorAppointmentsController {

    @FXML
    public Text currentDoctor;
    public TableView<Appointment> table;
    public javafx.scene.control.TableColumn<DoctorsAppointments, String> prDate;
    public javafx.scene.control.TableColumn<DoctorsAppointments, String> prHour;

    public TableView<DoctorsName> table1;
    public javafx.scene.control.TableColumn<DoctorsName, String> prName;

    private JsonParser jsonParser;

    @FXML
    public void goBack(ActionEvent event) throws IOException {

        Parent view = FXMLLoader.load(getClass().getClassLoader().getResource("view/DoctorNameAppointments.fxml"));
        Scene view2 = new Scene(view);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(view2);
        window.show();
    }

    public void initTable(DoctorsName doctorsName)
    {

        try{
            jsonParser = new JsonParser();
            org.json.simple.JSONObject obj = jsonParser.parse("/datastorage/appointments.json");
            JSONArray doctor = (JSONArray) obj.get("doctorName");
            Iterator<Object> it = doctor.iterator();

            List<DoctorsName> prList = new ArrayList<DoctorsName>();

            while (it.hasNext()) {
                JSONObject getDoctorName = (JSONObject) it.next();
                prList.add(new DoctorsName(getDoctorName.get("doctorsName").toString()));

            }

            ObservableList<DoctorsName> data = FXCollections.observableArrayList(prList);
            table.setEditable(true);
            prName.setCellValueFactory(new PropertyValueFactory<>("doctorsName"));

            table1.setItems(data);


        }catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void init(Appointment appointment)
    {
        try {

            jsonParser = new JsonParser();
            org.json.simple.JSONObject obj = jsonParser.parse("/datastorage/appointments.json");
            JSONArray doctors = (JSONArray) obj.get("appointmentList");

            Iterator<Object> it = doctors.iterator();

            List<Appointment> prList = new ArrayList<Appointment>();

            while (it.hasNext()) {
                JSONObject getDoctorSchedule = (JSONObject) it.next();
                prList.add(new Appointment(getDoctorSchedule.get("date").toString(), (String) getDoctorSchedule.get("hour")));

            }

            ObservableList<Appointment> data = FXCollections.observableArrayList(prList);
            table.setEditable(true);
            prDate.setCellValueFactory(new PropertyValueFactory<>("date"));
            prHour.setCellValueFactory(new PropertyValueFactory<>("hour"));

            table.setItems(data);
            table.setEditable(true);

        }catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}