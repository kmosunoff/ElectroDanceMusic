package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DAO implements DAOInterface {

    private static final String user = "user";
    private static final String password = "user";

    private Connection connection;
    private Statement statement;

    public DAO() throws SQLException {
        this.connection = getConnection();
        this.statement = connection.createStatement();
    }

    public Connection getConnection() throws SQLException {
        Properties connectionProps = new Properties();
        connectionProps.put("user", "user");
        connectionProps.put("password", "user");

        return DriverManager.getConnection(
                "jdbc:sqlserver://127.0.0.1:1433;database=ElectroDanceMusic",
                connectionProps
        );
    }

    public List<Track> getTracks() throws SQLException {
        List<Track> response = new ArrayList<>();

        String query = "SELECT * FROM Tracks";
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            response.add(new Track(
                    resultSet.getString("TrackName"),
                    resultSet.getString("Genre"),
                    resultSet.getInt("BPM"),
                    resultSet.getString("Duration"),
                    resultSet.getString("ReleaseDate"),
                    resultSet.getDouble("Cost")
            ));
        }

        return response;
    }

    public List<Track> getTracksWithArtists() throws SQLException {
        List<Track> response = new ArrayList<>();

        String query = "SELECT * FROM ArtistTrack";
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            response.add(new Track(
                    resultSet.getString("ArtistName"),
                    resultSet.getString("TrackName"),
                    resultSet.getString("Genre"),
                    resultSet.getInt("BPM"),
                    resultSet.getString("Duration"),
                    resultSet.getString("ReleaseDate"),
                    resultSet.getDouble("Cost")
            ));
        }

        return response;
    }

    public List<Artist> getArtists() throws SQLException {
        List<Artist> response = new ArrayList<>();

        String query = "SELECT * FROM Artists";
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            response.add(new Artist(
                    resultSet.getInt("ArtistID"),
                    resultSet.getString("NickName")
            ));
        }

        return response;
    }

    public List<Purchase> getPurchases() throws SQLException {
        List<Purchase> response = new ArrayList<>();

        String query = "SELECT * FROM UserBoughtTrack";
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            response.add(new Purchase(
                    resultSet.getString("Genre"),
                    resultSet.getString("PurchaseDate"),
                    resultSet.getDouble("Cost")
            ));
        }

        return response;
    }
}
