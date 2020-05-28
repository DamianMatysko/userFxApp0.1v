package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import java.io.IOException;


public class Controller {
    public TextField logInText;
    public PasswordField passwordText;

    public Button singupButton;
    public Button loginButton;
    public Button logoutButton;



    private static ServerCommunication serverCommunication = new ServerCommunication();

    @FXML
    public Label instructionText;
    public Label testLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label consoleText;


    public void singUpButton(ActionEvent actionEvent) {
    }


    public void loginMethod(ActionEvent actionEvent) throws IOException {
        serverCommunication.login(logInText.getText(), passwordText.getText());

        System.out.println((serverCommunication.getLogin() + " - " + serverCommunication.getFname() + " " + serverCommunication.getLname()).toUpperCase());
        instructionText.setText((serverCommunication.getLogin() + " - " + serverCommunication.getFname() + " " + serverCommunication.getLname()).toUpperCase());
        //testLabel.setText((serverCommunication.getLogin() + " - " + serverCommunication.getFname() + " " + serverCommunication.getLname()).toUpperCase());
        //consoleText.setText((serverCommunication.getLogin() + " - " + serverCommunication.getFname() + " " + serverCommunication.getLname()).toUpperCase());
        //nameLabel.setText((serverCommunication.getLogin() + " - " + serverCommunication.getFname() + " " + serverCommunication.getLname()).toUpperCase());


        Stage window = (Stage) loginButton.getScene().getWindow();
        window.close();


        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane root = fxmlLoader.load(getClass().getResource("secondSample.fxml").openStream());
        stage.setScene(new Scene(root, 1000, 500));
        stage.setTitle("second stage");
        stage.initStyle(StageStyle.TRANSPARENT);
        //Sfxml sfxml=fxmlLoader.getController();
        //sfxml.initData(stage);
        stage.show();
    }

    public void singupMethod(ActionEvent actionEvent) {

    }

    public void sendButton(ActionEvent actionEvent) {
    }

    public void logoutMethod(ActionEvent actionEvent) throws IOException {
        serverCommunication.logout();

        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
    }
}
