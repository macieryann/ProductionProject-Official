//------------------------------------------
//  Name: Macie Ryan
//Course: COP 3003
//  File: ItemType.java
//------------------------------------------
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
