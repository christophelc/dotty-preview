package exampleCaseClass

object MainCase1_1Class1 {

  case class User(id: Long, name: String)
  case class Client(id: Long, name: String)

  def main(args: Array[String]): Unit = {
    println("Case class!")

    val userId = 1L
    val user = findClient(userId)
    println("should not work")
    println(user)
  }

  def findUser(userId: Long) : Option[User] = {
    if (userId == 1L) {
    val user = User(1L, "user")
    Some(user)
    }
    else {
      None
    }
  }

  def findClient(clientId: Long): Option[Client] = {
    if (clientId == 1L) {
      val client = Client(1L, "client")
      Some(client)
    } else {
      None
    }
  }

}
