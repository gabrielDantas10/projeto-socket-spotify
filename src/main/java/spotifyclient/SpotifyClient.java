package spotifyclient;

public class SpotifyClient {
    private static final String BASE_URL = "https://api.spotify.com/v1/playlists/";

    public void fetchAndPrintPlaylistDetails(String playlistId) {
        String accessToken = SpotifyAuth.getAccessToken();
        String url = BASE_URL + playlistId;
        String jsonResponse = HttpUtils.sendGetRequest(url, accessToken);
        SpotifyDataHandler dataHandler = new SpotifyDataHandler();
        dataHandler.handleResponse(jsonResponse);
    }
}
