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

public class registrationController {
    public Button singupButton;
    public Label instructionText;
    public PasswordField passwordText;
    public TextField logInText;
    public TextField lastnameField;
    public TextField firstnameField;
    private static ServerCommunication serverCommunication = null;

    public void setServerCommunication(ServerCommunication serverCommunication) {
        registrationController.serverCommunication = serverCommunication;
    }

    public void singupMethod(ActionEvent actionEvent) throws IOException {
        if (firstnameField.getText().equals("") && lastnameField.getText().equals("") && logInText.getText().equals("") && passwordText.getText().equals("")) {
            instructionText.setText("You must fill all informations");
            return;
        }
        if (!serverCommunication.signup(logInText.getText(), passwordText.getText(), firstnameField.getText(), lastnameField.getText())) {
            instructionText.setText(serverCommunication.getResponseMessage().getString("error"));
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("loginSample.fxml"));
        Parent root = loader.load();

        LoginController loginController = loader.getController();

        Stage stage = new Stage();
        stage.setScene(new Scene(root, 300, 275));
        stage.setTitle("ITBANK");
        stage.show();

        Stage window = (Stage) singupButton.getScene().getWindow();
        window.close();
    }
}
