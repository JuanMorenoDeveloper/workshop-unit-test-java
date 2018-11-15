# Workshop pruebas unitarias con Java

[![Build Status](https://travis-ci.org/earth001/workshop-unit-test-java.svg?branch=solutions)](https://travis-ci.org/earth001/workshop-unit-test-java)

## Convenciones

### Nombrado de clases

* Los test siempre deben tener el sufijo: ...UnitTest.java, ...IntegrationTest.java, ...LiveTest.java
* Los test unitarios deben ejecutarse en el build standard; los de integración o live no.

### Nombrado de métodos

Existen muchas convenciones para nombrar los métodos test, escoja una, aprenda de ella y sea consistente. 

Para el siguiente actividad se recomienda la convención de BDD:

* `givenX_whenY_thenZ`

* La parte `given` es opcional, pero no las otras dos, por ejemplo: 
`whenSendingAPost_thenCorrectStatusCode`

* El delimitador (underscore) debe ser usado solo entre secciones , y no en otras partes, por ejemplo - este no es correcto:  
`whenSomethingHappens_andSomethingElse_thenSuccessfull`

### Estructura de test

* Siempre use una nueva linea entre secciones de los test
- Por ejemplo (note el salto de línea): 

```
public void whenSomething_thenSomethingElse {
    // some preparation code belonging to the when section

    assert( ...)
}
```

## Ejercicio 1 - String Calculator

El siguiente ejercicio de desarrollo es una Kata de TDD.

### Antes de empezar: 

* Trate leer y hacer un paso a la vez.
* Hacer una tarea a la vez. El truco esta en aprender a trabajar de manera incremental.
* Escribe test solo para entradas válidas, no es necesario incluir test para entradas inválidas en esta kata.  

### String Calculator (TDD)

1. El objetivo es crear un simple calculadora de Strings con un método `int add(String numbers)`.

    1. El método puede tomar 0, 1 o 2 números, y debería retornar su suma (para una string vacía debe retornar 0) por ejemplo "" o "1" o "1,2".

    2. Comience con el test más simple de un string vacío y luego avance al de uno o dos números. 

    3. Recuerde resolver las cosas de la manera más sencilla posible, de modo que se obligue a escribir pruebas en las que no haya pensado.

    4. Recuerde hacer refactor luego que pase un test. 

2. Modifique el método `add` para manejar una cantidad desconocida de números.

3. Modifique el método `add` para manejar un salto de línea entre números (además de comas).
    1. La siguiente entrada esta ok:  "1\n2,3"  (debe ser igual a 6)
    2. La siguiente entrada no es correcta:  "1,\n" (no es necesario escribir un test - solo para aclarar)
    
4. Añada soporte para diferentes delimitadores
    1. Para cambiar el delimitador, el comienzo del String debe contener un separador de línea de la siguiente manera: "//[delimiter]\n[numbers…]" por ejemplo “//;\n1;2” debe retornar 3 usando como delimitador el ';'.
    2. La primera línea es opcional. Todos los demás escenarios deben seguir siendo soportados. **Los siguientes puntos tienen mayor dificultad.**

5. Llamar a `add` con un número negativo debe lanzar una exception con el mensaje "negativos no están permitidos” - y el número negativo que fue enviado. Si hay múltiples números negativos se deberán mostrar todos en el mensaje de la excepción. 
6. Números mayores a 1000 deben ser ignorados, sumando 2 + 1001  = 2
7. Delimitadores pueden ser de cualquier tamaño con el siguiente formato:  "//[delimiter]\n" por ejemplo: "//[***]\n1\*\*\*2\*\*\*3" debe retornar 6
8. Permitir múltiples delimitadores de la siguiente manera:  "//[delim1][delim2]\n" por ejemplo "//[*][%]\n1*2%3" debe retornar 6.
9. Se debe asegurar que se pueden manejar diferentes delimitadores con longitud mayor a un carácter.

## Ejercicio 2 - FizzBuzz

## FizzBuzz (Mockito)

El objetivo de esta kata es escribir un programa que imprima los números del 1 al 100. Pero para los múltiplos de tres, imprima "Fizz" en lugar del número y para los múltiplos de cinco imprima "Buzz". Para los números que son múltiplos de tres y cinco, imprima "FizzBuzz".

Use interfaces para la impresión de los números. Use mockito para manejar hacer un mock de la dependencia real.
