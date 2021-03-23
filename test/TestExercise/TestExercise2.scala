package TestExercise

import u04lab.code._
import u04lab.code.Lists.List._
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions._

class TestExercise2 {

  val s1 = Student("Mario", 2020)
  s1.enrolling(Course("Analisi", "Luigi Rossi"))
  s1.enrolling(Course("Fisica", "Giorgia Bianchi"))
  s1.enrolling(Course("Programmazione", "Bob Rossi"))

  val s2 = Student("Piero", 2020)
  s2.enrolling(Course("PPS","Viroli"))
  s2.enrolling(Course("PCD","Ricci"))
  s2.enrolling(Course("SDR","D'Angelo"))

  @Test def testCourses(): Unit = {
    assertEquals(Cons("Programmazione",Cons("Fisica",Cons("Analisi",Nil()))), s1.courses)
    assertEquals(Cons("SDR",Cons("PCD",Cons("PPS",Nil()))), s2.courses)
  }

  @Test def testHasTeacher(): Unit = {
    assertTrue(s1.hasTeacher("Luigi Rossi"))
    assertTrue(s1.hasTeacher("Bob Rossi"))
    assertFalse(s1.hasTeacher("NO TEACHER"))

    assertTrue(s2.hasTeacher("Ricci"))
    assertTrue(s2.hasTeacher("D'Angelo"))
    assertFalse(s2.hasTeacher("NO TEACHER"))
  }

  @Test def testEnrollingWithVariableArguments(): Unit = {
    val s = Student("Max")
    s.enrolling(Course("PPS","Viroli"), Course("PCD","Ricci"), Course("Fisica", "Giorgia Bianchi"), Course("Analisi", "Luigi Rossi"))
    assertEquals(Cons("Analisi",Cons("Fisica",Cons("PCD",Cons("PPS",Nil())))), s.courses)
  }
}
