package intreface.messenger;

public class WhatsApp implements Messenger {

  public void sendMessage() {
    System.out.println("Отправляем сообщение в WhatsApp!");
  }

  public void getMessage() {
    System.out.println("Читаем сообщение в WhatsApp!");
  }
}
