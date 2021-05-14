package controller;

import javafx.application.Application;
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
import model.DoctorTable;
import model.DoctorsName;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import service.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DoctorNameListController {
    @FXML
    public Text currentDoctor;
    public TableView<Appointment> table;
    public javafx.scene.control.TableColumn<Appointment, String> prDate;
    public javafx.scene.control.TableColumn<Appointment, String> prHour;
    public TableColumn prSelect;

    List<Appointment> appointments = new ArrayList<>();
    List <DoctorsName> currentDoctors = new ArrayList<>();

    private JsonParser jsonParser;

    public void goToConfirm(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/ConfirmAppointment.fxml"));
        Parent view = loader.load();
        Scene view2 = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(view2);
        ConfirmAppointmentController controller = loader.getController();
        controller.init(this.appointments, this.currentDoctors);
        window.show();
    }

    public void goBack(ActionEvent event) throws IOException {

        Parent view = FXMLLoader.load(getClass().getClassLoader().getResource("view/DoctorNameList.fxml"));
        Scene view2 = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(view2);
        window.show();

    }

    public void init(DoctorsName doctorsName)
    {
        currentDoctor.setText(doctorsName.getDoctorsName());
        currentDoctors.add(doctorsName);
        try {
            jsonParser = new JsonParser();
            org.json.simple.JSONObject obj = jsonParser.parse("/datastorage/doctorsSchedule.json");
            JSONArray doctors = (JSONArray) obj.get(doctorsName.getDoctorsName());

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


            Callback<TableColumn<Appointment, String>, TableCell<Appointment, String>> cellFactory =  new Callback <TableColumn<Appointment, String>,TableCell<Appointment, String>>() {
                @Override
                public TableCell<Appointment, String> call(TableColumn<Appointment, String> productStringTableColumn) {
                    final TableCell<Appointment, String> cell = new TableCell<Appointment, String>() {

                        final Button btn = new Button("Confirm");

                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                                setText(null);
                            } else{
                                btn.setOnAction(event -> {
                                    Appointment chooseApp= getTableView().getItems().get(getIndex());
                                    Appointment appointment = new Appointment("","");
                                    appointment.setDate(chooseApp.getDate());
                                    appointment.setHour(chooseApp.getHour());
                                    appointments.add(appointment);
                                    btn.setDisable(true);

                                });
                                setGraphic(btn);
                                btn.setStyle("-fx-background-color: papayawhip; -fx-background-radius: 15;");
                                btn.setAlignment(Pos.CENTER);
                                setText(null);
                            }
                        }
                    };
                    return cell;
                }
            };

            prSelect.setCellFactory(cellFactory);
            table.setItems(data);

        }catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

}