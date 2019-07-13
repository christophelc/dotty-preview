package example2Implicit

object MainClass4_2LambdaType {

  def main(args: Array[String]): Unit = {

    assert(f(1)(2) == 3)

    implicit val i = 3
    assert(f(1) == 4)
  }

  def f(a: Int)(implicit b: Int) = a + b

}
