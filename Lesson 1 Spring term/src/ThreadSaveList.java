import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadSaveList {

  private final List<String> list = new ArrayList<>();
  private final ReentrantReadWriteLock listLock = new ReentrantReadWriteLock();

  String get(int i) {
    ReentrantReadWriteLock.ReadLock readLock = listLock.readLock();
    readLock.lock();
    try {
      return list.get(i);
    } finally {
      readLock.unlock();
    }
  }

  void add(String str) {
    ReentrantReadWriteLock.WriteLock writeLock = listLock.writeLock();
    writeLock.lock();
    try {
      list.add(str);
    } finally {
      writeLock.unlock();
    }
  }

}
