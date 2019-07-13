package example2Implicit

object MainClass2_2ImplicitRefactoringDotty {

  def f(i: Int)(implicit f: Int => Int) = i + f(i)


  def main(args: Array[String]): Unit = {

    def id(x: Int) = x
    
    assert(f(1)(id) == 2)

    implicit val g = id
    assert(f(1) == 2)

    // ok en dotty
    val fn: implicit Int => Unit =  ()
    type FImpl = implicit Int => Int

    // devient une déclaration standard (sans implciit apparent) => refactoring
    def f2(i: Int)(fimpl: FImpl) = i + fimpl(i)

  }

}
