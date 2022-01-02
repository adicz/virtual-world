package World.Organisms;

import World.World;

public class Animal extends Organisms {

    public Animal(Integer strength, Integer initiative, Integer positionX, Integer positionY, World world, String symbol, OrganismType organismType) {
        super(strength, initiative, positionX, positionY, world, symbol, organismType);
    }

}
