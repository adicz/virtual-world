package World.Organisms;

import World.World;

import java.util.Random;

public class Animal extends Organisms {

    public Animal(Integer strength, Integer initiative, Position position, World world, String symbol, OrganismType organismType) {
        super(strength, initiative, position, world, symbol, organismType);
    }

    @Override
    public void action() {
        switch (this.getOrganismType()) {
            case FOX:
                moveIfOpponentIsWeaker();
                break;

            case TURTLE:
                moveWith25PercentChance();
                break;

            case ANTELOPE:
                moveTwoFields();
                break;

            default:
                moveOneFiled();
                break;
        }
    }

    private void moveOneFiled() {
        Position oldPosition = position;
        Position newPosition = calculateNewPosition(1, 1);
        world.setOrganismOnField(oldPosition, null);
        if (world.getOrganismOnField(newPosition) != null) {
            position = newPosition;
            collision();
        } else {
            world.setOrganismOnField(oldPosition,  this);
        }
    }

    private void moveTwoFields() {
        Position oldPosition = position;
        Position newPosition = calculateNewPosition(2, 2);
        world.getFields()[oldPosition.X][oldPosition.Y] = null;
        if (world.getFields()[newPosition.X][newPosition.Y] != null) {
            position = newPosition;
            collision();
        } else {
            world.setOrganismOnField(oldPosition, this);
        }
    }

    private void moveWith25PercentChance() {
        Random random = new Random();
        Position oldPosition = position;
        int chanceToMove = random.nextInt(101);
        if (chanceToMove >= 75) {
            Position newPosition = calculateNewPosition(1, 1);
            position = newPosition;
            world.getFields()[oldPosition.X][oldPosition.Y] = null;
            if (world.getFields()[newPosition.X][newPosition.Y] != null) {
                collision();
            } else {
                world.getFields()[oldPosition.X][oldPosition.Y] = this;
            }
        }
    }

    private void moveIfOpponentIsWeaker() {
        Position oldPosition = position;
        Position newPosition = calculateNewPosition(1, 1);
        if (world.getFields()[newPosition.X][newPosition.Y].getStrength() <= this.getStrength()) {
            position = newPosition;
            world.getFields()[oldPosition.X][oldPosition.Y] = null;
            if (world.getFields()[newPosition.X][newPosition.Y] != null) {
                collision();
            } else {
                world.getFields()[oldPosition.X][oldPosition.Y] = this;
            }
        }
    }

    @Override
    public void collision() {
        OrganismType organismTypeOnNewPosition = world.getFields()[position.X][position.Y].getOrganismType();
        if (organismTypeOnNewPosition == getOrganismType()){
            //todo
        } else {
            switch (organismTypeOnNewPosition) {
                case GRASS:
                case DANDELION:
                    world.getFields()[position.X][position.Y] = this;
                    world.getWorldLogs().addLog(getOrganismType() + " ate " + organismTypeOnNewPosition);
                    break;

                case GUARANA:
                    world.getFields()[position.X][position.Y] = this;
                    this.gainStrength(3);
                    world.getWorldLogs().addLog(getOrganismType() + " ate " + organismTypeOnNewPosition + " and gain 3 strength");
                    break;

                case WOLFBERRIES:
                    world.getFields()[position.X][position.Y] = null;
                    world.getWorldLogs().addLog(getOrganismType() + " ate " + organismTypeOnNewPosition + " and died");
                    break;

                case TURTLE:
                    //todo
                    break;

                case ANTELOPE:
                    //todo
                    break;

                case WOLF:
                case SHEEP:
                case FOX:
                    //todo
                    break;
            }
        }
    }

    private Position calculateNewPosition(int moveRangeX, int moveRangeY) {
        Random random = new Random();
        int moveX = 0;
        int moveY = 0;

        if (position.X < moveRangeX) {
            moveX = random.nextInt(1 + moveRangeX);
        } else if (position.X >= world.getFields().length - moveRangeX) {
            moveX = random.nextInt(1 + moveRangeX) - moveRangeX;
        } else {
            moveX = random.nextInt(2 + moveRangeX) - moveRangeX;
        }

        if (position.Y < moveRangeY) {
            moveY = random.nextInt(1 + moveRangeY);
        } else if (position.Y >= world.getFields()[0].length - moveRangeY) {
            moveY = random.nextInt(1 + moveRangeY) - moveRangeY;
        } else {
            moveY = random.nextInt(2 + moveRangeY) - moveRangeY;
        }

        int newPositionX = position.X + moveX;
        int newPositionY = position.Y + moveY;

        return new Position(newPositionX, newPositionY);

    }


}
