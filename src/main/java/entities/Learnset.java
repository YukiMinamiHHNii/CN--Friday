package entities;

/**
 *CN--Friday on May 19, 2018
 */
public class Learnset {
    
    private MoveExcerpt move;
    private MoveOrigin origin;

    public MoveExcerpt getMove() {return move;}

    public void setMove(MoveExcerpt move) {this.move = move;}

    public MoveOrigin getOrigin() {return origin;}

    public void setOrigin(MoveOrigin origin) {this.origin = origin;}

    @Override
    public String toString() {
        return "Learnset{\n" 
                + "move=" + move + "\n"
                + ", origin=" + origin + "\n"
                + "}";
    }
    
}
