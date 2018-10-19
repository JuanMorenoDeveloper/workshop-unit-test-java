package uy.gub.bps.fizzbuzz;

import java.util.stream.IntStream;

/**
 * Implementación de ejemplo de la kata FizzBuzz.
 */
public class FizzBuzz {

  /**
   * Constante para de algoritmo FizzBuzz.
   */
  private static final int THREE = 3;
  /**
   * Constante para de algoritmo FizzBuzz.
   */
  private static final int FIVE = 5;
  /**
   * Limite de números a imprimir.
   */
  private final int limit;
  /**
   * Encargado de mostrar los resultados del algoritmo.
   */
  private final Printer printer;

  /**
   * Inicializa la clase.
   *
   * @param limit Límite de números a imprimir
   * @param printer Encargado de mostrar los resultados del algoritmo.
   */
  public FizzBuzz(final int limit, final Printer printer) {
    this.limit = limit;
    this.printer = printer;
  }

  /**
   * Calcula y muestra el resultado del algoritmo.
   */
  public void printResults() {
    IntStream.range(0, limit).mapToObj(this::calculate).forEach(printer::print);
  }

  private String calculate(final int number) {
    String result = String.valueOf(number);
    if (isDivisibleBy(number, THREE) && isDivisibleBy(number, FIVE)) {
      result = "FizzBuzz";
    }
    if (isDivisibleBy(number, THREE)) {
      result = "Fizz";
    }
    if (isDivisibleBy(number, FIVE)) {
      result = "Buzz";
    }
    return result;
  }

  private boolean isDivisibleBy(final int number, final int divisor) {
    return number % divisor == 0;
  }
}
