package World.Organisms.Factory;

import World.Organisms.Animal;
import World.Organisms.OrganismType;
import World.Organisms.Organisms;
import World.World;

public class AntelopeCreator implements OrganismFactory {
    @Override
    public Organisms create(World world, Integer positionX, Integer positionY) {
        return new Animal(4, 4, positionX, positionY ,world, "\uD83D\uDC08", OrganismType.ANTELOPE);
    }
}
