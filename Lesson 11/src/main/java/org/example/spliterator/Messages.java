package org.example.spliterator;

import static org.example.spliterator.MessageRecord.SIZE;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Messages {

  private StringBuilder messages = new StringBuilder();

  public void addMessage(MessageRecord message) {
    messages.append(message);
  }

  public Stream<MessageRecord> stream() {
    return StreamSupport.stream(getSpliterator(), false);
  }

  public Spliterator<MessageRecord> getSpliterator() {
    class InnerSpliterator implements Spliterator<MessageRecord> {

      private int start;
      private int end;

      public InnerSpliterator(int start, int end) {
        // super();
        this.start = start;
        this.end = end;
      }

      @Override
      public boolean tryAdvance(Consumer<? super MessageRecord> action) {
        if (start >= end) {
          return false;
        }
        action.accept(MessageRecord.fromString(messages.substring(start * SIZE, (start + 1) * SIZE)));
        ++start;
        return true;
      }

      @Override
      public Spliterator<MessageRecord> trySplit() {
        if (end - start < 2) {
          return null;
        }
        int middle = (start + end) / 2;
        Spliterator<MessageRecord> newSplit = new InnerSpliterator(start, middle);
        start = middle;
        return newSplit;
      }

      @Override
      public long estimateSize() {
        return end - start;
      }

      @Override
      public int characteristics() {
        return SIZED | SUBSIZED | NONNULL;
      }
    }
    return new InnerSpliterator(0, messages.length() / 100);
  }
}
