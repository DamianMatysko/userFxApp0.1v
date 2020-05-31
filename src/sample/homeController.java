package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class homeController {

    public Label consoleText;
    public Button logoutButton;
    ;
    public Label nameLabel;
    public TextField messageField;
    public TextField toUserField;
    public Text textForLogAndMes;

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

    public void sendButton(ActionEvent actionEvent) throws IOException {
        serverCommunication.sendMessage(toUserField.getText(), messageField.getText());
        consoleText.setText("The message was sent");
    }

    public void showLogs(ActionEvent actionEvent) throws IOException {
        textForLogAndMes.setText(serverCommunication.log());
    }

    public void showMessages(ActionEvent actionEvent) throws IOException, InterruptedException {
        //for (;;) {

            textForLogAndMes.setText(serverCommunication.getMessages());
            //
        //}
    }

    public void changePassword(ActionEvent actionEvent) {
    }
}
