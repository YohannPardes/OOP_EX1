import java.util.Objects;

public class GameLogic implements PlayableLogic {


    private final int board_size = 11;
    private Piece[][] board_data = new ConcretePiece[board_size][board_size];

    private Player attacking_player = new ConcretePlayer("Black");
    private Player defending_player = new ConcretePlayer("White");
    private boolean black_turn = true;
    public GameLogic(){

        // adding the white pieces
        // king and first layer of pawn
        for (int i = -1; i<=1; i += 1){
            for (int j = -1; j<=1; j += 1) {
                if (i== 0 & j == 0){
                    board_data[5+i][5+j] = new King(this.defending_player);
                }
                else{
                    board_data[5+i][5+j] = new Pawn(this.defending_player);
                }
            }
        }
        // the four farest white pawns
        board_data[5][7] = new Pawn(this.defending_player);
        board_data[7][5] = new Pawn(this.defending_player);
        board_data[5][3] = new Pawn(this.defending_player);
        board_data[3][5] = new Pawn(this.defending_player);

        // adding the black pieces
        for (int i=3; i<=7; i += 1){
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
        int[] move_data = this.move_data(a, b);
        int delta_x = move_data[0];
        int delta_y = move_data[1];
        int dir_x = move_data[2];
        int dir_y = move_data[3];
        //check that the right color piece has been selected
        if (getPieceAtPosition(a).getOwner().isPlayerOne() != this.black_turn){
            return false;
        }

        // if it's a valid cross move
        if (delta_x * delta_y != 0) {
            return false;
        }

        // check for blocking pieces
        // get y-axis tiles
        for (int i = a.Y + dir_y; i != b.Y; i += dir_y) {
            if (this.getPieceAtPosition(new Position(i, a.X)) != null) {
                return false;
            }
        }
        // get x axis tiles
        for (int i = a.X + dir_x; i != b.X; i += dir_x) {
            if (this.getPieceAtPosition(new Position(a.Y, i)) != null) {
                return false;
            }
        }
        // checking target tile is empty
        if (this.getPieceAtPosition(b) != null) {
            return false;
        }
        //check for a pawn moving to a corner
        if (Objects.equals(getPieceAtPosition(a).getType(), "♟")) {
            if (
                    b.same(new Position(10, 0)) ||
                    b.same(new Position(10, 10)) ||
                    b.same(new Position(0, 10)) ||
                    b.same(new Position(0, 0))
            ){
                return false;
            }
        }



        // if the move is a valid move then move the piece
        this.move_piece(a, b);
        //update next player
        this.black_turn = !this.black_turn;
        return true;
    }

    private int[] move_data(Position a, Position b) {
        int delta_x = b.X - a.X;
        int delta_y = b.Y - a.Y;
        int dir_x;
        int dir_y;
        try{
            dir_x = delta_x/Math.abs(delta_x);
        }
        catch (Exception ArithmeticException){
            dir_x = 0;
        }
        try{
            dir_y = delta_y/Math.abs(delta_y);
        }
        catch (Exception ArithmeticException){
            dir_y = 0;
        }
        return new int[] {delta_x, delta_y, dir_x, dir_y};
    }

    private record Result(int delta_x, int delta_y, int dir_x, int dir_y) {
    }

    private void move_piece(Position a, Position b){
        Piece moving_piece = this.getPieceAtPosition(a);
        board_data[a.X][a.Y] = null;
        board_data[b.X][b.Y] = moving_piece;

    }

    @Override
    public Piece getPieceAtPosition(Position position) {
        return board_data[position.X][position.Y];
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

        return false;
    }

    @Override
    public boolean isSecondPlayerTurn() {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public void undoLastMove() {

    }

    @Override
    public int getBoardSize() {
        return this.board_size;
    }
}
