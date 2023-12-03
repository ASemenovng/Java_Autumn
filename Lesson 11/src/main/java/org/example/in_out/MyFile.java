package org.example.in_out;

import java.io.ByteArrayOutputStream;
import java.lang.ref.Cleaner;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class MyFile implements AutoCloseable{

  private static final Cleaner CLEANER = Cleaner.create();
  private final int fd;
  private final Cleaner.Cleanable cleanable;


  public MyFile(int _fd) {
    fd = _fd;
    cleanable = CLEANER.register(this, () -> free(_fd));
  }

  public void read() {
    System.out.println("Reading from file desc: " + fd);
  }

  private static void free(int fd) {
    System.out.println("Freeing file desc: " + fd);
  }


  @Override
  public void close() {
    cleanable.clean();
  }

  public static void main(String[] args) throws InterruptedException {
    readFile();
    System.out.println("GC");
    System.gc();
    System.out.println("Sleep");
    Thread.sleep(1000);
    System.out.println("Done");

    //
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    baos.write(0x01);
    // baos.write(new byte[]{0x02});
    baos.write(0x03);
    byte[] res = baos.toByteArray();
    System.out.println(Arrays.toString(res));
  }

  private static void readFile() {
    try(MyFile mf = new MyFile(1)) {
      mf.read();
    }
  }

//  public static <T, R> Optional<R> processElements(List<T> elements, Predicate<T> predicate, Function<T, R> mapper, Function<List<R>, R> reduceFunction) {
//    return elements.stream()
//        .filter(predicate)
//        .map(mapper)
//        .reduce(reduceFunction);
//  }
}
