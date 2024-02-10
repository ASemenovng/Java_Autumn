package threads;

public class CalcSquare extends Thread{

  final int arg;

  int res;


  public CalcSquare(int arg) {
    this.arg = arg;
  }

  @Override
  public void run() {
    // smth
    res = arg * arg;
  }

  public int getRes() {
    return res;
  }
}
