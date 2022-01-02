package World.Organisms;

import World.World;

public abstract class Organisms {

    private Integer strength;
    private Integer initiative;
    private Integer positionX;
    private Integer positionY;
    private World world;
    private String symbol;
    private OrganismType organismType;

    public Organisms(Integer strength, Integer initiative, Integer positionX, Integer positionY, World world, String symbol, OrganismType organismType) {
        this.strength = strength;
        this.initiative = initiative;
        this.positionX = positionX;
        this.positionY = positionY;
        this.world = world;
        this.symbol = symbol;
        this.organismType = organismType;
    }

    public void action(){

    }

    public void collision(){

    }

    public void print(){
        System.out.print(symbol);
    }

    public String getSymbol() {
        return symbol;
    }

    public OrganismType getOrganismType() {
        return organismType;
    }
}
