public class King extends ConcretePiece{

    private final int id;

    public King(Player player, int id){
        super(player, "♚");
        this.id = id;
    }

    @Override
    public String toString(){
        return this.id + " ";
    }
}
