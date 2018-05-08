package entities;

/**
 * Friday on 06/05/2018.
 */
public class Move {

    private int moveID;
    private String name;
    private int power;
    private int accuracy;
    private String description;
    private MoveCategory category;

    public int getMoveID(){return moveID;}

    public void setMoveID(int moveID){this.moveID=moveID;}

    public String getName(){return name;}

    public void setName(String name){this.name=name;}

    public int getPower(){return power;}

    public void setPower(int power){this.power=power;}

    public int getAccuracy(){return accuracy;}

    public void setAccuracy(int accuracy){this.accuracy=accuracy;}

    public String getDescription(){return description;}

    public void setDescription(String description){this.description=description;}

    public MoveCategory getCategory(){return category;}

    public void setCategory(MoveCategory category){this.category=category;}

    @Override
    public String toString() {
        return "Move{" +
                "moveID=" + moveID +
                ", name='" + name + '\'' +
                ", power=" + power +
                ", accuracy=" + accuracy +
                ", description='" + description + '\'' +
                ", category=" + category +
                '}';
    }

}
