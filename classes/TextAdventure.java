package classes;

import java.util.Scanner;
import java.util.List;
import java.util.Arrays;
import classes.*;

public class TextAdventure{
    static Player player;
    static Monster monster;
    static Scanner scanner;
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        Room hypersleepChamber = new Room(
            "hypersleep chamber!!!",
            "You are standing in the ship's hypersleep chamber, lined with pods in which organic lifeforms can enter suspended animation for an extended period of time. One such pod sits open, as though its activity has just ended.",
            new Hide(
                "pod",
                "You crawl back inside your hypersleep pod and close the lid, but it can only be activated from outside.  You are safe from detection, but not the ravages of time.",
                "You press the emergency release button from inside the pod.  The door swings open, but not before a loud beep rings out.",
                "This pod lays idle, its door open.  You could fit inside it if you so desired.",
                "You are inside an inactive hypersleep pod.",
                true
            )
        );
        Room cargoBay = new Room(
                "cargo bay",
                "You are standing in the ship's cargo bay",
                null);
        Room diningHall = new Room("dining hall", "You are standing in the ship's dining hall", null);
        Room crewQuarters = new Room("crew quarters", "You are standing in the crew quarters", null);
        Room captainsQuarters = new Room(
            "captain's quarters",
            "You are in the captain's quarters.  There is a corpse slumped over a desk.",
                null
        );
        Room researchLab = new Room(
                "research lab",
                "You are in the research lab.  There are tables lined with samples of various liquids and minerals from all across the galaxy, though many of them have made their way to the floor.",
                null
        );
        monster = new Monster();
        monster.location = cargoBay;
        Room weaponsStorage = new Room("weapons storage", "You are in the weapons storage.", null);
        Prop dashboard = new Prop(
            new String[]{"screen","monitor","console", "dashboard", "television"},
            "These monitors are displaying video feed from cameras placed in every room of the ship.  You could use them to keep abreast of any unusual activities on the ship.",
            "You observe the monitors.  The ship appears to be littered with corpses, but you see no signs of life.  That is, until you glance at the camera feed in the " + monster.location.name + " and see a lifeform unlike anything you've seen before. It stands about six feet tall, has no discernible body fat, and has claws on every appendage.  Out from between its rows of pointy teeth, each longer than a human finger, drips a caustic green substance that appears to be partially melting whatever it lands on."
            
        );
        Room surveillance = new Room(
            "surveillance room",
            "You are in the surveillance room.  The walls are filled with closed-circuit television screens connected to the security cameras in each room.",
            null
        );
        Room medicalBay = new Room("medical bay", "You are in the medical bay.", null);
        Room bridge = new Room("bridge", "You are at the bridge of the ship.", null);
        Room escapePods= new Room("escape pods", "You are next to the ship's escape pods.", null);
        hypersleepChamber.north=diningHall;
        diningHall.south=hypersleepChamber;
        hypersleepChamber.east=medicalBay;
        medicalBay.west=hypersleepChamber;
        hypersleepChamber.hidingPlace = new Hide(
                "pod",
                "You crawl back inside your hypersleep pod and close the lid, but it needs to be activated from outside.  You are safe from detection, but not the ravages of time.",
                "You press the emergency release button from inside the pod.  The door swings open, but not before a loud beep rings out.", null,
                "You are inside an inactive hypersleep pod.",
                true);

        player = new Player();
        player.location = hypersleepChamber;
        //player.inventory.add(stunGrenade());
        System.out.println("You awaken from hypersleep, well rested but worried.  You are not greeted with the standard welcoming party; in fact, the ship is eerily quiet.");
        while(player.alive==true && monster.alive==true) {
            System.out.println("What will you do?");
            parse(scanner.nextLine());
        }
        if(player.alive==false){
            System.out.println("You are dead.");
//            switch (scanner.nextLine().toLowerCase()){
//                case "yes":
//                case "y":
//                    TextAdventure.main();
            return;
        }
    }
    public static void timePass(){
        
    }
    public static void listAdjacentRooms(){
        if(player.location.north!=null) {
            System.out.println("To the north is the " + player.location.north.name + ".");
        }
        if(player.location.south!=null) {
            System.out.println("To the south is the " + player.location.south.name + ".");
        }
        if(player.location.east!=null) {
            System.out.println("To the east is the " + player.location.east.name + ".");
        }
        if(player.location.west!=null) {
            System.out.println("To the west is the " + player.location.west.name + ".");
        }
        return;
    }

    public static void parse(String input){
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
            "TAKE: attempt to add an object to your inventory (requires followup prompt)",
            "USE: activate an inventory item or object in your environment (requires followup prompt)",
            "HIDE: conceal yourself if a suitable method exists in your current location (requires followup prompt)",
            "EMERGE: exit your hiding spot",
            "HELP: you seem to understand this one already",
        };
        input = input.toLowerCase();
        switch (input){
            case "help":
            case "instructions":
            case "how to play":
                System.out.println("Here is a list of non-secret commands (some synonyms may be accepted):");
                for(int i=0; i<actions.length; i++){
                    System.out.println(actions[i]);
                }
            case "north":
            case "n":
                if(player.hiding == true){
                    System.out.println("You cannot do that while hiding.  Type 'emerge' to leave your hiding spot.");
                    break;
                }
                if(player.location.north != null){
                    player.location = player.location.north;
                    timePass();
                    System.out.println(player.location.description);
                }
                else{
                    System.out.println("You cannot go north from here.");
                }
                break;
            case "south":
            case "s":
                if(player.hiding == true){
                    System.out.println("You cannot do that while hiding.  Type 'emerge' to leave your hiding spot.");
                    break;
                }
                if(player.location.south != null){
                    player.location = player.location.south;
                    timePass();
                    System.out.println(player.location.description);
                }
                else{
                    System.out.println("You cannot go south from here.");
                }
                break;
            case "east":
            case "e":
                if(player.hiding == true){
                    System.out.println("You cannot do that while hiding.  Type 'emerge' to leave your hiding spot.");
                    break;
                }
                if(player.location.east != null){
                    player.location = player.location.east;
                    timePass();
                    System.out.println(player.location.name);
                    System.out.println(player.location.description);
                }
                else{
                    System.out.println("You cannot go east from here.");
                }
                break;
            case "west":
            case "w":
                if(player.hiding == true){
                    System.out.println("You cannot do that while hiding.  Type 'emerge' to leave your hiding spot.");
                    break;
                }
                if(player.location.west != null){
                    player.location = player.location.west;
                    timePass();
                    System.out.println(player.location.name);
                    System.out.println(player.location.description);
                }
                else{
                    System.out.println("You cannot go west from here.");
                }
                break;
            case "look":
                if(player.hiding == true){
                    System.out.println(player.location.hidingPlace.hideDescription);
                    break;
                }
                System.out.println(player.location.name);
                System.out.println(player.location.description);
                listAdjacentRooms();
                break;
            case "inventory":
            case "items":
            case "possessions":
                if(player.inventory.size()==0){
                    System.out.println("You are not carrying anything noteworthy.");
                }
                else {
                    System.out.println("You are carrying: ");
                    for (int i = 0; i < player.inventory.size(); i++) {
                        System.out.println(player.inventory.get(i).name);
                    }
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
                if(hideAttempt.toLowerCase().contains(player.location.hidingPlace.name.toLowerCase())){
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
                if(player.hiding == true){
                    System.out.println("You are a little too cramped to do that.");
                    break;
                }
                System.out.println("What will you examine?");
                String lookAttempt = scanner.nextLine().toLowerCase();
                if(lookAttempt.contains(player.location.hidingPlace.name.toLowerCase())){
                    System.out.println("EXAMINING HIDING PLACE");
                    System.out.println(player.location.hidingPlace.description);
                    break;
                }
                for (int i=0; i<player.location.pickups.size(); i++){
                    if(lookAttempt.contains(player.location.pickups.get(i).name.toLowerCase())){
                        System.out.println(player.location.pickups.get(i).description);
                        break;
                    }
                }
                for(int i=0; i<player.location.inspectables.size(); i++) {
                    for (int j = 0; j < player.location.inspectables.get(i).name.length; j++) {
                        if(player.location.inspectables.get(i).name[j].contains(lookAttempt)) {
                            System.out.println(player.location.inspectables.get(i).description);
                        }
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
                for(int i=0; i<player.location.inspectables.size(); i++) {
                    for (int j = 0; j < player.location.inspectables.get(i).name.length; j++) {
                        if(player.location.inspectables.get(i).name[j].contains(pickUpAttempt)) {
                            System.out.println("You briefly consider bringing the " + player.location.inspectables.get(i).name[j] + " with you, but ultimately decide against it.");
                        }
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
                    for(int j=0; j<player.location.inspectables.get(i).name.length; j++){
                        if(player.location.inspectables.get(i).name[j].contains(useAttempt)) {
                            if (player.location.inspectables.get(i).useText == null) {
                                System.out.println("You cannot discern a productive way to use that.");
                                timePass();
                                break;
                            } else {
                                System.out.println(player.location.inspectables.get(i).useText);
                                timePass();
                                break;
                            }
                        }
                    }
                }
                for(int i=0; i<player.inventory.size(); i++){
                    if(useAttempt.contains(player.inventory.get(i).name.toLowerCase())){
                        System.out.println(player.inventory.get(i).useText);
                       // player.inventory.get(i).use();
                        timePass();
                        break;
                    }
                }
                System.out.println("You don't see one of those here.");
                break;
            case "shout":
            case "yell":
            case "scream":
            case "bellow":
            case "roar":
                if(monster.location == player.location && player.hiding == true){
                    System.out.println("You enthusiastically greet the creature which was prowling past you paying you no mind.  You begin to regret your decision as it quickly discerns your location and tears you to pieces.");
                    player.alive = false;
                }
                if(monster.location==player.location && monster.posturing == false){
                    System.out.println("Remembering what you read about wilderness survival in vintage magazines, you stand your ground and shout as a show of force.  Unfortunately, you suspect that the creature is not intimidated when it squats in front of you and stares directly into your eyes.  As you feel the musty warm emanating from its mouth, you suspect you may need to try something more substantial.");
                    monster.posturing = true;
                    break;
                }
                else if(monster.location==player.location && monster.posturing == true){
                    System.out.println("The creature is done toying with you.  You see a claw shoot toward your face, and then you see nothing.");
                    player.alive = false;
                    break;
                }
                System.out.println("You let out a hearty bellow at the top of your lungs.  There don't seem to be a lot of lifeforms hanging around, but you're sure that whatever IS still alive heard that.");
                monster.interested=player.location;
                timePass();
                break;
            case "die":
                System.out.println("Overwhelmed by your desperate situation, you sit down and wait for death.  It does not take long for it to find you.");
                player.alive=false;
                break;
            default:
                System.out.println("Invalid command.  Type HELP for a list of options.");

        }
    }
}




// public class stunGrenade extends classes.Item{
//     public stunGrenade(){
//         this.name= "stun grenade";
//         this.useText= "You throw the stun grenade on the ground and duck around a corner just before a disorienting flash and deafening roar fill the room.";
//         this.pickUpText= "You take the stun grenade.";
//         this.description= "A standard-issue stun grenade, sure to ruin the day (but not the life) of anyone or   anything caught in its blast radius.";
//     // public void use(){
//     //     return;
//     }
// }