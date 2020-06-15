package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeController {

    public Label consoleText;
    public Button logoutButton;
    public Label nameLabel;
    public TextField messageField;
    public TextField toUserField;
    public Text textForLogAndMes;
    public Button changePassButton;
    public Button deleteMessages;
    public ListView listView;

    public void setListView(ListView listView) {
        this.listView = listView;
    }



    ChatRefresher chatRefresher = null;
    private static ServerCommunication serverCommunication = null;

    public void setServerCommunication(ServerCommunication serverCommunication) {
        HomeController.serverCommunication = serverCommunication;
        this.nameLabel.setText((serverCommunication.getLogin() + " - " + serverCommunication.getFname() + " " + serverCommunication.getLname()).toUpperCase());
    }

    public void logoutMethod(ActionEvent actionEvent) throws IOException {
        serverCommunication.logout();


        chatRefresher.killThread();


        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
    }

    public void sendButton(ActionEvent actionEvent) throws IOException {
        serverCommunication.sendMessage(toUserField.getText(), messageField.getText());
        consoleText.setText("The message was sent");
    }

    public void showLogs(ActionEvent actionEvent) throws IOException, InterruptedException {

        chatRefresher.killThread();//todo ked ide prvi nema co zabit

        //textForLogAndMes.setText(serverCommunication.log());
        System.out.println(serverCommunication.log());
        String string = serverCommunication.log();
              JSONObject response = new JSONObject(string);
        ObservableList listLogs = FXCollections.observableArrayList();


        for(int i = 0 ; i < response.length() ; i++){
          listLogs.addAll(response.getJSONObject(String.valueOf(i)));
        }
        listView.setItems(listLogs);
}

    public void showMessages(ActionEvent actionEvent) throws IOException, InterruptedException {
        if (chatRefresher == null || chatRefresher.isInterrupted() ) {
            chatRefresher = new ChatRefresher(textForLogAndMes, serverCommunication,listView);
            chatRefresher.start();
        }
        chatRefresher.interrupt();
    }

    public void changePassword(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("changePasswordSample.fxml"));
        Parent root = loader.load();


        ChangePasswordController changePasswordController = loader.getController();

        changePasswordController.setServerCommunication(serverCommunication);


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
