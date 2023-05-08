# Virtual world

Virtual World is a project implemented as part of a Java course. The project consists of implementing a program as a virtual world simulator. The world has a two-dimensional structure of any user-set size. In the world there are simple life forms with different behaviors.

_Below is an example of how the program works_
![](https://raw.githubusercontent.com/adicz/virtual-world/main/example.gif)

# Project assumptions

The aim of the project is to implement a program that simulates a virtual world, which will have the structure of a
two-dimensional grid of any size NxM, determined by the user. In this world, there will be simple life forms with
different behaviors. Each organism occupies exactly one field in the grid, and at most one organism can be present on
each field (in case of collision, one of them should be removed or moved).

The simulator will have a turn-based character. In each turn, all existing organisms in the world will perform an action
appropriate to their type. Some of them will move (animal organisms), while others will be immobile (plant organisms).
In case of a collision (when one organism lands on the same field as another), one of the organisms wins by killing (
e.g., wolf) or repelling (e.g., turtle) the competitor.

The order of movements of organisms in a turn depends on their initiative. Animals with the highest initiative will move
first. In the case of animals with the same initiative, the order is determined by the seniority principle (the one that
has lived longer moves first). The victory in a meeting depends on the strength of the organism, although there will be
exceptions to this rule (see: Table 2). If the strength is equal, the organism that attacked wins.

A specific type of animal will be Human. Unlike animals, the human does not move randomly. The direction of its movement
is determined before the start of the turn using arrow keys or appropriate letters on the keyboard. The human also has a
special ability (see Appendix 1) that can be activated with a separate button. The activated ability remains active for
5 consecutive turns, after which it is deactivated. The ability cannot be activated again before the expiration of 5
consecutive turns. At the start of the program, there should be a few copies of all types of animals and plants on the
board. In addition to presenting the current state of the board, the program should also display information about the
results of battles, plant consumption, and other events occurring in the world.

The following notes do not cover all the details and are only guidelines for implementing the project according to the rules of
object-oriented programming.

You should create a **World** class that manages gameplay and organisms. Among other things, it should contain methods such as:

- `takeATurn()`.
- `printWorld()`.

and fields, e.g.:

- `organisms`.

You should also create an abstract class **Organism** . basic fields:

- `strength`.
- `initiation`.
- `position (x,y)`.
- `world` - reference to the world in which the organism is located.

basic methods:

- `action()` → determines the behavior of the organism during the turn,
- `collision()` → determines the behavior of an organism during contact/collision with another organism,
- `print()` → causes a symbolic representation of the organism to be drawn.

The Organism class should be abstract. It should be inherited by two more abstract classes: **Plant**
and **Animal** .

In the **Animal** class, behaviors common to all/most animals should be implemented, primarily:

- the basic form of movement in the `action()` method → each typical animal in its turn moves to a randomly selected,
  adjacent field,
- reproduction under the `collision()` method → when colliding with an organism of the same species, there is no fight, both
  animals remain in their places, a third animal, of the same species, appears next to them.

The class **Human** is intended to be an extension of the class Animal . It does not have its own intelligence (it is controlled by the
player) and does not reproduce (the player will be the only Human on the map).

_Table 1. Characteristics of the human class_
<table>
  <tr>
    <td> strength </td>
    <td> initiative </td>
    <td> specifics of the action() method  </td>
    <td> specifics of the collision() method  </td>
  </tr>
  <tr>
    <td> 5 </td>
    <td> 4 </td>
    <td>Man moves in the same way as animals but the direction
        of his movement is not random, and corresponds to the
        arrow pressed by the player on the keyboard i.e. if the
        player presses the arrow to the left, then (when it is
        his turn) the character will move one field to the left.</td>
    <td>The man has a special skill (see Appendix 1), which can 
        be activated by a separate button on the keyboard. Once 
        activated, this skill affects the behavior of the collision() 
        method for five consecutive turns. After that, the skill 
        is deactivated and cannot be activated again for the next 
        five turns.</td>
  </tr>
</table>

Implement 5 classes of animals. The types of animals are defined in the following table.

_Table 2. Inventory of animals found in the virtual world_
<table>
  <tr>
    <td> id </td>
    <td> animal </td>
    <td> strength  </td>
    <td> initiative  </td>
    <td> specifics of the action() method  </td>
    <td> specifics of the collision() method  </td>
  </tr>
  <tr>
    <td> 1 </td>
    <td> wolf </td>
    <td> 9 </td>
    <td> 5 </td>
    <td> - </td>
    <td> - </td>
  </tr>
  <tr>
    <td> 2 </td>
    <td> sheep </td>
    <td> 4 </td>
    <td> 4 </td>
    <td> - </td>
    <td> - </td>
  </tr>
  <tr>
    <td> 3 </td>
    <td> fox </td>
    <td> 3 </td>
    <td> 7 </td>
    <td> Good sense of smell: a fox will never move into a field occupied by an organism stronger than it. </td>
    <td> - </td>
  </tr>
  <tr>
    <td> 4 </td>
    <td> turtle </td>
    <td> 2 </td>
    <td> 1 </td>
    <td> In 75% of cases, it does not change its position. </td>
    <td> Repels attacks from animals with strength <5. The attacker must return to his previous field. </td>
  </tr>
  <tr>
    <td> 5 </td>
    <td> antelope </td>
    <td> 4 </td>
    <td> 4 </td>
    <td> The range of movement is 2 fields. </td>
    <td> 50% chance of escaping the fight. It then moves to an unoccupied adjacent field. </td>
  </tr>
</table>

In the **Plant** class, implement behaviors common to all/most plants,
primarily:
- simulate the spread of a plant in the `action()` method → with some probability each plant can "seed" a new plant of the same species on a random neighboring field.
  
All plants have zero initiative.
  
Implement 4 classes of plants. The types of plants are defined in the following table.

_Table 3. An inventory of plants found in the virtual world_
<table>
  <tr>
    <td> id </td>
    <td> plant </td>
    <td> strength  </td>
    <td> specifics of the action() method  </td>
    <td> specifics of the collision() method  </td>
  </tr>
  <tr>
    <td> 1 </td>
    <td> grass </td>
    <td> 0 </td>
    <td> - </td>
    <td> - </td>
  </tr>
  <tr>
    <td> 2 </td>
    <td> dandelion </td>
    <td> 0 </td>
    <td> He makes three attempts to spread in one turn. </td>
    <td> - </td>
  </tr>
  <tr>
    <td> 3 </td>
    <td> guarana </td>
    <td> 0 </td>
    <td> - </td>
    <td> Increases the strength of the animal that ate this plant by 3. </td>
  </tr>
  <tr>
    <td> 4 </td>
    <td> wolfberries </td>
    <td> 99 </td>
    <td> - </td>
    <td> The animal that ate the plant dies. </td>
  </tr>
</table>

Create a class **World** which includes objects of class **Organism** . Implement
turn flow, calling the `action()` methods for all organisms and `collision()` for
organisms on the same field. Remember that the order in which the `action()` method is called
depends on the initiative (or age, in the case of equal initiative values) of the organism.
Organisms have the ability to influence the state of the world. Therefore, there is a need to
pass the `action()` and `collision()` methods a parameter specifying an object of the World class .
Try to ensure that the World class defines as public components only such fields and
methods that are needed by the rest of the application objects for action. The remaining
functionality of the world try to include in private components.

_Table 4: Special human skills_
<table>
  <tr>
    <td> id </td>
    <td> skill </td>
    <td> features  </td>
  </tr>
  <tr>
    <td> 1 </td>
    <td> immortality </td>
    <td> A human cannot be killed. If confronted with a stronger opponent, he moves to a randomly selected adjacent field.  </td>
  </tr>
  <tr>
    <td> 2 </td>
    <td> magic potion </td>
    <td> A human strength increases by 10 in the first round of the skill. In each subsequent turn, it decreases by 1 until it returns to its initial state.  </td>
  </tr>
  <tr>
    <td> 3 </td>
    <td> antelope speed </td>
    <td> A human moves a distance of two fields instead of one for the first 3 turns of the skill. In the remaining 2 turns, the chance that the skill will work is supposed to be 50%.  </td>
  </tr>
  <tr>
    <td> 4 </td>
    <td> alzurs shield </td>
    <td> Human deters all animals. The animal that stands on the man's field is moved to a random neighboring field.  </td>
  </tr>
  <tr>
    <td> 5 </td>
    <td> burnout </td>
    <td> Human destroys all plants and animals in the fields adjacent to his position.  </td>
  </tr>
</table>



### Design tips
Visualize the world should be done in the console. Each organism is represented by a different ASCII symbol. Pressing one of the keys will transition to the next turn, clearing the console and again printing out the corresponding symbols, representing the changed state of the game. At least one line of text in the console is dedicated to reporting the results of events such as food or the outcome of a battle.

#### Classes and objects
1. Use classes and use objects in the project, it is not allowed to writing "loose" functions (except for the main function)
2. Logical division into namespaces - each namespace in a separate module (file)
3. Methods that do not use the object should be static. They should not be abused. 
4. At least one abstract class

#### Hermetization :
1. All class fields should be private or protected (protected)
2. Selected classes should have get and set methods for components or only get, or complete lack of direct access

#### Inheritance
1. At least 1 base class from which it directly inherits (in the same generation) several derived classes
2. Code reuse (code in base class used by objects of classes derived classes)
3. Overriding the method of the base class
4. Explicit invocation of methods from the base class despite their overriding in the derived class

#### Other requirements
1. The state of all objects should load and write to a file
2. Demonstrate exception handling and implement examples of your own exceptions

#### Programming style
Follow the rules related to programming style, primarily:
   - consistency in naming variables and types,
   - consistency in the use of tabs (indentation) and spacing,
   - limited size of functions,
   - maintaining consistency in the organization of source code within a class (e.g., uniform order public->protected->private).

