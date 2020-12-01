/*
 * NAME: Macie Ryan
 * CLASS: COP 3003
 * FILE: MonitorType.java
 *
 * Defines the MonitorType enum.
 */
public enum MonitorType {
  LCD("LCD"),
  LED("LED");

  private final String code;

  MonitorType(String code) {
    this.code = code;
  }

  public String getCode(){
    return code;
  }
}