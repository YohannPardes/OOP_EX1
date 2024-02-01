import java.util.Comparator;

/**
 * This comparator sort by winner
 * in case of equality sort by number of moves ascending
 * in case of another equality sort by their ID
 */
public class Compare_moves implements Comparator<ConcretePiece> {

    @Override
    public int compare(ConcretePiece CP1, ConcretePiece CP2) {

        ConcretePlayer P1 = (ConcretePlayer) CP1.getOwner();
        ConcretePlayer P2 = (ConcretePlayer) CP2.getOwner();

        if (P1.getWinner() == !P2.getWinner() && P1.getWinner()){
            return -1; // printed before
        }
        else if (P1.getWinner() == !P2.getWinner() && P2.getWinner()){
            return 1; // printed after
        }
        else if (P1.getWinner() == P2.getWinner()){ // if the winner is the same sort by moves
            return sort_by_moves(CP1, CP2);
        }

        return Integer.compare(CP1.move_history.size(), CP2.move_history.size());
    }

    /**
     * sort by moves length
     * @param CP1
     * @param CP2
     * @return
     */
    public int sort_by_moves(ConcretePiece CP1, ConcretePiece CP2){

        if (Integer.compare(CP1.move_history.size(), CP2.move_history.size()) == 0){
            return sort_by_ID(CP1, CP2); // if thay have the same history size sort by ID
        }
        else
        {
            return Integer.compare(CP1.move_history.size(), CP2.move_history.size());
        }


    }

    /**
     * sort by ID
     * @param CP1
     * @param CP2
     * @return
     */
    private int sort_by_ID(ConcretePiece CP1, ConcretePiece CP2){
        return Integer.compare(CP1.get_ID(), CP2.get_ID());
    }
}


