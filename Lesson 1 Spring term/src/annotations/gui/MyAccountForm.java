package annotations.gui;

@GUIForm(title = "Мой Учет", width = 400, height = 300)
public class MyAccountForm {
  @GUITextField(label = "Имя пользователя: ")
  private String username;

  @GUITextField(label = "Пароль: ")
  private String password;

  public MyAccountForm() {}


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
