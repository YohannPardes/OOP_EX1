public class Position {

    public int X; // the column of the position
    public int Y; // the row of the position

    public Position(int X, int Y){
        this.X = X;
        this.Y = Y;
    }

    public boolean isInside(int limits){

        boolean max = this.X >= limits || this.Y >= limits;
        boolean min = this.X < 0 || this.Y < 0;

        return max && min;
    }

    public boolean same(Position p){
        return (p.X == this.X)&& (p.Y == this.Y);
    }

    @Override
    public String toString() {
        return Integer.toString(this.X) + "," + Integer.toString(this.Y);
    }
}
