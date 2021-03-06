/*
 * NAME: Macie Ryan
 * CLASS: COP 3003
 * FILE: Product.java
 *
 * Defines the Product class.
 */

/**
 * A representation of a screen.
 *
 * @author Macie Ryan
 */
public class Screen implements ScreenSpec {

  /**
   * The screen's resolution.
   */
  String resolution;

  /**
   * The screen's refresh rate.
   */
  int refreshRate;

  /**
   * The screen's response time.
   */
  int responseTime;

  /**
   * Creates a screen with the given resolution, refresh rate, and response time.
   *
   * @param resolution   - the resolution
   * @param refreshRate  - the refresh rate
   * @param responseTime - the response time
   */
  public Screen(String resolution, int refreshRate, int responseTime) {
    this.resolution = resolution;
    this.refreshRate = refreshRate;
    this.responseTime = responseTime;
  }

  /**
   * Displays a descriptive string for the screen.
   *
   * @return resolution, refresh rate, and response time each on their own line
   */
  public String toString() {
    return "Screen:"
        + "\nResolution: " + resolution
        + "\nRefresh rate: " + refreshRate
        + "\nResponse time: " + responseTime;
  }

}