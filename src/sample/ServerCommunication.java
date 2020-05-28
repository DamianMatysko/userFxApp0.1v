package sample;

import netscape.javascript.JSObject;
import org.json.JSONObject;

import java.io.*;
import java.net.*;

import java.nio.charset.StandardCharsets;

public class ServerCommunication {
    private static String lname;
    private static String fname;
    private static String login;
    private static String token;

    public String getLname() {
        return lname;
    }

    public String getFname() {
        return fname;
    }

    public String getLogin() {
        return login;
    }

    public String getToken() {
        return token;
    }

    public String postOperation(String stringURL, String jsonInputString) throws IOException {
        URL url = new URL("http://localhost:8080/" + stringURL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            return response.toString();
        }
    }

    public boolean login(String login, String password) throws IOException {
        String responseArchiver = postOperation("login", "{login: " + login + ", password: " + password + "}");

        if (responseArchiver.equals("wrong login or password")) {
            return false;
        }
        JSONObject jsonResponze = new JSONObject(responseArchiver);
        this.fname = jsonResponze.getString("fname");
        this.lname = jsonResponze.getString("lname");
        this.login = jsonResponze.getString("login");
        this.token = jsonResponze.getString("token");

        System.out.println(responseArchiver);
        return true;
    }

    public void logout() throws IOException {
        String response = postOperation("logout?token=" + token, "{login: " + login + "}");
        System.out.println(response);
    }

    public String log() throws IOException {
        String response = postOperation("log?token=" + token, "{login:" + login + "}");
        System.out.println(response);
        return response;
    }

}
