import scala.concurrent.Future

object Q4 extends App {

  import scala.concurrent.ExecutionContext.Implicits.global

  def mySlowRequest(input: Int): Future[String] = Future {
    scala.io.Source.fromFile(s"$input.json").mkString
  }

  //Q4 what's bad about `mySlowRequest` and how could we improve it?

}
