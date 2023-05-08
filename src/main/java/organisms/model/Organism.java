package organisms.model;

import world.World;

public abstract class Organism implements Comparable<Organism> {

    protected Integer strength;
    protected Integer initiative;
    protected Position position;
    protected final World world;
    private final String symbol;
    protected final OrganismType organismType;
    protected Integer lifeTime;
    private boolean canMove;

    public Organism(Integer strength, Integer initiative, Position position, World world, String symbol, OrganismType organismType) {
        this.strength = strength;
        this.initiative = initiative;
        this.position = position;
        this.world = world;
        this.symbol = symbol;
        this.organismType = organismType;
        this.lifeTime = 1;
        this.canMove = true;
    }

    public abstract void action();

    public abstract void collision(Position position);

    public void print() {
        System.out.print(symbol);
    }

    public OrganismType getOrganismType() {
        return organismType;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void lifeTimeIncrement() {
        this.lifeTime++;
    }

    public void gainStrength(int value) {
        strength += value;
    }

    public boolean isCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    @Override
    public int compareTo(Organism o) {
        if (this.initiative == o.initiative) {
            return -this.lifeTime + o.lifeTime;
        } else {
            return -this.initiative + o.initiative;
        }
    }
}
