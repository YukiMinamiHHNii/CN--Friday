package entities;

import java.util.Set;

/**
 * CN--Friday on 11/05/2018.
 */
public class SpeciesExcerpt{

    private String speciesID;
    private String name;
    private Set<Typing> typing;
    private Set<Ability> ability;
    private boolean fullyEvolved;

    public String getSpeciesID() {return speciesID;}

    public void setSpeciesID(String speciesID) {this.speciesID = speciesID;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public Set<Typing> getTyping() {return typing;}

    public void setTyping(Set<Typing> typing) {this.typing = typing;}

    public Set<Ability> getAbility() {return ability;}

    public void setAbility(Set<Ability> ability) {this.ability = ability;}

    public boolean isFullyEvolved() {return fullyEvolved;}

    public void setFullyEvolved(boolean fullyEvolved) {this.fullyEvolved = fullyEvolved;}

    @Override
    public String toString() {
        return "SpeciesExcerpt{" +
                "speciesID='" + speciesID + '\'' +
                ", name='" + name + '\'' +
                ", typing=" + typing +
                ", ability=" + ability +
                ", fullyEvolved=" + fullyEvolved +
                '}';
    }

}
