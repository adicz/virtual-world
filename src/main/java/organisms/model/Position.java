package organisms.model;

public class Position {

    protected int x;
    protected int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getPositionX() {
        return x;
    }

    public int getPositionY() {
        return y;
    }

    @Override
    public String toString() {
        return "{" + y + "," + x + "}";
    }
}
