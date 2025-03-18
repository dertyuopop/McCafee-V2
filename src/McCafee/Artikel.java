package McCafee;

public class Artikel {
    private String name;
    private String size;
    private double price;

    // Constructor
    public Artikel(String name, String size, double price) {
        this.name = name;
        this.size = size;
        this.price = price;
    }

    // Get Methoden
    public String getName() { return name; }
    public String getSize() { return size; }
    public double getPrice() { return price; }

    //Überprüfe ob relevant für Stempelkarte
    public boolean istStempelrelevant() {
        return size.equalsIgnoreCase("groß");
    }

    // To String Methode
    @Override
    public String toString() {
        return name + " (" + size + ") - " + price + "€";
    }
}
