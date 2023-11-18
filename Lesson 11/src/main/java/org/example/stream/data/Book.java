package org.example.stream.data;

import java.util.Objects;

public class Book {
  private String author; //Автор
  private String name;	//Название
  private Integer issueYear; //Год издания

  public Book(String author, String name, Integer issueYear)   {
    this.author = author;
    this.name = name;
    this.issueYear = issueYear;
  }

  public String getAuthor() {
    return author;
  }

  public String getName() {
    return name;
  }

  public Integer getIssueYear() {
    return issueYear;
  }

  @Override
  public String toString() {
    return "Book{" +
        "author='" + author + '\'' +
        ", name='" + name + '\'' +
        ", issueYear=" + issueYear +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Book book = (Book) o;
    return author.equals(book.author) &&
        name.equals(book.name) &&
        issueYear.equals(book.issueYear);
  }

  @Override
  public int hashCode() {
    return Objects.hash(author, name, issueYear);
  }
}
