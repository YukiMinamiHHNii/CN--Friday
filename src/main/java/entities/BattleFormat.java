package entities;

import java.util.Set;

/**
 * Friday on 06/05/2018.
 */
public class BattleFormat {

    private int formatID;
    private String name;
    private String description;
    private Set<SpeciesEntry> restrictedSpecies;

    public int getFormatID(){return formatID;}

    public void setFormatID(int formatID){this.formatID=formatID;}

    public String getName(){return name;}

    public void setName(String name){this.name=name;}

    public String getDescription() {return description;}

    public void setDescription(String description){this.description=description;}

    public Set<SpeciesEntry> getRestrictedSpecies() {return restrictedSpecies;}

    public void setRestrictedSpecies(Set<SpeciesEntry> restrictedSpecies) {this.restrictedSpecies = restrictedSpecies;}

    @Override
    public String toString() {
        return "BattleFormat{" +
                "formatID=" + formatID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", restrictedSpecies=" + restrictedSpecies +
                '}';
    }

}
