package sample;

import org.json.JSONObject;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class ServerCommunication {
    private static String lname;
    private static String fname;
    private static String login;
    private static String token;
    private static JSONObject responseMessage;

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

    public JSONObject getResponseMessage() {
        return responseMessage;
    }

    public boolean postOperation(String stringURL, String jsonInputString) throws IOException {
        InputStream inputStream = null;
        URL url = new URL("http://localhost:8080/" + stringURL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
            System.out.println(con.getResponseCode() + "ASDFJHGFJKGKHO::GYIYF" + con.getErrorStream());

            int statusCode = con.getResponseCode();
            InputStream is = null;
            if (!(statusCode >= 200 && statusCode < 400)) {
                is = con.getErrorStream();

                BufferedReader streamReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                StringBuilder responseStrBuilder = new StringBuilder();

                String inputStr;
                while ((inputStr = streamReader.readLine()) != null)
                    responseStrBuilder.append(inputStr);

                System.out.println(responseStrBuilder.toString());


                responseMessage = new JSONObject(responseStrBuilder.toString());
                return false;

            }

            is = con.getInputStream();
            System.out.println(is.toString());
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            StringBuilder responseStrBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);

            System.out.println(responseStrBuilder.toString());
            responseMessage = new JSONObject(responseStrBuilder.toString());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean login(String login, String password) throws IOException {
        if (!postOperation("login", "{login: " + login + ", password: " + password + "}")) {
            return false;
        }
        this.fname = responseMessage.getString("fname");
        this.lname = responseMessage.getString("lname");
        this.login = responseMessage.getString("login");
        this.token = responseMessage.getString("token");
        return true;
    }

    public boolean logout() throws IOException {
        return postOperation("logout?token=" + token, "{login: " + login + "}");
    }

    public boolean log() throws IOException {
        return postOperation("log?token=" + token, "{login:" + login + "}");
    }

    public boolean getMessages() throws IOException {
        return postOperation("messages?token=" + token, "{login:" + login + "}");
    }

    public boolean sendMessage(String to, String message) throws IOException {
        return postOperation("message/new?token=" + token, "{from:" + login + ", to:" + to + ", message:" + message + "}");
    }

    public boolean signup(String login, String password, String fname, String lname) throws IOException {
        return postOperation("signup", "{fname:" + fname + ", lname:" + lname + ",login: " + login + ",password: " + password + "}");
    }

    public boolean changePassword(String oldpassword, String newpassword) throws IOException {
        return postOperation("/changepassword?token=" + token, "{login:" + login + ", oldpassword:" + oldpassword + ", newpassword:" + newpassword + "}");
    }

    public boolean deleteMessages() throws IOException {
        return postOperation("delete/messages?token=" + token, "{login:" + login + "}");
    }
}