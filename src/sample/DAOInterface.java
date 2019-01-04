package sample;

import java.sql.SQLException;
import java.util.List;

public interface DAOInterface {
    public List<Track> getTracks() throws SQLException;
    public List<Track> getTracksWithArtists() throws SQLException;
    public List<Artist> getArtists() throws SQLException;
    public List<Purchase> getPurchases() throws SQLException;
}
