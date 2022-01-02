package World.Organisms;

import World.World;

import java.util.Scanner;

public class Human extends Animal {

    public Human(Integer strength, Integer initiative, Integer positionX, Integer positionY, World world, String symbol, OrganismType organismType) {
        super(strength, initiative, positionX, positionY, world, symbol, organismType);
    }

    @Override
    public void action() {
        Scanner scanner = new Scanner(System.in);
        String nextMove;
        System.out.println();
        System.out.print("Enter where you want to move (w:↑ a:← d:→ s:↓): ");
        nextMove = scanner.nextLine();

        switch (nextMove) {
            case "w":
                moveUp();
                break;
            case "a":
                moveLeft();
                break;
            case "d":
                moveRight();
                break;
            case "s":
                moveDown();
                break;
        }

    }

    public void moveUp() {
        if (positionX != 0) {
            world.getFields()[positionX][positionY] = null;
            positionX -= 1;
            world.getFields()[positionX][positionY] = this;
            System.out.println("[LOG] You moved up");
        } else {
            System.out.print("You can't move there, it's the end of the world\n\n");
        }
    }

    public void moveDown() {
        if (positionX != world.getFields().length - 1) {
            world.getFields()[positionX][positionY] = null;
            positionX += 1;
            world.getFields()[positionX][positionY] = this;
            System.out.println("[LOG] You moved down");
        } else {
            System.out.print("You can't move there, it's the end of the world\n\n");
        }
    }

    public void moveLeft() {
        if (positionY != 0) {
            world.getFields()[positionX][positionY] = null;
            positionY -= 1;
            world.getFields()[positionX][positionY] = this;
            System.out.println("[LOG] You moved left");
        } else {
            System.out.print("You can't move there, it's the end of the world\n\n");
        }
    }

    public void moveRight() {
        if (positionY != world.getFields()[0].length - 1) {
            world.getFields()[positionX][positionY] = null;
            positionY += 1;
            world.getFields()[positionX][positionY] = this;
            System.out.println("[LOG] You moved down");
        } else {
            System.out.print("You can't move there, it's the end of the world\n\n");
        }
    }
}
