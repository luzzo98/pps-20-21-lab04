package u04lab.code

import Optionals._
import Lists._
import Lists.List._
import u04lab.code.Streams.Stream
import u04lab.code.Streams.Stream.Empty

import scala.util.Random

trait PowerIterator[A] {
  def next(): Option[A]
  def allSoFar(): List[A]
  def reversed(): PowerIterator[A]
}

trait PowerIteratorsFactory {

  def incremental(start: Int, successive: Int => Int): PowerIterator[Int]
  def fromList[A](list: List[A]): PowerIterator[A]
  def randomBooleans(size: Int): PowerIterator[Boolean]
}

class PowerIteratorsFactoryImpl extends PowerIteratorsFactory {

  def listToStream[A](list: List[A]): Stream[A] = list match {
    case Cons(head, tail) => Stream.Cons(() => head, () => listToStream(tail))
    case Nil() => Empty()
  }

  private def fromStream[X](s: Stream[X]): PowerIterator[X] = {
    new PowerIterator[X] {

      private var actualStream: Stream[X] = s
      private var pastList: List[X] = Nil[X]()

      override def next(): Option[X] = actualStream match {
        case Stream.Cons(h,t) => actualStream=t(); pastList=List.append(pastList,Cons(h(), Nil())); Option.Some(h())
        case _ => Option.empty
      }

      override def allSoFar(): List[X] = pastList

      override def reversed(): PowerIterator[X] = fromStream(listToStream(List.reverse(pastList)))
    }
  }

  override def incremental(start: Int, successive: Int => Int): PowerIterator[Int] =
    fromStream(Stream.iterate(start)(successive))

  override def fromList[A](list: List[A]): PowerIterator[A] = fromStream(listToStream(list))

  override def randomBooleans(size: Int): PowerIterator[Boolean] =
    fromStream(Stream.take(Stream.iterate(Random.nextBoolean())(_ => Random.nextBoolean()))(size))
}
