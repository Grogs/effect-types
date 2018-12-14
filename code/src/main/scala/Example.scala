import monix.eval.Task

import scala.concurrent.Future
import scala.concurrent.duration._

trait Example extends App {

  def await[T](f: Future[T]) = scala.concurrent.Await.result(f, Duration.Inf)

  def upload(doc: String): Future[Unit] =
    Task(println(s"Uploaded $doc")).delayExecution(350.millis).runToFuture(monix.execution.Scheduler.global)

}
