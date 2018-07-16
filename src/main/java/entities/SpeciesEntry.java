package entities;

/**
 * CN--Friday on 11/05/2018.
 */
public class SpeciesEntry {

    private String speciesID;
    private String name;

    public SpeciesEntry() {
    }

    public SpeciesEntry(String speciesID, String name) {
        this.speciesID = speciesID;
        this.name = name;
    }

    public String getSpeciesID() {return speciesID;}

    public void setSpeciesID(String speciesID) {this.speciesID = speciesID;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    @Override
    public String toString() {
        return "SpeciesEntry{" +
                "speciesID='" + speciesID + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}