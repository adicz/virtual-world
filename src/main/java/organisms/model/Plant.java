package organisms.model;

import organisms.factory.OrganismFactory;
import world.World;

import java.util.Random;

public class Plant extends Organism {

    public Plant(Integer strength, Integer initiative, Position position, World world, String symbol, OrganismType organismType) {
        super(strength, initiative, position, world, symbol, organismType);
    }

    @Override
    public void action() {
        switch (this.getOrganismType()) {
            case DANDELION:
                for (int i = 0; i < 3; i++) {
                    sow(4);
                }
                break;

            case WOLFBERRIES:
                sow(2);

            default:
                sow(5);
                break;
        }
    }

    @Override
    public void collision(Position position) {

    }

    private void sow(int percentChance) {
        Random random = new Random();
        int chanceToSowNewPlant = random.nextInt(101);
        if (chanceToSowNewPlant <= percentChance && isUnoccupiedPositionAround()) {
            Position newPosition = calculateNewUnoccupiedPosition(this);
            Organism newOrganism = OrganismFactory.create(this.getOrganismType(), newPosition, world);
            world.setOrganismOnField(newPosition, newOrganism);
            world.addLog(String.format("%s %s sowed a new one %s %s",
                    organismType, position.toString(),
                    organismType, newPosition.toString()));
        }
    }

    private Position calculateNewUnoccupiedPosition(Organism organism) {
        Random random = new Random();
        Position organismPosition = organism.position;
        Position newPosition;
        int moveX = 0;
        int moveY = 0;

        do {
            if (organismPosition.x < 1) {
                moveX = random.nextInt(2);
            } else if (organismPosition.x >= world.getFields().length - 1) {
                moveX = random.nextInt(2) - 1;
            } else {
                moveX = random.nextInt(3) - 1;
            }

            if (organismPosition.y < 1) {
                moveY = random.nextInt(2);
            } else if (organismPosition.y >= world.getFields()[0].length - 1) {
                moveY = random.nextInt(2) - 1;
            } else {
                moveY = random.nextInt(3) - 1;
            }
            int newPositionX = organismPosition.x + moveX;
            int newPositionY = organismPosition.y + moveY;

            newPosition = new Position(newPositionX, newPositionY);
        } while (world.getOrganismOnField(newPosition) != null);

        return newPosition;
    }

    private boolean isUnoccupiedPositionAround() {
        for (int i = position.x - 1; i <= position.x + 1; i++) {
            for (int j = position.y - 1; j <= position.y + 1; j++) {
                try {
                    if (world.getOrganismOnField(new Position(i, j)) == null) {
                        return true;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {

                }
            }
        }
        world.addLog(String.format("%s %s wanted to sow but there was no place",
                organismType, position.toString()));
        return false;
    }
}
