import java.util.Objects;

public class ConcretePlayer implements Player {

    private String color;
    private int wins = 0;

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
}



