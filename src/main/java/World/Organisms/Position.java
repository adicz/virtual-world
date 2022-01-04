package World.Organisms;

public class Position {

    protected int X;
    protected int Y;

    public Position() {
    }

    public Position(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }

    public int getPositionX() {
        return X;
    }

    public void setPositionX(int positionX) {
        this.X = positionX;
    }

    public int getPositionY() {
        return Y;
    }

    public void setPositionY(int positionY) {
        this.Y = positionY;
    }

    @Override
    public String toString() {
        return "{" + X + "," + Y + "}";
    }
}
