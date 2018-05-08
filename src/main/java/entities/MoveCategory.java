package entities;

/**
 * Friday on 06/05/2018.
 */
public class MoveCategory {

    private int moveCategoryID;
    private String name;

    public int getMoveCategoryID(){return moveCategoryID;}

    public void setMoveCategoryID(int moveCategoryID){this.moveCategoryID=moveCategoryID;}

    public String getName(){return name;}

    public void setName(String name){this.name=name;}

    @Override
    public String toString() {
        return "MoveCategory{" +
                "moveCategoryID=" + moveCategoryID +
                ", name='" + name + '\'' +
                '}';
    }

}
