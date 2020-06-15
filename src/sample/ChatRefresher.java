package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.awt.*;
import java.io.IOException;

public class ChatRefresher extends Thread {
    private Thread t;
    boolean stop = true;
    Text text;
    ServerCommunication serverCommunication;
    public ListView listView;

    public ChatRefresher(Text text,ServerCommunication serverCommunication, ListView listView) {
        this.listView = listView;
        this.text=text;
        this.serverCommunication=serverCommunication;
        System.out.println("Vlakno MyThread bolo vytvorene");
    }

    public void run(){
        System.out.println("Vlakno sa spustilo a bezi");

        while (stop){
            try {
                JSONObject response = new JSONObject(serverCommunication.getMessages());
                ObservableList listLogs = FXCollections.observableArrayList();
                JSONObject parse;

                for(int i = 1 ; i <= response.length() ; i++){
                    parse=response.getJSONObject(String.valueOf(i));
                    listLogs.addAll("FROM: "+parse.getString("from")+" TIME: "+parse.getString("time")+" MESSAGE: "+parse.getString("message"));
                }

                listView.setItems(listLogs);
                //text.setText(serverCommunication.getMessages());
                Thread.sleep(1000);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Vlakno sa ukoncilo");
    }

    public void start(){
        System.out.println("Start");
        if(t==null)
            t=new Thread(this);
        t.start(); // spustil run()
    }

    public void killThread(){
        stop=false;
    }
}
