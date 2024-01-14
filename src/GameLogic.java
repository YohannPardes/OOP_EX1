import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Objects;
import java.util.Stack;

public class GameLogic implements PlayableLogic {

    private final int board_size = 11;
    private ConcretePiece[][] board_data = new ConcretePiece[board_size][board_size];

    private final Player attacking_player = new ConcretePlayer("White");
    private final Player defending_player = new ConcretePlayer("Black");
    private boolean black_turn = false;
    private final Stack<ConcretePiece[][]> move_history = new Stack<>();

    public GameLogic() {

        // adding the white pieces
        // king and first layer of pawn
        for (int i = -1; i <= 1; i += 1) {
            for (int j = -1; j <= 1; j += 1) {
                if (i == 0 & j == 0) {
                    board_data[5 + i][5 + j] = new King(this.defending_player);
                } else {
                    board_data[5 + i][5 + j] = new Pawn(this.defending_player);
                }
            }
        }
        // the four farest white pawns
        board_data[5][7] = new Pawn(this.defending_player);
        board_data[7][5] = new Pawn(this.defending_player);
        board_data[5][3] = new Pawn(this.defending_player);
        board_data[3][5] = new Pawn(this.defending_player);

        // adding the black pieces
        for (int i = 3; i <= 7; i += 1) {
            board_data[0][i] = new Pawn(this.attacking_player);
            board_data[i][0] = new Pawn(this.attacking_player);
            board_data[10][i] = new Pawn(this.attacking_player);
            board_data[i][10] = new Pawn(this.attacking_player);
        }

        board_data[1][5] = new Pawn(this.attacking_player);
        board_data[5][1] = new Pawn(this.attacking_player);
        board_data[9][5] = new Pawn(this.attacking_player);
        board_data[5][9] = new Pawn(this.attacking_player);


    }

    @Override
    public boolean move(Position a, Position b) {
        //check that the move is valid
        if (!move_is_valid(a, b)) {
            return false;
        }

        // storing the board before the move is applied
        this.move_history.add(getDeepCopyData(this.board_data));

        // if the move is a valid move then move the piece
        this.move_piece(a, b);


        // handling eating situation
        this.eat(b);


        //update next player
        this.black_turn = !this.black_turn;
        return true;
    }

    private boolean move_is_valid(Position a, Position b) {
        int[] move_data = this.move_data(a, b);
        int delta_x = move_data[0];
        int delta_y = move_data[1];
        int dir_x = move_data[2];
        int dir_y = move_data[3];
        //check that the right color piece has been selected
        if (getPieceAtPosition(a).getOwner().isPlayerOne() != this.black_turn) {
            return false;
        }

        // if it's a valid cross move
        if (delta_x * delta_y != 0) {
            return false;
        }

        // check for blocking pieces
        // get y-axis tiles
        for (int i = a.Y + dir_y; i != b.Y; i += dir_y) {
            if (this.getPieceAtPosition(new Position(a.X, i)) != null) {
                return false;
            }
        }
        // get x axis tiles
        for (int i = a.X + dir_x; i != b.X; i += dir_x) {
            if (this.getPieceAtPosition(new Position(1, a.Y)) != null) {
                return false;
            }
        }
        // checking target tile is empty
        if (this.getPieceAtPosition(b) != null) {
            return false;
        }
        //check for a pawn moving to a corner
        if (Objects.equals(getPieceAtPosition(a).getType(), "♟")) {
            return !isCorner(b);
        }
        return true;
    }

    private static boolean isCorner(Position b) {
        return b.same(new Position(10, 0)) ||
                b.same(new Position(10, 10)) ||
                b.same(new Position(0, 10)) ||
                b.same(new Position(0, 0));
    }

    private int[] move_data(Position a, Position b) {
        int delta_x = b.X - a.X;
        int delta_y = b.Y - a.Y;
        int dir_x;
        int dir_y;
        try {
            dir_x = delta_x / Math.abs(delta_x);
        } catch (Exception ArithmeticException) {
            dir_x = 0;
        }
        try {
            dir_y = delta_y / Math.abs(delta_y);
        } catch (Exception ArithmeticException) {
            dir_y = 0;
        }
        return new int[]{delta_x, delta_y, dir_x, dir_y};
    }

    private void move_piece(Position a, Position b) {
        Piece moving_piece = this.getPieceAtPosition(a);
        board_data[a.X][a.Y] = null;
        board_data[b.X][b.Y] = (ConcretePiece) moving_piece;

    }

    /**
     * given the previous and the new position of the piece handle the eating
     */
    private void eat(Position b) {

        ConcretePlayer piece_owner = (ConcretePlayer) getPieceAtPosition(b).getOwner();

        //                  up,      right,  down,    left
        int[][] offsets = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
        for (int[] offset : offsets) {
            Position one_aside = new Position(b.X + offset[0], b.Y - offset[1]);
            Position two_aside = new Position(b.X + offset[0] * 2, b.Y - offset[1] * 2);
            ConcretePiece first_neighbor = (ConcretePiece) getPieceAtPosition(one_aside);
            ConcretePiece second_neighbor = (ConcretePiece) getPieceAtPosition(two_aside);

            if (first_neighbor != null && second_neighbor != null && !first_neighbor.getClass().getName().equals("King")) {
                ConcretePlayer first_neighbor_owner = (ConcretePlayer) first_neighbor.getOwner();
                ConcretePlayer second_neighbor_owner = (ConcretePlayer) second_neighbor.getOwner();

                if (piece_owner!=first_neighbor_owner) {

                    boolean is_sandwiched = (piece_owner == second_neighbor_owner);
                    boolean is_stuck_between_walls = (second_neighbor_owner.isWall() || isCorner(two_aside));
                    if (is_sandwiched || ((is_stuck_between_walls) && !first_neighbor_owner.isWall())) {
                        eatPieceAtPosition(one_aside);
                    }
                }
            }
        }
    }

    @Override
    public Piece getPieceAtPosition(Position position) {
        try {
            return board_data[position.X][position.Y];
        } catch (Exception IndexOutOfBoundsException) {
            return new ConcretePiece(new ConcretePlayer("Wall"), "Wall");
        }
    }

    public Piece getPieceAtPosition(int X, int Y) {
        try {
            return board_data[X][Y];
        } catch (Exception IndexOutOfBoundsException) {
            return new ConcretePiece(new ConcretePlayer("Wall"), "Wall");
        }
    }

    public void eatPieceAtPosition(int X, int Y) {
        board_data[X][Y] = null;
    }

    public void eatPieceAtPosition(Position p) {
        eatPieceAtPosition(p.X, p.Y);
    }

    @Override
    public Player getFirstPlayer() {
        return this.attacking_player;
    }

    @Override
    public Player getSecondPlayer() {
        return this.defending_player;
    }

    @Override
    public boolean isGameFinished() {
        // check for white or black win
        return this.white_win() || this.black_win();
    }

    private boolean white_win() {
        // all the black pieces has been eaten

        return false;
    }

    private boolean black_win() {
        // the king is surrounded

        return false;
    }

    @Override
    public boolean isSecondPlayerTurn() {
        return this.black_turn;
    }

    @Override
    public void reset() {
        GameLogic new_game = new GameLogic();

        this.board_data = getDeepCopyData(new_game.board_data);
        this.black_turn = true;
    }

    @Override
    public void undoLastMove() {
        try {
            ConcretePiece[][] last_state = move_history.pop();
            this.black_turn = !this.black_turn;
            this.board_data = getDeepCopyData(last_state);
        }
        catch (Exception EmptyStackException){

        }
    }

    @Override
    public int getBoardSize() {
        return this.board_size;
    }

    /**
     * A tool for deep copying a 2d array
     * @param original - the original array
     * @return a deepcopy of the array
     */
    public static ConcretePiece[][] getDeepCopyData(ConcretePiece[][] original) {
        if (original == null) {
            return null;
        }

        final ConcretePiece[][] result = new ConcretePiece[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return result;
    }
}
