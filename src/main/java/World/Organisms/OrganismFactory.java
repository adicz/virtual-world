package World.Organisms;

import World.World;

public class OrganismFactory {

    public static Organisms create(OrganismType organismType, Integer positionX, Integer positionY, World world){
        switch(organismType){
            case HUMAN:
                return new Human(5, 4, positionX, positionY , world, "\uD83E\uDDD0", OrganismType.HUMAN);
            case WOLF:
                return new Animal(9,5, positionX, positionY , world, "\uD83D\uDC3A", OrganismType.WOLF);
            case SHEEP:
                return new Animal(4, 4, positionX, positionY , world, "\uD83D\uDC11", OrganismType.SHEEP);
            case FOX:
                return new Animal(3, 7, positionX, positionY , world, "\uD83E\uDD8A", OrganismType.FOX);
            case TURTLE:
                return new Animal(2, 1, positionX, positionY , world, "\uD83D\uDC22", OrganismType.TURTLE);
            case ANTELOPE:
                return new Animal(4, 4, positionX, positionY ,world, "\uD83D\uDC08", OrganismType.ANTELOPE);
            case GRASS:
                return new Plant(0, 0, positionX, positionY , world, "\uD83C\uDF3F", OrganismType.GRASS);
            case DANDELION:
                return new Plant(0, 0, positionX, positionY ,world, "\uD83C\uDF3B", OrganismType.DANDELION);
            case GUARANA:
                return new Plant(0, 0, positionX, positionY , world, "\uD83C\uDF1F", OrganismType.GUARANA);
            case WOLFBERRIES:
                return new Plant(99, 0, positionX, positionY , world, "\uD83C\uDF52", OrganismType.WOLFBERRIES);
        }
        return null;
    }

}