package organisms.model;

import organisms.factory.OrganismFactory;
import world.World;

import java.util.Random;

public class Animal extends Organism {

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
                moveWithChance(25);
                break;

            case ANTELOPE:
                move(2);
                break;

            default:
                move(1);
                break;
        }
    }

    private void move(int moveRange) {
        Position newPosition = calculateNewPosition(moveRange, moveRange);
        if (position.x != newPosition.x || position.y != newPosition.y) {
            if (world.getOrganismOnField(newPosition) == null) {
                changePosition(newPosition);
            } else {
                collision(newPosition);
            }
        }
    }

    private void moveWithChance(int percentChance) {
        Random random = new Random();
        int chanceToMove = random.nextInt(101);
        if (chanceToMove >= percentChance) {
            move(1);
        }
    }

    private void moveIfOpponentIsWeaker() {
        Position newPosition = calculateNewPosition(1, 1);
        if (world.getOrganismOnField(newPosition) == null) {
            changePosition(newPosition);
        } else {
            if (position.x != newPosition.x || position.y != newPosition.y) {
                if (world.getOrganismOnField(newPosition).strength <= this.strength) {
                    collision(newPosition);
                } else {
                    changePosition(newPosition);
                }
            }
        }
    }

    public void changePosition(Position newPosition) {
        world.setOrganismOnField(position, null);
        position = newPosition;
        world.setOrganismOnField(newPosition, this);
    }

    @Override
    public void collision(Position newPosition) {
        Organism organismsOnNewPosition = world.getOrganismOnField(newPosition);
        OrganismType organismTypeOnNewPosition = organismsOnNewPosition.getOrganismType();

        if (organismTypeOnNewPosition == getOrganismType()) {
            bornNewAnimal(organismsOnNewPosition);
        } else {
            switch (organismTypeOnNewPosition) {
                case GRASS:
                case DANDELION:
                    changePosition(newPosition);
                    world.addLog(String.format("%s %s ate %s %s",
                            organismType, position.toString(),
                            organismTypeOnNewPosition, organismsOnNewPosition.position.toString()));
                    break;

                case GUARANA:
                    changePosition(newPosition);
                    this.gainStrength(3);
                    world.addLog(String.format("%s %s ate %s %s and gain +\uD83D\uDCAA3 strength",
                            organismType, position.toString(),
                            organismTypeOnNewPosition, organismsOnNewPosition.position.toString()));
                    break;

                case WOLFBERRIES:
                    changePosition(newPosition);
                    world.setOrganismOnField(newPosition, null);
                    this.setCanMove(false);
                    world.addLog(String.format("%s %s ate %s %s and died",
                            organismType, position.toString(),
                            organismTypeOnNewPosition, organismsOnNewPosition.position.toString()));
                    break;

                case TURTLE:
                    if (this.strength < 5) {
                        world.addLog(String.format("%s %s can't kill %s %s he back to %s",
                                organismType, position.toString(),
                                organismTypeOnNewPosition, organismsOnNewPosition.position.toString(),
                                position.toString()));
                    } else {
                        fight(organismsOnNewPosition, newPosition);
                    }
                    break;

                case ANTELOPE:
                    Random random = new Random();
                    int chanceToEscape = random.nextInt(101);
                    if (chanceToEscape <= 50) {
                        fight(organismsOnNewPosition, newPosition);
                    } else {
                        Position newPositionForEnemy = calculateNewUnoccupiedPosition(organismsOnNewPosition);
                        world.setOrganismOnField(newPositionForEnemy, organismsOnNewPosition);
                        changePosition(newPosition);
                        world.addLog(String.format("%s %s attacks %s %s but she runs out to %s",
                                organismType, position.toString(),
                                organismTypeOnNewPosition, organismsOnNewPosition.position.toString(),
                                newPositionForEnemy.toString()));
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

    private void bornNewAnimal(Organism partnerOrganism) {
        if (isUnoccupiedPositionAround()) {
            Position newPosition = calculateNewUnoccupiedPosition(this);
            Organism newOrganism = OrganismFactory.create(this.getOrganismType(), newPosition, world);
            world.setOrganismOnField(newPosition, newOrganism);
            world.addLog(String.format("%s %s with %s %s born a new %s %s",
                    organismType, position.toString(),
                    partnerOrganism.organismType, partnerOrganism.position.toString(),
                    organismType, newPosition.toString()));
        }
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
        world.addLog(String.format("%s %s  wanted to born but there was no place",
                organismType, position.toString()));
        return false;
    }

    private void fight(Organism enemy, Position newPosition) {
        if (strength < enemy.strength) {
            world.setOrganismOnField(position, null);
            world.addLog(String.format("%s \uD83D\uDCAA%d %s attack %s \uD83D\uDCAA%d %s and defeat",
                    getOrganismType(), strength, position.toString(),
                    enemy.getOrganismType(), enemy.strength, enemy.position.toString()));
        } else if (strength > enemy.strength) {
            changePosition(newPosition);
            enemy.setCanMove(false);
            world.addLog(String.format("%s \uD83D\uDCAA%d %s attack %s \uD83D\uDCAA%d %s and win",
                    getOrganismType(), strength, position.toString(),
                    enemy.getOrganismType(), enemy.strength, enemy.position.toString()));
        } else {
            changePosition(newPosition);
            enemy.setCanMove(false);
            world.addLog(String.format("%s \uD83D\uDCAA%d %s attack %s \uD83D\uDCAA%d %s, fight was equals but attacker won",
                    getOrganismType(), strength, position.toString(),
                    enemy.getOrganismType(), enemy.strength, enemy.position.toString()));
        }
    }

    private Position calculateNewPosition(int moveRangeX, int moveRangeY) {
        Random random = new Random();
        int moveX = 0;
        int moveY = 0;
        if (position.x < moveRangeX) {
            moveX = random.nextInt(1 + moveRangeX);
        } else if (position.x >= world.getFields().length - moveRangeX) {
            moveX = random.nextInt(1 + moveRangeX) - moveRangeX;
        } else {
            moveX = random.nextInt(2 + moveRangeX) - moveRangeX;
        }

        if (position.y < moveRangeY) {
            moveY = random.nextInt(1 + moveRangeY);
        } else if (position.y >= world.getFields()[0].length - moveRangeY) {
            moveY = random.nextInt(1 + moveRangeY) - moveRangeY;
        } else {
            moveY = random.nextInt(2 + moveRangeY) - moveRangeY;
        }

        int newPositionX = position.x + moveX;
        int newPositionY = position.y + moveY;

        return new Position(newPositionX, newPositionY);
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
}
