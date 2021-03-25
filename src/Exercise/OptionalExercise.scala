package Exercise

import Exercise.Exercise2.CourseImpl
import u04lab.code.Course
import u04lab.code.Lists._
import u04lab.code.Lists.List._

import scala.annotation.tailrec

object OptionalExercise {

  def factoryList[A](args: A*): List[A] = {
    var list: List[A] = Nil()
    for (elem <- args)
      list = List.append(list, Cons(elem, Nil()))
    list
  }

  trait sameTeacher {
    def courses: List[Course]
  }

  object sameTeacher {
    def unapply(courses: List[Course]): Option[String] = courses match {
      case Cons(CourseImpl(_, teacher), tail) => sameElement(tail)(teacher)
      case Nil() => Option.empty
    }
  }

  @tailrec
  def sameElement(l: List[Course])(elem: String): Option[String] = l match {
    case Cons(CourseImpl(_, teacher), t) => if (t != Nil()) {
      if (teacher==elem) sameElement(t)(elem) else Option.empty
    } else Option(teacher)
    case Nil() => Option.empty
  }
}
