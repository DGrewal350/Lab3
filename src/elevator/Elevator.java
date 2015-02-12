package elevator;

import java.util.Random;

/* @author Daniel Grewal
*  @date   02/11/15
*/
public class Elevator {

    public final int MAX_CAPACITY = 10;
    public final int NUM_FLOORS = 7;
    
    private final Random random = new Random();
    private int passenger_count = 0;
    private int current_floor;
    private boolean moving_up;
    private int[] passenger_targets;
    private Floor[] floors;
    private boolean[] stops;

    public Elevator() {
        floors = new Floor[NUM_FLOORS];
        current_floor = 0;
        moving_up = true;
        passenger_targets = new int[NUM_FLOORS];
        stops = new boolean[NUM_FLOORS];
        
        for (int i = 0; i < NUM_FLOORS; i++)
        {
            floors[i] = new Floor(i + 1);
            //int waiting_passengers = random.nextInt();
            //boolean direction = random.nextBoolean();
            
            //for (int j = 0; j < waiting_passengers; j++)
            //{
            //    floors[i].addPassenger(direction);
            //}
        }
    }

    public void move() {
        if (moving_up) {
            current_floor++;
        } else {
            current_floor--;
        }
        if (current_floor == 0) {
            moving_up = true;
        } else if (current_floor == NUM_FLOORS - 1) {
            moving_up = false;
        }
        if (passenger_targets[current_floor] != 0) {
            stop();
        }
        if (stops[current_floor] == true) {
            stop();
        }
    }

    public void stop() {
        passenger_count -= passenger_targets[current_floor];
        passenger_targets[current_floor] = 0;
        //System.out.println("\r\nStopping on floor " + (current_floor + 1));
        //System.out.println(this);
        stops[current_floor] = false;
        floors[current_floor].unloadPassengers(this, this.moving_up);
    }

    public void boardPassenger(int floor) throws ElevatorFullException{
        if (passenger_count < MAX_CAPACITY)
        {
            floor--;
            passenger_targets[floor]++;
            stops[floor] = true;
            passenger_count++;
        }
        else
        {
            throw new ElevatorFullException();
        }
    }
    
    public void registerRequest(int floor, boolean direction) {
        stops[floor] = true;
        moving_up = direction;
    }
    
    public int currentFloor() {
        return current_floor + 1;
    }
    
    public int passengers() {
        return passenger_count;
    }

    public String toString() {
        int passengers = 0;
        for (int i = 0; i < passenger_targets.length; i++) {
            if (passenger_targets[i] > 0) {
                passengers += passenger_targets[i];
            }
        }
        return "Current Passengers: " + passengers + "\r\nCurrent Floor: " + (current_floor + 1) + "\r\nDirection: " + (moving_up ? "Up" : "Down");
    }
}
