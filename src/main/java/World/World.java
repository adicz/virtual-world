package World;

import World.Organisms.OrganismFactory;
import World.Organisms.OrganismType;
import World.Organisms.Organisms;
import World.Organisms.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class World {

    private Integer dimensionN;
    private Integer dimensionM;
    private Organisms[][] fields;
    private Log worldLogs = new Log();

    public World(Integer dimensionN, Integer dimensionM) {
        this.dimensionN = dimensionN;
        this.dimensionM = dimensionM;
        this.fields = new Organisms[dimensionN][dimensionM];
    }

    public Organisms getOrganismOnField(Position position) {
        return fields[position.getPositionX()][position.getPositionY()];
    }

    public void setOrganismOnField(Position position, Organisms organism) {
        fields[position.getPositionX()][position.getPositionY()] = organism;
    }

    public Organisms[][] getFields() {
        return fields;
    }

    public Log getWorldLogs() {
        return worldLogs;
    }

    public void start() {
        createWorld();
        while (true) {
            printWorld();
            takeATurn();
            worldLogs.printLogs();
        }
    }

    private void createWorld() {
        placeGrassEverywhere();
        placeOrganisms();
    }

    private void placeOrganisms() {
        Random random = new Random();
        List<NumberOfOrganisms> numberOfOrganisms = createNumberOfOrganisms();
        int randomPositionX;
        int randomPositionY;
        boolean flag = true;

        for (NumberOfOrganisms organism : numberOfOrganisms) {
            for (int i = 0; i < organism.getNumberOfOrganism(); i++) {
                do {
                    flag = true;
                    randomPositionX = random.nextInt(dimensionN - 1);
                    randomPositionY = random.nextInt(dimensionM - 1);

                    if (fields[randomPositionX][randomPositionY].getOrganismType() == OrganismType.GRASS) {
                        fields[randomPositionX][randomPositionY] = OrganismFactory.create(organism.getOrganismType(), new Position(randomPositionX, randomPositionY), this);
                        flag = false;
                    }
                } while (flag);
            }
        }
    }

    private void placeGrassEverywhere() {
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                fields[i][j] = OrganismFactory.create(OrganismType.GRASS, new Position(i, j), this);
            }
        }
    }

    private List<NumberOfOrganisms> createNumberOfOrganisms() {
        List<NumberOfOrganisms> numberOfOrganisms = new ArrayList<>();

        numberOfOrganisms.add(new NumberOfOrganisms(OrganismType.HUMAN, 1));
        numberOfOrganisms.add(new NumberOfOrganisms(OrganismType.WOLF, 0));
        numberOfOrganisms.add(new NumberOfOrganisms(OrganismType.SHEEP, 0));
        numberOfOrganisms.add(new NumberOfOrganisms(OrganismType.FOX, 0));
        numberOfOrganisms.add(new NumberOfOrganisms(OrganismType.TURTLE, 1));
        numberOfOrganisms.add(new NumberOfOrganisms(OrganismType.ANTELOPE, 1));
        numberOfOrganisms.add(new NumberOfOrganisms(OrganismType.DANDELION, 0));
        numberOfOrganisms.add(new NumberOfOrganisms(OrganismType.GUARANA, 0));
        numberOfOrganisms.add(new NumberOfOrganisms(OrganismType.WOLFBERRIES, 0));

        return numberOfOrganisms;
    }

    private void takeATurn() {
        List<Organisms> turnPriority = new ArrayList<>();

        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                if (fields[i][j] != null) {
                    turnPriority.add(fields[i][j]);
                }
            }
        }

        Collections.sort(turnPriority);
        turnPriority.forEach(Organisms::action);
        turnPriority.forEach(Organisms::lifeTimeIncrement);
    }

    public void printWorld() {
        String spacer = "\t";
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {

                if (fields[i][j] != null) {
                    fields[i][j].print();
                } else {
                    System.out.print(" ");
                }
                System.out.print(spacer);

            }
            System.out.println();
        }
    }

}
