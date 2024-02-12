package collections;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserStatusTracker {

  private static final ConcurrentHashMap<String, Boolean> onlineStatusMap = new ConcurrentHashMap<>();

  public static void setOnline(String userId) {
    onlineStatusMap.put(userId, true);
    System.out.println(userId + " is now online.");
  }

  public static void setOffline(String userId) {
    onlineStatusMap.put(userId, false);
    System.out.println(userId + " is now offline.");
  }

  public static Boolean isOnline(String userId) {
    return onlineStatusMap.getOrDefault(userId, false);
  }

  public static void main(String[] args) {
    Runnable user1OnlineTask = () -> setOnline("user1");
    Runnable user2OnlineTask = () -> setOnline("user2");
    Runnable user1OfflineTask = () -> setOffline("user1");
    Runnable checkUser1StatusTask = () -> System.out.println("Is user1 online? " + isOnline("user1"));
    Runnable checkUser2StatusTask = () -> System.out.println("Is user2 online? " + isOnline("user2"));

    ExecutorService executorService = Executors.newCachedThreadPool();

    // Имитация многопоточного изменения и запроса статусов
    executorService.execute(user1OnlineTask);
    executorService.execute(user2OnlineTask);
    executorService.execute(checkUser1StatusTask);
    executorService.execute(user1OfflineTask);
    executorService.execute(checkUser2StatusTask);

    executorService.shutdown();
  }
}


