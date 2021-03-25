package u04lab.code

import Optionals._
import Lists._
import Lists.List._
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions._

class PowerIteratorsTest {

  val factory = new PowerIteratorsFactoryImpl()

  @Test def testIncremental() {
    val pi = factory.incremental(5,_+2) // pi produce 5,7,9,11,13,...
    assertEquals(Option.of(5), pi.next())
    assertEquals(Option.of(7), pi.next())
    assertEquals(Option.of(9), pi.next())
    assertEquals(Option.of(11), pi.next())
    assertEquals(List.Cons(5, List.Cons(7, List.Cons(9, List.Cons(11,List.Nil())))), pi.allSoFar()); // elementi già prodotti
    for (_ <- 0 until 10) {
      pi.next(); // procedo in avanti per un po'..
    }
    assertEquals(Option.of(33), pi.next()) // sono arrivato a 33
  }

  @Test def testAllSoFarWithIncremental(): Unit = {
    val pi = factory.incremental(0,_+1)
    for (_ <- 0 until 5) { //0,1,2,3,4
      pi.next()
    }
    assertEquals(Cons(0,Cons(1,Cons(2,Cons(3,Cons(4,Nil()))))), pi.allSoFar())
    for (_ <- 0 until 3) { //0,1,2,3,4 + 5,6,7
      pi.next()
    }
    assertEquals(Cons(0,Cons(1,Cons(2,Cons(3,Cons(4,Cons(5,Cons(6,Cons(7,Nil())))))))), pi.allSoFar())
  }

  @Test def testReverseWithIncremental(): Unit = {
    val pi = factory.incremental(0,_+1)
    for (_ <- 0 until 4) { //0,1,2,3
      pi.next()
    }
    val r = pi.reversed() //produce 3,2,1
    for (_ <- 0 until 3) { //3,2,1
      r.next()
    }
    assertEquals(Cons(3,Cons(2,Cons(1,Nil()))), r.allSoFar())
    val r2 = r.reversed() //produce 1,2,3
    assertEquals(Option.Some(1), r2.next())
  }

  @Test def testFromList(): Unit = {
    val list = Cons("a", Cons("b", Cons("c", Nil())))
    val pi = factory.fromList(list) //produce a,b,c
    assertEquals(Option.of("a"), pi.next())
    assertEquals(Option.of("b"), pi.next())
    assertEquals(Cons("a",Cons("b",Nil())), pi.allSoFar())
    val p2 = pi.reversed() //produce b,a
    assertEquals(Option.of("b"), p2.next())
    pi.next() //ultimo elemento

    //ogni next() successiva a quella che restituisce l'ultimo elemento restituirà un Option.empty
    assertEquals(Option.empty, pi.next())
    assertEquals(Option.empty, pi.next())
  }

  @Test def testRandomBooleans(): Unit = {
    val pi1 = factory.randomBooleans(5)
    val pi2 = factory.randomBooleans(5)
    for (_ <- 0 until 5) {
      pi1.next()
      pi2.next()
    }
    println(pi1.allSoFar())
    print(pi2.allSoFar()) //controllare a schermo che le due stampe siano random
    assertEquals(Option.empty, pi1.next())
    assertEquals(Option.empty, pi2.next())
  }
}