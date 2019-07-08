package classes;

import java.util.*;
public class Room{
    public String name = null;
    public String description = null;
    public Room north = null ;
    public Room south = null;
    public Room east = null;
    public Room west = null;
    public Room vent = null;
    public Hide hidingPlace = null;
    public List<Item> pickups;
    public List<Prop> inspectables;
    public Room(String name, String description, Hide hidingPlace){
        this.name=name;
        this.north=null;
        this.south=null;
        this.east=null;
        this.west=null;
        this.vent=null;
        this.hidingPlace=hidingPlace;
        this.pickups= new ArrayList<Item>();
        this.inspectables=new ArrayList<Prop>();
    }
    public Boolean isAdjacent(Room query){
        if(this.north == query || this.south == query || this.east == query || this.west == query || this.vent == query){
            return true;
        }
        return false;
    }
}