import slick.jdbc.H2Profile.api._

import scala.concurrent.ExecutionContext.Implicits.global
import models.{events, users}

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object DB {
  val db = Database.forURL("jdbc:h2:file:./data:test;DB_CLOSE_DELAY=-1")

  def prepareDb = {
    val createSchema = for {
      _ <- users.schema.create;
      _ <- events.schema.create
    } yield true

    Await.ready(db.run(createSchema), Duration.Inf)
  }
}
