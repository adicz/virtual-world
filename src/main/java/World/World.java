package World;

import World.Organisms.OrganismFactory;
import World.Organisms.OrganismType;
import World.Organisms.Organisms;
import World.Organisms.Position;

import java.util.*;

public class World {

    private Integer dimensionN;
    private Integer dimensionM;
    private Organisms[][] fields;
    private Log worldLogs = new Log();
    private List<Organisms> turnPriority = new ArrayList<>();

    public World(Integer dimensionN, Integer dimensionM) {
        this.dimensionN = dimensionN;
        this.dimensionM = dimensionM;
        this.fields = new Organisms[dimensionN][dimensionM];
    }

    public List<Organisms> getTurnPriority() {
        return turnPriority;
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

    public void addLog(String log){
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
        //placeGrassEverywhere();
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
                    randomPositionX = random.nextInt(dimensionN);
                    randomPositionY = random.nextInt(dimensionM);

                    if (fields[randomPositionX][randomPositionY] == null){
                        fields[randomPositionX][randomPositionY] = OrganismFactory.create(organism.getOrganismType(), new Position(randomPositionX, randomPositionY), this);
                        flag = false;
                    } else {
                        if (fields[randomPositionX][randomPositionY].getOrganismType() == null) {
                            fields[randomPositionX][randomPositionY] = OrganismFactory.create(organism.getOrganismType(), new Position(randomPositionX, randomPositionY), this);
                            flag = false;
                        }
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
        numberOfOrganisms.add(new NumberOfOrganisms(OrganismType.WOLF, 3));
        numberOfOrganisms.add(new NumberOfOrganisms(OrganismType.SHEEP, 5));
        numberOfOrganisms.add(new NumberOfOrganisms(OrganismType.FOX, 0));
        numberOfOrganisms.add(new NumberOfOrganisms(OrganismType.TURTLE, 0));
        numberOfOrganisms.add(new NumberOfOrganisms(OrganismType.ANTELOPE, 0));
        numberOfOrganisms.add(new NumberOfOrganisms(OrganismType.GRASS, 0));
        numberOfOrganisms.add(new NumberOfOrganisms(OrganismType.DANDELION, 0));
        numberOfOrganisms.add(new NumberOfOrganisms(OrganismType.GUARANA, 0));
        numberOfOrganisms.add(new NumberOfOrganisms(OrganismType.WOLFBERRIES, 0));

        return numberOfOrganisms;
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
        try {
            turnPriority.forEach(Organisms::action);
        } catch (ConcurrentModificationException e){
            System.out.println("Usunięto element z listy która właśnie była wykonywana");
        }

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
