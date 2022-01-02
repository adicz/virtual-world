package World.Organisms;

import World.World;

public class Human extends Animal {

    public Human(Integer strength, Integer initiative, Integer positionX, Integer positionY, World world, String symbol, OrganismType organismType) {
        super(strength, initiative, positionX, positionY, world, symbol, organismType);
    }

}
