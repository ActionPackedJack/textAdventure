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
    public Room(String description, Room north, Room south, Room east, Room west, Room vent, Hide hidingPlace, List<Item> pickups, List<Prop> inspectables){
        this.north=north;
        this.south=south;
        this.east=east;
        this.west=west;
        this.vent=vent;
        this.hidingPlace=hidingPlace;
        this.pickups=pickups;
        this.inspectables=inspectables;
    }