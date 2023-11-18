package org.example.stream.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Reader {
  private String fio; //ФИО
  private String email; //электронный адрес
  private boolean subscriber; //флаг согласия на рассылку
  private List<Book> books; //взятые книги

  public Reader(String fio, String email, boolean subscriber) {
    this.fio = fio;
    this.email = email;
    this.subscriber = subscriber;
    this.books = new ArrayList<>();
  }

  public boolean isSubscriber() {
    return subscriber;
  }

  public String getFio() {
    return fio;
  }

  public String getEmail() {
    return email;
  }

  public List<Book> getBooks() {
    return books;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Reader reader = (Reader) o;
    return fio.equals(reader.fio);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fio);
  }
}
