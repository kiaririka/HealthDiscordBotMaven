package isha.project;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;


public class PostRequest {

    public static String post(String message) throws Exception {
        URL url = new URL("https://gpa-rho.vercel.app/getBard_Response");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        String requestBody = "{\"Query\": \"" + message + "\" ,\"Key\":\"sweetfriend\"}";
        connection.setDoOutput(true);
        connection.getOutputStream().write(requestBody.getBytes());

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String line;
        String reply = null;
        while ((line = bufferedReader.readLine()) != null) {

            JSONObject jsonObject = new JSONObject(line);

            reply = (String) jsonObject.get("Content");
//            System.out.println(reply);
        }
        return reply;
    }

}