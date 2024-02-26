package reflection.orm;

@Table(name = "users")
class User {

  @Column(name = "id")
  private int id;

  @Column(name = "name")
  private String name;

  public User(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}