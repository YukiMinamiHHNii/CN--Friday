package entities;

import java.util.Set;

/**
 * CN--Friday on 30/04/2018.
 */
public class Species {

    private String speciesID;
    private String name;
    private int hp;
    private int atk;
    private int def;
    private int spAtk;
    private int spDef;
    private int speed;
    private int total;
    private boolean fullyEvolved;
    private Set<Ability> abilities;
    private Set<Typing> typing;
    private Set<MoveExcerpt> learnset;

    public Species() {}

    public Species(String speciesID, String name, int hp, int atk, int def, int spAtk, int spDef, int speed, int total,
                   boolean fullyEvolved, Set<Ability> abilities, Set<Typing> typing, Set<MoveExcerpt> learnset) {
        this.speciesID = speciesID;
        this.name = name;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.spAtk = spAtk;
        this.spDef = spDef;
        this.speed = speed;
        this.total = total;
        this.fullyEvolved = fullyEvolved;
        this.abilities = abilities;
        this.typing = typing;
        this.learnset = learnset;
    }

    public String getSpeciesID(){return speciesID;}

    public void setSpeciesID(String speciesID){this.speciesID=speciesID;}

    public String getName(){return name;}

    public void setName(String name){this.name=name;}

    public int getHp(){return hp;}

    public void setHp(int hp){this.hp=hp;}

    public int getAtk(){return atk;}

    public void setAtk(int atk){this.atk=atk;}

    public int getDef(){return def;}

    public void setDef(int def){this.def=def;}

    public int getSpAtk(){return spAtk;}

    public void setSpAtk(int spAtk){this.spAtk=spAtk;}

    public int getSpDef(){return spDef;}

    public void setSpDef(int spDef){this.spDef=spDef;}

    public int getSpeed(){return speed;}

    public void setSpeed(int speed){this.speed=speed;}

    public int getTotal(){return total;}

    public void setTotal(int total){this.total=total;}

    public boolean isFullyEvolved() {return fullyEvolved;}

    public void setFullyEvolved(boolean fullyEvolved) {this.fullyEvolved = fullyEvolved;}

    public Set<Ability> getAbilities() {return abilities;}

    public void setAbilities(Set<Ability> abilities) {this.abilities = abilities;}

    public Set<Typing> getTyping(){return typing;}

    public void setTyping(Set<Typing> typing){this.typing=typing;}

    public Set<MoveExcerpt> getLearnset() {return learnset;}

    public void setLearnset(Set<MoveExcerpt> learnset) {this.learnset = learnset;}

    @Override
    public String toString() {
        return "Species{" +
                "speciesID='" + speciesID + '\'' +
                ", name='" + name + '\'' +
                ", hp=" + hp +
                ", atk=" + atk +
                ", def=" + def +
                ", spAtk=" + spAtk +
                ", spDef=" + spDef +
                ", speed=" + speed +
                ", total=" + total +
                ", fullyEvolved=" + fullyEvolved +
                ", abilities=" + abilities +
                ", typing=" + typing +
                ", learnset=" + learnset +
                '}';
    }

}
