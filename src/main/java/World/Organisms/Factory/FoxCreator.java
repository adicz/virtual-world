package World.Organisms.Factory;

import World.Organisms.Animal;
import World.Organisms.OrganismType;
import World.Organisms.Organisms;
import World.World;

public class FoxCreator implements OrganismFactory {
    @Override
    public Organisms create(World world, Integer positionX, Integer positionY) {
        return new Animal(3, 7, positionX, positionY , world, "\uD83E\uDD8A", OrganismType.FOX);
    }
}
