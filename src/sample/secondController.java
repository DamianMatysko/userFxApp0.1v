package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class secondController {
    public TextField logInText;
    public Label consoleText;
    public Button logoutButton;
    public PasswordField passwordText;
    public Label nameLabel;

    private static ServerCommunication serverCommunication=null;

    public static void setServerCommunication(ServerCommunication serverCommunication) {
        secondController.serverCommunication = serverCommunication;
    }

    public void logoutMethod(ActionEvent actionEvent) {
        System.out.println((serverCommunication.getLogin() + " - " + serverCommunication.getFname() + " " + serverCommunication.getLname()).toUpperCase());
        nameLabel.setText((serverCommunication.getLogin() + " - " + serverCommunication.getFname() + " " + serverCommunication.getLname()).toUpperCase());
        //testLabel.setText((serverCommunication.getLogin() + " - " + serverCommunication.getFname() + " " + serverCommunication.getLname()).toUpperCase());
        //consoleText.setText((serverCommunication.getLogin() + " - " + serverCommunication.getFname() + " " + serverCommunication.getLname()).toUpperCase());
        //nameLabel.setText((serverCommunication.getLogin() + " - " + serverCommunication.getFname() + " " + serverCommunication.getLname()).toUpperCase());

    }

    public void sendButton(ActionEvent actionEvent) {
    }
}
