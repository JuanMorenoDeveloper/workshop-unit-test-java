package uy.gub.bps.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StringCalculator {

  public int add(String string) throws Exception {
    if (string.isEmpty()) {
      return 0;
    }
    if (isNumeric(string) && !hasDelimiters(string, new String[]{",", "\n"})) {
      return Integer.parseInt(string);
    }
    return getSumByDelimiters(string);
  }

  private int getSumByDelimiters(String input) throws Exception {
    int sum = 0;
    StringBuilder delimiters = new StringBuilder("\n|,");
    if (hasCustomDelimiter(input)) {
      delimiters.append("|");
      delimiters.append(extractCustomDelimiter(input));
    }
    Scanner scanner = new Scanner(input).useDelimiter(delimiters.toString());
    List<Integer> negativeInputs = new ArrayList<>();
    while (scanner.hasNext()) {
      String value = scanner.next();
      if (isNumeric(value)) {
        int num = Integer.parseInt(value);
        if (num < 0) {
          negativeInputs.add(num);
        }
        sum += num;
      }
    }
    if (!negativeInputs.isEmpty()) {
      throw new Exception(String.format("negativos no están permitidos %s", negativeInputs.toString()));
    }
    return sum;
  }

  private String extractCustomDelimiter(String input) {
    Scanner scanner = new Scanner(input).useDelimiter("//|\\n");
    return scanner.next();
  }

  private boolean hasCustomDelimiter(String input) {
    return input.contains("//");
  }

  private boolean isNumeric(String input) {
    try {
      Integer.parseInt(input);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

  private boolean hasDelimiters(String input, String[] delimiters) {
    for (String delimiter : delimiters) {
      return input.contains(delimiter);
    }
    return false;
  }
}
