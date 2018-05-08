package entities;

/**
 * CN--Friday on 07/05/2018.
 */
public class Learnset {

    private Move move;
    private MoveOrigin origin;

    public Move getMove(){return move;}

    public void setMove(Move move){this.move= move;}

    public MoveOrigin getOrigin(){return origin;}

    public void setOrigin(MoveOrigin origin){this.origin= origin;}

    @Override
    public String toString(){
        return "Learnset{" +
                "move=" + move +
                ", origin=" + origin +
                '}';
    }

}
