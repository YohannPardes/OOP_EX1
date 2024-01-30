import java.util.Objects;

public class ConcretePlayer implements Player {

    private final String color;
    private int wins = 0;

    private boolean isWinner = false;

    private String name;

    public ConcretePlayer(String color){
        this.color = color;

    }
    @Override
    public boolean isPlayerOne() {
        if (Objects.equals(this.color, "White")){
            return false;
        }
        return true;
    }

    @Override
    public int getWins() {
        return this.wins;
    }

    public boolean isWall() {
        return Objects.equals(this.color, "Wall");
    }

    public void addWin() {
        this.wins ++;
    }

    @Override
    public String toString(){
        return Objects.equals(this.color, "White") ? "D" : "A";
    }

    public void setWinner(boolean val){
        this.isWinner = val;
    }

    public boolean getWinner(){
        return this.isWinner;
    }
}



