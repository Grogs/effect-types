import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

object Q3 extends App {

  def mySlowRequest(input: Int): Future[String] = Future {
    Thread.sleep(500)
    input.toString
  }

  for {
    a <- mySlowRequest(1)
    b <- mySlowRequest(2)
  } yield a + b

  //Q3 what does the above for-comprehension desugar into?

}
