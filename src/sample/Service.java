package sample;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Service implements ServiceInterface {

    private DAO dao;

    public Service() throws SQLException {
        dao = new DAO();
    }

    public List<Track> getTracksByArtist(String artistName) throws SQLException {
        List<Track> response = new ArrayList<>();
        for (Track track : dao.getTracksWithArtists()) {
            if (track.getArtistName().equals(artistName)) {
                response.add(track);
            }
        }
        return response;
    }

    public List<Track> getTracks() throws SQLException {
        return dao.getTracks();
    }

    public List<Artist> getArtists() throws SQLException {
        return dao.getArtists();
    }

    public Integer getAmountOfTracks(String year, String genre) throws SQLException {
        return dao.getTracks().stream().filter((track) -> track.getYear().equals(year)
                                                            && track.getGenre().equals(genre)).toArray().length;
    }

    public Double getCommonSumOfPurchases(String year, String genre) throws SQLException {
        List<Purchase> purchases = dao.getPurchases();
        Double response = 0.0;
        for (Purchase purchase : purchases) {
            if (purchase.getGenre().equals(genre) && purchase.getYear().equals(year)) {
                response += purchase.getCost();
            }
        }
        return response;
    }
}
