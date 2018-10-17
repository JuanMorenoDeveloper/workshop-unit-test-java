package uy.gub.bps.calculator;

import java.util.Scanner;

public class StringCalculator {

  public int add(String string) {
    final String COMMA = ",";
    final String LINE_FEED = "\n";
    if (string.isEmpty()) {
      return 0;
    }
    if (isNumeric(string) && (!hasDelimiter(string, COMMA) || !hasDelimiter(string, LINE_FEED))) {
      return Integer.parseInt(string);
    }
    return getSumByDelimiter(string, COMMA) + getSumByDelimiter(string, LINE_FEED);
  }

  private int getSumByDelimiter(String input, String delimiter) {
    Scanner scanner = new Scanner(input).useDelimiter(delimiter);
    int sum = 0;
    while (scanner.hasNext()) {
      String value = scanner.next();
      if (isNumeric(value)) {
        sum += Integer.parseInt(value);
      }
    }
    return sum;
  }

  private boolean isNumeric(String input) {
    try {
      Integer.parseInt(input);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

  private boolean hasDelimiter(String input, String delimiter) {
    return input.contains(delimiter);
  }
}
