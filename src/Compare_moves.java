import java.util.Comparator;

public class Compare_moves implements Comparator<ConcretePiece> {

    @Override
    public int compare(ConcretePiece CP1, ConcretePiece CP2) {

        return Integer.compare(CP1.move_history.size(), CP2.move_history.size());
    }


}
