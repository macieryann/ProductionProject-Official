/*
 * NAME: Macie Ryan
 * CLASS: COP 3003
 * FILE: MoviePlayer.java
 *
 * Defines the MoviePlayer class.
 */

/**
 * Represents an movie player product with multimedia controls.
 * @author Macie Ryan
 */
public class MoviePlayer extends Product implements MultimediaControl {

  /**
   * The screen utilized by the product
   */
  Screen screen;

  /**
   * The monitor type utilized by this product
   */
  MonitorType monitorType;

  /**
   * Creates an movie player product.
   * @param name - the name
   * @param manufacturer - the manufacturer name
   * @param screen - the screen
   * @param monitorType - the monitor type
   */
  public MoviePlayer(String name, String manufacturer, Screen screen, MonitorType monitorType) {
    super(name, manufacturer, ItemType.VISUAL);
    this.screen = screen;
    this.monitorType = monitorType;
  }

  /**
   * One multimedia control of the movie player
   * Prints "Playing movie" to the console.
   */
  public void play(){
    System.out.println("Playing movie");
  }

  /**
   * One multimedia control of the audio player
   * Prints "Stopping movie" to the console.
   */
  public void stop(){
    System.out.println("Stopping movie");
  }

  /**
   * One multimedia control of the audio player
   * Prints "Previous movie" to the console.
   */
  public void previous(){
    System.out.println("Previous movie");
  }

  /**
   * One multimedia control of the audio player
   * Prints "Next movie" to the console.
   */
  public void next(){
    System.out.println("Next movie");
  }

  /**
   * Displays a string for this movie player
   * @return product name, manufacturer, and item type from Product and
   * screen and monitor type each on their own lines
   */
  public String toString(){
    return super.toString()
        + "\n" + screen.toString()
        + "\nMonitor Type: " + monitorType;
  }
}