package spotifyclient;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpUtils {
    public static String sendGetRequest(String requestUrl, String accessToken) {
        StringBuilder response = new StringBuilder();
        SSLSocket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            URL url = new URL(requestUrl);
            String host = url.getHost();
            int port = 443;  // Porta padrão para HTTPS

            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            socket = (SSLSocket) factory.createSocket(host, port);

            // Configurando o socket
            socket.startHandshake();

            // Criando streams para comunicação
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

            // Enviando requisição HTTP
            out.print("GET " + url.getPath() + " HTTP/1.1\r\n");
            out.print("Host: " + host + "\r\n");
            out.print("Authorization: Bearer " + accessToken + "\r\n");
            out.print("Content-Type: application/json\r\n");
            out.print("Connection: close\r\n");
            out.print("\r\n");
            out.flush();

            // Lendo a resposta
            String line;
            boolean headersEnded = false;
            while ((line = in.readLine()) != null) {
                if (headersEnded) {
                    response.append(line);
                }
                // Detectar o fim dos cabeçalhos HTTP
                if (line.isEmpty()) {
                    headersEnded = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return response.toString();
    }
}
