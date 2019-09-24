package classes;

import java.util.Scanner;
import java.util.List;
import java.util.Arrays;
import java.util.*;
import classes.*;

public class TextAdventure{
    static Player player;
    static Monster monster;
    static Scanner scanner;
    static Random random = new Random();
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        //We begin by creating all the room objects and filling in their descriptions.
        Room hypersleepChamber = new Room(
            "hypersleep chamber",
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
        Room weaponsStorage = new Room("weapons storage", "You are in the weapons storage.", null);
        Room surveillance = new Room(
            "surveillance room",
            "You are in the surveillance room.  The walls are filled with closed-circuit television screens connected to the security cameras in each room.",
            null
        );
        Room recreation = new Room(
                "recreation room",
                "You are in the recreation room.",
                null
        );
        Room generator = new Room(
                "generator room",
                "You are in the generator room",
                null
        );
        Room medicalBay = new Room("medical bay", "You are in the medical bay.", null);
        Room bridge = new Room("bridge", "You are at the bridge of the ship.", null);
        Room escapePods= new Room("escape pods", "You are next to the ship's escape pods.", null);
        //We need to link up the rooms after they have all been created so that they can find one another.
        captainsQuarters.south = crewQuarters;
        crewQuarters.north = captainsQuarters;
        crewQuarters.east = diningHall;
        crewQuarters.south = recreation;
        crewQuarters.vent = medicalBay;
        recreation.north = crewQuarters;
        recreation.south = generator;
        recreation.east = hypersleepChamber;
        recreation.vent = surveillance;
        generator.north = recreation;
        hypersleepChamber.north=diningHall;
        hypersleepChamber.west = recreation;
        hypersleepChamber.east=medicalBay;
        diningHall.east = surveillance;
        diningHall.west = crewQuarters;
        diningHall.south=hypersleepChamber;
        surveillance.west = diningHall;
        surveillance.north = cargoBay;
        surveillance.east = bridge;
        surveillance.south = medicalBay;
        surveillance.vent = recreation;
        cargoBay.south = surveillance;
        bridge.west = surveillance;
        bridge.south = weaponsStorage;
        weaponsStorage.north = bridge;
        weaponsStorage.west = medicalBay;
        medicalBay.north = surveillance;
        medicalBay.west=hypersleepChamber;
        medicalBay.east = weaponsStorage;
        medicalBay.south = researchLab;
        medicalBay.vent = crewQuarters;
        researchLab.north = medicalBay;
        //We then spawn a monster and place it in the Cargo Bay.
        monster = new Monster();
        monster.location = cargoBay;
        Prop dashboard = new Prop(
                new String[]{"screen", "monitor", "console", "dashboard", "television"},
                "These monitors are displaying video feed from cameras placed in every room of the ship.  You could use them to keep abreast of any unusual activities on the ship.",
                "You observe the monitors.  The ship appears to be littered with corpses, but you see no signs of life.  That is, until you glance at the camera feed in the " + monster.location.name + " and see a lifeform unlike anything you've seen before. It stands about six feet tall, has no discernible body fat, and has claws on every appendage.  Out from between its rows of pointy teeth, each longer than a human finger, drips a caustic green substance that appears to be partially melting whatever it lands on."

        );
        surveillance.inspectables.add(dashboard);
        hypersleepChamber.hidingPlace = new Hide(
                "pod",
                "You crawl back inside your hypersleep pod and close the lid, but it needs to be activated from outside.  You are safe from detection, but not the ravages of time.",
                "You press the emergency release button from inside the pod.  The door swings open, but not before a loud beep rings out.", null,
                "You are inside an inactive hypersleep pod.",
                true);
        //We spawn the player and place them in the Hypersleep Chamber.
        player = new Player();
        player.location = hypersleepChamber;
        //player.inventory.add(stunGrenade());
        System.out.println("You awaken from hypersleep, well rested but worried.  You are not greeted with the standard welcoming party; in fact, the ship is eerily quiet.");
        //The below loop continuously prompts the player for input as long as an ending condition is not detected.
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
    //The below code accounts for everything that happens after the player takes a turn.
    public static void timePass(){
        monster.move();
        //The next dozen or so lines cause a randomly-generated warning to appear when the monster is in an adjacent room to the player.
        String[] creepyAdjectives = {
                "unsettling",
                "stomach-churning",
                "ominous",
                "bizarre",
                "alarming",
                "foreboding",
        };
        String[] creepySounds = {
                "dripping",
                "shuffling",
                "creaking",
                "growling",
        };
        if(player.location.isAdjacent(monster.location)){
            int randomAdjective = random.nextInt(creepyAdjectives.length);
            int randomSound = random.nextInt(creepySounds.length);
            System.out.println("You hear a " + creepyAdjectives[randomAdjective] + " " + creepySounds[randomSound] + " sound " + player.location.whereAdjacent(monster.location) + ".");
        }
        if(player.location == monster.location){
            String monsterDescription = new String();
            if(monster.seen == false) {
                monsterDescription = "a lifeform unlike anything you've seen before. It stands about six feet tall, has no discernible body fat, and has claws on every appendage.  Out from between its rows of pointy teeth, each longer than a human finger, drips a caustic green substance that appears to be partially melting whatever it lands on.";
                monster.seen = true;
            }
            else{
                monsterDescription = "the creature.";
            }
            if(player.hiding == false){
                System.out.println("You suddenly find yourself standing face to thorax with " + monsterDescription + ".  It towers over you and gazes down at you ravenously.  It looks like it could pounce on you at any moment.  What will you do?");
                monsterParse(scanner.nextLine());
            }
            if(player.hiding == true && monster.interested != player.location){
                if(monsterDescription.length()>20) {
                    System.out.println("From your concealment you observe entering the room a lifeform unlike anything you've seen before. It stands about six feet tall, has no discernible body fat, and has claws on every appendage.  Out from between its rows of pointy teeth, each longer than a human finger, drips a caustic green substance that appears to be partially melting whatever it lands on.  Fortunately, it does not seem to be aware of your presence.");
                }
                else{
                    System.out.println("The extraterrestrial enters the room, but does not appear to notice you.");
                }
            }
            if(player.hiding == true && monster.interested == player.location){
                System.out.println("The alien bursts into the room and begins searching for the source of the noise.  It uncovers your hiding spot and eviscerates you before you can leave it.");
                player.alive = false;
            }
        }
    }
    //Below is a helper method to remind the player which rooms are accessible from their current location.
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
    //Below is the method that makes most of the action happen, by parsing the player's input.
    public static void parse(String input){
        //These action definitions are purely for instructional purposes.
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
        //We turn the input to lower case before evaluating it to cut down on any confusion.
        input = input.toLowerCase();
        switch (input){
            case "help":
            case "instructions":
            case "how to play":
                //These commands just iterate through the instructional array and print them to the console.
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
                Boolean found = false;
                if(player.hiding == true){
                    System.out.println("You are a little too cramped to do that.");
                    break;
                }
                System.out.println("What will you examine?");
                String lookAttempt = scanner.nextLine().toLowerCase();
                if(player.location.hidingPlace != null) {
                    if (lookAttempt.contains(player.location.hidingPlace.name.toLowerCase())) {
                        System.out.println(player.location.hidingPlace.description);
                        found = true;
                        break;
                    }
                }
                for (int i=0; i<player.location.pickups.size(); i++){
                    if(lookAttempt.contains(player.location.pickups.get(i).name.toLowerCase())){
                        System.out.println(player.location.pickups.get(i).description);
                        found = true;
                        break;
                    }
                }
                for(int i=0; i<player.location.inspectables.size(); i++) {
                    for (int j = 0; j < player.location.inspectables.get(i).name.length; j++) {
                        if(player.location.inspectables.get(i).name[j].contains(lookAttempt)) {
                            System.out.println(player.location.inspectables.get(i).description);
                            found = true;
                        }
                    }
                }
                for (int i=0; i<player.inventory.size(); i++){
                    if(lookAttempt.contains(player.inventory.get(i).name.toLowerCase())){
                        System.out.println(player.inventory.get(i).description);
                        found = true;
                        break;
                    }
                }
                if(found == false) {
                    System.out.println("You don't see one of those here.");
                }
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
                Boolean used = false;
                System.out.println("What will you use?");
                String useAttempt = scanner.nextLine().toLowerCase();
                for(int i=0; i<player.location.inspectables.size(); i++){
                    for(int j=0; j<player.location.inspectables.get(i).name.length; j++){
                        if(player.location.inspectables.get(i).name[j].contains(useAttempt)) {
                            if (player.location.inspectables.get(i).useText == null) {
                                System.out.println("You cannot discern a productive way to use that.");
                                used = true;
                                timePass();
                                break;
                            } else {
                                useProp(player.location.inspectables.get(i).name[0]);
                                used = true;
                                break;
                            }
                        }
                    }
                }
                for(int i=0; i<player.inventory.size(); i++){
                    if(useAttempt.contains(player.inventory.get(i).name.toLowerCase())){
                        System.out.println(player.inventory.get(i).useText);
                       // player.inventory.get(i).use();
                        used = true;
                        break;
                    }
                }
                if(used == false) {
                    System.out.println("You don't see one of those here.");
                }
                break;
            //The below code enables a secret command that allows the player to yell.  Depending on the monster's location and some other factors, this can have various outcomes.
            case "shout":
            case "yell":
            case "scream":
            case "bellow":
            case "roar":
                //If the monster is in the same room as the player and the player is hiding, it will find and kill them.
                if(monster.location == player.location && player.hiding == true){
                    System.out.println("You enthusiastically greet the creature which was prowling past you paying you no mind.  You begin to regret your decision as it quickly discerns your location and tears you to pieces.");
                    player.alive = false;
                    break;
                }
                //The more common outcome, however, is that if the player and monster are in different rooms, the monster will hear the shout and come to investigate wherever the player was when they yelled.
                System.out.println("You let out a hearty bellow at the top of your lungs.  There don't seem to be a lot of lifeforms hanging around, but you're sure that whatever IS still alive heard that.");
                monster.interested=player.location;
                timePass();
                break;
            case "wait":
                System.out.println("You stand still and let time pass.");
                timePass();
                break;
            case "die":
                //Secret command smartarse players can use to kill themselves.
                System.out.println("Overwhelmed by your desperate situation, you sit down and wait for death.  It does not take long for it to find you.");
                player.alive=false;
                break;
            default:
                //If the player's command is not recognized, they will be encouraged to check the full list of commands.
                System.out.println("Invalid command.  Type HELP for a list of options.");

        }
    }
    //The below method handles similar inputs to parse() but gives different results to account for the monster preparing to attack the player.
    public static void monsterParse(String input){
        input = input.toLowerCase();
        switch (input){
            case "north":
            case "n":
            case "south":
            case "s":
            case "east":
            case "e":
            case "west":
            case "w":
            case "run":
            case "flee":
                System.out.println("You try to flee, but you are unable to travel more than a few steps before you feel the beast's weight on top of you.  You hear a loud shriek, feel something sharp piercing into your back, and then you don't feel much of anything at all.");
                player.alive = false;
                break;
            case "hide":
                System.out.println("Hiding typically works better against enemies that aren't already staring at you.  Besides, you doubt you could keep quiet now that your face is currently dissolving in acid.");
                player.alive = false;
                break;
            case "shout":
            case "yell":
            case "scream":
            case "bellow":
            case "roar":
                if(monster.posturing == false) {
                    System.out.println("Remembering what you read about wilderness survival in vintage magazines, you stand your ground and shout as a show of force.  Unfortunately, you suspect that the creature is not intimidated when it squats in front of you and stares directly into your eyes.  As you feel the musty warmth emanating from its mouth, you suspect you may need to try something more substantial.  Any ideas?");
                    monster.posturing = true;
                    monsterParse(scanner.nextLine());
                    break;
                }
                else{
                    System.out.println("The creature is done toying with you.  You see a claw shoot toward your face, and then you see nothing.");
                    player.alive = false;
                    break;
                }
            case "wait":
                System.out.println("Hoping that the alien's vision is based on movement, you stand perfectly still in an attempt to confuse it.  Your gamble, however, does not pay off.");
                player.alive = false;
                break;
            case "look":
                System.out.println("You examine your surroundings.  Let's see... floor, ceiling, entrails being ripped out of your body and chewed up.  Yeah, the usual.");
                player.alive = false;
                break;
            case "die":
                System.out.println("You accept your fate with quiet dignity.  After all, your high school graduating class was right to name you 'Most Easygoing.'  The monster almost looks disappointed as it dispassionately devours you.");
                player.alive = false;
                break;
            default:
                System.out.println("You think you have some kind of idea, but are too confused to execute it.  The creature leaps forward and catches your face between its talons, then everything goes black.");
                player.alive = false;
                break;
        }
    }
    public static void useProp(String name){
        switch (name){
            case "screen":
                if(monster.seen == false) {
                    System.out.println("You observe the monitors.  The ship appears to be littered with corpses, but you see no signs of life.  That is, until you glance at the camera feed in the " + monster.location.name + " and see a lifeform unlike anything you've seen before. It stands about six feet tall, has no discernible body fat, and has claws on every appendage.  Out from between its rows of pointy teeth, each longer than a human finger, drips a caustic green substance that appears to be partially melting whatever it lands on.");
                    monster.seen = true;
                    break;
                }
                else if (monster.interested == null){
                    System.out.println("You observe the monitors. Your nemesis appears to be wandering through the " + monster.location.name + ".");
                    break;
                }
                else if (monster.searching == true){
                    System.out.println("You observe the monitors. The trespasser appears to be curiously poking about the " + monster.location.name + ".");
                    break;
                }
                else if (monster.interested != null) {
                    System.out.println("You observe the monitors. The trespasser appears to be hastily bounding through the " + monster.location.name + ".");
                    break;
                }
        }
    }
}