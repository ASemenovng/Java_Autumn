package intreface.messenger;

public class Telegram implements Messenger {

  @Override
  public void sendMessage() {
    System.out.println("Отправляем сообщение в Telegram!");
  }

  @Override
  public void getMessage() {
    System.out.println("Читаем сообщение в Telegram!");
  }
}

