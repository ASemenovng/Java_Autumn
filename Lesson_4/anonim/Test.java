package anonim;

import anonim.monitoring.ErrorMonitoring;
import anonim.monitoring.GeneralMonitoring;
import anonim.monitoring.SecurityMonitoring;

public class Test {

  public static void main(String[] args) {
    GeneralMonitoring general = new GeneralMonitoring();
    ErrorMonitoring error = new ErrorMonitoring();
    SecurityMonitoring security = new SecurityMonitoring();

    general.start();
    error.start();
    security.start();

  }
}
