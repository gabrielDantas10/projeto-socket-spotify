package spotifyclient;

import com.google.gson.Gson;

public class SpotifyDataHandler {
    private Gson gson = new Gson();

    public void handleResponse(String jsonResponse) {
        Playlist playlist = gson.fromJson(jsonResponse, Playlist.class);
        printPlaylistDetails(playlist);
    }

    private void printPlaylistDetails(Playlist playlist) {
        if (playlist != null) {
            System.out.println("Playlist: " + playlist.getName());
            TrackItems trackItems = playlist.getTracks();
            if (trackItems != null && trackItems.getItems() != null) {
                for (TrackWrapper trackWrapper : trackItems.getItems()) {
                    Track track = trackWrapper.getTrack();
                    if (track != null) {
                        System.out.print("Musica: " + track.getName());
                        if (track.getArtists() != null && track.getArtists().length > 0) {
                            System.out.print(" / Artista(s): ");
                            for (Artist artist : track.getArtists()) {
                                System.out.print(artist.getName() + " ");
                            }
                        } else {
                            System.out.print(" / Artista(s): [Informacao nao disponivel]");
                        }
                        System.out.println();
                    }
                }
            } else {
                System.out.println("Nenhuma música disponível.");
            }
        } else {
            System.out.println("Dados da playlist indisponiveis.");
        }
    }
}
