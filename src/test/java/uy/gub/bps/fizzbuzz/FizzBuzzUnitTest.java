package uy.gub.bps.fizzbuzz;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

public class FizzBuzzUnitTest {

  @Test
  public void whenPrintZero_thenPrintNumber() {
    Printer printer = mock(Printer.class);
    FizzBuzz fizzBuzz = new FizzBuzz(1, printer);

    fizzBuzz.printResults();

    verify(printer, times(1)).print("0");
  }

  @Test
  public void givenRangeTo5_whenPrint_thenPrintFizzBuzzOnce() {
    Printer printer = mock(Printer.class);
    FizzBuzz fizzBuzz = new FizzBuzz(5, printer);

    fizzBuzz.printResults();

    verify(printer, times(1)).print("FizzBuzz");
  }

}
