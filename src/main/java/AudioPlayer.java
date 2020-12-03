/*
 * NAME: Macie Ryan
 * CLASS: COP 3003
 * FILE: AudioPlayer.java
 *
 * Defines the AudioPlayer class.
 */

/**
 * Represents an audio player product with multimedia controls.
 *
 * @author Macie Ryan
 */
public class AudioPlayer extends Product implements MultimediaControl {

  /**
   * The audio formats supported by this audio player.
   */
  String supportedAudioFormats;

  /**
   * The playlist formats supported by this audio player.
   */
  String supportedPlaylistFormats;

  /**
   * Creates an audio player product.
   *
   * @param name                     - the name
   * @param manufacturer             - the manufacturer name
   * @param supportedAudioFormats    - supported audio formats
   * @param supportedPlaylistFormats - supported playlist formats
   */
  public AudioPlayer(String name, String manufacturer, String supportedAudioFormats,
      String supportedPlaylistFormats) {
    super(name, manufacturer, ItemType.AUDIO);
    this.supportedAudioFormats = supportedAudioFormats;
    this.supportedPlaylistFormats = supportedPlaylistFormats;
  }

  /**
   * Displays a string for this audio player.
   *
   * @return product name, manufacturer, item type,
   *     audio format, and playlist format each on their own lines.
   */
  public String toString() {
    return "Name: " + name
        + "\nManufacturer: " + manufacturer
        + "\nType: " + type
        + "\nSupported Audio Formats: " + supportedAudioFormats
        + "\nSupported Playlist Formats: " + supportedPlaylistFormats;
  }

}