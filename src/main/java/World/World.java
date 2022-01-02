package World;

import World.Organisms.OrganismFactory;
import World.Organisms.OrganismType;
import World.Organisms.Organisms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World {

    private Integer dimensionN;
    private Integer dimensionM;
    private Organisms[][] world;

    public World(Integer dimensionN, Integer dimensionM) {
        this.dimensionN = dimensionN;
        this.dimensionM = dimensionM;
        this.world = new Organisms[dimensionN][dimensionM];
    }

    public void start() {
        createWorld();
        printWorld();
    }

    private void createWorld() {
        placeGrassEverywhere();
        placeOrganisms();
    }

    private void placeOrganisms() {
        Random random = new Random();
        List<NumberOfOrganisms> numberOfOrganisms = createNumberOfOrganisms();
        Integer randomPositionX;
        Integer randomPositionY;
        boolean flag = true;

        for (NumberOfOrganisms organism : numberOfOrganisms) {
            for (int i = 0; i < organism.getNumberOfOrganism(); i++) {
                do {
                    flag = true;
                    randomPositionX = random.nextInt(dimensionN - 1);
                    randomPositionY = random.nextInt(dimensionM - 1);

                    if (world[randomPositionX][randomPositionY].getOrganismType() == OrganismType.GRASS) {
                        world[randomPositionX][randomPositionY] = OrganismFactory.create(organism.getOrganismType(), randomPositionX, randomPositionY, this);
                        flag = false;
                    }
                } while (flag);
            }
        }
    }

    private void placeGrassEverywhere() {
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[i].length; j++) {
                world[i][j] = OrganismFactory.create(OrganismType.GRASS, i, j, this);
            }
        }
    }

    private List<NumberOfOrganisms> createNumberOfOrganisms() {
        List<NumberOfOrganisms> numberOfOrganisms = new ArrayList<>();

        numberOfOrganisms.add(new NumberOfOrganisms(OrganismType.HUMAN, 1));
        numberOfOrganisms.add(new NumberOfOrganisms(OrganismType.WOLF, 4));
        numberOfOrganisms.add(new NumberOfOrganisms(OrganismType.SHEEP, 4));
        numberOfOrganisms.add(new NumberOfOrganisms(OrganismType.FOX, 2));
        numberOfOrganisms.add(new NumberOfOrganisms(OrganismType.TURTLE, 3));
        numberOfOrganisms.add(new NumberOfOrganisms(OrganismType.ANTELOPE, 2));
        numberOfOrganisms.add(new NumberOfOrganisms(OrganismType.DANDELION, 12));
        numberOfOrganisms.add(new NumberOfOrganisms(OrganismType.GUARANA, 8));
        numberOfOrganisms.add(new NumberOfOrganisms(OrganismType.WOLFBERRIES, 2));

        return numberOfOrganisms;
    }

    private void takeATurn() {

    }

    private void printWorld() {
        String spacer = "\t";
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[i].length; j++) {
                world[i][j].print();
                System.out.print(spacer);
            }
            System.out.println();
        }
    }

}
