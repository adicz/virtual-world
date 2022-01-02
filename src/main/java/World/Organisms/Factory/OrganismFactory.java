package World.Organisms.Factory;

import World.Organisms.Organisms;
import World.World;

public interface OrganismFactory {

    Organisms create(World world, Integer positionX, Integer positionY);

}
