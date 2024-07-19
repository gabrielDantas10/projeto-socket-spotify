package spotifyclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import java.util.Base64;
import java.util.Properties;
import java.io.FileInputStream;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SpotifyAuth {
    private static final String TOKEN_URL = "https://accounts.spotify.com/api/token";
    private static String clientId;
    private static String clientSecret;

    static {
        loadProperties();
    }

    private static void loadProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/application.properties"));
            clientId = properties.getProperty("spotify.client.id");
            clientSecret = properties.getProperty("spotify.client.secret");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getAccessToken() {
        try {
            URL url = new URL(TOKEN_URL);
            String encodedCredentials = Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes());
            String params = "grant_type=client_credentials";

            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Basic " + encodedCredentials);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setDoOutput(true);

            try (OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream())) {
                writer.write(params);
                writer.flush();
            }

            StringBuilder response = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            }

            conn.disconnect();

            JsonObject jsonObject = JsonParser.parseString(response.toString()).getAsJsonObject();
            return jsonObject.get("access_token").getAsString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
