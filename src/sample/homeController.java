package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class homeController {
    public TextField logInText;
    public Label consoleText;
    public Button logoutButton;
    public PasswordField passwordText;
    public Label nameLabel;

    private static ServerCommunication serverCommunication=null;

    public void setServerCommunication(ServerCommunication serverCommunication) {
        homeController.serverCommunication = serverCommunication;
        this.nameLabel.setText((serverCommunication.getLogin() + " - " + serverCommunication.getFname() + " " + serverCommunication.getLname()).toUpperCase());
    }

    public void logoutMethod(ActionEvent actionEvent) throws IOException {
        serverCommunication.logout();

        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
    }

    public void sendButton(ActionEvent actionEvent) {
    }
}
