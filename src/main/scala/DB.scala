import slick.jdbc.H2Profile.api._

import scala.concurrent.ExecutionContext.Implicits.global
import models.{events, users}
import slick.jdbc.H2Profile
import H2Profile.backend.DatabaseDef

import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration

object DB {
  val db: DatabaseDef = Database.forURL("jdbc:h2:file:./data:test;DB_CLOSE_DELAY=-1")

  def prepareDb: Future[Boolean] = {
    val createSchema = for {
      _ <- users.schema.create
      _ <- events.schema.create
    } yield true

    Await.ready(db.run(createSchema), Duration.Inf)
  }
}
