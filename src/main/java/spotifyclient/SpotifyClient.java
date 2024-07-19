package spotifyclient;

public class SpotifyClient {
    private static final String BASE_URL = "https://api.spotify.com/v1/playlists/";

    // Busca e exibe os detalhes da playlist com base no ID fornecido
    public void fetchAndPrintPlaylistDetails(String playlistId) {
        // Obtém o token de acesso
        String accessToken = SpotifyAuth.getAccessToken();

        // Imprime o token de acesso vigente
        System.out.println("Token de acesso atual: " + accessToken);

        // Constrói a URL da requisição
        String url = BASE_URL + playlistId;

        // Imprime o ID da playlist antes de buscar os detalhes
        System.out.println("ID da Playlist: " + playlistId);

        // Envia a requisição e obtém a resposta JSON
        String jsonResponse = HttpUtils.sendGetRequest(url, accessToken);

        // Processa a resposta JSON e exibe os detalhes da playlist
        SpotifyDataHandler dataHandler = new SpotifyDataHandler();
        dataHandler.handleResponse(jsonResponse);
    }
}
