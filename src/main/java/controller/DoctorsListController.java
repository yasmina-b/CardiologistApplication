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
import model.City;
import model.DoctorTable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import service.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class DoctorsListController {

    @FXML
    public TableView<DoctorTable> table;
    public javafx.scene.control.TableColumn<DoctorTable, String> prName;
    public javafx.scene.control.TableColumn<DoctorTable, String> prPrice;
    public javafx.scene.control.TableColumn<DoctorTable, String> prWorkingHours;

    private JsonParser jsonParser;

    public void goBack(ActionEvent event) throws IOException {

        Parent view = FXMLLoader.load(getClass().getClassLoader().getResource("view/CitiesList.fxml"));
        Scene view2 = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(view2);
        window.show();

    }

    public void init(City city)
    {
        try {
            jsonParser = new JsonParser();
            org.json.simple.JSONObject obj = jsonParser.parse("/datastorage/doctorsList.json");
            JSONArray doctors = (JSONArray) obj.get(city.getCityName());

            Iterator<Object> it = doctors.iterator();

            List<DoctorTable> prList = new ArrayList<>();
            while (it.hasNext()) {
                JSONObject getDoctor = (JSONObject) it.next();
                prList.add(new DoctorTable(getDoctor.get("name").toString(),getDoctor.get("price").toString(), getDoctor.get("workingHours").toString()));
            }

            ObservableList<DoctorTable> data = FXCollections.observableArrayList(prList);
            table.setEditable(true);
            prName.setCellValueFactory(new PropertyValueFactory<>("name"));
            prPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
            prWorkingHours.setCellValueFactory(new PropertyValueFactory<>("workingHours"));

            table.setItems(data);
            table.setEditable(true);

        }catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

}