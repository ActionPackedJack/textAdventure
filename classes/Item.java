package classes;

public class Item {
    public String[] name = null;
    public String description = null;
    public String pickUpText = null;
    public String propDescription;
    public Prop location;

    public Item(String[] name, String description, String pickUpText, String propDescription, Prop location) {
        this.name = name;
        this.description = description;
        this.pickUpText = pickUpText;
        this.propDescription = propDescription;
        this.location = location;
    }
}