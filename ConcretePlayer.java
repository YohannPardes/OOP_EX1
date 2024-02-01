import java.util.Objects;

/**
 * This class represent a player in the game
 */
public class ConcretePlayer implements Player {

    private final String color;
    private int wins = 0;
    private boolean isWinner = false;
    private String name;

    public ConcretePlayer(String color){
        this.color = color;
    }

    /**
     * @return true if this is the player one, false otherwise
     */
    @Override
    public boolean isPlayerOne() {
        if (Objects.equals(this.color, "White")){
            return true;
        }
        return false;
    }

    /**
     * getter of wins
     * @return
     */
    @Override
    public int getWins() {
        return this.wins;
    }

    /**
     * @return return true if the player is a wall
     */
    public boolean isWall() {
        return Objects.equals(this.color, "Wall");
    }

    /**
     * add a win to the player
     */
    public void addWin() {
        this.wins ++;
    }

    @Override
    public String toString(){
        return Objects.equals(this.color, "White") ? "D" : "A";
    }

    /**
     * setter for
     * @param val
     */
    public void setWinner(boolean val){
        this.isWinner = val;
    }

    /**
     * @return return true is the player is a winner
     */
    public boolean getWinner(){
        return this.isWinner;
    }
}



