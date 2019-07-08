package classes;

import java.util.*;
public class Monster{
    public int alert = 0;
    public boolean stunned = false;
    public Room location;
    public Room interested = null;
    public Boolean seen = false;
    public Room lastTraveled = null;
    public Boolean moved = false;
    public List<Room> moveOptions;
    public void move(){
        this.moved = false;
        this.moveOptions.clear();
        if(this.interested == this.location){
            this.interested = null;
        }
        if(this.location.south !=null){
            this.moveOptions.add(this.location.south);
        }
        if(this.location.north !=null){
            this.moveOptions.add(this.location.north);
        }
        if(this.location.west !=null){
            this.moveOptions.add(this.location.west);
        }
        if(this.location.east !=null){
            this.moveOptions.add(this.location.east);
        }
        if(this.location.vent !=null){
            this.moveOptions.add(this.location.vent);
        }
        if(this.interested != null){
            for(int i=0; i<this.moveOptions.size();i++){
                if(this.moveOptions.get(i)==this.interested){
                    this.lastTraveled=this.location;
                    this.location=this.moveOptions.get(i);
                    this.moved = true;
                }
            }
            if(this.moved==false){
                for(int i=0; i<this.moveOptions.size();i++){
                    if(this.moveOptions.get(i).isAdjacent(this.interested)){
                        this.lastTraveled=this.location;
                        this.location=this.moveOptions.get(i);
                        this.moved = true;
                    }
                }
            }
            if(this.moved==false){
                for(int i=0; i<this.moveOptions.size();i++){
                    if(
                        this.moveOptions.get(i).north.isAdjacent(this.interested) || 
                        this.moveOptions.get(i).south.isAdjacent(this.interested) || 
                        this.moveOptions.get(i).east.isAdjacent(this.interested) || 
                        this.moveOptions.get(i).west.isAdjacent(this.interested) ||
                        this.moveOptions.get(i).vent.isAdjacent(this.interested)
                    ){
                        this.lastTraveled=this.location;
                        this.location=this.moveOptions.get(i);
                        this.moved = true;
                    }
                }
            }
        }
        if(this.moved ==false){
            if((this.moveOptions.size()>1 && this.lastTraveled !=null)){
                moveOptions.remove(this.lastTraveled);
            }
            this.lastTraveled=this.location;
            this.location= this.moveOptions.get((int)Math.random() * this.moveOptions.size() + 1);
            this.moved = true;
        }
    }
}