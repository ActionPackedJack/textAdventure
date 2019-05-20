import java.util.List;
public class Room{
    public String description = null;
    public Room north = null ;
    public Room south = null;
    public Room east = null;
    public Room west = null;
    public Room vent = null;
    public Hide hidingPlace = null;
    public List<Item> pickups;
    public List<Prop> inspectables;
    public Room(String description, Hide hidingPlace, List<Item> pickups, List<Prop> inspectables){
        this.north=null;
        this.south=null;
        this.east=null;
        this.west=null;
        this.vent=null;
        this.hidingPlace=hidingPlace;
        this.pickups=pickups;
        this.inspectables=inspectables;
    }