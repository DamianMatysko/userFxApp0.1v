package sample;

import netscape.javascript.JSObject;
import org.json.JSONObject;

import java.io.*;
import java.net.*;

import java.nio.charset.StandardCharsets;

public class ServerCommunication {
    String token;

    public void login(String login, String password) throws IOException {
            String stringURL ="http://localhost:8080/login";
            URL url = new URL (stringURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            String jsonInputString = "{login: "+login+", password: "+password+"}";

            try(OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());


                JSONObject jsonResponze = new JSONObject(response.toString());
                token=jsonResponze.getString("token");
                System.out.println(token);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }


}
