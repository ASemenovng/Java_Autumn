package org.example.tmp.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationExample {

  public static final String IO_PATH = "./src/main/java/org/example/tmp/serialization";

  public static void main(String[] args) {
    Person person = new Person("Alice", 30, "secure");

    try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(IO_PATH + "/person.serr"))) {
      outputStream.writeObject(person);
      System.out.println("Данные успешно сериализованы");
    } catch (IOException e) {
      System.out.println(e);
    }

    try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(IO_PATH + "/person.serr"))) {
      Person person1 = (Person) inputStream.readObject();
      System.out.println(person1);
    } catch (IOException | ClassNotFoundException e) {
      System.out.println(e);
    }
  }
}
