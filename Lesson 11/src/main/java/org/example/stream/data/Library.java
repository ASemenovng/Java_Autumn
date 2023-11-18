package org.example.stream.data;

import java.util.ArrayList;
import java.util.List;

public class Library {

  private List<Book> books;
  private List<Reader> readers;

  public Library() {
    init();
  }

  private void init() {
    books = new ArrayList<>();
    books.add(new Book("Оруэлл", "1984", 2021));
    //и так далее для других книг

    readers = new ArrayList<>();
    readers.add(new Reader("Иванов Иван Иванович", "ivanov.email@test.ru", true));
    //и так далее для других читателей

    readers.get(0).getBooks().add(books.get(1));
    //и так далее для других читателей и взятых книг
  }

  public List<Book> getBooks() {
    return books;
  }

  public List<Reader> getReaders() {
    return readers;
  }
}