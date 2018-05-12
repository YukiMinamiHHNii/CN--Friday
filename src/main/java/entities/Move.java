package entities;

import java.util.Set;

/**
 * Friday on 06/05/2018.
 */
public class Move {

    private int moveID;
    private String name;
    private Typing typing;
    private int power;
    private int accuracy;
    private String description;
    private MoveCategory category;
    private Set<SpeciesEntry> learntBy;

    public int getMoveID() {
        return moveID;
    }

    public void setMoveID(int moveID) {
        this.moveID = moveID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Typing getTyping() {
        return typing;
    }

    public void setTyping(Typing typing) {
        this.typing = typing;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MoveCategory getCategory() {
        return category;
    }

    public void setCategory(MoveCategory category) {
        this.category = category;
    }

    public Set<SpeciesEntry> getLearntBy() {
        return learntBy;
    }

    public void setLearntBy(Set<SpeciesEntry> learntBy) {
        this.learntBy = learntBy;
    }

    @Override
    public String toString() {
        return "Move{" +
                "moveID=" + moveID +
                ", name='" + name + '\'' +
                ", typing=" + typing +
                ", power=" + power +
                ", accuracy=" + accuracy +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", learntBy=" + learntBy +
                '}';
    }

}
