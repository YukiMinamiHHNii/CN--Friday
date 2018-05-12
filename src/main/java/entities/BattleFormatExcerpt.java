package entities;

/**
 * CN--Friday on 11/05/2018.
 */
public class BattleFormatExcerpt {

    private int formatID;
    private String name;

    public int getFormatID() {return formatID;}

    public void setFormatID(int formatID) {this.formatID = formatID;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    @Override
    public String toString() {
        return "BattleFormatExcerpt{" +
                "formatID=" + formatID +
                ", name='" + name + '\'' +
                '}';
    }

}
