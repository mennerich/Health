package models

import javax.inject.Inject
import org.apache.commons.codec.digest.DigestUtils
import play.api.db.slick.DatabaseConfigProvider
import protocols.Protocol._
import slick.jdbc.JdbcProfile

import scala.concurrent.Future
import scala.util.Random

class UserRepo @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]
  val db = dbConfig.db
  import dbConfig.profile.api._

  def create(email: String, password: String): Future[Int] = {
    val salt = Random.alphanumeric.take(10).mkString
    val hash = DigestUtils.md5Hex(password + salt)
    val user = User(0, email, hash, salt)
    db.run(_create(user))
  }

  private def _create(user: User): DBIO[Int] = Users returning Users.map(_.id) += user

  def findById(id: Int): Future[Option[User]] = db.run(_findById(id))

  private def _findById(id: Int): DBIO[Option[User]] = Users.filter(_.id === id).result.headOption


  //============================= Relation Definition ========================//
  private[models] val Users = TableQuery[UsersTable]

  private[models] class UsersTable(tag: Tag) extends Table[User](tag, "user") {

    def id = column[Int]("ID", O.AutoInc, O.PrimaryKey)
    def email = column[String]("EMAIL")
    def hash = column[String]("HASH")
    def salt = column[String]("SALT")
    def * = (id, email, hash, salt) <> (User.tupled, User.unapply)

  }
}
