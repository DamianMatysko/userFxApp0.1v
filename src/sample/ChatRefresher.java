package sample;

import javafx.scene.text.Text;

import java.awt.*;
import java.io.IOException;

public class ChatRefresher extends Thread {
    private Thread t;
    boolean stop = true;
    Text text;
    ServerCommunication serverCommunication;

    public ChatRefresher(Text text,ServerCommunication serverCommunication) {
        this.text=text;
        this.serverCommunication=serverCommunication;
        System.out.println("Vlakno MyThread bolo vytvorene");
    }

    public void run(){
        System.out.println("Vlakno sa spustilo a bezi");

        while (stop){
            try {
                text.setText(serverCommunication.getMessages());
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
