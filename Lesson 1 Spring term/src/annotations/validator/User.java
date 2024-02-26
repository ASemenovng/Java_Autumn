package annotations.validator;

public class User {
  @NotNull
  private String name;

  @Size(min = 2, max = 30)
  private String username;

  public User() {}

  public User(String name, String username) {
    this.name = name;
    this.username = username;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}