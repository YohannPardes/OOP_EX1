public abstract class ConcretePiece implements Piece{

    private final Player owner;
    private final String type;

    public ConcretePiece(Player owner, String type){
        this.owner = owner;
        this.type = type;
    }

    @Override
    public Player getOwner() {
        return this.owner;
    }

    @Override
    public String getType() {
        return this.type;
    }
}
