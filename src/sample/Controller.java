package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;


public class Controller {
    public TextField logInText;
    public PasswordField passwordText;
    public Button singupButton;
    public Button loginButton;
    public Button logoutButton;
    public Label instructionText;

    private static ServerCommunication serverCommunication = new ServerCommunication();

    public void singUpButton(ActionEvent actionEvent) {
    }

    public void loginMethod(ActionEvent actionEvent) throws IOException {
        serverCommunication.login(logInText.getText(), passwordText.getText());


        //Load second scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("secondSample.fxml"));
        Parent root = loader.load();

        //Get controller of scene2
        secondController scene2Controller = loader.getController();
        //Pass whatever data you want. You can have multiple method calls here
        secondController.setServerCommunication(serverCommunication);

        //Show scene 2 in new window
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 1000, 500));
        stage.setTitle("Second Window");
        stage.show();






/*
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane root = fxmlLoader.load(getClass().getResource("secondSample.fxml").openStream());
        stage.setScene(new Scene(root, 1000, 500));
        stage.setTitle("second stage");
        stage.initStyle(StageStyle.TRANSPARENT);
        //Sfxml sfxml=fxmlLoader.getController();
        //sfxml.initData(stage);
        stage.show();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/javafxcontrollercommunication/scene2/scene2.fxml"));
        Parent root = loader.load();
        secondController scene2Controller = loader.getController();
        //Pass whatever data you want. You can have multiple method calls here
        scene2Controller.transferMessage(inputField.getText());


*/

        Stage window = (Stage) loginButton.getScene().getWindow();
        window.close();
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
