package classes;

public class Item {
    public String[] name = null;
    public String description = null;
    public String pickUpText = null;

    public Item(String[] name, String description, String pickUpText) {
        this.name = name;
        this.description = description;
        this.pickUpText = pickUpText;
    }
}