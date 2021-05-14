import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/*public class CardiologistApplicationMain extends Application {
    private Stage primaryStage;
    //private BorderPane mainLayout;

    public void start(Stage primaryStage) throws IOException {

        this.primaryStage=primaryStage;
        this.primaryStage.setTitle("Cardiologist Appointment Application");
    }

    private void showHomePage() throws IOException{

        Parent root = FXMLLoader.load(CardiologistApplicationMain.class.getResource("view/HomePage.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}*/

public class CardiologistApplicationMain extends Application {

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

       // UserService.loadUsersFromFile();

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/HomePage.fxml"));
        primaryStage.setTitle("Cardiologist Application");
        primaryStage.setScene(new Scene(root, 576, 432));
        primaryStage.show();
    }
}
