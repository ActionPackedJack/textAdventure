import java.util.Scanner;
import java.util.List;
import java.util.Arrays;

public class TextAdventure{
    public static void main(){
        String[] actions= {
            "NORTH: move north",
            "SOUTH: move south",
            "EAST: move east",
            "WEST: move west",
            "WAIT: do nothing and let time pass",
            "INVENTORY: view your current possessions",
            "STATUS: take stock of your personal well-being",
            "LOOK: examine your general surroundings",
            "LOOK AT: examine specific object (requires followup prompt)",
            "TAKE: attempt to add an object to your inventory (requires followup prompt",
            "USE: activate an inventory item or object in your environment (requires followup prompt)",
            "HIDE: conceal yourself if a suitable method exists in your current location (requires followup prompt)",
            "EMERGE: exit your hiding spot",
            "HELP: you seem to understand this one already",
        };

        Scanner scanner = new Scanner(System.in);
        Room hypersleepChamber = new Room();
        Room cargoBay = new Room();
        Room diningHall = new Room();
        Room crewQuarters = new Room();
        Room captainsQuarters = new Room();
        Room weaponsStorage = new Room();
        Room surveillance = new Room();
        Room medicalBay = new Room();
        Room bridge = new Room();
        Room escapePods= new Room();
        Player player = new Player();
        Monster monster = new Monster();
        
    }
    public static void timePass(){

    }

    public static void parse(String input){
        input = input.toLowerCase();
        switch (input){
            case "help":
            case "instructions":
            case "how to play":
                System.out.println("Here is a list of non-secret commands (some synonyms may be accepted):");
                for(int i=0; i<actions.size(); i++){
                    System.out.println(actions.get(i));
                }
            case "north":
            case "n":
                if(player.hiding == true){
                    System.out.println("You cannot do that while hiding.  Type 'emerge' to leave your hiding spot");
                    break;
                }
                if(player.location.north != null){
                    player.location = player.location.north;
                    timePass();
                }
                else{
                    System.out.println("You cannot go north from here.");
                }
                break;
            case "south":
            case "s":
                if(player.hiding == true){
                    System.out.println("You cannot do that while hiding.  Type 'emerge' to leave your hiding spot");
                    break;
                }
                if(player.location.south != null){
                    player.location = player.location.south;
                    timePass();
                }
                else{
                    System.out.println("You cannot go south from here.");
                }
                break;
            case "east":
            case "e":
                if(player.hiding == true){
                    System.out.println("You cannot do that while hiding.  Type 'emerge' to leave your hiding spot");
                    break;
                }
                if(player.location.east != null){
                    player.location = player.location.east;
                    timePass();
                }
                else{
                    System.out.println("You cannot go east from here.");
                }
                break;
            case "west":
            case "w":
                if(player.hiding == true){
                    System.out.println("You cannot do that while hiding.  Type 'emerge' to leave your hiding spot");
                    break;
                }
                if(player.location.west != null){
                    player.location = player.location.west;
                    timePass();
                }
                else{
                    System.out.println("You cannot go west from here.");
                }
                break;
            case "look":
                if(player.hiding == true){
                    System.out.println(player.location.hidingPlace.description);
                    break;
                }
                System.out.println(player.location.description);
                break;
            case "inventory":
            case "items":
            case "possessions":
                if(player.inventory.size()==0){
                    System.out.println("You are not carrying anything noteworthy.");
                }
                System.out.println("You are carrying: ");
                for (int i=0; i<player.inventory.size(); i++){
                    System.out.println(player.inventory.get(i).name);
                }
                break;
            case "hide":
                if(player.hiding == true){
                    System.out.println("You are already hiding.");
                    break;
                }
                if(player.location.hidingPlace == null){
                    System.out.println("There is nowhere to hide here.");
                    break;
                }
                System.out.println("Where will you hide?");
                String hideAttempt = scanner.nextLine();
                if(hideAttempt.toLowerCase().contains(player.location.hidingPlace.name.toLowerCaser())){
                    System.out.println(player.location.hidingPlace.hideText);
                    player.hiding=true;
                    System.out.println("Type 'emerge' to leave your hiding spot.");
                    timePass();
                    break;
                }
                else{
                    System.out.println("You cannot hide there.");
                    break;
                }
            case "emerge":
            case "unhide":
                if(player.hiding == false){
                    System.out.println("You are already sitting in plain view.");
                    break;
                }
                else{
                    player.hiding = false;
                    System.out.println(player.location.hidingPlace.emergeText);
                    if(player.location.hidingPlace.noisy == true){
                        monster.interested = player.location;
                    }
                    timePass();
                    break;
                }
            case "look at":
            case "examine":
            case "inspect":
                if(player.hiding = true){
                    System.out.println("You are a little too cramped to do that.");
                    break;
                }
                System.out.println("What will you examine?");
                String lookAttempt = scanner.nextLine().toLowerCase();
                if(lookAttempt.contains(player.location.hidingPlace.name.toLowerCase())){
                    System.out.println(player.location.hidingPlace.description);
                    break;
                }
                for (int i=0; i<player.location.pickups.size(); i++){
                    if(lookAttempt.contains(player.location.pickups.get(i).name.toLowerCase())){
                        System.out.println(player.location.pickups.get(i).description);
                        break;
                    }
                }
                for (int i=0; i<player.location.inspectables.size(); i++){
                    if(lookAttempt.contains(player.location.inspectables.get(i).name.toLowerCase())){
                        System.out.println(player.location.inspectables.get(i).description);
                        break;
                    }
                }
                for (int i=0; i<player.inventory.size(); i++){
                    if(lookAttempt.contains(player.inventory.get(i).name.toLowerCase())){
                        System.out.println(player.inventory.get(i).description);
                        break;
                    }
                }
                System.out.println("You don't see one of those here.");
                break;
            case "pick up":
            case "pickup":
            case "take":
            case "get":
                System.out.println("What will you take?");
                String pickUpAttempt = scanner.nextLine().toLowerCase();
                for(int i=0; i<player.location.pickups.size(); i++){
                    if(pickUpAttempt.contains(player.location.pickups.get(i).name.toLowerCase())){
                        System.out.println(player.location.pickups.get(i).pickUpText);
                        player.inventory.add(player.location.pickups.get(i));
                        player.location.pickups.remove(i);
                        timePass();
                        break;
                    }
                }
                for(int i=0; i<player.location.inspectables.size(); i++){
                    if(pickUpAttempt.contains(player.location.inspectables.get(i).name.toLowerCase())){
                        System.out.println("You cannot pick that up.");
                        break;
                    }
                }
                if(pickUpAttempt.contains(player.location.hidingPlace.name)){
                    System.out.println("You cannot pick that up.");
                    break;
                }
                else{
                    System.out.println("You don't see one of those here.");
                    break;
                }
            case "use":
                System.out.println("What will you use?");
                String useAttempt = scanner.nextLine().toLowerCase();
                for(int i=0; i<player.location.inspectables.size(); i++){
                    if(useAttempt.contains(player.location.inspectables.get(i).name.toLowerCase())){
                        if(player.location.inspectables.get(i).useText == null){
                            System.out.println("You cannot discern a productive way to use that.");
                            timePass();
                            break;
                        }
                        else{
                            System.out.println(player.location.inspectables.get(i).useText);
                            timePass();
                            break;
                        }
                    }
                }
                for(int i=0; i<player.inventory.size(); i++){
                    if(useAttempt.contains(player.inventory.get(i).name.toLowerCase())){
                        System.out.println((player.inventory.get(i).useText));
                        timePass();
                        break;
                    }
                }
                System.out.println("You don't see one of those here.");
                break;
        }
    }
}
public class Room{
    public string description;
    public Room north;
    public Room south;
    public Room east;
    public Room west;
    public Room vent;
    public Hide hidingPlace;
    public List<Item> pickups;
    public List<Prop> inspectables;
}
public class Player{
    public List<Item> inventory = ;
    public Room location = hypersleepChamber;
    public bool bleeding = false;
    public bool hiding;
    public int health;
}
public class Monster{
    public int alert;
    public bool stunned;
    public Room location;
    public Room interested;
}
public class Item{
    public string name;
    public string description;
    public string useText;
    public string pickUpText;
}
public class Hide{
    public string name;
    public string hideText;
    public string emergeText;
    public string description;
    public string hideDescription;
    public bool noisy;
}
public class Prop{
    public string name;
    public string description;
    public string useText;
}