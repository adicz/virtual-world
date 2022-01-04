package World.Organisms;

import World.World;

public class Plant extends Organisms {

    public Plant(Integer strength, Integer initiative, Position position, World world, String symbol, OrganismType organismType) {
        super(strength, initiative, position, world, symbol, organismType);
    }

    @Override
    public void action() {

    }

    @Override
    public void collision(Position position) {

    }
}
