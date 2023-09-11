package record;

import java.util.Objects;

public class Student {
  private final String name;
  private final CourseType courseType;

  public Student(String name, CourseType courseType) {
    this.name = name;
    this.courseType = courseType;
  }

  public String getName() {
    return name;
  }

  public CourseType getCourseType() {
    return courseType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Student student = (Student) o;
    return name.equals(student.name) && courseType == student.courseType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, courseType);
  }

  @Override
  public String toString() {
    return "Student{" +
        "name='" + name + '\'' +
        ", courseType=" + courseType +
        '}';
  }
}