import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.Await
import scala.concurrent.duration.Duration.Inf
import scala.concurrent.duration._


object MonixTaskExample extends App {

  def upload(doc: String): Task[Unit] = {
    Task(println(s"Uploaded $doc")).delayExecution(500.millis)
  }

  Await.result({

    for {
      a <- upload("a")
      b <- upload("b")
    } yield ()

  }.runToFuture, Inf)

}