package problems;

public class RaceCondition {

  static class GlassOfWater {
    private int volume = 0;

    public int getVolume() {
      return volume;
    }
    public void setVolume(int volume) {
      this.volume = volume;
    }
  }


  public static void main(String[] args) throws InterruptedException {
    GlassOfWater glassOfWater = new GlassOfWater();
    Thread thread1 = new Thread(() -> {
      for (int i = 0; i < 1000; i++) {
        glassOfWater.setVolume(glassOfWater.getVolume() + 1);
      }
    });
    Thread thread2 = new Thread(() -> {
      for (int i = 0; i < 1000; i++) {
        glassOfWater.setVolume(glassOfWater.getVolume() + 1);
      }
    });
    thread1.start();
    thread2.start();
    thread1.join();
    thread2.join();
    System.out.println("Volume is " + glassOfWater.getVolume());
  }

}
