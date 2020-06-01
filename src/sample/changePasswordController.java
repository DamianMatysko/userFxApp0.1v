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

public class changePasswordController {
    public TextField newLogInText;
    public PasswordField newPasswordText;
    public Label instructionText;
    public Button changeButton;
    public PasswordField oldPasswordField;
    public Button backButton;



    private static ServerCommunication serverCommunication=null;

    public void setServerCommunication(ServerCommunication serverCommunication) {
        changePasswordController.serverCommunication = serverCommunication;
    }

    public void changePassMethod(ActionEvent actionEvent) throws IOException {
        if (oldPasswordField.getText() == null || oldPasswordField.getText().trim().isEmpty()) {
            instructionText.setText("You must fill old password");
            return;
        }
        if (newPasswordText.getText() == null || newPasswordText.getText().trim().isEmpty()) {
            instructionText.setText("You must fill new password");
            return;
        }
        serverCommunication.changePassword(oldPasswordField.getText(),newPasswordText.getText());
        instructionText.setText("Success changed");
    }

    public void backMethod(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("homeSample.fxml"));
        Parent root = loader.load();

        homeController homeController = loader.getController();
        homeController.setServerCommunication(serverCommunication);

        Stage stage = new Stage();
        stage.setScene(new Scene(root, 1000, 500));
        stage.setTitle("ITBANK");
        stage.show();

        Stage window = (Stage) backButton.getScene().getWindow();
        window.close();
    }
}
