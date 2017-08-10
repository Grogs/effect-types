import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

object Q1 extends App {

  def mySlowRequest(input: Int): Future[String] = Future {
    Thread.sleep(500)
    input.toString
  }

  val someInputs: List[Int] = (1 to 10).toList

  //Q1 implement getResponses
  def getResponses(input: List[Int]): Future[List[String]] = {
    ???
  }

  println(Await.result(getResponses(someInputs), 4.seconds))
}
