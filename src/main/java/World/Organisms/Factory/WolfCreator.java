package World.Organisms.Factory;

import World.Organisms.Animal;
import World.Organisms.OrganismType;
import World.Organisms.Organisms;
import World.World;

public class WolfCreator implements OrganismFactory {
    @Override
    public Organisms create(World world, Integer positionX, Integer positionY) {
        return new Animal(9,5, positionX, positionY , world, "\uD83D\uDC3A", OrganismType.WOLF);
    }
}
