package spotifyclient;

public class Playlist {
    private String name;
    private TrackItems tracks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TrackItems getTracks() {
        return tracks;
    }

    public void setTracks(TrackItems tracks) {
        this.tracks = tracks;
    }
}
