import java.util.List;
public class Player{
    public List<Item> inventory = Arrays.asList(new stunGrenade());
    public Room location;
    public boolean bleeding = false;
    public boolean hiding = false;
    public int health = 10;
}