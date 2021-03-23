package Exercise

import u04lab.code._
import u04lab.code.Lists.List
import u04lab.code.Lists.List._

object Exercise2 {

  case class CourseImpl(override val name: String,
                        override val teacher: String) extends Course


  case class StudentImpl(override val name: String,
                         override val year: Int) extends Student {

    private var list: List[Course] = Nil[Course]()

    override def enrolling(courses: Course*): Unit = {
      for (course <- courses)
        list = List.append(Cons(course, Nil()), list)
    }

    override def courses: List[String] = map(list) { case CourseImpl(name,_) => name }

    override def hasTeacher(teacher: String): Boolean = contains(map(list){ case CourseImpl(_, t) => t })(teacher)
  }


  //implementation of the function "contains()" used in "hasTeacher()"

//  @tailrec
//  def contains[A](l: List[A])(elem: A): Boolean = l match {
//    case Cons(h, t) => if (h==elem) true else contains(t)(elem)
//    case Nil() => false
//  }
}
