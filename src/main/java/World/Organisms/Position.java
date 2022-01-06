package World.Organisms;

public class Position {

    protected int X;
    protected int Y;

    public Position(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }

    public int getPositionX() {
        return X;
    }

    public int getPositionY() {
        return Y;
    }

    @Override
    public String toString() {
        return "{" + Y + "," + X + "}";
    }
}
