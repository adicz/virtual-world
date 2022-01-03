package World.Organisms;

import World.World;

import java.util.Objects;
import java.util.Scanner;

public class Human extends Animal {

    public Human(Integer strength, Integer initiative, Position position, World world, String symbol, OrganismType organismType) {
        super(strength, initiative, position, world, symbol, organismType);
    }

    @Override
    public void action() {
        String nextMove = getNextMove();

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
            case "e":
                // todo
        }

    }

    private String getNextMove() {
        Scanner scanner = new Scanner(System.in);
        String nextMove;
        System.out.println();

        do {
            System.out.print("Enter where you want to move (w:↑ a:← d:→ s:↓): ");
            nextMove = scanner.nextLine();
            if (isCorrectMove(nextMove)){
                System.out.println("Wrong index");
            }
        } while (isCorrectMove(nextMove));
        return nextMove;
    }

    private boolean isCorrectMove(String nextMove) {
        return !Objects.equals(nextMove, "w") &&
                !Objects.equals(nextMove, "a") &&
                !Objects.equals(nextMove, "s") &&
                !Objects.equals(nextMove, "d");
    }

    public void moveUp() {
        if (position.X != 0) {
            world.getFields()[position.X][position.Y] = null;
            position.X -= 1;
            world.getFields()[position.X][position.Y] = this;
            world.getWorldLogs().addLog("You moved up");
        } else {
            System.out.print("You can't move there, it's the end of the world\n\n");
        }
    }

    public void moveDown() {
        if (position.X != world.getFields().length - 1) {
            world.getFields()[position.X][position.Y] = null;
            position.X += 1;
            world.getFields()[position.X][position.Y] = this;
            world.getWorldLogs().addLog("You moved down");
        } else {
            System.out.print("You can't move there, it's the end of the world\n\n");
        }
    }

    public void moveLeft() {
        if (position.Y != 0) {
            world.getFields()[position.X][position.Y] = null;
            position.Y -= 1;
            world.getFields()[position.X][position.Y] = this;
            world.getWorldLogs().addLog("You moved left");
        } else {
            System.out.print("You can't move there, it's the end of the world\n\n");
        }
    }

    public void moveRight() {
        if (position.Y != world.getFields()[0].length - 1) {
            world.getFields()[position.X][position.Y] = null;
            position.Y += 1;
            world.getFields()[position.X][position.Y] = this;
            world.getWorldLogs().addLog("You moved down");
        } else {
            System.out.print("You can't move there, it's the end of the world\n\n");
        }
    }
}
