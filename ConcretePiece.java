import java.util.ArrayList;

/**
 * This abstract class represent a Piece on the board
 */
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

    /**
     * @return the owner of the piece
     */
    @Override
    public Player getOwner() {
        return this.owner;
    }

    /**
     * @return the type of the pice
     */
    @Override
    public String getType() {
        return this.type;
    }

    /**
     * adding a move to the piece move history
     * @param b the move to add
     */
    public void addMove(Position b){
        this.move_history.add(b);
    }

    /**
     * @return print the position history according to each piece
     */
    public abstract String pos_hist();

    /**
     * @return return the owner + ID of the piece
     */
    protected abstract String get_Name();

    /**
     * @return the string for the recap in kill history
     */
    public String kill_hist(){
        return this.get_Name() + this.kills + " kills";
    }

    /**
     * add a kill to the piece
     */
    public void add_Kill(){
        this.kills ++;
    }

    /**
     * using the print history calculate the total distance a piece has passee
     * @return the distance a piece has passed as an int
     */
    public int calculate_moves_distance(){
        int total_dist = 0;
        for (int i = 0; i < this.move_history.size() - 1; i++) {
            Position first_move = this.move_history.get(i);
            Position second_move = this.move_history.get(i+1);

            total_dist += Math.abs(first_move.X - second_move.X) + Math.abs(first_move.Y - second_move.Y);
        }

        return total_dist;

    }

    /**
     * @return the string needed for the print_dist_hist function
     */
    public String dist_hist(){
        return this.get_Name() + this.calculate_moves_distance() + " squares";
    }

    /**
     * getter for this.id
     * @return this.id
     */
    public int get_ID(){
        return this.id;
    }
}
