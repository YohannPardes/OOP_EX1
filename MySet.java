import java.util.HashSet;

/**
 * this class extends HashSet to add a pos field
 * @param <ConcretePiece>
 */
public class MySet<ConcretePiece> extends HashSet<ConcretePiece> {

    public Position pos;
    public MySet(Position pos) {
        super();
        this.pos = pos;
    }
}
