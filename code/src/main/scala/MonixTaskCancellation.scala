import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.duration._

object MonixTaskCancellation extends Example {

  //given def upload(doc: String): Future[Unit]
  def uploadMonix(doc: String) = Task.deferFuture(upload(doc))

  val uploadGreg = for {
    _ <- uploadMonix("g")
    _ <- uploadMonix("r")
    _ <- uploadMonix("e")
    _ <- uploadMonix("g")
  } yield ()

  await(uploadGreg.runToFuture)

}
