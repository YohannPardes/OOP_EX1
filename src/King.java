public class King extends ConcretePiece{

    private final int id;

    public King(Player player, int id, Position pos){
        super(player, "♚", pos);
        this.id = id;
    }

    @Override
    public String toString(){
        return this.id + " ";
    }

    public String pos_hist(){
        String string = "";
        string += "K" + this.id + ": [";
        for (int i = 0; i < this.move_history.size() - 1; i++) {
            string += move_history.get(i) +", ";
        }
        string += move_history.get(this.move_history.size()-1) + "]";

        return string;
    }
}
