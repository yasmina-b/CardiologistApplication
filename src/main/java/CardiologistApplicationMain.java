import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CardiologistApplicationMain extends Application {

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/HomePage.fxml"));
        primaryStage.setTitle("Cardiologist Application");
        primaryStage.setScene(new Scene(root, 576, 432));
        primaryStage.show();
    }
}
