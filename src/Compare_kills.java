import java.util.Comparator;


/**
 * This class sort Pieces by their kills
 * in case of equality sort by ID
 * in cse of equality sort by winner
 */
public class Compare_kills implements Comparator<ConcretePiece> {

    @Override
    public int compare(ConcretePiece CP1, ConcretePiece CP2) {



        if (CP1.kills == CP2.kills){
            return sort_by_ID(CP1, CP2);
        }

        return Integer.compare(CP1.kills, CP2.kills);
    }

    public int winner_first(ConcretePiece CP1, ConcretePiece CP2)
    {

        ConcretePlayer first = (ConcretePlayer) CP1.getOwner();
        ConcretePlayer second = (ConcretePlayer) CP2.getOwner();

        if (first.getWinner() && !second.getWinner()){
            return -1; // is before
        }
        else if (!first.getWinner() && second.getWinner()){
            return -1; // is after
        }
        else if (first.getWinner() == second.getWinner()){
            return 0;
        }
        return 0;
    }

    public int sort_by_ID(ConcretePiece CP1, ConcretePiece CP2)
    {
        if (Integer.compare(CP1.kills, CP2.kills) == 0)
        {
            return winner_first(CP1, CP2);
        }
        else {
            return  Integer.compare(CP1.kills, CP2.kills);
        }
    }

}
