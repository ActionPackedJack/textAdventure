package classes;

import java.util.*;
public class Room{
    public String name = null;
    public String description;
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
        this.description=description;
    }
    public Boolean isAdjacent(Room query){
        if(this.north == query || this.south == query || this.east == query || this.west == query || this.vent == query){
            return true;
        }
        return false;
    }
    public List<Room> adjacentRooms(){
        List<Room> result = new ArrayList<>();
        if(this.south !=null){
            result.add(this.south);
        }
        if(this.north !=null){
            result.add(this.north);
        }
        if(this.west !=null){
            result.add(this.west);
        }
        if(this.east !=null){
            result.add(this.east);
        }
        if(this.vent !=null){
            result.add(this.vent);
        }
        return result;
    }
    public String whereAdjacent(Room query){
        if(this.north == query){
            return "to the north";
        }
        if(this.south == query){
            return "to the south";
        }
        if(this.east == query){
            return "to the east";
        }
        if(this.west == query){
            return "to the west";
        }
        if(this.vent == query){
            return "through the ventilation system";
        }
        return "indeterminate direction";
    }
}