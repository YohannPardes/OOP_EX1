public class Wall extends ConcretePiece{

    Wall(){
        super(new ConcretePlayer("Wall"), "Wall");
    }
    @Override
    public Player getOwner() {
        return super.getOwner();
    }

    @Override
    public String getType() {
        return super.getType();
    }
}
