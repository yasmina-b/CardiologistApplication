package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.City;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import model.Doctor;
import model.DoctorTable;


import org.json.simple.parser.ParseException;
import service.JsonParser;
import javafx.scene.control.Button;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;


public class CitiesListController implements Initializable {
    @FXML
    public VBox cities;
    private JsonParser jsonParser;


    @FXML
    public void goBack(ActionEvent event) throws IOException {

        Parent view = FXMLLoader.load(getClass().getClassLoader().getResource("view/PatientLogin.fxml"));
        Scene view2 = new Scene(view);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(view2);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            jsonParser = new JsonParser();
            JSONObject obj = jsonParser.parse("/datastorage/cities.json");
            JSONArray cities_list = (JSONArray) obj.get("Cities_List");

            Iterator it = cities_list.iterator();

            while (it.hasNext()) {
                String element = (String) it.next();

                Button b = new Button();
                b.setMaxWidth(Double.MAX_VALUE);
                b.setText(element);
                b.setAlignment(Pos.CENTER);
                b.setStyle("-fx-background-color: white; -fx-background-radius: 15px; -fx-text-fill: #ff2323; -fx-text-effect: Glow");
                cities.getChildren().add(b);
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        for (Node i : cities.getChildren()) {
            Button b = (Button) i;
            b.setOnAction(new ChooseCityEventHandler());
        }

    }

    @FXML

    public void goToDoctors(ActionEvent event)  {
        try{

            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/DoctorNameList.fxml"));
            Parent view = loader.load();
            Scene view2 = new Scene(view);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(view2);

            window.show();

        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
