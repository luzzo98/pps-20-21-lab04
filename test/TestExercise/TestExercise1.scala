package TestExercise

import u04lab.code.Complex
import Exercise.Exercise1._
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions._

class TestExercise1 {

  @Test def testComplexImpl(): Unit = {
    val n1 = Complex(2, 3)
    val n2 = Complex(3, 4)
    println(n1)
    assertTrue(n1==ComplexImpl(2,3))
    assertEquals(Complex(5,7), n1+n2)
    assertEquals(Complex(6,12), n1*n2)
  }

}
