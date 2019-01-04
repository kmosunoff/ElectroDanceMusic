package sample;

public class Purchase {
    private String genre;
    private String purchaseDate;
    private Double cost;

    public Purchase(String genre, String purchaseDate, Double cost) {
        this.genre = genre;
        this.purchaseDate = purchaseDate;
        this.cost = cost;
    }

    public String getGenre() {
        return genre;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public Double getCost() {
        return cost;
    }

    public String getYear() {
        return purchaseDate.substring(0, 4);
    }
}
