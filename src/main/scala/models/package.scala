import slick.jdbc.H2Profile.api._

package object models {
  val events = TableQuery[Events]
  val users  = TableQuery[Users]
}
