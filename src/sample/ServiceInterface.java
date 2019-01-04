package sample;

import java.sql.SQLException;
import java.util.List;

public interface ServiceInterface {
    public List<Track> getTracksByArtist(String artistName) throws SQLException;
    public List<Track> getTracks() throws SQLException;
    public List<Artist> getArtists() throws SQLException;
    public Integer getAmountOfTracks(String year, String genre) throws SQLException;
    public Double getCommonSumOfPurchases(String year, String genre) throws SQLException;
}
