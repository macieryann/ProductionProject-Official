/*
 * NAME: Macie Ryan
 * CLASS: COP 3003
 * FILE: ItemType.java
 *
 * Defines the ItemType enum.
 */
public enum ItemType {
  AUDIO ("AU"),
  VISUAL ("VI"),
  AUDIO_MOBILE ("AM"),
  VISUAL_MOBILE ("VM");

  public final String code;

  ItemType(String code) {
    this.code = code;
  }
}
