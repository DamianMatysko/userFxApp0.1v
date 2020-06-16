package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginController {
    public TextField logInText;
    public PasswordField passwordText;
    public Button singupButton;
    public Button loginButton;
    ;
    public Label instructionText;
    private static ServerCommunication serverCommunication = new ServerCommunication();

    public void loginMethod(ActionEvent actionEvent) throws IOException {
        if (!serverCommunication.login(logInText.getText(), passwordText.getText())) {
            instructionText.setText(serverCommunication.getResponseMessage().toString());
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("homeSample.fxml"));
        Parent root = loader.load();

        HomeController homeController = loader.getController();

        homeController.setServerCommunication(serverCommunication);

        Stage stage = new Stage();
        stage.setScene(new Scene(root, 1000, 500));
        stage.setTitle("ITBANK");
        stage.show();

        Stage window = (Stage) loginButton.getScene().getWindow();
        window.close();
    }

    public void singupMethod(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("registrationSample.fxml"));
        Parent root = loader.load();

        registrationController registrationController = loader.getController();

        registrationController.setServerCommunication(serverCommunication);

        Stage stage = new Stage();
        stage.setScene(new Scene(root, 300, 275));
        stage.setTitle("ITBANK");
        stage.show();

        Stage window = (Stage) singupButton.getScene().getWindow();
        window.close();
    }
}
