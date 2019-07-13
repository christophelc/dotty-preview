package exampleCaseClass

object MainCase1_4ClassDottyOpaqueType4 {

  opaque type UserId = Long
  object UserId {
    def apply(id: Long): UserId = id
  }
  opaque type ClientId = Long
  object ClientId {
    def apply(id: Long): ClientId = id
  }
  case class User(userId: Long, name: String)
  case class Client(ClientId: Long, name: String)

  def main(args: Array[String]): Unit = {
    println("Case class!")

    val userId: UserId = UserId(1L)
    // not compile
    //val user = findClient(userId)
    val user = findUser(userId)
    println(user)
  }

  def findUser(userId: UserId) : Option[User] = {
    if (userId == UserId(1L)) {
    val user = User(1L, "user")
    Some(user)
    }
    else {
      None
    }
  }

  def findClient(clientId: ClientId): Option[Client] = {
    if (clientId == ClientId(1L)) {
      val client = Client(1L, "client")
      Some(client)
    } else {
      None
    }
  }

}
