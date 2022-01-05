package World.Organisms;

import World.World;

import java.util.Comparator;

public abstract class Organisms implements Comparable<Organisms> {

    protected Integer strength;
    protected Integer initiative;
    protected Position position;
    protected World world;
    private String symbol;
    private OrganismType organismType;
    protected Integer lifeTime;
    private boolean canMove;

    public Organisms(Integer strength, Integer initiative, Position position, World world, String symbol, OrganismType organismType) {
        this.strength = strength;
        this.initiative = initiative;
        this.position = position;
        this.world = world;
        this.symbol = symbol;
        this.organismType = organismType;
        this.lifeTime = 1;
        this.canMove = true;
    }

    public void action(){}

    public void collision(Position position){}

    public void print(){
        System.out.print(symbol);
    }

    public String getSymbol() {
        return symbol;
    }

    public OrganismType getOrganismType() {
        return organismType;
    }

    public Integer getInitiative() {
        return initiative;
    }

    public Integer getLifeTime() {
        return lifeTime;
    }

    public void lifeTimeIncrement(){
        this.lifeTime++;
    }

    public void gainStrength(int value){
        strength += value;
    }

    public boolean isCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    @Override
    public int compareTo(Organisms o) {
        if (this.initiative == o.initiative){
            return -this.lifeTime + o.lifeTime;
        } else {
            return -this.initiative + o.initiative;
        }
    }
}
