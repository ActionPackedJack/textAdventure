package classes;

import java.util.*;
public class Room{
    //Below is the room's name.
    public String name = null;
    //Below is what will be printed when the player is in the room and examines their surroundings.
    public String description;
    //The next five variables are used to link rooms together.
    public Room north = null ;
    public Room south = null;
    public Room east = null;
    public Room west = null;
    public Room vent = null;
    //The next three variables specify whether the room has a place the player can hide, as well as items they can pick up or scenery they can look at..  These will be filled in when the ship is created in the main method.
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
    //Rooms have a method to check whether another room is adjacent.
    public Boolean isAdjacent(Room query){
        if(this.north == query || this.south == query || this.east == query || this.west == query || this.vent == query){
            return true;
        }
        return false;
    }
    //They can also list all adjacent rooms.
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
    //Additionally, they can determine which directional path  will lead to an adjacent room.
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