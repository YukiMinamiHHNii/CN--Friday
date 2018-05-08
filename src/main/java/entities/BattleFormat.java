package entities;

import java.util.List;

/**
 * Friday on 06/05/2018.
 */
public class BattleFormat {

    private int formatID;
    private String name;
    private String description;
    private List<Species> species;

    public int getFormatID(){return formatID;}

    public void setFormatID(int formatID){this.formatID=formatID;}

    public String getName(){return name;}

    public void setName(String name){this.name=name;}

    public String getDescription() {return description;}

    public void setDescription(String description){this.description=description;}

    public List<Species> getSpecies(){return species;}

    public void setSpecies(List<Species> species){this.species=species;}

    @Override
    public String toString() {
        return "BattleFormat{" +
                "formatID=" + formatID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", species=" + species +
                '}';
    }

}
