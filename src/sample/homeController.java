package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class homeController {
    public TextField logInText;
    public Label consoleText;
    public Button logoutButton;
    public PasswordField passwordText;
    public Label nameLabel;

    private static ServerCommunication serverCommunication=null;
    public ScrollPane scrollPane;
    public TextField messageField;
    public TextField toUserField;

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

    public void showLogs(ActionEvent actionEvent) throws IOException {
        consoleText.setText(serverCommunication.log());
    }
}
