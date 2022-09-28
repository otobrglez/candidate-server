import DB.{db, prepareDb}
import io.finch._
import cats.effect.IO
import io.finch.circe._
import io.circe.generic.auto._
import io.circe._
import io.circe.generic.semiauto._
import com.twitter.finagle.Http
import com.twitter.util.Await

import scala.concurrent.ExecutionContext.Implicits.global
import models.{Event, Events, User, Users}

import java.sql.Timestamp
import java.time.Instant
import scala.util.Try

object Main extends App with Endpoint.Module[IO] {

  implicit val tsDecoder: Decoder[Timestamp] = Decoder.decodeInt.emapTry {
    ts => Try(Timestamp.from(Instant.ofEpochSecond(ts)))
  }

 val postEvent = post("event" :: jsonBody[Event]) { event: Event =>
   val eFuture = db.run(Events.create(event))

   eFuture.map(e => Ok(s"added new event with id ${e.id.get}"))
 }

 val postUser = post("user" :: jsonBody[User]) { user: User =>
   val uFuture = db.run(Users.create(user))

   uFuture.map(u => Ok(s"added new event with id ${u.id.get}"))
 }

  prepareDb

  Await.ready(
    Http.server.serve(":8000", (postEvent :+: postUser).toServiceAs[Text.Plain])
  )
}