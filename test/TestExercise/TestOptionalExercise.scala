package TestExercise

import Exercise.OptionalExercise._
import u04lab.code.Lists.List._
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions._
import u04lab.code.Course

class TestOptionalExercise {

  @Test def testFactoryListWithString(): Unit = {
    val list = factoryList("a","b","c","d","e")
    assertEquals(Cons("a", Cons("b", Cons("c", Cons("d", Cons("e", Nil()))))), list)
  }

  @Test def testFactoryListWithInt(): Unit = {
    val list = factoryList(1,2,3,4,5)
    assertEquals(Cons(1, Cons(2, Cons(3, Cons(4, Cons(5, Nil()))))), list)
  }

  @Test def testFactoryListWithNoElem(): Unit = {
    val list = factoryList()
    assertEquals(Nil(), list)
  }

  @Test def testWithSameTeacher(): Unit = {
    val courses = factoryList(Course("Fisica", "Mario"), Course("Analisi", "Mario"), Course("PPS", "Mario"))
    courses match {
      case sameTeacher(t) => println(s"$courses have same teacher $t")
      case _ => println(s"$courses have different teacher")
    }
  }

  @Test def testWithNotSameTeacher(): Unit = {
    val courses = factoryList(Course("Fisica", "Mario"), Course("Analisi", "Giovanni"), Course("PPS", "Mario"))
    courses match {
      case sameTeacher(t) => println(s"$courses have same teacher $t")
      case _ => println(s"$courses have different teacher")
    }
  }
}
