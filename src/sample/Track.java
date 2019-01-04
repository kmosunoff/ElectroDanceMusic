package sample;

public class Track {
    private String artistName;
    private String trackName;
    private String genre;
    private Integer bpm;
    private String duration;
    private String releaseDate;
    private Double cost;

    public Track(String artistName, String trackName, String genre, Integer bpm, String duration, String releaseDate, Double cost) {
        this.artistName = artistName;
        this.trackName = trackName;
        this.genre = genre;
        this.bpm = bpm;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.cost = cost;
    }

    public Track(String trackName, String genre, Integer bpm, String duration, String releaseDate, Double cost) {
        this.trackName = trackName;
        this.genre = genre;
        this.bpm = bpm;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.cost = cost;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getTrackName() {
        return trackName;
    }

    public String getGenre() {
        return genre;
    }

    public Integer getBpm() {
        return bpm;
    }

    public String getDuration() {
        return duration;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Double getCost() {
        return cost;
    }

    public String getYear() {
        return releaseDate.substring(0, 4);
    }
}
