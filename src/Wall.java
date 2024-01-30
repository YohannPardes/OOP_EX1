public class Wall extends ConcretePiece{

    Wall(){
        super(new ConcretePlayer("Wall"), "Wall", new Position(-1, -1));
    }
    @Override
    public Player getOwner() {
        return super.getOwner();
    }

    @Override
    public String getType() {
        return super.getType();
    }

    @Override
    public String pos_hist() {
        return null;
    }

    @Override
    protected String get_Name() {
        return "";
    }
}
