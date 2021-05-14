package controller;

import exceptions.EmptyPasswordException;
import exceptions.EmptyUsernameException;
import exceptions.UsernameAlreadyExistsException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import service.PatientService;

import java.io.IOException;

public class PatientRegisterController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Text messageField;

    @FXML
    public void goToPatientLogin(ActionEvent event) throws IOException {

        Parent view = FXMLLoader.load(getClass().getClassLoader().getResource("view/PatientLogin.fxml"));
        Scene view2 = new Scene(view);

        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();

        window.setScene(view2);
        window.show();

    }

    @FXML
    public void gotoUserRegister(ActionEvent event) throws IOException {

        Parent view = FXMLLoader.load(getClass().getClassLoader().getResource("view/UserRegisterMainView.fxml"));
        Scene view2 = new Scene(view);

        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();

        window.setScene(view2);
        window.show();

    }

    public void registerButtonAction() {

        try {

            PatientService.addPatient(usernameField.getText(), passwordField.getText());
            messageField.setText("Account created successfully!");
        } catch (UsernameAlreadyExistsException | EmptyPasswordException | EmptyUsernameException e) {

            messageField.setText(e.getMessage());
            Alert alert=new Alert(Alert.AlertType.WARNING, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
            if(alert.getResult()==ButtonType.OK)
                alert.close();
        }

    }
}
