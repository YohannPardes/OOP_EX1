import java.util.ArrayList;

public abstract class ConcretePiece implements Piece{

    private final Player owner;
    private final String type;
    protected ArrayList<Position> move_history = new ArrayList<>();

    private final int id = 0;

    public ConcretePiece(Player owner, String type, Position pos){
        this.owner = owner;
        this.type = type;
        this.addMove(pos);
    }

    @Override
    public Player getOwner() {
        return this.owner;
    }

    @Override
    public String getType() {
        return this.type;
    }

    public void addMove(Position b){
        this.move_history.add(b);
    }

    public abstract String pos_hist();

}
