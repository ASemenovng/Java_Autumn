package enums;

public enum DayOfWeek {
  MONDAY("MONDAY"),
  TUESDAY("TUESDAY"),
  THURSDAY("THURSDAY"),
  FRIDAY("FRIDAY"),
  SATURDAY("SATURDAY"),
  SUNDAY("SUNDAY");

  private String text;

  DayOfWeek(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }
}
