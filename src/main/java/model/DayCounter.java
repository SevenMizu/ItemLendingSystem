package model;

/**
 * This class focuses on handling day counter.
 */
public class DayCounter {
  private int currentDate = 0;

  public DayCounter() {
    this.currentDate = 0;
  }

  public int getCurrentDate() {
    return currentDate;
  }

  public void advanceDate() {
    currentDate++;
  }
  
}
