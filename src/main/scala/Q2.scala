import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

object Q2 extends App {

  def mySlowRequest(input: Int): Future[String] = Future {
    Thread.sleep(500)
    input.toString
  }

  //Q2 what's the difference between these two for-comprehensions?
  val eventualA = mySlowRequest(1)
  val eventualB = mySlowRequest(2)
  for {
    a <- eventualA
    b <- eventualB
  } yield a + b

  for {
    a <- mySlowRequest(1)
    b <- mySlowRequest(2)
  } yield a + b

}
