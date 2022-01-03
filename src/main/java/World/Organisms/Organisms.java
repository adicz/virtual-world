package World.Organisms;

import World.World;

import java.util.Comparator;

public abstract class Organisms implements Comparable<Organisms> {

    private Integer strength;
    private Integer initiative;
    protected Position position;
    protected World world;
    private String symbol;
    private OrganismType organismType;
    private Integer lifeTime;

    public Organisms(Integer strength, Integer initiative, Position position, World world, String symbol, OrganismType organismType) {
        this.strength = strength;
        this.initiative = initiative;
        this.position = position;
        this.world = world;
        this.symbol = symbol;
        this.organismType = organismType;
        this.lifeTime = 1;
    }

    public void action(){}

    public void collision(){}

    public void print(){
        System.out.print(symbol);
    }

    public String getSymbol() {
        return symbol;
    }

    public OrganismType getOrganismType() {
        return organismType;
    }

    public Integer getStrength() {
        return strength;
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

    @Override
    public int compareTo(Organisms o) {
        if (this.initiative == o.initiative){
            return -this.lifeTime + o.lifeTime;
        } else {
            return -this.initiative + o.initiative;
        }
    }
}
