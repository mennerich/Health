package protocols

object Protocol {

  case class User(id: Int, email: String, hash: String, salt: String)

}
