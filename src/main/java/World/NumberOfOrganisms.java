package World;

import World.Organisms.OrganismType;

import java.util.Map;
import java.util.TreeMap;

public class NumberOfOrganisms {

    private static final Map<OrganismType, Integer> numberOfOrganism = new TreeMap<>();

    static {
        numberOfOrganism.put(OrganismType.HUMAN, 1);
        numberOfOrganism.put(OrganismType.WOLF, 2);
        numberOfOrganism.put(OrganismType.SHEEP, 2);
        numberOfOrganism.put(OrganismType.FOX, 2);
        numberOfOrganism.put(OrganismType.TURTLE, 10);
        numberOfOrganism.put(OrganismType.ANTELOPE, 2);
        numberOfOrganism.put(OrganismType.GRASS, 5);
        numberOfOrganism.put(OrganismType.DANDELION, 2);
        numberOfOrganism.put(OrganismType.GUARANA, 2);
        numberOfOrganism.put(OrganismType.WOLFBERRIES, 1);
    }

    public static Map<OrganismType, Integer> getNumberOfOrganism() {
        return numberOfOrganism;
    }

}
