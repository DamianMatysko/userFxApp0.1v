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
    public Button loginButton;;
    public Label instructionText;

    private static ServerCommunication serverCommunication = new ServerCommunication();

    public void singUpButton(ActionEvent actionEvent) {
    }

    public void loginMethod(ActionEvent actionEvent) throws IOException {
        if (serverCommunication.login(logInText.getText(), passwordText.getText())==false){
            instructionText.setText("wrong login or password");
            return;
        }

        //Load second scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("homeSample.fxml"));
        Parent root = loader.load();

        //Get controller of scene2
        homeController homeController = loader.getController();
        //Pass whatever data you want. You can have multiple method calls here
        homeController.setServerCommunication(serverCommunication);

        //Show scene 2 in new window
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 1000, 500));
        stage.setTitle("ITBANK");
        stage.show();

        //stage.initStyle(StageStyle.TRANSPARENT);


        Stage window = (Stage) loginButton.getScene().getWindow();
        window.close();
    }

    public void singupMethod(ActionEvent actionEvent) {

    }




}
