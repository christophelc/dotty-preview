package exampleCaseClass

object MainCase1_3ClassAnyVal3 {


  case class UserId(id: Long) extends AnyVal
  case class ClientId(id: Long) extends AnyVal
  case class User(id: UserId, name: String) 
  case class Client(id: ClientId, name: String) 

  def main(args: Array[String]): Unit = {
    println("Case class!")

    val userId = UserId(1L)
    // not compile
    //val user = findClient(userId)
    val user = findUser(userId)
    println(user)
  }

  def findUser(userId: UserId) : Option[User] = {
    if (userId == UserId(1)) {
    val user = User(userId, "user")
    Some(user)
    }
    else {
      None
    }
  }

  def findClient(clientId: ClientId): Option[Client] = {
    if (clientId == ClientId(1)) {
      val client = Client(clientId, "client")
      Some(client)
    } else {
      None
    }
  }

}
