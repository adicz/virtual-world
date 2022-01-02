package World.Organisms.Factory;

import World.Organisms.Animal;
import World.Organisms.OrganismType;
import World.Organisms.Organisms;
import World.World;

public class TurtleCreator implements OrganismFactory {
    @Override
    public Organisms create(World world, Integer positionX, Integer positionY) {
        return new Animal(2, 1, positionX, positionY , world, "\uD83D\uDC22", OrganismType.TURTLE);
    }
}
