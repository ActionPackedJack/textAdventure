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
            "TAKE: attempt to add an object to your inventory (requires followup prompt)",
            "USE: activate an inventory item or object in your environment (requires followup prompt)",
            "HIDE: conceal yourself if a suitable method exists in your current location (requires followup prompt)",
            "EMERGE: exit your hiding spot",
            "HELP: you seem to understand this one already",
        };

        Scanner scanner = new Scanner(System.in);
        Room hypersleepChamber = new Room(
            "You are standing in the ship's hypersleep chamber, lined with pods in which organic lifeforms can enter suspended animation for an extended period of time. One such pod sits open, as though its activity has just ended.  To your north is the ship's dining hall.  To your east is the medical bay.",
            new Hide(
                "pod",
                "You crawl back inside your hypersleep pod and close the lid, but it can only be activated from outside.  You are safe from detection, but not the ravages of time.",
                "You press the emergency release button from inside the pod.  The door swings open, but not before a loud beep rings out.",
                "This pod lays idle, its door open.  You could fit inside it if you so desired.",
                "You are inside an inactive hypersleep pod."
                true
            ),
            null,
            Arrays.asList(
                new Prop(
                    ""
                )
            )

        );
        Room cargoBay = new Room();
        Room diningHall = new Room();
        Room crewQuarters = new Room();
        Room captainsQuarters = new Room();
        Room weaponsStorage = new Room();
        Room surveillance = new Room();
        Room medicalBay = new Room();
        Room bridge = new Room();
        Room escapePods= new Room();
        hypersleepChamber.north=diningHall;
        hypersleepChamber.east=medicalBay;
        hypersleepChamber.description= "You are standing in the ship's hypersleep chamber, lined with pods in which organic lifeforms can enter suspended animation for an extended period of time. One such pod sits open, as though its activity has just ended.  To your north is the ship's dining hall.  To your east is the medical bay.";
        hypersleepChamber.hidingPlace = new Hide();
        hypersleepChamber.hidingPlace.name = "pod";
        hypersleepChamber.hidingPlace.hideText = "You crawl back inside your hypersleep pod and close the lid, but it needs to be activated from outside.  You are safe from detection, but not the ravages of time.";
        hypersleepChamber.hidingPlace.emergeText = "You press the emergency release button from inside the pod.  The door swings open, but not before a loud beep rings out.";
        hypersleepChamber.hidingPlace.noisy = true;
        hypersleepChamber.hidingPlace.hideDescription = "You are inside an inactive hypersleep pod.";

        Player player = new Player();
        player.location = hypersleepChamber;
        player.inventory.add(stunGrenade());
        Monster monster = new Monster();
        System.out.println("You awaken from hypersleep, well rested but worried.  You are not greeted with the standard welcoming party; in fact, the ship is eerily quiet.");
        System.out.println("What will you do?");
        parse(scanner.nextLine());
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
                    System.out.println("You cannot do that while hiding.  Type 'emerge' to leave your hiding spot.");
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
                    System.out.println("You cannot do that while hiding.  Type 'emerge' to leave your hiding spot.");
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
                    System.out.println("You cannot do that while hiding.  Type 'emerge' to leave your hiding spot.");
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
                    System.out.println("You cannot do that while hiding.  Type 'emerge' to leave your hiding spot.");
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
                        monster.interested = player.location.name;
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
                       // player.inventory.get(i).use();
                        timePass();
                        break;
                    }
                }
                System.out.println("You don't see one of those here.");
                break;
            default:
                System.out.println("Invalid command.  Type HELP for a list of options.");
        }
    }
}


public class stunGrenade extends Item{
    public stunGrenade(){
        this.name= "stun grenade";
        this.useText= "You throw the stun grenade on the ground and duck around a corner just before a disorienting flash and deafening roar fill the room.";
        this.pickUpText= "You take the stun grenade.";
        this.description= "A standard-issue stun grenade, sure to ruin the day (but not the life) of anyone or   anything caught in its blast radius.";
    // public void use(){
    //     return;
    }
}