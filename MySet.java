import java.util.HashSet;

public class MySet<ConcretePiece> extends HashSet<ConcretePiece> {

    public Position pos;
    public MySet(Position pos) {
        super();
        this.pos = pos;
    }
}
