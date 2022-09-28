package models

import slick.jdbc.H2Profile.api._
import java.sql.Timestamp

case class Event(
                  id: Option[Int],
                  name: String,
                  properties: String,
                  timestamp: Timestamp,
                  user_id: String
                ) {
  override def toString: String = s"Event(id=$id, name=$name, properties=$properties, timestamp=$timestamp, user_id=$user_id)"
}

class Events(tag: Tag) extends Table[Event](tag, "events") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def properties = column[String]("properties")
  def timestamp = column[Timestamp]("timestamp")
  def user_id = column[String]("user_id")

  def * = (id.?, name, properties, timestamp, user_id).shaped <> (Event.tupled, Event.unapply)
}

object Events {
  def create(event: Event) =
    events returning events.map(_.id) into ((event, id) => event.copy(id=Some(id))) += event
}