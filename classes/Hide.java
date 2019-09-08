package classes;

public class Hide{
    public String name;
    //The below text is printed when the player enters the hiding spot.
    public String hideText;
    //The below text is printed when the player leaves the hiding spot.
    public String emergeText;
    public String description;
    //The below text is printed when the player examines their surroundings while hiding here.
    public String hideDescription;
    //If the hiding spot is noisy, the monster will be alerted when the player exits it.
    public boolean noisy;
    public Hide(String name, String hideText, String emergeText, String description, String hideDescription, boolean noisy){
        this.name=name;
        this.hideText=hideText;
        this.emergeText=emergeText;
        this.description=description;
        this.hideDescription=hideDescription;
        this.noisy=noisy;
    }
}