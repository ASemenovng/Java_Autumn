package org.example.ls12;

import java.util.List;

public class University {

  private List<Student> students;

  public University(List<Student> students) {
    this.students = students;
  }

  public List<Student> getStudents() {
    return students;
  }

  public void setStudents(List<Student> students) {
    this.students = students;
  }
}
