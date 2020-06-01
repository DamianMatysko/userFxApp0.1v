package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class homeController {

    public Label consoleText;
    public Button logoutButton;
    public Label nameLabel;
    public TextField messageField;
    public TextField toUserField;
    public Text textForLogAndMes;
    public Button changePassButton;
    public Button deleteMessages;

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
        //}
    }

    public void changePassword(ActionEvent actionEvent) throws IOException {
        //Load second scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("changePasswordSample.fxml"));
        Parent root = loader.load();

        //Get controller of scene2
        changePasswordController changePasswordController = loader.getController();
        //Pass whatever data you want. You can have multiple method calls here
        changePasswordController.setServerCommunication(serverCommunication);

        //Show scene 2 in new window
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 300, 275));
        stage.setTitle("ITBANK");
        stage.show();

        Stage window = (Stage) changePassButton.getScene().getWindow();
        window.close();
    }

    public void deleteMesages(ActionEvent actionEvent) throws IOException {
        serverCommunication.deleteMessages();
        consoleText.setText("Deleted");
        textForLogAndMes.setText(serverCommunication.getMessages());
    }
}
