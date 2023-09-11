package record;

import java.util.Comparator;
import java.util.List;

public class Test {

  public static void main(String[] args) {
    record Vector(int x, int y) {

      // ошибка
      void test() {
        // System.out.println(Arrays.toString(args));
      }
    }
  }

  // можно record создавать локально
  public static List<Student> findTreeStudentsByAlphabet(List<Student> students) {
    record CourseTypeToFirstLetter(Student student, char firstLetter) {}

    return students.stream()
        .map(st -> new CourseTypeToFirstLetter(st, st.getName().charAt(0)))
        .sorted((Comparator.comparing(CourseTypeToFirstLetter::firstLetter)))
        .map(CourseTypeToFirstLetter::student)
        .limit(3)
        .toList();
  }

  // локально можно создавать не только record
  void test() {
    enum Letters {A, B, C}
  }

}
