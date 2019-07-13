package exampleCaseClass

object MainCase1_2ClassTypeAlias2 {

  type UserId = Long
  type ClientId = Long
  case class User(userId: UserId, name: String)
  case class Client(clientId: ClientId, name: String)

  def main(args: Array[String]): Unit = {
    println("Case class!")

    val userId: UserId = 1L
    val user = findClient(userId)
    println("should not work")
    println(user)
  }

  def findUser(userId: UserId) : Option[User] = {
    if (userId == 1L) {
    val user = User(1L, "user")
    Some(user)
    }
    else {
      None
    }
  }

  def findClient(clientId: ClientId): Option[Client] = {
    if (clientId == 1L) {
      val client = Client(1L, "client")
      Some(client)
    } else {
      None
    }
  }

}
