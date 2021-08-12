package classes;

import java.util.Arrays;
public class Prop{
    public String[] name;
    public String description;
    public Boolean usable;
    public Boolean active;
    public String iceMaker;
    public Boolean open;

    public Prop(String[] name, String description, Boolean usable){
        this.name=name;
        this.description=description;
        this.usable=usable;
        this.active = false;
        this.iceMaker = "inactive";
        this.open = false;
    }
}