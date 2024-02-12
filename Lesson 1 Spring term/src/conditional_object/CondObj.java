package conditional_object;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CondObj {

  private ReentrantLock bankLock = new ReentrantLock();
  private Condition sufficientFunds = bankLock.newCondition();

  int[] accounts = new int[10_000];

  void moneyTransfer(int from, int to, int amount) throws InterruptedException {

    bankLock.lock();
    try {
      while (accounts[from] < amount) {
        sufficientFunds.await();
      }

      accounts[from] -= amount;
      accounts[to] += amount;

      sufficientFunds.signalAll();
    } finally {
      bankLock.unlock();
    }
  }
}
