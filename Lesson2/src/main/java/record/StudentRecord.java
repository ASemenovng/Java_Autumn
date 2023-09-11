package record;

// аналогично классу Student
public record StudentRecord(String name, CourseType courseType) {
  public StudentRecord {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException();
    }
  }

  public StudentRecord(String name){
    this(name, CourseType.MATH);
  }

}

