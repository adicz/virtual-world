package world;

import world_logger.WorldLogger;
import organisms.factory.OrganismFactory;
import organisms.model.OrganismType;
import organisms.model.Organism;
import organisms.model.Position;

import java.util.*;

public class World {

    private final Integer dimensionN;
    private final Integer dimensionM;
    private Organism[][] fields;
    private WorldLogger worldLogs = new WorldLogger();
    private List<Organism> turnPriority = new ArrayList<>();

    public World(Integer dimensionN, Integer dimensionM) {
        this.dimensionN = dimensionN;
        this.dimensionM = dimensionM;
        this.fields = new Organism[dimensionN][dimensionM];
    }

    public Organism getOrganismOnField(Position position) {
        return fields[position.getX()][position.getY()];
    }

    public void setOrganismOnField(Position position, Organism organism) {
        fields[position.getX()][position.getY()] = organism;
    }

    public Organism[][] getFields() {
        return fields;
    }

    public void addLog(String log) {
        worldLogs.addLog(log);
    }

    public WorldLogger getWorldLogs() {
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
        Random random = new Random();
        Map<OrganismType, Integer> numberOfOrganisms = NumberOfOrganisms.getNumberOfOrganism();
        int randomPositionX;
        int randomPositionY;
        boolean flag = true;

        for (Map.Entry<OrganismType, Integer> organism : numberOfOrganisms.entrySet()) {
            for (int i = 0; i < organism.getValue(); i++) {
                do {
                    flag = true;
                    randomPositionX = random.nextInt(dimensionN);
                    randomPositionY = random.nextInt(dimensionM);

                    if (fields[randomPositionX][randomPositionY] == null) {
                        fields[randomPositionX][randomPositionY] = OrganismFactory
                                .create(organism.getKey(), new Position(randomPositionX, randomPositionY), this);
                        flag = false;
                    } else {
                        if (fields[randomPositionX][randomPositionY].getOrganismType() == null) {
                            fields[randomPositionX][randomPositionY] = OrganismFactory
                                    .create(organism.getKey(), new Position(randomPositionX, randomPositionY), this);
                            flag = false;
                        }
                    }
                } while (flag);
            }
        }
    }

    private void takeATurn() {
        turnPriority.clear();

        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                if (fields[i][j] != null) {
                    turnPriority.add(fields[i][j]);
                }
            }
        }

        Collections.sort(turnPriority);
        turnPriority.stream()
                .filter(Organism::isCanMove)
                .forEach(Organism::action);
        turnPriority.forEach(Organism::lifeTimeIncrement);
    }

    public void printWorld() {
        String spacer = "\t";
        System.out.print(" +");
        for (int i = 0; i < fields.length; i++) {
            System.out.print("--" + i + "-");
        }
        System.out.println("--+");

        for (int i = 0; i < fields.length; i++) {
            System.out.print(i + "|\t");
            for (int j = 0; j < fields[i].length; j++) {
                if (fields[i][j] != null) {
                    fields[i][j].print();
                } else {
                    System.out.print(" ");
                }
                System.out.print(spacer);
            }
            System.out.println("|");
        }

        System.out.print(" +");
        for (int i = 0; i < fields.length * 4 + 2; i++) {
            System.out.print("-");
        }
        System.out.println("+");
    }

}
