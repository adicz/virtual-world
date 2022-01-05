package World.Organisms;

import World.World;

import java.util.Random;

public class Animal extends Organisms {

    private Position previousPosition;

    public Animal(Integer strength, Integer initiative, Position position, World world, String symbol, OrganismType organismType) {
        super(strength, initiative, position, world, symbol, organismType);
        previousPosition = position;
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
                move(2);
                break;

            default:
                move();
                break;
        }
    }

    private void move() {
        Position newPosition = calculateNewPosition(1, 1);
        if (world.getOrganismOnField(newPosition) == null) {
            changePosition(newPosition);
        } else {
            collision(newPosition);
        }
    }

    private void move(int moveRange) {
        Position newPosition = calculateNewPosition(moveRange, moveRange);
        if (world.getOrganismOnField(newPosition) == null) {
            changePosition(newPosition);
        } else {
            collision(newPosition);
        }
    }

    private void moveWith25PercentChance() {
        Random random = new Random();
        int chanceToMove = random.nextInt(101);
        if (chanceToMove >= 75) {
            Position newPosition = calculateNewPosition(1, 1);
            if (world.getOrganismOnField(newPosition) == null) {
                changePosition(newPosition);
            } else {
                collision(newPosition);
            }
        }
    }

    private void moveIfOpponentIsWeaker() {
        Position newPosition = calculateNewPosition(1, 1);
        if (world.getOrganismOnField(newPosition) == null) {
            changePosition(newPosition);
        } else {
            if (world.getOrganismOnField(newPosition).strength <= this.strength) {
                collision(newPosition);
            } else {
                changePosition(newPosition);
            }
        }
    }

    public void changePosition(Position newPosition){
        world.setOrganismOnField(position, null);
        previousPosition = position;
        position = newPosition;
        world.setOrganismOnField(newPosition, this);
    }

    @Override
    public void collision(Position newPosition) {
        Organisms organismsOnNewPosition = world.getOrganismOnField(newPosition);
        OrganismType organismTypeOnNewPosition = organismsOnNewPosition.getOrganismType();

        if (organismTypeOnNewPosition == getOrganismType()) {
            //todo bornNewAnimal();
        } else {
            switch (organismTypeOnNewPosition) {
                case GRASS:
                case DANDELION:
                    changePosition(newPosition);
                    world.addLog(getOrganismType() + " ate " + organismTypeOnNewPosition);
                    break;

                case GUARANA:
                    changePosition(newPosition);
                    this.gainStrength(3);
                    world.addLog(getOrganismType() + " ate " + organismTypeOnNewPosition + " and gain 3 strength");
                    break;

                case WOLFBERRIES:
                    changePosition(newPosition);
                    world.setOrganismOnField(newPosition, null);
                    this.setCanMove(false);
                    world.addLog(getOrganismType() + " ate " + organismTypeOnNewPosition + " and died");
                    break;

                case TURTLE:
                    if (this.strength < 5){
                        changePosition(previousPosition);
                        world.addLog(getOrganismType() + " can't kill " + organismTypeOnNewPosition + " he back to his previous position");
                    } else {
                        fight(organismsOnNewPosition, newPosition);
                    }
                    break;
                case ANTELOPE:
                    Random random = new Random();
                    int chanceToEscape = random.nextInt(101);
                    if (chanceToEscape <= 50){
                        fight(organismsOnNewPosition, newPosition);
                    } else {
                        Position newPositionForEnemy = calculateNewUnoccupiedPosition(organismsOnNewPosition);
                        world.setOrganismOnField(newPositionForEnemy, organismsOnNewPosition);
                        changePosition(newPosition);
                        world.addLog(this.getOrganismType() + " attacks " + organismTypeOnNewPosition + " but she runs out ");
                    }
                    break;

                case SHEEP:
                case WOLF:
                case FOX:
                    fight(organismsOnNewPosition, newPosition);
                    break;
            }
        }
    }

    private void fight(Organisms enemy, Position newPosition) {
        if (this.strength < enemy.strength) {
            world.setOrganismOnField(position, null);
            world.addLog(this.getOrganismType() + " defeat fight with " + enemy.getOrganismType());
        } else if (this.strength > enemy.strength) {
            changePosition(newPosition);
            enemy.setCanMove(false);
            world.addLog(this.getOrganismType() + " won fight with " + enemy.getOrganismType());
        } else if (this.lifeTime < enemy.lifeTime){
            world.setOrganismOnField(position, null);
            world.addLog(this.getOrganismType() + " defeat fight with " + enemy.getOrganismType() + " because he was older");
        } else if (this.lifeTime > enemy.lifeTime){
            changePosition(newPosition);
            enemy.setCanMove(false);
            world.addLog(this.getOrganismType() + "won fight with " + enemy.getOrganismType() + " because he was younger");
        } else {
            world.addLog("Nothing happened because both " + this.getOrganismType() + " " + enemy.getOrganismType() + " have same attack and life time");
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

    private Position calculateNewUnoccupiedPosition(Organisms organism) {
        Random random = new Random();
        Position organismPosition = organism.position;
        Position newPosition;
        int moveX = 0;
        int moveY = 0;

        do {
            if (organismPosition.X < 1) {
                moveX = random.nextInt(2);
            } else if (organismPosition.X >= world.getFields().length - 1) {
                moveX = random.nextInt(2) - 1;
            } else {
                moveX = random.nextInt(3) - 1;
            }

            if (organismPosition.Y < 1) {
                moveY = random.nextInt(2);
            } else if (organismPosition.Y >= world.getFields()[0].length - 1) {
                moveY = random.nextInt(2) - 1;
            } else {
                moveY = random.nextInt(3) - 1;
            }
            int newPositionX = organismPosition.X + moveX;
            int newPositionY = organismPosition.Y + moveY;

            newPosition = new Position(newPositionX, newPositionY);
        } while (world.getOrganismOnField(newPosition) != null);

        return newPosition;
    }
}
