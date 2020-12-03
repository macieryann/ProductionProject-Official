/*
 * NAME: Macie Ryan
 * CLASS: COP 3003
 * FILE: MoviePlayer.java
 *
 * Defines the MoviePlayer class.
 */

/**
 * Represents an movie player product with multimedia controls.
 *
 * @author Macie Ryan
 */
public class MoviePlayer extends Product implements MultimediaControl {

  /**
   * The screen utilized by the product.
   */
  Screen screen;

  /**
   * The monitor type utilized by this product.
   */
  MonitorType monitorType;

  /**
   * Creates an movie player product.
   *
   * @param name         - the name
   * @param manufacturer - the manufacturer name
   * @param screen       - the screen
   * @param monitorType  - the monitor type
   */
  public MoviePlayer(String name, String manufacturer, Screen screen, MonitorType monitorType) {
    super(name, manufacturer, ItemType.VISUAL);
    this.screen = screen;
    this.monitorType = monitorType;
  }

  /**
   * Displays a string for this movie player.
   *
   * @return product name, manufacturer, and item type from Product
   *     and screen and monitor type each on their own lines
   */
  public String toString() {
    return super.toString()
        + "\n" + screen.toString()
        + "\nMonitor Type: " + monitorType;
  }
}