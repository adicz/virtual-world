package World;

import World.Organisms.OrganismType;

public class NumberOfOrganisms {

    private final OrganismType organismType;
    private final Integer numberOfOrganism;

    public NumberOfOrganisms(OrganismType organismType, Integer numberOfOrganism) {
        this.organismType = organismType;
        this.numberOfOrganism = numberOfOrganism;
    }

    public OrganismType getOrganismType() {
        return organismType;
    }

    public Integer getNumberOfOrganism() {
        return numberOfOrganism;
    }
}
