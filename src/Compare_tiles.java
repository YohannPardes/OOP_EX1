import java.util.Comparator;

/**
 * This comparator sort the tiles by the number of pawns that has passed by them in ascending order
 * In case of equality sort them by their X and then by their Y
 *
 *
 */
public class Compare_tiles implements Comparator<MySet<ConcretePiece>> {

    @Override
    public int compare(MySet<ConcretePiece> o1, MySet<ConcretePiece> o2) {
        if (o1.size() > o2.size()){
            return 1; // if bigger he should appear second
        } else if (o1.size() < o2.size()) {
            return -1; // if smaller he should be first
        } else { // in case of same number of players walking
            return this.equal_decider(o1, o2);
        }
    }

    private int equal_decider(MySet<ConcretePiece> o1, MySet<ConcretePiece> o2){
        if (o1.pos.Y != o2.pos.Y){
            return Integer.compare(o1.pos.Y, o2.pos.Y);
        }
        else return Integer.compare(o1.pos.X, o2.pos.X);
    }
}
