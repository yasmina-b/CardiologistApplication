package controller;

import exceptions.EmptyPasswordException;
import exceptions.EmptyUsernameException;
import exceptions.WrongPasswordException;
import exceptions.WrongUsernameException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.PatientService;

import java.io.IOException;

public class PatientLoginController {
    @FXML
    public TextField passwordField;
    @FXML
    public TextField usernameField;


    @FXML
    public void loginButtonAction(ActionEvent event) throws IOException, WrongPasswordException, WrongUsernameException, EmptyPasswordException, EmptyUsernameException {
        try {
            PatientService.checkPatient(usernameField.getText(), passwordField.getText());
            Alert alert=new Alert(Alert.AlertType.INFORMATION, "Logged in successfuly!", ButtonType.OK);
            alert.showAndWait();
            if(alert.getResult()==ButtonType.OK)
                alert.close();

            Parent view = FXMLLoader.load(getClass().getClassLoader().getResource("view/CitiesList.fxml"));
            Scene view2 = new Scene(view);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(view2);
            window.show();
        }
        catch (WrongUsernameException | WrongPasswordException | EmptyUsernameException | EmptyPasswordException e) {
            Alert alert=new Alert(Alert.AlertType.WARNING, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
            if(alert.getResult()==ButtonType.OK)
                alert.close();
        }

    }


    @FXML
    public void goToUserLogin(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/UserLoginMainView.fxml"));
        Parent view = loader.load();
        Scene view2 = new Scene(view);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(view2);
        window.show();
    }
}
