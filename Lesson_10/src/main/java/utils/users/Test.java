package utils.users;

import static java.util.stream.Collectors.flatMapping;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Test {

  public static void main(String[] args) {

  }

  static Map<User, List<Department>> departsByChief(Company company) {
    return company
        .departments()
        .collect(groupingBy(Department::chief));
  }

  static Map<User, List<String>> departsNamesByChief(Company company) {
    return company
        .departments()
        .collect(groupingBy(Department::chief,
            mapping(Department::title, toList())));
  }

  static Map<User, Set<User>> func(Company company) {
    return company
        .departments()
        .collect(groupingBy(Department::chief,
            flatMapping(Department::users, toSet())));
  }
}
