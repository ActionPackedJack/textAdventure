package classes;

import java.util.ArrayList;
import java.util.List;
public class Player{
    public List<Item> inventory;
    public Room location;
    public boolean bleeding = false;
    public boolean hiding = false;
    public int health = 10;
    public boolean alive = true;
    public boolean invisible = false;
    
    public Player (){
        this.inventory = new ArrayList();
        this.health = 10;
        this.location = null;
        this.hiding = false;
        this.bleeding = false;
        this.alive = true;
        this.invisible = false;
    }
}