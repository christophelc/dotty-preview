package example2Implicit

object MainClass3_1Enumeration {

  // scala 2.x
  object WeekDay extends Enumeration {
    type WeekDay = Value
    val Mon, Tue, Wed, Thu, Fri, Sat, Sun = Value
  }
  // Enumerations have the same type after erasure
  // no exhaustive matching check during compile
  // => selead case class
  // => no simple way to retrieve all the enumeration values
  //    no default serialize/deserialize methods
  //    no default ordering between enumeration values
  // => itemized
  // => enumeratum

  // dotty 


  def main(args: Array[String]): Unit = {

    enum Color {
      case Red, Green, Blue
    }


    enum Color2(val rgb: Int) {
      case Red   extends Color2(0xFF0000)
      case Green extends Color2(0x00FF00)
      case Blue  extends Color2(0x0000FF)
  }
  val red = Color.Red
  assert(red == Color.enumValue(1))
  assert(Color.Green != Color.enumValue(1))
  // ne compile pas
  //assert(Color.Red != Color2.Red)
  }

}
