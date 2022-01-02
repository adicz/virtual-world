package World.Organisms.Factory;

import World.Organisms.Human;
import World.Organisms.OrganismType;
import World.Organisms.Organisms;
import World.World;

public class HumanCreator implements OrganismFactory {
    @Override
    public Organisms create(World world, Integer positionX, Integer positionY) {
        return new Human(5, 4, positionX, positionY , world, "\uD83E\uDDD0", OrganismType.HUMAN);
    }
}
