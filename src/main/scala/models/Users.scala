package models

import slick.jdbc.H2Profile.api._
import java.sql.Timestamp

case class User(
  id: Option[Int] = None,
  user_id: String,
  properties: String,
  timestamp: Timestamp
)

class Users(tag: Tag) extends Table[User](tag, "users") {
  def id         = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def user_id    = column[String]("name")
  def properties = column[String]("properties")
  def timestamp  = column[Timestamp]("timestamp")

  def * = (id.?, user_id, properties, timestamp).shaped <> (User.tupled, User.unapply)
}

object Users {
  def create(user: User) =
    users returning users.map(_.id) into ((user, id) => user.copy(id = Some(id))) += user
}
