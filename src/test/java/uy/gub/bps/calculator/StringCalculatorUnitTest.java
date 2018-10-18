package uy.gub.bps.calculator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class StringCalculatorUnitTest {

  @Test
  public void givenStringVacio_whenAdd_thenRetornoCero() throws Exception {
    StringCalculator calc = new StringCalculator();

    int result = calc.add("");

    assertThat(result).isEqualTo(0);
  }

  @Test
  public void givenUnNumero_whenAdd_thenRetornoMismoNumero() throws Exception {
    StringCalculator calc = new StringCalculator();

    int result = calc.add("5");

    assertThat(result).isEqualTo(5);
  }

  @Test
  public void givenDosNumeros_whenAdd_thenRetornoSuma() throws Exception {
    StringCalculator calc = new StringCalculator();

    int result = calc.add("5,10");

    assertThat(result).isEqualTo(15);
  }

  @ParameterizedTest
  @CsvSource(value = {"5,10,5;20", "4,3;7", "8,2;10", "4,5;9"}, delimiter = ';')
  public void givenListaNumeros_whenAdd_thenRetornoSuma(String input, int output) throws Exception {
    StringCalculator calc = new StringCalculator();

    int result = calc.add(input);

    assertThat(result).isEqualTo(output);
  }

  @Test
  public void givenDosNumerosConSaltoDeLinea_whenAdd_thenRetornoSuma() throws Exception {
    StringCalculator calc = new StringCalculator();

    int result = calc.add("5\n10");

    assertThat(result).isEqualTo(15);
  }

  @Test
  public void givenTresNumerosConSaltoDeLinea_whenAdd_thenRetornoSuma() throws Exception {
    StringCalculator calc = new StringCalculator();

    int result1 = calc.add("1\n2,3");
    int result2 = calc.add("1,2\n3");

    assertSoftly(softly -> {
      softly.assertThat(result1).isEqualTo(6);
      softly.assertThat(result2).isEqualTo(6);
    });
  }

  @Test
  public void givenTresNumerosWithCustomDelimiter_whenAdd_thenRetornoSuma() throws Exception {
    StringCalculator calc = new StringCalculator();

    int result1 = calc.add("//;\n1\n2;5");
    int result2 = calc.add("//\\:\n1:2\n4");

    assertSoftly(softly -> {
      softly.assertThat(result1).isEqualTo(8);
      softly.assertThat(result2).isEqualTo(7);
    });
  }

  @ParameterizedTest
  @CsvSource(value = {"5,-10,-5;negativos no están permitidos [-10, -5]",
      "4,-3;negativos no están permitidos [-3]",
      "8,-2;negativos no están permitidos [-2]"}, delimiter = ';')
  public void givenNumeroNegativo_whenAdd_thenThrowException(String input, String message) {
    StringCalculator calc = new StringCalculator();

    Throwable thrown = catchThrowable(() -> calc.add(input));

    assertThat(thrown).hasMessage(message);
  }
}
