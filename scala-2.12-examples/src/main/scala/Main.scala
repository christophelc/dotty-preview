case class LinkUserClient(userId: Long, clientId: Long)  

case class UserId(id: Long) extends AnyVal  
case class ClientId(id: Long) extends AnyVal  
case class LinkUserClient2(userId: UserId, clientId: ClientId)  

object Main {


  def main(args: Array[String]): Unit = {
    val link = LinkUserClient(1L, 10L)
    val link2 = LinkUserClient2(UserId(1L), ClientId(10L))
    println(msg)
  }

  def msg = "I was compiled by scala 2.12 :)"

}
