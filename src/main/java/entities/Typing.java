package entities;

/**
 * Created by user on 06/05/2018.
 */
public class Typing {

    private int typingID;
    private String name;

    public int getTypingID(){return typingID;}

    public void setTypingID(int typingID){this.typingID=typingID;}

    public String getName(){return name;}

    public void setName(String name){this.name=name;}

    @Override
    public String toString() {
        return "Typing{" +
                "typingID=" + typingID +
                ", name='" + name + '\'' +
                '}';
    }

}