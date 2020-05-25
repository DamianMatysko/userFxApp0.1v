package sample;

import javafx.event.ActionEvent;
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
    public Label instructionText;

    ServerCommunication serverCommunication = new ServerCommunication();


    public void logInButton(ActionEvent actionEvent) throws IOException, IOException, InterruptedException {
        serverCommunication.login(logInText.getText(),passwordText.getText());


        Stage stage =new Stage();
        FXMLLoader fxmlLoader=new FXMLLoader();
        Pane root=fxmlLoader.load(getClass().getResource("secondSample.fxml").openStream());
        stage.setScene(new Scene(root,1000,500));
        stage.setTitle("second stage");
        stage.initStyle(StageStyle.TRANSPARENT);
        //Sfxml sfxml=fxmlLoader.getController();
        //sfxml.initData(stage);
        stage.showAndWait();
    }

    public void singUpButton(ActionEvent actionEvent) {
    }

    public void logOutButton(ActionEvent actionEvent) throws IOException {
        serverCommunication.logout();
    }
}
