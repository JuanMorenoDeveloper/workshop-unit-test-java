package uy.gub.bps.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Implementación de la kata String Calculator.
 */

public class StringCalculator {

  /**
   * Limite máximo para suma.
   */
  private static final int LIMIT = 1000;

  /**
   * Devuelve la suma de los números en la cadena string criterio.
   *
   * @param string cadena de entrada
   * @return Resultado suma
   * @throws IllegalArgumentException si hay números negativos
   */
  public int add(final String string) throws IllegalArgumentException {
    final int result;
    if (string.isEmpty()) {
      result = 0;
    } else if (isNumeric(string) && !hasDelimiters(string, new String[]{",", "\n"})) {
      result = Integer.parseInt(string);
    } else {
      result = getSumByDelimiters(string);
    }
    return result;
  }

  private int getSumByDelimiters(final String input) throws IllegalArgumentException {
    final String delimiters = extractDelimiters(input);
    final List<Integer> numbers = Arrays.stream(input.split(delimiters))
        .filter(this::isNumeric).map(Integer::parseInt).collect(Collectors.toList());
    final List<Integer> negatives = numbers.stream().filter(num -> num < 0).collect(
        Collectors.toList());
    if (negatives.isEmpty()) {
      return numbers.stream().mapToInt(Integer::intValue).filter(this::isInRange).sum();
    }
    throw new IllegalArgumentException(
        String.format("negativos no están permitidos %s", negatives.toString()));
  }

  private boolean isInRange(final int num) {
    return num < LIMIT;
  }

  private String extractDelimiters(final String input) {
    final StringBuilder delimiters = new StringBuilder("\n|,");
    if (hasCustomDelimiter(input)) {
      extractCustomDelimiter(input, delimiters);
    }
    if (hasLongDelimiter(input)) {
      extractLongDelimiter(input, delimiters);
    }
    return delimiters.toString();
  }

  private void extractLongDelimiter(final String input, final StringBuilder delimiters) {
    final String[] customDelimiters = extractLongDelimiters(input);
    for (final String customDelimiter : customDelimiters) {
      delimiters.append('|').append(customDelimiter);
    }
  }

  private void extractCustomDelimiter(final String input, final StringBuilder delimiters) {
    delimiters.append('|').append(extractCustomDelimiter(input));
  }

  private String extractCustomDelimiter(final String input) {
    final Scanner scanner = new Scanner(input).useDelimiter("//|\\n");
    return Pattern.quote(scanner.next());
  }

  private boolean hasCustomDelimiter(final String input) {
    return input.contains("//");
  }

  private boolean hasLongDelimiter(final String input) {
    return input.contains("//[");
  }

  private String[] extractLongDelimiters(final String input) {
    final Pattern pattern = Pattern.compile("\\[(.*?)\\]");
    final Matcher matcher = pattern.matcher(Pattern.quote(input));
    final List<String> delimiters = new ArrayList<>();
    while (matcher.find()) {
      delimiters.add(Pattern.quote(matcher.group(1)));
    }
    return delimiters.toArray(new String[0]);
  }

  private boolean isNumeric(final String input) {
    boolean isNumber = true;
    try {
      Integer.parseInt(input);
    } catch (NumberFormatException e) {
      isNumber = false;
    }
    return isNumber;
  }

  private boolean hasDelimiters(final String input, final String... delimiters) {
    boolean foundDelimiter = false;
    for (int i = 0; i < delimiters.length && !foundDelimiter; i++) {
      foundDelimiter = input.contains(delimiters[i]);
    }
    return foundDelimiter;
  }
}
