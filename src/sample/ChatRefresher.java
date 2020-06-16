package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.io.IOException;

public class ChatRefresher extends Thread {
    private Thread t;
    boolean stop = true;
    boolean pauzet = false;
    Text text;
    ServerCommunication serverCommunication;
    public ListView listView;

    public boolean isPauzet() {
        return pauzet;
    }

    public void setPauzet(boolean pauzet) {
        this.pauzet = pauzet;
    }

    public ChatRefresher(Text text, ServerCommunication serverCommunication, ListView listView) {
        this.listView = listView;
        this.text = text;
        this.serverCommunication = serverCommunication;
        System.out.println("Vlakno MyThread bolo vytvorene");
    }

    public void run() {
        System.out.println("Vlakno sa spustilo a bezi");
        while (stop) {
            if (!pauzet) {
                try {
                    if (!serverCommunication.getMessages()) {
                        System.out.println("coskaproblem");
                    }

                    JSONObject response = new JSONObject(serverCommunication.getResponseMessage().toString());
                    ObservableList listLogs = FXCollections.observableArrayList();
                    JSONObject parse;

                    for (int i = 1; i <= response.length(); i++) {
                        parse = response.getJSONObject(String.valueOf(i));
                        listLogs.addAll("FROM: " + parse.getString("from") + " TIME: " + parse.getString("time") + " MESSAGE: " + parse.getString("message"));
                    }

                    listView.setItems(listLogs);
                    Thread.sleep(1000);
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Vlakno sa ukoncilo");
    }

    public void start() {
        System.out.println("Start");
        if (t == null)
            t = new Thread(this);
        t.start();
    }

    public void killThread() {
        stop = false;
    }
}
