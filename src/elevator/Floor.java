package elevator;
/* @author Daniel Grewal
*  @date   02/11/15
*/
public class Floor {
  private int floor_num;
  private int passengers_up;
  private int passengers_down;
  
  
  public Floor(int floor) {
    floor_num = floor;
    passengers_up = 0;
    passengers_down = 0;
  }
  
  
  public void unloadPassengers(Elevator elev, boolean going_up) {
    int passengers = going_up?passengers_up:passengers_down;
    for (; passengers>0; passengers--) {
      try {
        elev.boardPassenger(1);
      }
      catch (ElevatorFullException e) {
        System.out.println(e.getMessage());
        elev.registerRequest(floor_num, going_up);
        break;
      }
    }
    
    if (going_up) {
      passengers_up = passengers;
    }
    else {
      passengers_down = passengers;
    }
  }
  
  /**
  * Testing function
  */
  public void addPassenger(boolean going_up) {
    if (going_up) {
      passengers_up++;
    }
    else {
      passengers_down++;
    }
  }
}