package World.Organisms;

import World.World;

import java.util.Random;

public class Animal extends Organisms {

    public Animal(Integer strength, Integer initiative, Integer positionX, Integer positionY, World world, String symbol, OrganismType organismType) {
        super(strength, initiative, positionX, positionY, world, symbol, organismType);
    }

    @Override
    public void action() {
        Random random = new Random();
        Integer moveX = 0;
        Integer moveY = 0;

        if (positionX == 0) {
            moveX = random.nextInt(2);
        } else if (positionX == world.getFields().length - 1) {
            moveX = random.nextInt(2) - 1;
        } else {
            moveX = random.nextInt(3) - 1;
        }

        if (positionY == 0) {
            moveY = random.nextInt(2);
        } else if (positionY == world.getFields()[0].length - 1) {
            moveY = random.nextInt(2) - 2;
        } else {
            moveY = random.nextInt(3) - 1;
        }

        Integer newPositionX = positionX + moveX;
        Integer newPositionY = positionY + moveY;

        switch (this.getOrganismType()) {
            case FOX:
                if (world.getFields()[newPositionX][newPositionY].getStrength() < this.getStrength()) {
                    world.getFields()[positionX][positionY] = null;
                    world.getFields()[newPositionX][newPositionY] = this;
                }
                break;

            case TURTLE:
                Integer changeToMove = random.nextInt(101);
                if (changeToMove > 75){
                    world.getFields()[positionX][positionY] = null;
                    world.getFields()[newPositionX][newPositionY] = this;
                }
                break;

            case ANTELOPE:


            default:
                world.getFields()[positionX][positionY] = null;
                world.getFields()[newPositionX][newPositionY] = this;
        }

        System.out.println("[LOG] " + this.getSymbol() + " moved {" + moveX + "; " + moveY + "}");
    }

    private void calculateNewPosition(){

    }


}
