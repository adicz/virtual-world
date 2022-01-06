package World;

import World.Organisms.OrganismFactory;
import World.Organisms.OrganismType;
import World.Organisms.Organisms;
import World.Organisms.Position;

import java.util.*;

public class World {

    private final Integer dimensionN;
    private final Integer dimensionM;
    private Organisms[][] fields;
    private Log worldLogs = new Log();
    private List<Organisms> turnPriority = new ArrayList<>();

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

    public void addLog(String log) {
        worldLogs.addLog(log);
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
                .filter(Organisms::isCanMove)
                .forEach(Organisms::action);
        turnPriority.forEach(Organisms::lifeTimeIncrement);
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
