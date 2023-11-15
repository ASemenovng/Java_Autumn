package utils.users;

import java.util.stream.Stream;

public interface Department {

  String title();
  User chief();
  Stream<User> users();
}
