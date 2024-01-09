public class Position {

    public int X; // the column of the position
    public int Y; // the row of the position

    public Position(int row, int col){
        this.Y = row;
        this.X = col;
    }
    public boolean same(Position p){
        return (p.X == this.X)&& (p.Y == this.Y);
    }
}
