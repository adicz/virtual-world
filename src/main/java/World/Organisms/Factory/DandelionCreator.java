package World.Organisms.Factory;

import World.Organisms.OrganismType;
import World.Organisms.Organisms;
import World.Organisms.Plant;
import World.World;

public class DandelionCreator implements OrganismFactory {
    @Override
    public Organisms create(World world, Integer positionX, Integer positionY) {
        return new Plant(0, 0, positionX, positionY ,world, "\uD83C\uDF3B", OrganismType.DANDELION);
    }
}
