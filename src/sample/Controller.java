package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private LineChart<String, Double> lineChart;

    @FXML
    public TableView<Track> tracksTable;

    @FXML
    public TableView<Artist> artistsTable;

    @FXML
    public TextField artistNameField;

    @FXML
    public TextField periodField;

    @FXML
    public Slider periodSlider;

    @FXML
    private TableColumn<Artist, Integer> artistIdColumn;

    @FXML
    private TableColumn<Artist, String> artistNameColumn;

    @FXML
    private TableColumn<Track, String> trackNameColumn;

    @FXML
    private TableColumn<Track, String> genreColumn;

    @FXML
    private TableColumn<Track, Integer> bpmColumn;

    @FXML
    private TableColumn<Track, String> durationColumn;

    @FXML
    private TableColumn<Track, String> releaseDateColumn;

    @FXML
    private TableColumn<Track, Double> costColumn;

    @FXML
    private CategoryAxis barChartCategoryAxis;

    @FXML
    private NumberAxis barChartNumberAxis;

    @FXML
    private CategoryAxis lineChartCategoryAxis;

    @FXML
    private NumberAxis lineChartNumberAxis;

    private List<String> years;
    private List<String> genres;
    private Service service;

    @FXML
    public void initialize() throws SQLException {
        artistIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        artistNameColumn.setCellValueFactory(new PropertyValueFactory<>("nickname"));

        trackNameColumn.setCellValueFactory(new PropertyValueFactory<>("trackName"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        bpmColumn.setCellValueFactory(new PropertyValueFactory<>("bpm"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        releaseDateColumn.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
        costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));

        genres = new ArrayList<>();
        genres.add("Trap");
        genres.add("Drum&bass");
        genres.add("Dubstep");
        genres.add("Future house");
        genres.add("Electro house");

        service = new Service();
    }

    @FXML
    public void fillTracksTable() throws SQLException {
        List<Track> tracks = service.getTracksByArtist(artistNameField.getText());
        ObservableList<Track> tracksData = FXCollections.observableArrayList(tracks);
        tracksTable.setItems(tracksData);
    }

    @FXML
    public void showArtists() throws SQLException {
        List<Artist> artists = service.getArtists();
        ObservableList<Artist> artistsData = FXCollections.observableArrayList(artists);
        artistsTable.setItems(artistsData);
    }

    @FXML
    public void fillBarChartWithData() throws SQLException{
        barChart.getData().clear();

        Integer period = (int) periodSlider.getValue(); //Integer.parseInt(periodField.getText());

        barChart.setTitle("Количество треков по жанрам за последние " + period + " лет");
        barChartCategoryAxis.setLabel("Годы");
        barChartNumberAxis.setLabel("Количество");

        years = new ArrayList<>();
        for (int i = 2018 - period + 1; i < 2019; ++i) {
            years.add(Integer.toString(i));
        }
        barChartCategoryAxis.getCategories().clear();
        barChartCategoryAxis.setCategories(FXCollections.<String>
                observableArrayList(years));


        List<XYChart.Series<String, Number>> series = new ArrayList<>();
        for (String genre : genres) {
            XYChart.Series<String, Number> currentSeries = new XYChart.Series<>();
            series.add(currentSeries);
            currentSeries.setName(genre);
            for (String year : years) {
                currentSeries.getData().add(new XYChart.Data<>(year, service.getAmountOfTracks(year, genre)));
            }
        }

        barChart.getData().addAll(series);
    }

    @FXML
    public void fillLineChartWithData() throws SQLException {
        lineChart.getData().clear();

        Integer period = (int) periodSlider.getValue();

        lineChart.setTitle("Общая сумма покупок за последние " + period + " лет");
        lineChartCategoryAxis.setLabel("Годы");
        lineChartNumberAxis.setLabel("Общая сумма");

        List<String> years = new ArrayList<>();
        for (int i = 2018 - period + 1; i < 2019; ++i) {
            years.add(Integer.toString(i));
        }
        lineChartCategoryAxis.getCategories().clear();
        lineChartCategoryAxis.setCategories(FXCollections.<String>
                observableArrayList(years));

        List<XYChart.Series<String, Double>> series = new ArrayList<>();
        for (String genre : genres) {
            XYChart.Series<String, Double> currentSeries = new XYChart.Series<>();
            series.add(currentSeries);
            currentSeries.setName(genre);
            for (String year : years) {
                currentSeries.getData().add(new XYChart.Data<>(year, service.getCommonSumOfPurchases(year, genre)));
            }
        }

        lineChart.getData().addAll(series);

    }
}
