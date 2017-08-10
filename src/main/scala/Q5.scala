import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

object Q5 extends App {

  def mySlowRequest(input: Int): Future[String] = Future {
    Thread.sleep(500)
    println(s"Running $input")
    input.toString
  }

  val someInputs: List[Int] = (1 to 10).toList

  def getResponses(input: List[Int]): Future[List[String]] = {
      Future.traverse(input)(mySlowRequest)
  }

  println(Await.result(getResponses(someInputs), 6.seconds))

  //Q5 a new requirement comes in, we need to throttle the number of requests running in parallel. how do we do this?
}
