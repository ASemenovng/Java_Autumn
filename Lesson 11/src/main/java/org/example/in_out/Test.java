package org.example.in_out;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class Test {

  public static final String INPUT_PATH = "./src/main/java/org/example/in_out/source.txt";
  public static final String OUTPUT_PATH = "./src/main/java/org/example/in_out/result.txt";

  public static void main(String[] args) throws IOException {
    // 1
    try (InputStream inStream = new FileInputStream(INPUT_PATH);
        OutputStream outStream = new FileOutputStream(OUTPUT_PATH)) {
      while (inStream.available() > 0) {
        byte[] data = new byte[11];
        int res = inStream.read(data);
        System.out.println("res: " + res);
        System.out.println("data: " + Arrays.toString(data));
        int dataRead = inStream.read(); //читаем один байт из потока для чтения
        outStream.write(dataRead); //записываем прочитанный байт в другой поток.
      }
    }

    // 2
    byte[] b = new byte[10];
    readFully(System.in, b);
    System.out.println(Arrays.toString(b));

    byte[] bw = new byte[11];
    readFully(new FileInputStream(INPUT_PATH), bw);
    System.out.println(Arrays.toString(bw));

    // 3
    Scanner scanner = new Scanner(System.in);

    // 4
    try(InputStream in = new URL("http://www.google.com").openStream();) {
      System.out.println(new String(in.readAllBytes(), UTF_8));
    }

    try(HttpClient client = HttpClient.newHttpClient()) {
      HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create("http://www.google.com"))
          .build();
      client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
          .thenApply(HttpResponse::body)
          .thenAccept(System.out::println)
          .join();

      var path = Paths.get("a/b/c");
      for (var p : path) {
        System.out.println(p);
      }
    }

  }

  static void readFully(InputStream in, byte[] b) throws IOException {
    int offset = 0;
    while (offset < b.length) {
      int count = in.read(b, offset, b.length - offset);
      System.out.println("count: " + count);
      if (count == -1) {
        throw new IOException("Stream size less than " + b.length);
      }
      offset += count;
    }
  }

}
