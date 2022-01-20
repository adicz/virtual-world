package organisms.model;

import organisms.human_skills.*;
import world.World;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Human extends Animal {

    public List<Skill> skills = Arrays.asList(new AlzursShield(), new AntelopeSpeed(), new Holocaust(), new Immortal(), new MagicPotion());

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
                // todo obsługa umiejętności
            case "q":
                // todo obsługo statystyk świata
        }
    }

    private String getNextMove() {
        Scanner scanner = new Scanner(System.in);
        String nextMove;
        System.out.println();
        do {
            System.out.print("Enter where you want to move (w:↑ a:← d:→ s:↓): ");
            nextMove = scanner.nextLine();
            if (isCorrectMove(nextMove)) {
                System.out.println("Wrong index");
            }
        } while (isCorrectMove(nextMove));
        return nextMove;
    }

    private boolean isCorrectMove(String nextMove) {
        return !"w".equals(nextMove) &&
                !"a".equals(nextMove) &&
                !"s".equals(nextMove) &&
                !"d".equals(nextMove);
    }

    public void moveUp() {
        if (position.x != 0) {
            world.getFields()[position.x][position.y] = null;
            position.x -= 1;
            world.getFields()[position.x][position.y] = this;
            world.getWorldLogs().addLog("You moved up");
        } else {
            System.out.print("You can't move there, it's the end of the world\n\n");
        }
    }

    public void moveDown() {
        if (position.x != world.getFields().length - 1) {
            world.getFields()[position.x][position.y] = null;
            position.x += 1;
            world.getFields()[position.x][position.y] = this;
            world.getWorldLogs().addLog("You moved down");
        } else {
            System.out.print("You can't move there, it's the end of the world\n\n");
        }
    }

    public void moveLeft() {
        if (position.y != 0) {
            world.getFields()[position.x][position.y] = null;
            position.y -= 1;
            world.getFields()[position.x][position.y] = this;
            world.getWorldLogs().addLog("You moved left");
        } else {
            System.out.print("You can't move there, it's the end of the world\n\n");
        }
    }

    public void moveRight() {
        if (position.y != world.getFields()[0].length - 1) {
            world.getFields()[position.x][position.y] = null;
            position.y += 1;
            world.getFields()[position.x][position.y] = this;
            world.getWorldLogs().addLog("You moved right");
        } else {
            System.out.print("You can't move there, it's the end of the world\n\n");
        }
    }
}
