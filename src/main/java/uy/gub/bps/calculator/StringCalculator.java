package uy.gub.bps.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    StringBuilder delimiters = extractDelimiters(input);
    Scanner scanner = new Scanner(input).useDelimiter(delimiters.toString());
    List<Integer> negativeInputs = new ArrayList<>();
    while (scanner.hasNext()) {
      String value = scanner.next();
      if (isNumeric(value)) {
        int num = Integer.parseInt(value);
        if (num < 0) {
          negativeInputs.add(num);
        }
        if (num < 1000) {
          sum += num;
        }
      }
    }
    if (!negativeInputs.isEmpty()) {
      throw new Exception(
          String.format("negativos no están permitidos %s", negativeInputs.toString()));
    }
    return sum;
  }

  private StringBuilder extractDelimiters(String input) {
    StringBuilder delimiters = new StringBuilder("\n|,");
    if (hasCustomDelimiter(input)) {
      delimiters.append("|");
      delimiters.append(extractCustomDelimiter(input));
    }
    if (hasLongDelimiter(input)) {
      String customDelimiters[] = extractLongDelimiters(input);
      for (int i = 0; i < customDelimiters.length; i++) {
        delimiters.append("|");
        delimiters.append(customDelimiters[i]);
      }
    }
    return delimiters;
  }

  private String extractCustomDelimiter(String input) {
    Scanner scanner = new Scanner(input).useDelimiter("//|\\n");
    return Pattern.quote(scanner.next());
  }

  private boolean hasCustomDelimiter(String input) {
    return input.contains("//");
  }

  private boolean hasLongDelimiter(String input) {
    return input.contains("//[");
  }

  private String[] extractLongDelimiters(String input) {
    Pattern p = Pattern.compile("\\[(.*?)\\]");
    Matcher m = p.matcher(Pattern.quote(input));
    List<String> delimiters = new ArrayList<>();
    while (m.find()) {
      delimiters.add(Pattern.quote(m.group(1)));
    }
    return delimiters.toArray(new String[delimiters.size()]);
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
