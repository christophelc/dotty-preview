package example2Implicit

object Main3_2_ClassTuple {

  def main(args: Array[String]): Unit = {
 
     val xs: List[(Int, Int)] = List((1,1),(2,2))


     // scala 2
     val r1 = xs.map {
       case (x,y) => x + y
     }

     // dotty
     val r2 = xs.map {
       (x,y) => x + y
     }

     val r3 = xs.map(_ + _)
     println(Seq(r1, r2, r3).mkString("\t"))
  }

}
