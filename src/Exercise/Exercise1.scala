package Exercise

import u04lab.code.Complex

object Exercise1 {

  case class ComplexImpl(override val re: Double,
                    override val im: Double) extends Complex {

    override def +(c: Complex): Complex = Complex(re+c.re, im+c.im)

    override def *(c: Complex): Complex = Complex(re*c.re, im*c.im)
  }
}
