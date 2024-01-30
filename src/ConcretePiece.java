import java.util.ArrayList;

public abstract class ConcretePiece implements Piece{

    private final Player owner;
    private final String type;
    protected ArrayList<Position> move_history = new ArrayList<>();

    private final int id = 0;
    protected int kills;

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

    protected abstract String get_Name();

    public String kill_hist(){
        return this.get_Name() + this.kills + " kills";
    }

    public void add_Kill(){
        this.kills ++;
    }

    public int calculate_moves_distance(){
        int total_dist = 0;
        for (int i = 0; i < this.move_history.size() - 1; i++) {
            Position first_move = this.move_history.get(i);
            Position second_move = this.move_history.get(i+1);

            total_dist += Math.abs(first_move.X - second_move.X) + Math.abs(first_move.Y - second_move.Y);
        }

        return total_dist;

    }

    public String dist_hist(){
        return this.get_Name() + this.calculate_moves_distance() + " squares";
    }

    public int get_ID(){
        return this.id;
    }
}
