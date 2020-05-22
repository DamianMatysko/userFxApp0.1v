package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Controller {
    public TextField logInText;
    public PasswordField passwordText;
    public Label instructionText;

    ServerCommunication serverCommunication = new ServerCommunication();


    public void logInButton(ActionEvent actionEvent) throws IOException, IOException, InterruptedException {
        serverCommunication.login(logInText.getText(),passwordText.getText());
    }

    public void singUpButton(ActionEvent actionEvent) {
    }
}
