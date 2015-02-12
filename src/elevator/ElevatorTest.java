/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elevator;

/**
 *
 * @author daniel.grewal090
 */
public class ElevatorTest {
    static public void main(String argv[]) {
        Elevator elev = new Elevator();
        try{
            elev.boardPassenger(3);
            elev.boardPassenger(5);
        }
        catch (ElevatorFullException efe)
        {
            System.err.println("Err: Full");
        }
        System.out.printf("Floor %d: %d passengers\n", elev.currentFloor(), elev.passengers());
        for (int i = 0; i < 13; i++) {
            elev.move();
            System.out.printf("Floor %d: %d passengers\n", elev.currentFloor(), elev.passengers());
        }
    }
}
