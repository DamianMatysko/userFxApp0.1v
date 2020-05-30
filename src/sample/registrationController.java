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

    private static ServerCommunication serverCommunication=null;


    public void setServerCommunication(ServerCommunication serverCommunication) {
        registrationController.serverCommunication = serverCommunication;
    }

    public void singupMethod(ActionEvent actionEvent) throws IOException {
        if (firstnameField.getText().equals("") && lastnameField.getText().equals("") && logInText.getText().equals("") && passwordText.getText().equals("")){
            instructionText.setText("You must fill all informations");
            return;
        }
        if (serverCommunication.signup(logInText.getText(), passwordText.getText(), firstnameField.getText(), lastnameField.getText()).equals("User already exists")){
            instructionText.setText("User already exists");
            return;
        }

        //Load second scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("loginSample.fxml"));
        Parent root = loader.load();

        //Get controller of scene2
        LoginController loginController  = loader.getController();
        //Pass whatever data you want. You can have multiple method calls here
        //loginController.setServerCommunication(serverCommunication);

        //Show scene 2 in new window
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 300, 275));
        stage.setTitle("ITBANK");
        stage.show();

        //stage.initStyle(StageStyle.TRANSPARENT);


        Stage window = (Stage) singupButton.getScene().getWindow();
        window.close();
    }
}
