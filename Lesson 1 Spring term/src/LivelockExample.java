public class LivelockExample {
  static class Worker {
    private boolean isActive;

    public Worker(boolean isActive) {
      this.isActive = isActive;
    }

    public synchronized void work(CommonResource resource, Worker otherWorker) {
      while (isActive) {
        // Проверка, занят ли ресурс другим работником
        if (resource.getOwner() != this) {
          try {
            wait(10);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          continue;
        }

        // Если другой работник также активен, передаем ему ресурс
        if (otherWorker.isActive) {
          System.out.println(Thread.currentThread().getName() + ": передаю ресурс другому потоку");
          resource.setOwner(otherWorker);
          continue;
        }

        // Текущий поток выполняет работу (условно)
        System.out.println(Thread.currentThread().getName() + ": работаю с ресурсом");
        isActive = false;
        resource.setOwner(otherWorker);
      }
    }
  }

  static class CommonResource {
    private Worker owner;

    public CommonResource(Worker owner) {
      this.owner = owner;
    }

    public Worker getOwner() {
      return owner;
    }

    public synchronized void setOwner(Worker owner) {
      this.owner = owner;
    }
  }

  public static void main(String[] args) {
    final Worker worker1 = new Worker(true);
    final Worker worker2 = new Worker(true);
    final CommonResource commonResource = new CommonResource(worker1);

    new Thread(() -> worker1.work(commonResource, worker2), "Worker 1").start();
    new Thread(() -> worker2.work(commonResource, worker1), "Worker 2").start();
  }
}
