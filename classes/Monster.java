package classes;

import java.util.*;
public class Monster{
    public int alert = 0;
    //The below data will keep the monster from taking action if it is true.
    public boolean stunned = false;
    public Room location;
    //The below data can store a room that the monster wants to investigate.  If there is none, it will move randomly.
    public Room interested = null;
    //The below data tracks whether the player has seen the monster yet this game.  This is so that the initial, rather lengthy, description of its appearance will not keep being displayed.
    public Boolean seen = false;
    //The below data stores the last room the monster visited, to prevent it from just moving back and forth between two rooms.
    public Room lastTraveled = null;
    //The next two points of data data are used for logic when the monster is figuring out where to move.
    public Boolean moved = false;
    public List<Room> moveOptions;
    //The below data will be turned to false to indicate that the player has won.
    public Boolean alive = true;
    //The below data is used for a hidden interaction.
    public Boolean posturing = false;
    static Random random;
    public void move(){
        this.moved = false;
        this.moveOptions = this.location.adjacentRooms();
        if(this.interested == this.location) {
            this.interested = null;
        }
        if(this.interested != null){
            //If the monster wants to move to a particular room, it will first check whether that room is adjacent to its current location.
            for(int i=0; i<this.moveOptions.size();i++){
                if(this.moveOptions.get(i)==this.interested){
                    this.lastTraveled=this.location;
                    this.location=this.moveOptions.get(i);
                    this.moved = true;
                }
            }
            //If not, it will check whether that room is adjacent to any adjacent rooms.
            if(this.moved==false){
                for(int i=0; i<this.moveOptions.size();i++){
                    if(this.moveOptions.get(i).isAdjacent(this.interested)){
                        this.lastTraveled=this.location;
                        this.location=this.moveOptions.get(i);
                        this.moved = true;
                    }
                }
            }
            //If not, it checks whether that room is adjacent to any room adjacent to its current location.  Because the monster can "teleport" through ventilation shafts, it will never be more than three moves away from any room.
            if(this.moved==false){
                for(int i=0; i<this.moveOptions.size();i++){
                    for( int j=0; j<this.moveOptions.get(i).adjacentRooms().size(); j++)
                    if(
                        this.moveOptions.get(i).adjacentRooms().get(j).isAdjacent(this.interested)
                    ){
                        this.lastTraveled=this.location;
                        this.location=this.moveOptions.get(i);
                        this.moved = true;
                    }
                }
            }
        }
        //If the monster is not interested in a particular destination and has more than one move option, its most recently visited room will be removed from its list of options and then it randomly select one of its remaining options.
        if(this.moved ==false) {
            if ((this.moveOptions.size() > 1 && this.lastTraveled != null)) {
                moveOptions.remove(this.lastTraveled);
            }
            this.lastTraveled = this.location;
            this.location = this.moveOptions.get(random.nextInt(this.moveOptions.size()));
            this.moved = true;
        }
        //The player is warned whenever the monster moves through a vent.
        if(this.moved==true && this.lastTraveled.vent == this.location) {
            System.out.println("You hear ominous noises in the ventilation shafts.");
        }
    }
}