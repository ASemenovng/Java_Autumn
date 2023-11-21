package org.example.stream.tasks;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.Comparator.naturalOrder;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Spliterator;
import java.util.stream.Collectors;
import org.example.stream.data.Book;
import org.example.stream.data.EmailAddress;
import org.example.stream.data.Library;
import org.example.stream.data.Reader;

public class Test {


  public static void main(String[] args) {
    List<Integer> list = List.of(1, 2, 3, 4, 5, 6);
    Spliterator<Integer> spliterator = list.spliterator();
    Spliterator<Integer> sp2 = spliterator.trySplit();
    System.out.println("spliterator:");
    spliterator.forEachRemaining(System.out::println);
    System.out.println("sp2:");
    sp2.forEachRemaining(System.out::println);
  }


  // Получить список всех книг библиотеки, отсортированных по году издания.
  // методы listSort() и streamSort()
  public static List<Book> listSort(Library libr) {
    List<Book> listBook = libr.getBooks();
    listBook.sort(comparing(Book::getIssueYear));
    return listBook;
  }

  public static List<Book> streamSort(Library libr) {
    return libr.getBooks().stream()
        .sorted(comparing(Book::getIssueYear))
        .toList();
  }

  // Требуется создать список рассылки (объекты типа EmailAddress) из адресов всех читателей библиотеки.
  // При этом флаг согласия на рассылку учитывать не будем: библиотека закрывается, так что хотим оповестить всех.
  // методы listMap() и streamMap()

  public static List<EmailAddress> listMap(Library lib) {
    List<EmailAddress> lst = new ArrayList<>();

    for (var reader : lib.getReaders()) {
      lst.add(new EmailAddress(reader.getEmail()));
    }
    return lst;
  }

  public static List<EmailAddress> streamMap(Library lib) {
    return lib.getReaders().stream()
        .map(Reader::getEmail)
        .map(EmailAddress::new)
        .toList();
  }

  // Снова нужно получить список рассылки. Но на этот раз включаем в него только адреса читателей,
  // которые согласились на рассылку. Дополнительно нужно проверить, что читатель взял из библиотеки
  // больше одной книги.
  // методы listFilter() и streamFilter()


  public static List<EmailAddress> listFilter(Library lib) {
    List<EmailAddress> lst = new ArrayList<>();

    for (var reader : lib.getReaders()) {
      if (reader.getBooks().size() > 1 && reader.isSubscriber()) {
        lst.add(new EmailAddress(reader.getEmail()));
      }
    }
    return lst;
  }

  public static List<EmailAddress> streamFilter(Library lib) {
    return lib.getReaders().stream()
        .filter(reader -> reader.getBooks().size() > 1)
        .filter(Reader::isSubscriber)
        .map(Reader::getEmail)
        .map(EmailAddress::new)
        .toList();
  }




  // Получить список всех книг, взятых читателями. Список не должен содержать дубликатов
  // (книг одного автора, с одинаковым названием и годом издания).
  // методы setFlatMap() и streamFlatMap()
  public static Set<Book> setFlatMap(Library lib) {
    Set<Book> books = new HashSet<>();
    for (var reader : lib.getReaders()) {
      books.addAll(reader.getBooks());
    }
    return books;
  }

  public static Set<Book> streamFlatMap(Library lib) {
    return lib.getReaders().stream()
        .flatMap(reader -> reader.getBooks().stream())
        .collect(toSet());
  }


  // Проверить, взял ли кто-то из читателей библиотеки какие-нибудь книги Оруэлла.
  // методы listAnyMatch() и streamAnyMatch()

  public static boolean listAnyMatch(Library lib) {
    for (var reader: lib.getReaders()) {
      for (var book: reader.getBooks()) {
        if ("Oruell".equals(book.getAuthor()))
          return true;
      }
    }
    return false;
  }

  public static boolean streamAnyMatch(Library lib) {
    return lib.getReaders().stream()
        .flatMap(reader -> reader.getBooks().stream())
        .anyMatch(book -> book.getAuthor().equals("Oruell"));
  }

  // Узнать наибольшее число книг, которое сейчас на руках у читателя.
  // методы listReduce(),  streamReduce() и streamMax()
  public static int listReduce(Library lib) {
    int max = 0;
    for (var reader : lib.getReaders()){
      max = Math.max(max, reader.getBooks().size());
    }
    return max;
  }

  public  static int streamReduce(Library lib) {
    return lib.getReaders().stream()
        .map(reader -> reader.getBooks().size())
        .reduce(0, Math::max);
  }

  public static int streamMax(Library lib) {
    return lib.getReaders().stream()
        .map(reader -> reader.getBooks().size())
        .max(naturalOrder())
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
  // методы mapCollector() и streamCollector()

  public static Map<String, List<EmailAddress>> mapCollector(Library library) {
    Map<String, List<EmailAddress>> result = new HashMap<>();
    for (var reader : library.getReaders()) {
      if (reader.isSubscriber()) {
        if (reader.getBooks().size() > 2) {
          if (!result.containsKey("TOO_MUCH")) {
            result.put("TOO_MUCH", new ArrayList<>());
          }
          result.get("TOO_MUCH").add(new EmailAddress(reader.getEmail()));
        } else {

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
