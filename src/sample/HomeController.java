package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.IOException;

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
    ChatRefresher chatRefresher;
    private static ServerCommunication serverCommunication = null;

    public void setListView(ListView listView) {
        this.listView = listView;
    }

    public void setServerCommunication(ServerCommunication serverCommunication) {
        HomeController.serverCommunication = serverCommunication;
        this.nameLabel.setText((serverCommunication.getLogin() + " - " + serverCommunication.getFname() + " " + serverCommunication.getLname()).toUpperCase());
    }

    public void logoutMethod(ActionEvent actionEvent) throws IOException {
        serverCommunication.logout();
        try {
            chatRefresher.killThread();
        } catch (Exception e) {
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.close();
        }
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
    }

    public void sendButton(ActionEvent actionEvent) throws IOException {
        if (!serverCommunication.sendMessage(toUserField.getText(), messageField.getText())) {
            consoleText.setText(serverCommunication.getResponseMessage().toString());
        }
        consoleText.setText(serverCommunication.getResponseMessage().toString());
    }

    public void showLogs(ActionEvent actionEvent) throws IOException, InterruptedException {

        if (chatRefresher != null) {
            if (!chatRefresher.isPauzet()) {
                try {
                    chatRefresher.setPauzet(true);
                } catch (Exception e) {
                }
            }
        }

        if (!serverCommunication.log()) {
            textForLogAndMes.setText(serverCommunication.getResponseMessage().toString());
        }
        JSONObject response = new JSONObject(serverCommunication.getResponseMessage().toString());
        ObservableList listLogs = FXCollections.observableArrayList();
        JSONObject parse;

        for (int i = 1; i <= response.length(); i++) {
            parse = response.getJSONObject(String.valueOf(i));
            listLogs.addAll(parse.getString("type").toUpperCase() + " TIME: " + parse.getString("time"));
        }
        listView.setItems(listLogs);
    }

    public void showMessages(ActionEvent actionEvent) throws IOException, InterruptedException {//todo
        if (chatRefresher == null) {
            chatRefresher = new ChatRefresher(textForLogAndMes, serverCommunication, listView);
            chatRefresher.start();
        }
        if (!chatRefresher.isPauzet()) {
            chatRefresher.setPauzet(false);
        }
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
        consoleText.setText(serverCommunication.getResponseMessage().toString());
    }
}
