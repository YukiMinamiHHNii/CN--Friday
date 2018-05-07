package entities;

/**
 * Friday on 06/05/2018.
 */
public class MoveOrigin {

    private int originID;
    private String name;

    public int getOriginID(){return originID;}

    public void setOriginID(int originID){this.originID=originID;}

    public String getName(){return name;}

    public void setName(String name){this.name=name;}

    @Override
    public String toString() {
        return "MoveOrigin{" +
                "originID=" + originID +
                ", name='" + name + '\'' +
                '}';
    }

}
