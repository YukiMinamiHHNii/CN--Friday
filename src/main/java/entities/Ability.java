package entities;

/**
 * Friday on 06/05/2018.
 */
public class Ability {

    private int abilityID;
    private String name;
    private String description;

    public Ability(int abilityID, String name, String description){
        this.abilityID= abilityID;
        this.name= name;
        this.description= description;
    }

    public int getAbilityID() {return abilityID;}

    public void setAbilityID(int abilityID) {this.abilityID = abilityID;}

    public String getName(){return name;}

    public void setName(String name){this.name = name;}

    public String getDescription(){return description;}

    public void setDescription(String description){this.description = description;}

    @Override
    public String toString() {
        return "Ability{" +
                "abilityID=" + abilityID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
