package org.example.serr;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationExample {

  public static final String PATH = "./src/main/java/org/example/serr";
  public static void main(String[] args) {
    Person person = new Person("Alice", 30, "secure");

    // Сериализация объекта
    try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(PATH + "/person.ser"))) {
      outputStream.writeObject(person);
      System.out.println("Объект успешно сериализован и сохранен в файл.");
    } catch (IOException e) {
      System.out.println("Произошла ошибка при сериализации объекта: " + e.getMessage());
    }

    // Десериализация объекта
    try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(PATH + "/person.ser"))) {
      Person deserializedPerson = (Person) inputStream.readObject();
      System.out.println("Прочитанный объект: " + deserializedPerson);
    } catch (IOException | ClassNotFoundException e) {
      System.out.println("Произошла ошибка при десериализации объекта: " + e.getMessage());
    }
  }
}
