public class Pawn extends ConcretePiece{

    private final int id;

    public Pawn(Player player, int id){
        super(player, "♟");
        this.id = id;

    }

    @Override
    public String toString(){
        return this.id + " ";
    }
}
