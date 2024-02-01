import java.util.Comparator;


/**
 * This class Compare 2 ConcretePiece according to the distance they have traveled in descending order
 * In case of equality they should be sorted by their ID in ascending order
 * In case of same ID the winner of the game should appear first
 */
public class Compare_dist implements Comparator<ConcretePiece> {

    @Override
    public int compare(ConcretePiece o1, ConcretePiece o2) {

        ConcretePlayer first = (ConcretePlayer) o1.getOwner();
        ConcretePlayer second = (ConcretePlayer) o2.getOwner();

        // sort them by  distance
        if (o1.calculate_moves_distance() > o2.calculate_moves_distance()){
            return -1; // -1 he appears first
        } else if (o1.calculate_moves_distance() < o2.calculate_moves_distance()) {
            return 1; // 1 he appears second
        }
        // sort them by ID
        else if (o1.get_ID() > o2.get_ID()) {
            return 1;
        } else if (o1.get_ID() < o2.get_ID()) {
            return -1;
        }
        //sort them by winner
        if (first.getWinner() == !second.getWinner() && first.getWinner()){
            return 1; // printed before
        }
        else if (first.getWinner() == !second.getWinner() && second.getWinner()){
            return -1; // printed after
        }
        else {
            return 0;
        }
    }
}
