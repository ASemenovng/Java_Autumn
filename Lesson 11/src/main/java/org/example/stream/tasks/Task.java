package org.example.stream.tasks;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.example.stream.data.Book;
import org.example.stream.data.EmailAddress;
import org.example.stream.data.Library;
import org.example.stream.data.Reader;

public class Task {

  // Получить список всех книг библиотеки, отсортированных по году издания.
  public static List<Book> listSort(Library library) {
    library.getBooks().sort(comparing(Book::getIssueYear));
    return library.getBooks();
  }

  public static List<Book> streamSort(Library library) {
    return library.getBooks().stream()
        .sorted(comparing(Book::getIssueYear))
        .toList();
  }



  // Требуется создать список рассылки (объекты типа EmailAddress) из адресов всех читателей библиотеки.
  // При этом флаг согласия на рассылку учитывать не будем: библиотека закрывается, так что хотим оповестить всех.
  public static List<EmailAddress> listMap(Library library) {
    List<EmailAddress> addresses = new ArrayList<>();
    for (Reader reader : library.getReaders()) {
      addresses.add(new EmailAddress(reader.getEmail()));
    }
    return addresses;
  }

  public static List<EmailAddress> streamMap(Library library) {
    return library.getReaders().stream()
        .map(Reader::getEmail)
        .map(EmailAddress::new)
        .toList();
  }



  // Снова нужно получить список рассылки. Но на этот раз включаем в него только адреса читателей,
  // которые согласились на рассылку. Дополнительно нужно проверить, что читатель взял из библиотеки
  // больше одной книги.
  public static List<EmailAddress> listFilter(Library library) {
    List<EmailAddress> addresses = new ArrayList<>();
    for (Reader reader : library.getReaders()){
      if (reader.getBooks().size() > 1 && reader.isSubscriber())
        addresses.add(new EmailAddress(reader.getEmail()));
    }
    return addresses;
  }

  public static List<EmailAddress> streamFilter(Library library) {
    return library.getReaders().stream()
        .filter(Reader::isSubscriber)
        .filter(reader -> reader.getBooks().size() > 1)
        .map(Reader::getEmail).map(EmailAddress::new)
        .toList();
  }



  // Получить список всех книг, взятых читателями. Список не должен содержать дубликатов
  // (книг одного автора, с одинаковым названием и годом издания).
  public static Set<Book> setFlatMap(Library library) {
    Set<Book> result = new HashSet<>();
    for (Reader reader : library.getReaders()) {
      result.addAll(reader.getBooks());
    }
    return result;
  }

  public static Set<Book> streamFlatMap(Library library) {
    return library.getReaders().stream()
        .flatMap(reader -> reader.getBooks().stream())
        .collect(Collectors.toSet());
  }



  // Проверить, взял ли кто-то из читателей библиотеки какие-нибудь книги Оруэлла.
  public static boolean listAnyMatch(Library library) {
    for (Reader reader : library.getReaders()){
      for (Book book :reader.getBooks()){
        if ("Оруэлл".equals(book.getAuthor())){
          return true;
        }
      }
    }
    return false;
  }

  public static boolean streamAnyMatch(Library library) {
    return library.getReaders().stream()
        .flatMap(reader -> reader.getBooks().stream())
        .anyMatch(book -> "Оруэлл".equals(book.getAuthor()));
  }



  // Узнать наибольшее число книг, которое сейчас на руках у читателя.
  public static int listReduce(Library library) {
    int max = 0;
    for (Reader reader : library.getReaders()){
      if (reader.getBooks().size() > max)
        max = reader.getBooks().size();
    }
    return max;
  }

  public static int streamReduce(Library library) {
    return library.getReaders().stream()
        .map(reader -> reader.getBooks().size())
        .reduce(0, (max, size) -> size > max ? size : max);
  }

  // конкретно тут можно (и желательно без reduce просто методом max)
  public static int streamMax(Library library) {
    return library.getReaders().stream()
        .map(reader -> reader.getBooks().size())
        .max((max, size) -> size > max ? size : max)
        .get();
  }



  // Теперь нужно не просто отправить письма всем, кто согласился на рассылку,
  // будем рассылать разные тексты двум группам:
  //    1. тем, у кого взято меньше двух книг, просто расскажем о новинках библиотеки;
  //    2. тем, у кого две книги и больше, напомним о том, что их нужно вернуть в срок.
  // То есть надо написать метод, который вернёт два списка адресов (типа EmailAddress):
  //    1. с пометкой OK — если книг не больше двух
  //    2. TOO_MUCH — если их две и больше.
  // Порядок групп не важен.
  public static Map<String, List<EmailAddress>> mapCollector(Library library) {
    Map<String, List<EmailAddress>> result = new HashMap<>();
    for (Reader reader : library.getReaders()) {
      if (reader.isSubscriber()) {
        if (reader.getBooks().size() > 2) {
          if (!result.containsKey("TOO_MUCH")) {
            result.put("TOO_MUCH", new ArrayList<>());
          }
          result.get("TOO_MUCH").add(new EmailAddress(reader.getEmail()));
        } else {
          if (!result.containsKey("OK")) {
            result.put("OK", new ArrayList<>());
          }
          result.get("OK").add(new EmailAddress(reader.getEmail()));
        }
      }
    }
    return result;
  }

  public static Map<String, List<EmailAddress>> streamCollector(Library library) {
    return library.getReaders().stream()
        .filter(Reader::isSubscriber)
        .collect(groupingBy(r -> r.getBooks().size() > 2 ? "TOO_MUCH" : "OK",
            mapping(r -> new EmailAddress(r.getEmail()), toList())));
  }
}
