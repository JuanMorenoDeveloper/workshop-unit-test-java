package uy.gub.bps.calculator;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class StringCalculatorUnitTest {

  @Test
  public void givenStringVacio_whenAdd_thenRetornoCero() {
    StringCalculator calc = new StringCalculator();

    int result = calc.add("");

    assertThat(result).isEqualTo(0);
  }

  @Test
  public void givenUnNumero_whenAdd_thenRetornoMismoNumero() {
    StringCalculator calc = new StringCalculator();

    int result = calc.add("5");

    assertThat(result).isEqualTo(5);
  }

  @Test
  public void givenDosNumeros_whenAdd_thenRetornoSuma() {
    StringCalculator calc = new StringCalculator();

    int result = calc.add("5,10");

    assertThat(result).isEqualTo(15);
  }

  @ParameterizedTest
  @CsvSource(value = {"5,10,5;20", "4,3;7", "8,2;10", "4,5;9"}, delimiter = ';')
  public void givenListaNumeros_whenAdd_thenRetornoSuma(String input, int output) {
    StringCalculator calc = new StringCalculator();

    int result = calc.add(input);

    assertThat(result).isEqualTo(output);
  }

  @Test
  public void givenDosNumerosConSaltoDeLinea_whenAdd_thenRetornoSuma() {
    StringCalculator calc = new StringCalculator();

    int result = calc.add("5\n10");

    assertThat(result).isEqualTo(15);
  }

  @Test
  public void givenTresNumerosConSaltoDeLinea_whenAdd_thenRetornoSuma() {
    StringCalculator calc = new StringCalculator();

    int result = calc.add("1\n2,3");

    assertThat(result).isEqualTo(6);
  }
}
