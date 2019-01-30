import monix.eval.Task
import monix.execution.Scheduler
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.Await
import scala.concurrent.duration.Duration.Inf
import scala.concurrent.duration._


object MonixParallelismExample extends App {

  def upload(doc: String): Task[Unit] = {
    Task(println(s"Uploaded $doc")).delayExecution(500.millis)
  }

  Task.sequence(
    Seq(
      upload("a"),
      upload("b"),
      upload("c"),
      upload("d"),
      upload("e"),
    )
  ).runSyncUnsafe()
}